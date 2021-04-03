package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileHelper {
    private static final String DOWNLOAD_FOLDER = "src/test/resources/downloads";


    public static File downloadFile(SelenideElement element) throws IOException {
        Configuration.downloadsFolder = DOWNLOAD_FOLDER;
        return element.download();
    }

    public static String convertFileToString(File file) throws IOException {
        return FileUtils.readFileToString(file, "UTF-8");
    }

    public static String convertFileToStringUsingPath(String path) throws IOException {
        File file = new File(path);
        return convertFileToString(file);
    }

    public static void unzipFile(String file, String unzipFolder, String password) throws ZipException {
        new ZipFile(file, password.toCharArray()).extractAll(unzipFolder);
    }

    public static String getFilePath(File file) {
        return String.valueOf(file);
    }

    public static String readWordFile(File file) throws IOException {
        if (getFilePath(file).endsWith("doc")) {
            FileInputStream fileInputStream = new FileInputStream(file);
            HWPFDocument document = new HWPFDocument(fileInputStream);
            WordExtractor extractor = new WordExtractor(document);
            return extractor.getText();
        } else if (getFilePath(file).endsWith("docx")) {
            FileInputStream fileInputStream = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(fileInputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            return extractor.getText();
        } else {
            throw new IllegalArgumentException("The specified file is not a Word file");
        }
    }

    public static String readExcelFile(File path) throws IOException {
        if (getFilePath(path).endsWith("xlsx")) {
            String result = "";
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(path));
            XSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
            for (Row row : myExcelSheet) {
                for (Cell cell : row) {
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            result += cell.getStringCellValue() + "=";
                            break;
                        case NUMERIC:
                            result += "[" + cell.getNumericCellValue() + "]";
                            break;
                        case FORMULA:
                            result += "[" + cell.getCellFormula() + "]";
                            break;
                        default:
                            result += cell.toString();
                            break;
                    }
                }
            }
            myExcelBook.close();
            return result;

        } else if (getFilePath(path).endsWith("xls")) {
            String result = "";
            HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(path));
            HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
            for (Row row : myExcelSheet) {
                for (Cell cell : row) {
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            result += cell.getStringCellValue() + "=";
                            break;
                        case NUMERIC:
                            result += "[" + cell.getNumericCellValue() + "]";
                            break;
                        case FORMULA:
                            result += "[" + cell.getCellFormula() + "]";
                            break;
                        case BOOLEAN:
                            result += "[" + cell.getBooleanCellValue() + "]";
                            break;
                        default:
                            result += cell.toString();
                            break;
                    }
                }
            }
            myExcelBook.close();
            return result;

        } else {
            throw new IllegalArgumentException("The specified file is not an Excel file");
        }
    }
}