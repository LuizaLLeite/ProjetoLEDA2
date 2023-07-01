import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class BaseArquivo {
    

    public static void reverseArray(String[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            String temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
    public static void writeCSV(String[][] data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            
            writer.write(",password,length,data,class\n");

            for (String[] row : data) {
                reverseArray(row);

                for (int i = 0; i < row.length; i++) {
                    if (row[i] != null) { 
                        writer.write(row[i]);
                    }
                    if (i != row.length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countRows(String filePath) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count - 1; 
    }

    public static String[][] readCSV(String filePath) {
        int rowCount = countRows(filePath);
        if (rowCount == 0) {
            return null;
        }

        String[][] data = new String[rowCount][];

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); 
            int currentRow = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String[] reversedRow = new String[row.length];
                int columnIndex = 0;
                for (int i = row.length - 1; i >= 0; i--) {
                    String trimmedValue = row[i].trim();
                    if (!trimmedValue.isEmpty()) {
                        reversedRow[columnIndex] = trimmedValue;
                        columnIndex++;
                    }
                }
                data[currentRow] = Arrays.copyOf(reversedRow, columnIndex);
                currentRow++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}