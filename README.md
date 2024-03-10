# mirante

Mirante is a data-oriented educational system that aims to minimize the loss of relevant data that could bring more insight into how we learn.

This repository contains an implementation using Java and the Spring Boot Framework.

## Running

After cloning or downloading this repository, you can use the included `gradlew` wrapper file to build it:

```sh
./gradlew build
```

This will build Java `.jar` files in the `build/libs` directory.

Once built, you can start the server using:

```sh
java -jar build/libs/mirante-<version>.jar
```

Replace `<version>` with the current version, for example: `java -jar build/libs/mirante-0.1.2-SNAPSHOT.jar`.

If you are unsure about the current version number, look into the `build/libs` directory for what files were generated, or check the `build.gradle` file.

HTML forms meant as a minimal working front-end demo are available under `src/web/`. Given default port `8080` is usually in use, the forms send requests to port `8888` instead. 

To use them, set port 8888 when running:

```sh
java -Dserver.port=8888 -jar build/libs/mirante-<version>.jar
```

## Documentation

To know more, go to the [documentation website](https://jultty.github.io/mirante/) for access to the latest available documentation.

If you are looking for more formal descriptions of the system, a document explaining v0.1.0 can be found in the [docs](docs) directory. This document was adapted from the version submitted as part of a capstone project for the Applied Software Engineering course in IFSP's graduation program in Systems Analysis and Development.

This main document will only be updated on major versions. For more up-to-date information, check the reports, also in the `docs` directory, or access the [documentation website](https://jultty.github.io/mirante/).

## Development

Mirante is developed and tested on Java 17, more specifically [Eclipse Temurin JDK 17.0.10+7](https://adoptium.net/temurin/archive/?version=17).

Gradle is used as a build tool to resolve dependencies and build this project. You can use the included wrapper, use Devbox (see below) or [install Gradle locally](https://gradle.org/install/).

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

If you have [Devbox](https://www.jetpack.io/devbox/) available on your system, you can run `devbox shell` to get a development environment with JDK 17 and Gradle 8 already set up. You can also use `devbox run <script>` to run `build`, `test` and `run` scripts. These scripts will all run inside the Devbox shell and come with port `8888` preconfigured.
