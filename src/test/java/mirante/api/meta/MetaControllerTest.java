package mirante.api.meta;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MetaControllerTest {

  @Test
  void versionEndpointReturnsCurrentVersion(@Autowired TestRestTemplate template) {
    ResponseEntity<String> response = template.withBasicAuth("spring","secret")
      .getForEntity("/version", String.class);
    assertThat(Objects.requireNonNull(response.getBody())).contains("v0.2.0");
    assertEquals(response.getStatusCode(), (HttpStatus.OK));
  }
}