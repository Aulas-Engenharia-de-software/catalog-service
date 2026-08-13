# Serviço de Atividades Complementares

Exemplo preparado para uma demonstração de refatoração em sala de aula. O caso
de uso é a inscrição de alunos do ensino superior em atividades complementares.

> Esta branch contém propositalmente uma implementação com responsabilidades
> misturadas. Ela funciona, mas ainda não representa uma boa arquitetura. A
> intenção é refatorá-la ao vivo para uma arquitetura em camadas.

## Cenário

A instituição precisa registrar a inscrição de um aluno em uma atividade
complementar. Cada inscrição possui:

- nome do aluno;
- matrícula;
- nome da atividade;
- modalidade, que pode ser `PRESENCIAL` ou `ONLINE`;
- carga horária;
- link de acesso, quando aplicável.

### Validações básicas

- nome do aluno, matrícula e nome da atividade são obrigatórios;
- carga horária deve ser um número inteiro maior que zero;
- modalidade deve ser `PRESENCIAL` ou `ONLINE`.

### Regras de negócio

- uma única atividade pode contabilizar no máximo 40 horas;
- atividades online precisam possuir um link de acesso.

## Executando

Pré-requisitos: Java 21 e Maven 3.9 ou superior.

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`. O projeto usa um banco H2
em memória. O console pode ser acessado em `http://localhost:8080/h2-console`,
usando:

- JDBC URL: `jdbc:h2:mem:atividades_complementares`
- usuário: `sa`
- senha: deixe em branco

## Criando uma inscrição

`POST /api/inscricoes`

```bash
curl -X POST http://localhost:8080/api/inscricoes \
  -H "Content-Type: application/json" \
  -d '{
    "nomeAluno": "Ana Silva",
    "matricula": "20261234",
    "nomeAtividade": "Workshop de Git",
    "modalidade": "ONLINE",
    "cargaHoraria": 8,
    "linkAcesso": "https://eventos.exemplo.edu/git"
  }'
```

Uma inscrição válida retorna `201 Created`. Dados inválidos ou que não atendem
às regras de negócio retornam `400 Bad Request`.

## Roteiro da demonstração em sala

Antes de começar, execute os testes:

```bash
mvn test
```

O `InscricaoController` recebe a requisição, converte dados, faz validações,
aplica regras de negócio, executa SQL e monta a resposta HTTP. Essas
responsabilidades estão juntas de propósito.

Uma sequência possível para a refatoração é:

1. criar classes para representar a entrada, o domínio e a resposta;
2. extrair o acesso ao banco para um repository;
3. extrair as regras de inscrição para um service;
4. deixar o controller responsável somente pela comunicação HTTP;
5. ajustar os testes a cada pequena mudança, preservando o comportamento.

Este exemplo usa um domínio independente para demonstrar o processo de
refatoração e as decisões arquiteturais envolvidas, sem oferecer uma solução
diretamente reaproveitável em outro exercício.
