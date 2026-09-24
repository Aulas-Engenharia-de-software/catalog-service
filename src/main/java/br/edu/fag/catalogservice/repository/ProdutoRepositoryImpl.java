package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.domain.Produto;
import br.edu.fag.catalogservice.entity.ProdutoEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Produto salvar(Produto produto) {

        ProdutoEntity entity = new ProdutoEntity(
                null,
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCriadoEm()
        );

        KeyHolder chaveGerada = new GeneratedKeyHolder();

        jdbcTemplate.update(conexao -> {
            PreparedStatement comando = conexao.prepareStatement(
                    "INSERT INTO produtos (nome, descricao, preco, criado_em) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            comando.setString(1, entity.getNome());
            comando.setString(2, entity.getDescricao());
            comando.setDouble(3, entity.getPreco());
            comando.setTimestamp(4, Timestamp.valueOf(entity.getCriadoEm()));

            return comando;
        }, chaveGerada);

        Number chave = chaveGerada.getKey();

        produto.setId(chave.longValue());

        return produto;
    }
}