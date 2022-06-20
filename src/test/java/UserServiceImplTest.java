import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserServiceImplTest {
    @Test
    public void test() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get("C:\\Users\\Kudakster\\IdeaProjects\\cinema\\src\\main\\java\\com\\epam\\cinema\\commands\\open"))) {
            Set<String> set = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());

            set.forEach(System.out::println);
        }
    }
}
