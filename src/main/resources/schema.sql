-- Exemplo usado em sala para demonstrar a refatoração em camadas.
-- O domínio é a inscrição de alunos em atividades complementares.

CREATE TABLE IF NOT EXISTS inscricoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_aluno VARCHAR(150) NOT NULL,
    matricula VARCHAR(30) NOT NULL,
    nome_atividade VARCHAR(200) NOT NULL,
    modalidade VARCHAR(20) NOT NULL,
    carga_horaria INT NOT NULL,
    link_acesso VARCHAR(500),
    inscrito_em TIMESTAMP NOT NULL
);
