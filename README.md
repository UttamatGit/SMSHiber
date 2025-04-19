
# Hibernate Student Management Project

This is a basic Hibernate-based CRUD application for managing student records using Java, Hibernate ORM, and MySQL database. The project is structured using Maven or Gradle for dependency management.

---

## 🛠️ Technologies Used

- Java 17+
- Hibernate `6.6.5.Final`
- MySQL Connector/J `8.0.33`
- Jakarta Persistence API `3.1.0`
- Maven or Gradle

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── Controller/
│   │   │   └── StudentController.java
│   │   ├── DAO/
│   │   │   └── StudentDAO.java
│   │   ├── DAOImplementation/
│   │   │   └── StudentDAOImpl.java
│   │   ├── Models/
│   │   │   └── Student.java
│   │   ├── Utils/
│   │   │   └── SessionFactoryUtil.java
│   │   └── RUN.java
│   └── resources/
│       └── hibernate.cfg.xml
```

---

## ⚙️ How to Set Up

### 1. Clone the Repository

```bash
git clone https://github.com/UttamatGit/SMSHiber.git
cd SMSByHibernate
```

### 2. Configure Database

Create a MySQL database:

```sql
CREATE DATABASE student_db;
```

Update `hibernate.cfg.xml` located in `src/main/resources`:

```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/student_db</property>
<property name="hibernate.connection.username">your_username</property>
<property name="hibernate.connection.password">your_password</property>
```

### 3. Add Dependencies

#### Maven (`pom.xml`)

```xml
<dependencies>
    <!-- Hibernate Core -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.6.5.Final</version>
    </dependency>

    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

    <!-- Jakarta Persistence API -->
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
        <version>3.1.0</version>
    </dependency>
</dependencies>
```

#### OR Gradle (`build.gradle`)

```groovy
dependencies {
    implementation 'org.hibernate:hibernate-core:6.6.5.Final'
    implementation 'mysql:mysql-connector-java:8.0.33'
    implementation 'jakarta.persistence:jakarta.persistence-api:3.1.0'
}
```

### 4. Build and Run

Use your IDE or command line to build and run the project.

To run from terminal (using Maven):

```bash
mvn compile exec:java -Dexec.mainClass="RUN"
```

---

## ✅ Features

- Add new student records
- Update student email by roll number
- Delete student records
- View individual or all student records
- Hibernate SessionFactory management
- Automatic table creation (`hibernate.hbm2ddl.auto=update`)

---

## 👤 Author

**Uttam Kumar** – (https://github.com/UttamatGit)

---

## 📌 License

This project is open-source and available under the [MIT License](LICENSE).
