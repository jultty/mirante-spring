# mirante

Mirante is a data-oriented educational system that aims to minimize the loss of relevant data that could bring more insight into how we learn.

This repository contains an implementation using Java and the Spring Boot Framework.

A document explaining v0.1.0 can be found in the [docs](docs) directory. This currently is a PDF that was submitted as part of a capstone project for the Applied Software Engineering course of IFSP's graduation program in Systems Analysis and Development.

## Running

After cloning or downloading this repository, you can use the included gradlew wrapper file to build it:

```sh
./gradlew build
```

If you have Gradle locally installed, `gradle build` will work as well.

This will build Java `.jar` files in the `build/libs` directory.

Once built, you can start the server using:

```sh
java -jar target/mirante-<version>.jar
```

Replace `<version>` with the current version.

HTML forms meant as a minimal working front-end are available under `src/web/`. Given default port `8080` is usually in use, the forms send requests to port 8888 instead. 

To use them, set port 8888 when running:

```sh
java -Dserver.port=8888 -jar target/mirante-<version>.jar
```

## Development

Gradle is used to resolve dependencies and build this project. You can use the included wrapper or [install Gradle locally](https://gradle.org/install/).

To build and run the server:

```sh
gradle bootRun
```

To build only:

```sh
gradle build
```

To see all available options:

```sh
gradle tasks
```

Once the server is running, for development environments an [H2 database console](https://www.h2database.com/html/tutorial.html) is available on `localhost:<port>/h2-console`.

The following options allow access to the H2 console:

- **Driver class:** `org.h2.Driver`
- **JDBC URL:** `jdbc:h2:mem:mirante`
- **User Name:** `dev`
- **Password:** Empty 

If you have [Nix](https://nixos.org/manual/nix/stable/introduction) available on your system and flake support enabled, you can use the flake file to setup a development environment with JDK 21 and Gradle using `nix develop`.

