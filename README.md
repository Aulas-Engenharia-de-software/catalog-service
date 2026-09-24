# Catalog Service

Projeto da disciplina de Arquitetura de Software para cadastrar produtos usando
uma arquitetura em camadas.

O fluxo passa pelo controller, service, domínio e repository. O controller
cuida apenas da entrada HTTP, o service coordena a criação, o domínio valida
as regras e o repository salva o produto no banco.

## Cenário

Uma API de catálogo precisa permitir o cadastro de um produto com nome,
descrição e preço. Por enquanto, não fazem parte do exercício estoque,
categorias, descontos, edição ou consulta de produtos.

### Validações básicas

- `nome` é obrigatório;
- `descricao` é opcional;
- `preco` é obrigatório e deve ser maior que zero;
- o preço é armazenado com duas casas decimais.

### Regras de negócio

- o nome do produto deve ter pelo menos 3 caracteres;
- produtos com preço a partir de R$ 1.000,00 precisam possuir uma descrição.

As regras são executadas antes de salvar o produto no banco.

## Executando o projeto

Pré-requisitos: Java 21 e Maven 3.9 ou superior.

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`. <br> 
O projeto usa um banco H2   em memória, recriado sempre que a aplicação inicia.  
O console pode ser acessado em `http://localhost:8080/h2-console`, usando:

- JDBC URL: `jdbc:h2:mem:catalog_service`
- usuário: `sa`
- senha: deixe em branco

## Criando um produto

`POST /api/produtos`

```bash
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Teclado mecânico",
    "descricao": "Teclado ABNT2 com iluminação",
    "preco": 349.90
  }'
```

Resposta esperada: status `201 Created` e o produto com seu `id` gerado.
Dados inválidos retornam status `400 Bad Request` e uma mensagem no campo
`erro`.

## Testes

Para executar os testes:

```bash
mvn test
```

## Consultando e desativando produtos

Consultar um produto, mesmo desativado:

```bash
curl http://localhost:8080/products/1
```

Consultar somente produtos ativos:

```bash
curl "http://localhost:8080/products?active=true"
```

Desativar um produto sem removê-lo do banco:

```bash
curl -X PATCH http://localhost:8080/products/1/deactivate
```

