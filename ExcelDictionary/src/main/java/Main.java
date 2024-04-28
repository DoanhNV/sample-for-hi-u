import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String DATA_FOLDER = "D:\\Projectr\\Hieu\\data";
    private static final String DICTIONARY_FILE_PATH = DATA_FOLDER + "\\dictionaries.xlsx";
    private static final String MEDIA_FILE_FOLDER = DATA_FOLDER + "\\media";

    public static void main(String[] args) {
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readData() throws IOException {
        FileInputStream file = new FileInputStream(new File(DICTIONARY_FILE_PATH));
        Workbook workbook = new XSSFWorkbook(file);


        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        data.get(i).add(cell.getRichStringCellValue().getString());
                        break;
                    default:
                        data.get(i).add(" ");
                }
            }
            i++;
        }

        printData(data);
    }

    private static void printData(Map<Integer, List<String>> data) {
        data.forEach((row, values) -> {
            System.out.println("row: " + row);
            System.out.println("values: " + values);
        });
    }
}
