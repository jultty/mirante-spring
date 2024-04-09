package mirante.api.security;

import mirante.api.account.AccountDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountCreationTest {

  @Value("${server.port:8889}")
  private String port;

  WebTestClient unauthenticatedClient;
  WebTestClient authenticatedClient;
  WebTestClient invalidAuthenticationClient;
  AccountDTO account;
  AccountDetailsDTO details;

  @BeforeAll
  void setUp() {
    unauthenticatedClient = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .build();

    authenticatedClient = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .filter(basicAuthentication("jc222222", "xyz6060"))
        .build();

    invalidAuthenticationClient = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .filter(basicAuthentication("jc222222", "xyz0000"))
        .build();

      account = new AccountDTO(
          "jc222222", "Tania Wolfgramm", "tania@mirante.dev", "xyz6060"
      );

      details = new AccountDetailsDTO(account.registration, account.password);
  }

  @Test @Order(1)
  void accountEndpointReturnsForbiddenIfUnauthenticated() {
    unauthenticatedClient.get().uri("/account").exchange()
        .expectStatus().isForbidden();
  }

  @Test @Order(2)
  void authEndpointReturnsOkOnGetIfUnauthenticated() {
    unauthenticatedClient.get().uri("/auth").exchange()
        .expectStatus().isOk();
  }

  @Test @Order(3)
  void accountCreationCanHappenIfUnauthenticated() {
    unauthenticatedClient
        .post().uri("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(account)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isCreated();
  }

  @Test @Order(4)
  void createdAccountDetailsAllowObtainingAToken() {
    unauthenticatedClient
        .post().uri("/auth/token")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(details)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isCreated()
        .expectBody().jsonPath("$.token").isNotEmpty();
  }

  @Test @Order(5)
  void wrongPasswordForCreatedAccountDoesNotAllowObtainingAToken() {

    details.password = "xyz0000";

     invalidAuthenticationClient
        .post().uri("/auth/token")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(details)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .jsonPath("$.error").isEqualTo("No matching account found");

  }
}
