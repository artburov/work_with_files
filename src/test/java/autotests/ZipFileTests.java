package autotests;

import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;

import static helpers.FileHelper.unzipFile;

public class ZipFileTests {

    String source = "src/test/resources/zipTest.zip";
    String destination = "src/test/resources/unzipped";
    String password = "1234qwe";

    @Test
    void zipFileTest() throws ZipException {
        unzipFile(source, destination, password);
    }
}