package GerarArquivosBase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class OrdenarArquivosAux {
   
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

    public static void quickSort(String[][] data, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(data, low, high);
            quickSort(data, low, partitionIndex - 1);
            quickSort(data, partitionIndex + 1, high);
        }
    }

    public static int partition(String[][] data, int low, int high) {
        String pivotLength = data[high][2].trim();
        int pivot = Integer.parseInt(pivotLength);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            String elementLength = data[j][2].trim();
            int element = Integer.parseInt(elementLength);
            if (element < pivot) {
                i++;
                String[] temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        String[] temp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = temp;
        return i + 1;
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

private static int extractMonth(String dateString) {
    
    String[] parts = dateString.split(" ")[0].split("/");
    if (parts.length >= 2) {
        return Integer.parseInt(parts[1]);
    } else {
        return -1; 
    }
}

private static void heapify(String[][] data, int n, int i, int monthIndex) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < n && extractMonth(data[left][monthIndex]) < extractMonth(data[largest][monthIndex])) {
        largest = left;
    }

    if (right < n && extractMonth(data[right][monthIndex]) < extractMonth(data[largest][monthIndex])) {
        largest = right;
    }

    if (largest != i) {
        String[] temp = data[i];
        data[i] = data[largest];
        data[largest] = temp;

        heapify(data, n, largest, monthIndex);
    }
}

public static void heapSortMesDecrescente(String[][] data) {
    int n = data.length;
    int monthIndex = 1;
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(data, n, i, monthIndex);
    }

   
    for (int i = n - 1; i >= 0; i--) {
        String[] temp = data[0];
        data[0] = data[i];
        data[i] = temp;

        heapify(data, i, 0, monthIndex);
    }
}

public static void arquivoMes() {
    String filePath = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
    String[][] data = readCSV(filePath); 
    heapSortMesDecrescente(data); 
    String outputFileName = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_month_decrescente.csv";

    
    writeCSV(data, outputFileName);
}
public static void arquivoLength() {
    String inputFilePath = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
    String outputFilePath = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_crescente.csv"; 

    String[][] data = readCSV(inputFilePath);
    if (data != null) {
        quickSort(data, 0, data.length - 1);
        writeCSV(data, outputFilePath);
    } else {
        System.out.println("O arquivo de entrada está vazio.");
    }
}

 private static int compararDatas(String data1, String data2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(data1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(data2, formatter);
        return dateTime1.compareTo(dateTime2);
    }

    public static void heapSortDataCrescente(String[][] data) {
        int n = data.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyData(data, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            String[] temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            heapifyData(data, i, 0);
        }
    }

    public static void heapSortDataDecrescente(String[][] data) {
        int n = data.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDataDecrescente(data, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            String[] temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            heapifyDataDecrescente(data, i, 0);
        }
    }

    private static void heapifyData(String[][] data, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compararDatas(data[left][1].trim(), data[largest][1].trim()) > 0) {
            largest = left;
        }

        if (right < n && compararDatas(data[right][1].trim(), data[largest][1].trim()) > 0) {
            largest = right;
        }

        if (largest != i) {
            String[] temp = data[i];
            data[i] = data[largest];
            data[largest] = temp;
            heapifyData(data, n, largest);
        }
    }

    private static void heapifyDataDecrescente(String[][] data, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compararDatas(data[left][1].trim(), data[largest][1].trim()) < 0) {
            largest = left;
        }

        if (right < n && compararDatas(data[right][1].trim(), data[largest][1].trim()) < 0) {
            largest = right;
        }

        if (largest != i) {
            String[] temp = data[i];
            data[i] = data[largest];
            data[largest] = temp;
            heapifyDataDecrescente(data, n, largest);
        }
    }
    public static void arquivoData() {
    String filePath = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
    String[][] data = readCSV(filePath); 
    heapSortDataDecrescente(data); 

    // Escrever o arquivo de saída com as datas ordenadas
    String outputFileName = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_decrescente.csv";
    writeCSV(data, outputFileName);
}

}