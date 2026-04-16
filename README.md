# 📚 Plataforma de Doação de Livros

A **Plataforma de Doação de Livros** é uma aplicação web desenvolvida para facilitar a doação e o gerenciamento de livros. Ela oferece uma interface amigável para administradores gerenciarem coleções, rastrearem doações e realizarem diversas tarefas administrativas. A plataforma foi construída com **Spring Boot**, aproveitando seus recursos robustos para a construção de aplicações web modernas.

## 🚀 Funcionalidades

- **Gerenciamento de Livros**: Criar, ler, atualizar e excluir (CRUD) registros de livros.
- **Gerenciamento de Administradores**: Gerenciar contas administrativas, incluindo a criação, atualização e exclusão de usuários admins.
- **Integração com Banco de Dados**: Interação com banco de dados para armazenar e recuperar dados de livros e administradores.
- **API RESTful**: Endpoints de API estruturados para operações de livros e administradores.
- **Segurança**: Implementação de autenticação e autorização utilizando Spring Security.

## 🛠️ Tecnologias Utilizadas

- **Spring Boot**: Framework principal para construção da aplicação.
- **Spring Data JPA**: Para operações de banco de dados e ORM.
- **Spring Security**: Para autenticação e autorização.
- **MySQL**: Sistema de gerenciamento de banco de dados.
- **Java**: Linguagem de programação.
- **Jackson**: Para serialização e desserialização de JSON.

## 📦 Instalação

Para configurar o projeto localmente, siga estas etapas:

1. **Pré-requisitos**: Certifique-se de ter o Java 11 ou superior, MySQL 8 ou superior e Maven 3.6 ou superior instalados.
2. **Clonar o Repositório**:
   ```bash
   git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
Configurar o Banco de Dados: Atualize o arquivo src/main/resources/application.properties com as suas credenciais do MySQL.

Build do Projeto:

Bash
mvn clean install
Executar a Aplicação:

Bash
mvn spring-boot:run
💻 Como Usar
Acessar a Aplicação: Abra o navegador e acesse http://localhost:8080.

Login Administrativo: Use as credenciais de admin padrão (usuário: admin, senha: password) para logar.

Gerenciar Dados: Utilize os endpoints da API REST ou a interface web para gerenciar os registros de livros e administradores.

📂 Estrutura do Projeto
Plaintext
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── example
│   │   │   │   │   ├── DemoApplication.java
│   │   │   │   │   ├── controller
│   │   │   │   │   │   ├── AdminController.java
│   │   │   │   │   │   └── LivroController.java
│   │   │   │   │   ├── repository
│   │   │   │   │   │   ├── AdminRepository.java
│   │   │   │   │   │   └── LivroRepository.java
│   │   │   │   │   ├── service
│   │   │   │   │   │   ├── AdminService.java
│   │   │   │   │   │   └── LivroService.java
│   │   │   │   │   ├── entity
│   │   │   │   │   │   ├── Admin.java
│   │   │   │   │   │   └── Livro.java
│   │   │   │   │   └── dto
│   │   │   │   │       └── LivroRequestDTO.java
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── test
│   │       └── java
│   └── BancoDedados.sql

📸 Demonstração
(Você pode adicionar capturas de tela aqui para mostrar o seu projeto funcionando)

🤝 Contribuição
Para contribuir com a Plataforma de Doação de Livros, faça um fork do repositório, crie uma branch com suas alterações e envie um pull request.

📝 Licença
Este projeto está licenciado sob a Licença MIT.

📬 Contato
Para dúvidas ou sugestões, entre em contato em: luizfernando.carrupt@gmail.com

💖 Agradecimentos
Este projeto foi possível graças à contribuição de diversas pessoas. Muito obrigado a todos que colaboraram com a Plataforma de Doação de Livros.
