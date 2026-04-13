# Sistema de Biblioteca com JPA, DAO e H2

## Integrantes da dupla
- Vergílio Júnior
- Marcus Vinicius

## Descrição
Projeto desenvolvido a partir de um diagrama de classes de um Sistema de Biblioteca, aplicando mapeamento objeto-relacional com Java JPA. O projeto contém as entidades `Aluno`, `Publicacao` e `Emprestimo`, uma classe DAO para `Emprestimo`, um teste com operações CRUD e o mapeamento SQL das tabelas.

## Estrutura de pacotes
- `com.biblioteca.model`: classes de entidade do sistema.
- `com.biblioteca.dao`: classe DAO da entidade `Emprestimo`.
- `com.biblioteca.app`: classe principal para executar o teste.
- `com.biblioteca.test`: classe de teste do DAO.
- `src/main/resources/META-INF`: arquivo `persistence.xml` com a configuração do JPA/Hibernate.
- `src/main/resources/sql`: arquivo `schema.sql` com o mapeamento SQL das tabelas.

## Entidades
### Aluno
Representa o aluno que realiza empréstimos.
- `matriculaAluno`: identificador do aluno.
- `nome`: nome do aluno.

### Publicacao
Representa a publicação disponível na biblioteca.
- `codigoPub`: identificador da publicação.
- `titulo`: título da publicação.
- `ano`: ano de publicação.
- `autor`: autor da publicação.
- `tipo`: tipo da publicação.

### Emprestimo
Representa o empréstimo de uma publicação para um aluno.
- `id`: identificador do empréstimo.
- `dataEmprestimo`: data em que o empréstimo foi realizado.
- `dataDevolucao`: data prevista ou realizada da devolução.
- `aluno`: referência para o aluno.
- `publicacao`: referência para a publicação.

## Relacionamentos
- Um `Aluno` pode ter vários `Emprestimo`.
- Uma `Publicacao` pode estar em vários `Emprestimo`.
- Cada `Emprestimo` pertence a um `Aluno` e a uma `Publicacao`.

## Classe DAO
A classe `EmprestimoDAO` implementa as operações:
- `salvar`
- `buscar` por ID
- `atualizar`
- `deletar`
- `listar` todos
- `buscarPorNomeAluno`

## Classe de teste
A classe `TesteEmprestimoDAO` demonstra o uso do DAO com os seguintes cenários:
- incluir um empréstimo
- alterar o empréstimo
- consultar por ID
- consultar todos os empréstimos
- consultar empréstimos por nome do aluno
- deletar o empréstimo

## Banco de dados
Foi utilizado o banco H2 em memória para facilitar a execução e correção do projeto.

Configuração principal no `persistence.xml`:
- JPA com Hibernate
- banco H2 em memória
- criação automática das tabelas com `hibernate.hbm2ddl.auto=create`

## Como executar no Eclipse
1. Importar o projeto como **Existing Maven Project**.
2. Clicar em **Maven > Update Project**.
3. Executar a classe `com.biblioteca.app.Main` como **Java Application**.

## Arquivos importantes
- `pom.xml`: dependências do projeto.
- `src/main/resources/META-INF/persistence.xml`: configuração da persistência.
- `src/main/resources/sql/schema.sql`: mapeamento SQL das tabelas.
- `src/test/java/com/biblioteca/test/TesteEmprestimoDAO.java`: teste do DAO.

