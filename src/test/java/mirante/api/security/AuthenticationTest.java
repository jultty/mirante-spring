package mirante.api.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthenticationTest {

  @Value("${server.port:8889}")
  private String port;

  WebTestClient noAuthenticationClient;
  WebTestClient authenticatedClient;
  WebTestClient invalidAuthenticationClient;

  @BeforeEach
  void buildClients() {
    noAuthenticationClient = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .build();

    authenticatedClient = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .filter(basicAuthentication("demo", "demo"))
        .build();

    invalidAuthenticationClient = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .filter(basicAuthentication("demo", "secret"))
        .build();
  }

  @Test
  void exerciseEndpointReturnsForbiddenIfUnauthenticated() {
    noAuthenticationClient.get().uri("/exercise").exchange()
        .expectStatus().isForbidden();
  }

  @Test
  void versionEndpointReturnsCurrentVersionWithoutAuthentication() {
    noAuthenticationClient.get().uri("/version").exchange()
        .expectStatus().isOk()
        .expectBody().jsonPath("$.version").isEqualTo("v0.3.0");
  }

  @Test
  void versionEndpointReturnsCurrentVersionWithInvalidAuthentication() {
    invalidAuthenticationClient.get().uri("/version").exchange()
        .expectStatus().isOk()
        .expectBody().jsonPath("$.version").isEqualTo("v0.3.0");
  }

  @Test
  void versionEndpointReturnsCurrentVersionWithAuthentication() {
    authenticatedClient.get().uri("/version").exchange()
        .expectStatus().isOk()
        .expectBody().jsonPath("$.version").isEqualTo("v0.3.0");
  }

  @Test
  void authEndpointReturnsOkWithoutAuthentication() {
    noAuthenticationClient.get().uri("/auth").exchange()
        .expectStatus().isOk();
  }

  @Test
  void authEndpointReturnsOkWithInvalidAuthentication() {
    invalidAuthenticationClient.get().uri("/auth").exchange()
        .expectStatus().isOk();
  }

  @Test
  void authEndpointReturnsOkWithAuthentication() {
    authenticatedClient.get().uri("/auth").exchange()
        .expectStatus().isOk();
  }

  @Test
  void tokenEndpointReturnsOkWithoutAuthentication() {
    noAuthenticationClient.get().uri("/auth/token/health").exchange()
        .expectStatus().isOk();
  }

  @Test
  void tokenEndpointReturnsOkWithInvalidAuthentication() {
    invalidAuthenticationClient.get().uri("/auth/token/health").exchange()
        .expectStatus().isOk();
  }

  @Test
  void tokenEndpointReturnsOkWithAuthentication() {
    authenticatedClient.get().uri("/auth/token/health").exchange()
        .expectStatus().isOk();
  }
}