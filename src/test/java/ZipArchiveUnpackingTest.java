import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipArchiveUnpackingTest {
    private final ClassLoader cl = ZipArchiveUnpackingTest.class.getClassLoader();

    @Test
    @DisplayName("Чтение и проверка PDF-файла")
    public void pdfFileUnpacking() throws Exception {
        boolean pdfFound = false;
        try (InputStream is = cl.getResourceAsStream("archive.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".pdf")) {
                    pdfFound = true;
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("Пример документа в формате PDF"));
                }
            }
            assertThat(pdfFound).isTrue();
        }
    }

    @Test
    @DisplayName("Чтение и проверка XLS-файла")
    public void xlsFileUnpacking() throws Exception {
        boolean xlsFound = false;
        try (InputStream is = cl.getResourceAsStream("archive.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".xlsx")) {
                    xlsFound = true;
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                    Assertions.assertEquals("строка для проверки", actualValue);
                }
            }
            assertThat(xlsFound).isTrue();
        }
    }

        @Test
        @DisplayName("Чтение и проверка CSV-файла")
        void csvFileUnpacking() throws Exception {
            boolean csvFound = false;
            try (ZipInputStream zis = new ZipInputStream(
                    cl.getResourceAsStream("archive.zip")
            )) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    if (zipEntry.getName().endsWith(".csv")) {
                        csvFound = true;
                        CSVReader csv = new CSVReader(new InputStreamReader(zis));
                        List<String[]> csvStrings = csv.readAll();
                        Assertions.assertEquals(2, csvStrings.size());
                        Assertions.assertArrayEquals(
                                new String[]{"Тестовые данные для java-тестов"},
                                csvStrings.get(0)
                        );
                        Assertions.assertArrayEquals(
                                new String[]{"Другие тестовые данные"},
                                csvStrings.get(1)
                        );
                    }
                }
                assertThat(csvFound).isTrue();
            }
        }
    }


