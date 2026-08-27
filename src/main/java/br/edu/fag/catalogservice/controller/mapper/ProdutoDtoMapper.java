package br.edu.fag.catalogservice.controller.mapper;

import br.edu.fag.catalogservice.controller.dto.ProdutoRequestDTO;
import br.edu.fag.catalogservice.controller.dto.ProdutoResponseDTO;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;

public class ProdutoDtoMapper {

    public static ProdutoDomain toDomain(ProdutoRequestDTO dto) {

        ProdutoDomain produto = new ProdutoDomain();

        produto.setNome(texto(dto.getNome()));
        produto.setDescricao(texto(dto.getDescricao()));
        produto.setPreco(numeroDecimal(dto.getPreco()));

        return produto;
    }

    public static ProdutoResponseDTO toResponseDto(ProdutoDomain produto) {

        ProdutoResponseDTO dto = new ProdutoResponseDTO();

        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setCriadoEm(produto.getCriadoEm());

        return dto;
    }

    private static String texto(Object valor) {
        return valor instanceof String ? (String) valor : null;
    }

    private static Double numeroDecimal(Object valor) {

        if (valor == null) {
            return null;
        }

        try {
            String numero = valor.toString().trim().replace(',', '.');
            double resultado = Double.parseDouble(numero);

            return Double.isFinite(resultado) ? resultado : null;

        } catch (NumberFormatException excecao) {
            return null;
        }
    }
}