package br.edu.fag.atividadescomplementares.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {

    private static final int CARGA_HORARIA_MAXIMA = 40;

    private final JdbcTemplate jdbcTemplate;

    public InscricaoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(@RequestBody Map<String, Object> dados) {
        String nomeAluno = texto(dados.get("nomeAluno"));
        String matricula = texto(dados.get("matricula"));
        String nomeAtividade = texto(dados.get("nomeAtividade"));
        String modalidade = texto(dados.get("modalidade"));
        Integer cargaHoraria = numeroInteiro(dados.get("cargaHoraria"));
        String linkAcesso = texto(dados.get("linkAcesso"));

        // Validações dos dados da requisição.
        if (nomeAluno == null || nomeAluno.isBlank()) {
            return ResponseEntity.badRequest().body(erro("O nome do aluno é obrigatório."));
        }

        if (matricula == null || matricula.isBlank()) {
            return ResponseEntity.badRequest().body(erro("A matrícula do aluno é obrigatória."));
        }

        if (nomeAtividade == null || nomeAtividade.isBlank()) {
            return ResponseEntity.badRequest().body(erro("O nome da atividade é obrigatório."));
        }

        if (cargaHoraria == null || cargaHoraria <= 0) {
            return ResponseEntity.badRequest().body(
                    erro("A carga horária deve ser um número inteiro maior que zero."));
        }

        if (modalidade == null
                || (!modalidade.equalsIgnoreCase("PRESENCIAL")
                && !modalidade.equalsIgnoreCase("ONLINE"))) {
            return ResponseEntity.badRequest().body(
                    erro("A modalidade deve ser PRESENCIAL ou ONLINE."));
        }

        nomeAluno = nomeAluno.trim();
        matricula = matricula.trim();
        nomeAtividade = nomeAtividade.trim();
        modalidade = modalidade.trim().toUpperCase();
        linkAcesso = linkAcesso == null ? null : linkAcesso.trim();

        // Regra de negócio 1: uma única atividade pode contabilizar no máximo 40 horas.
        if (cargaHoraria > CARGA_HORARIA_MAXIMA) {
            return ResponseEntity.badRequest().body(
                    erro("Uma atividade complementar pode contabilizar no máximo 40 horas."));
        }

        // Regra de negócio 2: inscrições online precisam informar como acessar a atividade.
        if (modalidade.equals("ONLINE") && (linkAcesso == null || linkAcesso.isBlank())) {
            return ResponseEntity.badRequest().body(
                    erro("Atividades online devem possuir um link de acesso."));
        }

        LocalDateTime inscritoEm = LocalDateTime.now();
        KeyHolder chaveGerada = new GeneratedKeyHolder();

        String nomeAlunoParaSalvar = nomeAluno;
        String matriculaParaSalvar = matricula;
        String nomeAtividadeParaSalvar = nomeAtividade;
        String modalidadeParaSalvar = modalidade;
        String linkAcessoParaSalvar = linkAcesso;

        jdbcTemplate.update(conexao -> {
            PreparedStatement comando = conexao.prepareStatement(
                    "INSERT INTO inscricoes "
                            + "(nome_aluno, matricula, nome_atividade, modalidade, carga_horaria, link_acesso, inscrito_em) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, nomeAlunoParaSalvar);
            comando.setString(2, matriculaParaSalvar);
            comando.setString(3, nomeAtividadeParaSalvar);
            comando.setString(4, modalidadeParaSalvar);
            comando.setInt(5, cargaHoraria);
            comando.setString(6, linkAcessoParaSalvar);
            comando.setTimestamp(7, Timestamp.valueOf(inscritoEm));
            return comando;
        }, chaveGerada);

        Number chave = chaveGerada.getKey();

        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("id", chave.longValue());
        resposta.put("nomeAluno", nomeAluno);
        resposta.put("matricula", matricula);
        resposta.put("nomeAtividade", nomeAtividade);
        resposta.put("modalidade", modalidade);
        resposta.put("cargaHoraria", cargaHoraria);
        resposta.put("linkAcesso", linkAcesso);
        resposta.put("inscritoEm", inscritoEm);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    private String texto(Object valor) {
        return valor instanceof String ? (String) valor : null;
    }

    private Integer numeroInteiro(Object valor) {
        if (valor == null) {
            return null;
        }

        try {
            return Integer.parseInt(valor.toString());
        } catch (NumberFormatException excecao) {
            return null;
        }
    }

    private Map<String, Object> erro(String mensagem) {
        return Map.of("erro", mensagem);
    }
}
