🚀 fast-pos

📄 Description

Backend project for a Point of Sale (POS) system developed in Java with Spring Boot, using hexagonal architecture. It manages sales, products, and users.

Each new store registered in the system has its own dynamic schema in PostgreSQL, allowing data isolation per store within the same database.

🧰 Technologies used

☕ Java 17 (or your version)

🌱 Spring Boot

🐘 PostgreSQL

🧱 Hexagonal Architecture

🎯 Key features

🛒 Sales, products, and users management

🏪 Multi-tenant with dynamic schemas per store

🗃️ Database migrations with SQL scripts

🌐 REST API to interact with the system

📦 Requirements

✅ Java 17+

🐘 PostgreSQL installed and running

🧰 Maven or Gradle (depending on your build tool)

▶️ How to run the project

🔁 Clone the repository

git clone https://github.com/Dheltt/fast-pos.git

⚙️ Configure the database connection in src/main/resources/application.properties

▶️ Run the application

./mvnw spring-boot:run

or from your favorite IDE

🧱 Project structure

The project uses hexagonal architecture separating domain, infrastructure, and application layers. This facilitates maintainability and scalability.

🤝 How to contribute

Contributions are welcome. Please open an issue or pull request to suggest improvements or fix bugs.

📜 License

This project is licensed under the MIT License.

