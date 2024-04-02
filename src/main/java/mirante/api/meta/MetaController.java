package mirante.api.meta;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MetaController {

  public static final class MetaData {
    @JsonProperty("version") String version = "v0.3.0";
  }

  MetaData metadata = new MetaData();

  @GetMapping("/version")
  @ResponseBody
  public MetaData version() {
    return this.metadata;
  }

}
