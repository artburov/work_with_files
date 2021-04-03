package autotests;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static helpers.FileHelper.readExcelFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ExcelFilesTests {
    File xlsFile = new File("src/test/resources/xlsTest.xls");
    File xlsxFile = new File("src/test/resources/xlsxTest.xlsx");

    @Test
    void readXlsFileStringTests() throws IOException {
        String actualData = readExcelFile(xlsFile);
        assertThat(actualData, containsString("XLS file format"));
    }

    @Test
    void readXlsFileBooleanTests() throws IOException {
        String actualData = readExcelFile(xlsFile);
        assertThat(actualData, containsString("true"));
    }

    @Test
    void readXlsxFileStringTests() throws IOException {
        String actualData = readExcelFile(xlsxFile);
        assertThat(actualData, containsString("XLSX format"));
    }

    @Test
    void readXlsxFileNumericTests() throws IOException {
        String actualData = readExcelFile(xlsxFile);
        assertThat(actualData, containsString("256"));
    }

    @Test
    void readXlsxFileFormulaTests() throws IOException {
        String actualData = readExcelFile(xlsxFile);
        assertThat(actualData, containsString("SUM(C1,D1)"));
    }
}