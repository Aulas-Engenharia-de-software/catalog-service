package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.domain.Produto;
import br.edu.fag.catalogservice.entity.ProdutoEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcProdutoRepository implements ProdutoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long salvar(Produto produto) {
        ProdutoEntity entity = ProdutoEntity.de(produto);
        KeyHolder chave = new GeneratedKeyHolder();

        jdbcTemplate.update(conexao -> {
            PreparedStatement comando = conexao.prepareStatement(
                    "INSERT INTO produtos (nome, descricao, preco, criado_em, ativo) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, entity.nome());
            comando.setString(2, entity.descricao());
            comando.setDouble(3, entity.preco());
            comando.setTimestamp(4, java.sql.Timestamp.valueOf(entity.criadoEm()));
            comando.setBoolean(5, entity.ativo());
            return comando;
        }, chave);

        return chave.getKey().longValue();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        List<Produto> produtos = jdbcTemplate.query(
                "SELECT * FROM produtos WHERE id = ?",
                this::mapear,
                id);
        return produtos.stream().findFirst();
    }

    @Override
    public List<Produto> buscarAtivos() {
        return jdbcTemplate.query(
                "SELECT * FROM produtos WHERE ativo = true ORDER BY id",
                this::mapear);
    }

    @Override
    public Optional<Produto> desativar(Long id) {
        int alterados = jdbcTemplate.update(
                "UPDATE produtos SET ativo = false WHERE id = ?",
                id);

        if (alterados == 0) {
            return Optional.empty();
        }

        return buscarPorId(id);
    }

    private Produto mapear(java.sql.ResultSet resultado, int numeroLinha) throws java.sql.SQLException {
        return new Produto(
                resultado.getLong("id"),
                resultado.getString("nome"),
                resultado.getString("descricao"),
                resultado.getDouble("preco"),
                resultado.getTimestamp("criado_em").toLocalDateTime(),
                resultado.getBoolean("ativo"));
    }
}
