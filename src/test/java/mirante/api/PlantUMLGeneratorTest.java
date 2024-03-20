package mirante.api;

import com.credibledoc.plantuml.svggenerator.SvgGeneratorService;
import de.elnarion.util.plantuml.generator.classdiagram.PlantUMLClassDiagramGenerator;
import de.elnarion.util.plantuml.generator.classdiagram.config.PlantUMLClassDiagramConfigBuilder;
import de.elnarion.util.plantuml.generator.sequencediagram.PlantUMLSequenceDiagramGenerator;
import de.elnarion.util.plantuml.generator.sequencediagram.config.PlantUMLSequenceDiagramConfigBuilder;
import de.elnarion.util.plantuml.generator.sequencediagram.exception.NotFoundException;
import mirante.api.account.Account;
import mirante.api.exercise.Exercise;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlantUMLGeneratorTest {

  @Test @Order(1)
  void canGeneratePlantUMLClassDiagram() throws IOException {

    List<String> scanPackages = new ArrayList<>(); scanPackages.add("mirante.api");
    PlantUMLClassDiagramConfigBuilder configBuilder = new PlantUMLClassDiagramConfigBuilder(scanPackages);
    PlantUMLClassDiagramGenerator generator = new PlantUMLClassDiagramGenerator(configBuilder.build());
    String result = generator.generateDiagramText();
    assertNotNull(result);
    writeFile(result, "mirante-api.puml");
    String readResult = readFile("mirante-api.puml");
    assertEquals(result, readResult);

  }

  @Test @Order(2)
  void canGeneratePlantUMLSequenceDiagramSource() throws IOException, NotFoundException {

    PlantUMLSequenceDiagramConfigBuilder builder =
        new PlantUMLSequenceDiagramConfigBuilder(Account.class.getName(), "checkPassword");
    PlantUMLSequenceDiagramGenerator generator =
        new PlantUMLSequenceDiagramGenerator(builder.build());
    String result = generator.generateDiagramText().replaceAll("\\$", "");
    assertNotNull(result);
    writeFile(result, "account-checkPassword.puml");
    String readResult = readFile("account-checkPassword.puml");
    assertEquals(result.replaceAll("\\$", ""), readResult + System.lineSeparator());

  }

  @Test @Order(3)
  void canGeneratePlantUMLImage() throws IOException, NotFoundException {
    String source = readFile("mirante-api.puml");
    String svg = SvgGeneratorService.getInstance().generateSvgFromPlantUml(source);
    writeFile(svg, "mirante-api.svg");
    String readSVGResult = readFile("mirante-api.svg");
    assertEquals(svg, readSVGResult + System.lineSeparator());
  }

  private void writeFile(String content, String fileName) throws IOException {
    BufferedWriter writer = new BufferedWriter(
        new FileWriter(Paths.get("docs", "generated", "plantuml", fileName)
            .toAbsolutePath().toString()));
    writer.write(content);
    writer.close();
  }

  private String readFile(String fileName) throws IOException {
    InputStream inStream = new FileInputStream(
      Paths.get("docs", "generated", "plantuml", fileName)
          .toAbsolutePath().toString()
    );
    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
    return reader.lines().collect(Collectors.joining(System.lineSeparator()));
  }

}
