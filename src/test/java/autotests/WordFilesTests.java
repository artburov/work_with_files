package autotests;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static helpers.FileHelper.readWordFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class WordFilesTests {
    File docFile = new File("src/test/resources/docTest.doc");
    File docxFile = new File("src/test/resources/docxTest.docx");

    @Test
    void readDocFileTests() throws IOException {
        String actualData = readWordFile(docFile);
        assertThat(actualData, containsString("Selenide!"));
    }

    @Test
    void readDocxFileTests() throws IOException {
        String actualData = readWordFile(docxFile);
        assertThat(actualData, containsString("Apache POI!"));
    }
}