package mirante.api.meta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MetaControllerTest {

  @Value("${server.port:8888}")
  private String port;

  WebTestClient client;
  WebTestClient authenticatedClient;
  WebTestClient unauthenticatedClient;

  @BeforeEach
  void buildClients() {
    client = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .build();

    authenticatedClient = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .filter(basicAuthentication("demo", "demo"))
        .build();

    unauthenticatedClient = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .filter(basicAuthentication("demo", "secret"))
        .build();
  }

  @Test
  void versionEndpointReturns401IfUnauthenticated() {
    client.get().uri("/version").exchange()
        .expectStatus().isUnauthorized();
  }

  @Test
  void versionEndpointReturnsCurrentVersion() {
    authenticatedClient.get().uri("/version").exchange()
        .expectStatus().isOk()
        .expectBody().jsonPath("$.version").isEqualTo("v0.2.0");
  }

  @Test
  void versionEndpointReturns401IfCredentialsAreInvalid() {
    unauthenticatedClient.get().uri("/version").exchange()
        .expectStatus().isUnauthorized();
  }
}