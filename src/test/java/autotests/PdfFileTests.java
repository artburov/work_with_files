package autotests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.pdftest.PDF.containsText;
import static org.hamcrest.MatcherAssert.assertThat;

public class PdfFileTests {
    File pdfFile = new File("src/test/resources/pdfTest.pdf");

    @Test
    void readPdfFileTests() throws IOException {
        PDF pdf = new PDF(pdfFile);
        assertThat(pdf, containsText("with PDF"));
    }
}