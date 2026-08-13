package br.edu.fag.atividadescomplementares;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InscricaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void limparBanco() {
        jdbcTemplate.update("DELETE FROM inscricoes");
    }

    @Test
    void deveCriarUmaInscricaoValida() throws Exception {
        mockMvc.perform(post("/api/inscricoes")
                        .contentType("application/json")
                        .content("""
                                {
                                  "nomeAluno": "Ana Silva",
                                  "matricula": "20261234",
                                  "nomeAtividade": "Workshop de Git",
                                  "modalidade": "online",
                                  "cargaHoraria": 8,
                                  "linkAcesso": "https://eventos.exemplo.edu/git"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nomeAluno").value("Ana Silva"))
                .andExpect(jsonPath("$.matricula").value("20261234"))
                .andExpect(jsonPath("$.nomeAtividade").value("Workshop de Git"))
                .andExpect(jsonPath("$.modalidade").value("ONLINE"))
                .andExpect(jsonPath("$.cargaHoraria").value(8));

        Integer quantidade = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM inscricoes WHERE matricula = ? AND nome_atividade = ?",
                Integer.class,
                "20261234",
                "Workshop de Git");

        assertThat(quantidade).isEqualTo(1);
    }

    @Test
    void naoDeveCriarInscricaoSemMatricula() throws Exception {
        mockMvc.perform(post("/api/inscricoes")
                        .contentType("application/json")
                        .content("""
                                {
                                  "nomeAluno": "Ana Silva",
                                  "nomeAtividade": "Palestra de carreira",
                                  "modalidade": "PRESENCIAL",
                                  "cargaHoraria": 4
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("A matrícula do aluno é obrigatória."));

        assertThat(totalDeInscricoes()).isZero();
    }

    @Test
    void naoDeveCriarInscricaoComCargaHorariaInvalida() throws Exception {
        mockMvc.perform(post("/api/inscricoes")
                        .contentType("application/json")
                        .content("""
                                {
                                  "nomeAluno": "Ana Silva",
                                  "matricula": "20261234",
                                  "nomeAtividade": "Palestra de carreira",
                                  "modalidade": "PRESENCIAL",
                                  "cargaHoraria": 0
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro")
                        .value("A carga horária deve ser um número inteiro maior que zero."));

        assertThat(totalDeInscricoes()).isZero();
    }

    @Test
    void naoDeveCriarInscricaoComMaisDeQuarentaHoras() throws Exception {
        mockMvc.perform(post("/api/inscricoes")
                        .contentType("application/json")
                        .content("""
                                {
                                  "nomeAluno": "Ana Silva",
                                  "matricula": "20261234",
                                  "nomeAtividade": "Curso de extensão",
                                  "modalidade": "PRESENCIAL",
                                  "cargaHoraria": 60
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro")
                        .value("Uma atividade complementar pode contabilizar no máximo 40 horas."));

        assertThat(totalDeInscricoes()).isZero();
    }

    @Test
    void naoDeveCriarInscricaoOnlineSemLinkDeAcesso() throws Exception {
        mockMvc.perform(post("/api/inscricoes")
                        .contentType("application/json")
                        .content("""
                                {
                                  "nomeAluno": "Ana Silva",
                                  "matricula": "20261234",
                                  "nomeAtividade": "Workshop de Git",
                                  "modalidade": "ONLINE",
                                  "cargaHoraria": 8
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro")
                        .value("Atividades online devem possuir um link de acesso."));

        assertThat(totalDeInscricoes()).isZero();
    }

    private int totalDeInscricoes() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM inscricoes", Integer.class);
    }
}
