package mirante.api.security;

import mirante.api.account.AccountDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AccountCreationTest {

  @Value("${server.port:8889}")
  private String port;

  WebTestClient unauthenticatedClient;
  WebTestClient authenticatedClient;
  WebTestClient invalidAuthenticationClient;

  @BeforeAll
  void buildClients() {
    unauthenticatedClient = WebTestClient
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
  void accountEndpointReturnsForbiddenIfUnauthenticated() {
    unauthenticatedClient.get().uri("/account").exchange()
        .expectStatus().isForbidden();
  }

  @Test
  void authEndpointReturnsOkOnGetIfUnauthenticated() {
    unauthenticatedClient.get().uri("/auth").exchange()
        .expectStatus().isOk();
  }

  @Test
  void accountCreationCanHappenIfUnauthenticated() {
    AccountDTO account = new AccountDTO(
        "jc222222", "Tania Wolfgramm", "tania@mirante.dev", "xyz6060"
    );
    unauthenticatedClient
        .post().uri("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(account)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isCreated();
  }
}
