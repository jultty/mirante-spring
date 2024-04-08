package mirante.api.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class AuthenticationDocTest {

  private WebTestClient client;

  @Value("${server.port:8889}")
  private String port;

  @BeforeEach
  void setUp(WebApplicationContext applicationContext, RestDocumentationContextProvider restDocumentation) {
    this.client = MockMvcWebTestClient.bindToApplicationContext(applicationContext)
        .configureClient()
        .filter(documentationConfiguration(restDocumentation))
        .baseUrl("http://localhost:" + port)
        .build();
  }

  @Test
  void versionEndpointReturnsOK() throws Exception {

    this.client.get()
        .uri("/version")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .consumeWith(document("version"));

  }

}
