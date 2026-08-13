package br.edu.fag.atividadescomplementares.service.domain;

import org.springframework.http.ResponseEntity;

public class InscricaoDomain {

    private String nomeAluno;

    private String matricula;

    private String nomeAtividade;

    private String modalidade;

    private String horario;

    private Integer cargaHoraria;

    private String linkAcesso;

    public void validarModalidade() {
        if (modalidade == null
                || (!modalidade.equalsIgnoreCase("PRESENCIAL")
                && !modalidade.equalsIgnoreCase("ONLINE"))) {
            throw new RuntimeException("A modalidade deve ser PRESENCIAL ou ONLINE.");
        }
    }

    public void validarCargaHoraria() {
        if (cargaHoraria > 40) {
            throw new RuntimeException("Uma atividade complementar pode contabilizar no máximo 40 horas.");
        }
    }

    public void validarLinkAcessoParaOnline() {
        if (modalidade.equals("ONLINE") && (linkAcesso == null || linkAcesso.isBlank())) {
            throw new RuntimeException("Atividades online devem possuir um link de acesso.");
        }
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getLinkAcesso() {
        return linkAcesso;
    }

    public void setLinkAcesso(String linkAcesso) {
        this.linkAcesso = linkAcesso;
    }
}
