# mirante-spring

Mirante is a data-oriented educational system that aims to minimize the loss of relevant data that could bring us more insight into how we learn.

This repository contains an implementation using Java and the Spring Boot Framework.

A document explaining v0.1.0 can be found in the [docs](docs) directory. This currently is a PDF that was submitted as part of a capstone project for the Applied Software Engineering course of IFSP's graduation program in Systems Analysis and Development.

## Running

To set a specific port when running:

```sh
java -Dserver.port=8888 -jar target/api-0.1.0-SNAPSHOT.jar
```

## Development

If you have [Nix](https://nixos.org/manual/nix/stable/introduction) available on your system, you can use the flake file to setup a development environment with JDK 21 and Maven using `nix develop`.
