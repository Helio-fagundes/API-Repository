# 🛠️ API RESTful de Produtos com Spring Boot

Este projeto consiste no desenvolvimento de uma **API RESTful** utilizando o ecossistema **Spring Boot**. O objetivo principal da aplicação é permitir a realização das operações de **criação, leitura, atualização e exclusão** (CRUD) de produtos, com persistência em banco de dados PostgreSQL.

## ✅ O que foi implementado

- 🔄 **CRUD completo** de produtos via endpoints RESTful.
- 🧪 **Validação de dados** com Jakarta Validation.
- 💾 **Persistência** usando JPA com PostgreSQL.
- 🔗 **HATEOAS** para enriquecer as respostas com links navegáveis.
- 📦 **Repository Pattern** para separação clara entre lógica de negócios e acesso a dados.
- 🧰 **BeanUtils** para cópia eficiente de dados entre DTOs e entidades.
- 🧱 **Maturidade de Richardson** (níveis 1 ao 3):
  - 🔹 *Nível 1:* Uso de URIs organizadas para recursos.
  - 🔹 *Nível 2:* Utilização correta de verbos HTTP (GET, POST, PUT, DELETE).
  - 🔹 *Nível 3:* Inclusão de HATEOAS para navegação entre recursos.

## 🧭 Estrutura do Projeto

O projeto segue boas práticas de organização em camadas:

- 🎯 **Controller**: Responsável pelos endpoints e lógica de requisições.
- 🧩 **Model**: Entidades mapeadas para o banco de dados.
- ✉️ **DTO**: Objetos para transferência de dados entre as camadas.
- 🗃️ **Repository**: Interface de acesso aos dados com Spring Data JPA.
- ⚙️ **Configuration**: Parâmetros da aplicação (como conexão com banco).

## 🧰 Tecnologias e Ferramentas

- ☕ **Java 21 LTS**
- 🌱 **Spring Boot**
- 🌐 **Spring Web**
- 🛢️ **Spring Data JPA**
- 🔗 **Spring HATEOAS**
- 📏 **Jakarta Validation**
- 🐘 **PostgreSQL**
- 📦 **Maven**

## 🚀 Como executar

1. Clone o repositório 📂  
2. Configure seu banco PostgreSQL conforme as propriedades do projeto  
3. Execute com: `mvn spring-boot:run`  
4. Acesse a API em: `http://localhost:8080` 🚀

---

Este projeto é uma base sólida para construção de **APIs RESTful modernas e escaláveis** com Spring Boot.  
Feito com dedicação e boas práticas! 💻✨
