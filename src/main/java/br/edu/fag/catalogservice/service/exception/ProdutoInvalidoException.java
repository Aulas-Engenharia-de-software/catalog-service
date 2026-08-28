package br.edu.fag.catalogservice.service.exception;


public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
