import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuickSort extends BaseArquivo {
    
    static class Node {
        String[] rowData;
        Node prev;
        Node next;
        
        public Node(String[] rowData) {
            this.rowData = rowData;
            this.prev = null;
            this.next = null;
        }
    }
    
    private static Node partition(Node low, Node high) {
        String pivoLength = high.rowData[2].trim();
        int pivo = Integer.parseInt(pivoLength);
        Node i = low.prev;
        for (Node j = low; j != high; j = j.next) {
            String elementoLength = j.rowData[2].trim();
            int elemento = Integer.parseInt(elementoLength);
            if (elemento > pivo) {
                i = (i == null) ? low : i.next;
                String[] temp = i.rowData;
                i.rowData = j.rowData;
                j.rowData = temp;
            }
        }
        i = (i == null) ? low : i.next;
        String[] temp = i.rowData;
        i.rowData = high.rowData;
        high.rowData = temp;
        return i;
    }
    
    private static void quickSort(Node low, Node high) {
        if (high != null && low != high && low != high.next) {
            Node partitionIndex = partition(low, high);
            quickSort(low, partitionIndex.prev);
            quickSort(partitionIndex.next, high);
        }
    }
    
    private static int extractMonth(String dateString) {
        String[] partes = dateString.split(" ")[0].split("/");
        if (partes.length >= 2) {
            return Integer.parseInt(partes[1]);
        } else {
            return -1; 
        }
    }
    
    private static Node partitionMes(Node low, Node high) {
        String[] pivo = high.rowData;
        Node i = low.prev;
        for (Node j = low; j != high; j = j.next) {
            String dateString = j.rowData[1].trim();
            int mes = extractMonth(dateString);
            if (mes < extractMonth(pivo[1].trim())) {
                i = (i == null) ? low : i.next;
                String[] temp = i.rowData;
                i.rowData = j.rowData;
                j.rowData = temp;
            }
        }
        i = (i == null) ? low : i.next;
        String[] temp = i.rowData;
        i.rowData = high.rowData;
        high.rowData = temp;
        return i;
    }
    
    private static void quickSortMes(Node low, Node high) {
        if (high != null && low != high && low != high.next) {
            Node pi = partitionMes(low, high);
            quickSortMes(low, pi.prev);
            quickSortMes(pi.next, high);
        }
    }
    
    public static void medioCaso() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
        String arquivoMedioCaso = "LProjetoLEDA2//Arquivos//passwords_length_quickSort_medioCaso.csv";
        
        String[][] data = readCSV(arquivoDeEntrada);
        if (data != null) {
            Node head = new Node(data[0]);
            Node tail = head;
            for (int i = 1; i < data.length; i++) {
                Node newNode = new Node(data[i]);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            
            quickSort(head, tail);
            
            writeCSV(convertToArray(head), arquivoMedioCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    
    public static void melhorCaso() {
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_length_quickSort_medioCaso.csv";
        String arquivoMelhorCaso = "ProjetoLEDA2//Arquivos//passwords_length_quickSort_melhorCaso.csv";
        
        String[][] data = readCSV(arquivoMedioCaso);
        if (data != null) {
            Node head = new Node(data[0]);
            Node tail = head;
            for (int i = 1; i < data.length; i++) {
                Node newNode = new Node(data[i]);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            
            quickSort(head, tail);
            
            writeCSV(convertToArray(head), arquivoMelhorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    
    public static void piorCaso() {
        String arquivoDeEntradaPiorCaso = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_crescente.csv";
        String arquivoPiorCaso = "ProjetoLEDA2//Arquivos//passwords_length_quickSort_piorCaso.csv";
        
        String[][] data = readCSV(arquivoDeEntradaPiorCaso);
        if (data != null) {
            Node head = new Node(data[0]);
            Node tail = head;
            for (int i = 1; i < data.length; i++) {
                Node newNode = new Node(data[i]);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            
            quickSort(head, tail);
            
            writeCSV(convertToArray(head), arquivoPiorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    
    public static void medioCasoMes() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        Node head = new Node(data[0]);
        Node tail = head;
        for (int i = 1; i < data.length; i++) {
            Node newNode = new Node(data[i]);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        
        quickSortMes(head, tail);
        
        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_month_quickSort_medioCaso.csv";
        writeCSV(convertToArray(head), arquivoDeSaida);
    }
    
    public static void melhorCasoMes() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos//passwords_data_month_quickSort_medioCaso.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        Node head = new Node(data[0]);
        Node tail = head;
        for (int i = 1; i < data.length; i++) {
            Node newNode = new Node(data[i]);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        
        quickSortMes(head, tail);
        
        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_month_quickSort_melhorCaso.csv";
        writeCSV(convertToArray(head), arquivoDeSaida);
    }
    
    public static void piorCasoMes() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_month_decrescente.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        Node head = new Node(data[0]);
        Node tail = head;
        for (int i = 1; i < data.length; i++) {
            Node newNode = new Node(data[i]);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        
        quickSortMes(head, tail);
        
        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_month_quickSort_piorCaso.csv";
        writeCSV(convertToArray(head), arquivoDeSaida);
    }
    
    // Helper method to convert the linked list of Nodes to a 2D array
    private static String[][] convertToArray(Node head) {
        int rowCount = getRowCount(head);
        String[][] data = new String[rowCount][];
        
        Node current = head;
        int rowIndex = 0;
        while (current != null) {
            data[rowIndex] = current.rowData;
            current = current.next;
            rowIndex++;
        }
        
        return data;
    }
    
    // Helper method to get the number of rows in the linked list
    private static int getRowCount(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
        public static void quickSortData(Node low, Node high) {
        if (high != null && low != high && low != high.next) {
            Node partitionIndex = partitionData(low, high);
            quickSortData(low, partitionIndex.prev);
            quickSortData(partitionIndex.next, high);
        }
    }

    public static int compareDates(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(date1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date2, formatter);
        return dateTime1.compareTo(dateTime2); // Mantém a comparação original
    }

    private static Node partitionData(Node low, Node high) {
        String pivoDate = high.rowData[1].trim();
        Node i = low.prev;
        for (Node j = low; j != high; j = j.next) {
            String elementDate = j.rowData[1].trim();
            if (compareDates(elementDate, pivoDate) < 0) { // Altera para < 0 para ordenar em ordem crescente
                i = (i == null) ? low : i.next;
                String[] temp = i.rowData;
                i.rowData = j.rowData;
                j.rowData = temp;
            }
        }
        i = (i == null) ? low : i.next;
        String[] temp = i.rowData;
        i.rowData = high.rowData;
        high.rowData = temp;
        return i;
    }

    public static void melhorCasoData() {
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_data_quickSort_medioCaso.csv";
        String arquivoMelhorCaso = "ProjetoLEDA2//Arquivos//passwords_data_quickSort_melhorCaso.csv";

        String[][] data = readCSV(arquivoMedioCaso);
        if (data != null) {
            Node head = new Node(data[0]);
            Node tail = head;
            for (int i = 1; i < data.length; i++) {
                Node newNode = new Node(data[i]);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }

            quickSortData(head, tail);

            writeCSV(convertToArray(head), arquivoMelhorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    public static void medioCasoData() {
         String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_data_quickSort_medioCaso.csv";

        String[][] data = readCSV(arquivoDeEntrada);
        if (data != null) {
            Node head = new Node(data[0]);
            Node tail = head;
            for (int i = 1; i < data.length; i++) {
                Node newNode = new Node(data[i]);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }

            quickSortData(head, tail);

            writeCSV(convertToArray(head), arquivoMedioCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    public static void piorCasoData() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_decrescente.csv";
        String arquivoPiorCaso = "ProjetoLEDA2//Arquivos//passwords_data_quickSort_piorCaso.csv";

        String[][] data = readCSV(arquivoDeEntrada);
        if (data != null) {
            Node head = new Node(data[0]);
            Node tail = head;
            for (int i = 1; i < data.length; i++) {
                Node newNode = new Node(data[i]);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }

            quickSortData(head, tail);

            writeCSV(convertToArray(head), arquivoPiorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }

}
