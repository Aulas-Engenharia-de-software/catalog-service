-- Nesta primeira atividade existe apenas o contexto de criação de produtos.
-- As próximas atividades poderão evoluir o modelo e adicionar novos contextos.

CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(500),
    preco DOUBLE NOT NULL,
    criado_em TIMESTAMP NOT NULL,
    ativo BOOLEAN NOT NULL
);
