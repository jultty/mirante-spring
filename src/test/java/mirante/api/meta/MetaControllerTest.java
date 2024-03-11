package mirante.api.meta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MetaControllerTest {

  @Value("${server.port:8888}")
  private String port;

  WebTestClient client;

  @BeforeEach
  void buildClient() {
    client = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:" + port)
        .build();
  }

  @Test
  void versionEndpointReturns401IfUnauthenticated() {
    client.get().uri("/version").exchange()
        .expectStatus().isUnauthorized();
  }

//  @Test
//  void versionEndpointReturnsCurrentVersion(@Autowired TestRestTemplate template) {
//    ResponseEntity<String> response = template.withBasicAuth("spring","secret")
//      .getForEntity("/version", String.class);
//    assertThat(Objects.requireNonNull(response.getBody())).contains("v0.2.0");
//    assertEquals(response.getStatusCode(), (HttpStatus.OK));
//  }
}