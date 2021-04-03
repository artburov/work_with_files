package autotests;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static helpers.FileHelper.convertFileToStringUsingPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TextFileTests {
    String textFilePath = "src/test/resources/textTest.txt";

    @Test
    void readTextFileTests() throws IOException {
        String expected = "Hello";
        String actual = convertFileToStringUsingPath(textFilePath);
        assertThat(actual, containsString(expected));
    }
}