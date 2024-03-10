package mirante.api.meta;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MetaController {

  public static final class MetaData {
    @JsonProperty("version") String version = "v0.2.0";
  }

  MetaData metadata = new MetaData();

  @RequestMapping("/version")
  @ResponseBody
  public MetaData version() {
    return this.metadata;
  }

}
