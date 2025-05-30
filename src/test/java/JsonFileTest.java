import com.fasterxml.jackson.databind.ObjectMapper;
import model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonFileTest {
    private ClassLoader cl = JsonFileTest.class.getClassLoader();
    private ObjectMapper om = new ObjectMapper();
    @Test
    @DisplayName("Проверка файла .json")

    void jsonFile() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testJSON.json")) {
            assertNotNull(is, "Файл .json не найден в resources");
            Person person = om.readValue(is, Person.class);

            assertEquals("Anna", person.getName());
            assertEquals(27, person.getAge());
            assertEquals("Tester", person.getProfession());
            Person.Skills skills = person.getSkills();
            List<String> hardSkills = skills.getHardSkills();
            assertIterableEquals(List.of("Java", "TestIT", "Swagger"), hardSkills);
            List<String> softSkills = skills.getSoftSkills();
            assertIterableEquals(List.of("responsibility", "attentiveness", "friendliness"), softSkills);
        }
    }
}
