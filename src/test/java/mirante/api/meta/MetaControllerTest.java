package mirante.api.meta;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MetaControllerTest {

  @Test
  void versionEndpointReturnsCurrentVersion(@Autowired TestRestTemplate template) {
    String body = template.getForObject("/version", String.class);
    assertThat(body).contains("v0.2.0");
  }
}