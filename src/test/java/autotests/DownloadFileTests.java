package autotests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.FileHelper.convertFileToString;
import static helpers.FileHelper.downloadFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class DownloadFileTests {
    SelenideElement file = $("#raw-url");

    @BeforeAll
    static void openUrl() {
        open("https://github.com/selenide/selenide/blob/master/README.md");
    }

    @Test
    void downloadFileTests() throws IOException {
        String fileData = convertFileToString(downloadFile(file));
        //Hamcrest matcher
        assertThat(fileData, containsString("Selenide = UI Testing Framework powered by Selenium WebDriver"));
    }
}