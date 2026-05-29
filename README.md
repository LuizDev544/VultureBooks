# VultureBooks
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/LuizDev544/VultureBooks)

O VultureBooks é um sistema de gerenciamento de doação de livros desenvolvido em Java com o framework Spring Boot. A aplicação oferece uma plataforma para que administradores gerenciem um catálogo de livros disponíveis para doação e para que os usuários possam navegá-los e solicitá-los.

Este projeto serve como uma demonstração prática de diversos padrões de design e arquitetura de software cruciais, incluindo Domain-Driven Design (DDD), o Padrão Decorator (Decorator Pattern) e práticas robustas de segurança.

## Principais Funcionalidades

* **Painel do Administrador**: Uma área segura para o gerenciamento do inventário de livros, incluindo a criação, leitura, atualização e exclusão (CRUD) de registros de livros.
* **Catálogo para o Cliente**: Uma página pública onde os usuários podem navegar pelos livros disponíveis, filtrar por categoria e pesquisar por títulos específicos.
* **Autenticação Segura**: O login do administrador é protegido com criptografia de senha via bcrypt e um mecanismo de limitação de taxa (rate-limiting) baseado em IP para evitar ataques de força bruta.
* **Histórico e Auditoria**: Todas as modificações nos registros dos livros (criar, atualizar, excluir) são registradas automaticamente usando o padrão Decorator, fornecendo uma trilha de auditoria completa.
* **Notificações Multicanais**: O sistema utiliza o padrão Decorator para enviar notificações sobre eventos do sistema através de vários canais (E-mail e simulação de WhatsApp/Facebook).
* **Domain-Driven Design (DDD)**: A aplicação é estruturada em torno de um modelo de domínio rico, utilizando Entidades, Objetos de Valor (Value Objects) e Repositórios para um código limpo e de fácil manutenção.

## Conceitos Arquiteturais e Padrões de Projeto

Este projeto utiliza intensamente padrões de projeto para garantir escalabilidade, manutenibilidade e separação de conceitos (separation of concerns).

### Domain-Driven Design (DDD)
A estrutura do código segue os princípios do DDD:
* **Entidades**: Objetos centrais do domínio que possuem uma identidade única, como `Livro` e `Admin`.
* **Objetos de Valor (Value Objects)**: Objetos imutáveis que descrevem atributos, como `Titulo`, `Email` e `StatusLivro`, garantindo a integridade e consistência dos dados.
* **Repositórios**: Abstrações para persistência de dados, gerenciadas pelo Spring Data JPA (`LivroRepository`, `AdminRepository`).
* **Serviços (Services)**: Encapsulam a lógica de negócio que não se encaixa naturalmente dentro de uma entidade (`AdminService`, `LivroService`).
* **Arquitetura em Camadas**: O código é dividido nas camadas de `domain` (domínio), `infrastructure` (infraestrutura) e `presentation` (apresentação).

### Padrão Decorator (Decorator Pattern)
O padrão Decorator é amplamente utilizado para adicionar responsabilidades aos objetos de forma dinâmica.
1. **Auditoria (`LivroHistoricoDecorator`)**: Este decorator envolve o `LivroService`. Quando métodos como `criarLivro`, `atualizarLivro` ou `excluirLivro` são chamados, o decorator primeiro executa a lógica central e, em seguida, cria uma entrada em `Historico` para registrar a ação, adicionando a funcionalidade de auditoria de forma transparente.
2. **Notificações (`EmailDecorator`, `WhatsAppDecorator`, etc.)**: O sistema de notificação permite a composição flexível de canais de saída. Um `Notificador` base pode ser envolvido por múltiplos decorators em tempo de execução para enviar um alerta via e-mail, WhatsApp e Facebook simultaneamente, com base na escolha do usuário na interface.

### Segurança
* **Interceptor de Autorização (`AutorizacaoInterceptor`)**: Protege todas as rotas administrativas (ex: `/livros/painel`, `/livros/editar/**`) verificando se há uma sessão de administrador válida. Requisições não autorizadas são redirecionadas para a página de login.
* **Limitação de Taxa (`RateLimitingFiltragem`)**: Um filtro personalizado limita o número de tentativas de login a partir de um único endereço IP dentro de uma janela de tempo específica, mitigando o risco de ataques de força bruta.
* **Criptografia de Senhas (`BCryptPasswordEncoder`)**: As senhas dos administradores são armazenadas com segurança utilizando o algoritmo de hashing bcrypt, que é o padrão da indústria.

## Tecnologias Utilizadas

* **Backend**: Java 21, Spring Boot, Spring Data JPA, Spring Security, Spring Web
* **Banco de Dados**: MySQL
* **Frontend**: Thymeleaf, HTML5, CSS3, JavaScript
* **Estilização**: Bootstrap 5
* **Ferramenta de Build**: Maven

## Configuração e Execução do Projeto

### Pré-requisitos

* JDK 21 ou superior
* Maven 3.x
* Servidor MySQL

### 1. Configuração do Banco de Dados

1. Conecte-se ao seu servidor MySQL.
2. Execute o script `BancoDedados.sql` para criar o banco de dados `DonationBooks`.
    ```sql
    create database DonationBooks;
    use DonationBooks;
    ```
    As tabelas serão geradas automaticamente pelo Hibernate.

### 2. Configurar as Propriedades da Aplicação

1. Abra o arquivo `src/main/resources/application.properties`.
2. Atualize as propriedades de conexão do banco de dados com o seu usuário e senha do MySQL.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/DonationBooks
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```
3. Para ativar o recurso de notificação por e-mail, configure as definições de SMTP do Gmail. Pode ser necessário gerar uma "Senha de App" nas configurações da sua Conta Google se você tiver a Verificação em Duas Etapas ativada.
    ```properties
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=seu-email@gmail.com
    spring.mail.password=sua_senha_de_app_do_google
    ```

### 3. Compilar e Executar

1. Navegue até o diretório raiz do projeto `demo` (onde o arquivo `pom.xml` está localizado).
2. Execute a aplicação usando o Maven wrapper:
    ```bash
    # No Linux/macOS
    ./mvnw spring-boot:run

    # No Windows
    mvnw.cmd spring-boot:run
    ```
3. A aplicação estará acessível em `http://localhost:8080`.

## Rotas do Sistema

* **Login do Admin**: `http://localhost:8080/admin/login`
* **Painel do Admin**: `http://localhost:8080/livros/painel` (acessível após o login)
* **Catálogo de Livros do Cliente**: `http://localhost:8080/livros/catalogo`
* **Histórico de Auditoria**: `http://localhost:8080/livros/historico`
