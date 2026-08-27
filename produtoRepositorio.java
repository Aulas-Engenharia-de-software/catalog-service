package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository
public class ProdutoRepositorio {

    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long salvar(Produto produto) {

        KeyHolder chaveGerada = new GeneratedKeyHolder();

        jdbcTemplate.update(conexao -> {

            PreparedStatement comando = conexao.prepareStatement(
                    """
                    INSERT INTO produtos (nome, descricao, preco, criado_em)
                    VALUES (?, ?, ?, ?)
                    """,
                    Statement.RETURN_GENERATED_KEYS
            );

            comando.setString(1, produto.getNome());
            comando.setString(2, produto.getDescricao());
            comando.setBigDecimal(3, produto.getPreco());
            comando.setTimestamp(
                    4,
                    Timestamp.valueOf(produto.getCriadoEm())
            );

            return comando;

        }, chaveGerada);

        return chaveGerada.getKey().longValue();
    }
}
