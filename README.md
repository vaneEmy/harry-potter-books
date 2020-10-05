# Harry Potter Store 

### Requirements

- Java v8 

### Steps to Setup

**1. Clone the repository**

```bash
git clone https://github.com/vaneEmy/harry-potter-books.git
```

**2. Specify the file uploads directory**

Open `src/main/resources/application.properties` file and change the property `file.upload-dir` to the path where you want the uploaded files to be stored.

```
file.upload-dir=/Users/documents/uploads
```

**2. Run the app using maven**

```bash
cd harry-potter-books
mvn spring-boot:run
```

That's it! The application can be accessed at `http://127.0.0.1:8080`.
