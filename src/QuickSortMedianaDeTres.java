import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuickSortMedianaDeTres extends BaseArquivo {

    public static void quickSort(String[][] dados, int baixo, int alto) {
        if (baixo < alto) {
            int indicePivo = particionar(dados, baixo, alto);
            quickSort(dados, baixo, indicePivo - 1);
            quickSort(dados, indicePivo + 1, alto);
        }
    }
    
    public static int particionar(String[][] dados, int baixo, int alto) {
        int meio = baixo + (alto - baixo) / 2;
        int indicePivo = medianaDeTres(dados, baixo, meio, alto);
        String[] pivo = dados[indicePivo];
        trocar(dados, indicePivo, alto);
    
        int i = baixo;
        for (int j = baixo; j < alto; j++) {
            if (comparar(dados[j], pivo) > 0) {
                trocar(dados, i, j);
                i++;
            }
        }
    
        trocar(dados, i, alto);
        return i;
    }
    
    public static int medianaDeTres(String[][] dados, int a, int b, int c) {
        String[] elementoA = dados[a];
        String[] elementoB = dados[b];
        String[] elementoC = dados[c];
    
        int compararAB = comparar(elementoA, elementoB);
        int compararAC = comparar(elementoA, elementoC);
        int compararBC = comparar(elementoB, elementoC);
    
        if (compararAB > 0) {
            if (compararAC > 0) {
                return compararBC > 0 ? b : c;
            } else {
                return a;
            }
        } else {
            if (compararAC > 0) {
                return a;
            } else {
                return compararBC > 0 ? c : b;
            }
        }
    }
    
    public static int comparar(String[] a, String[] b) {
        String elemento1 = a[2].trim();
        String elemento2 = b[2].trim();
        int valor1 = Integer.parseInt(elemento1);
        int valor2 = Integer.parseInt(elemento2);
        return Integer.compare(valor1, valor2);
    }
    
    public static void trocar(String[][] dados, int i, int j) {
        String[] temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }
    
    
    private static int extrairMes(String dataString) {

        String[] partes = dataString.split(" ")[0].split("/");
        if (partes.length >= 2) {
            return Integer.parseInt(partes[1]);
        } else {
            return -1; 
        }
    }
    
    public static void quickSortMes(String[][] dados, int esquerda, int direita) {
        if (esquerda < direita) {
            int indicePivo = particionarMes(dados, esquerda, direita);
            quickSortMes(dados, esquerda, indicePivo - 1);
            quickSortMes(dados, indicePivo + 1, direita);
        }
    }
    private static int particionarMes(String[][] dados, int esquerda, int direita) {
        int meio = (esquerda + direita) / 2;
        int indicePivo = medianaDeTresMes(dados, esquerda, meio, direita);
    
        String[] pivo = dados[indicePivo];
        trocar(dados, indicePivo, direita);
    
        int i = esquerda;
        for (int j = esquerda; j < direita; j++) {
            if (compararMeses(dados[j][1], pivo[1]) <= 0) {
                trocar(dados, i, j);
                i++;
            }
        }
    
        trocar(dados, i, direita);
        return i;
    }

    private static int medianaDeTresMes(String[][] dados, int esquerda, int meio, int direita) {
    if (compararMeses(dados[esquerda][1], dados[meio][1]) > 0) {
    trocar(dados, esquerda, meio);
    }
    if (compararMeses(dados[esquerda][1], dados[direita][1]) > 0) {
    trocar(dados, esquerda, direita);
    }
    if (compararMeses(dados[meio][1], dados[direita][1]) > 0) {
    trocar(dados, meio, direita);
    }
    return meio;
    }
    
    private static int compararMeses(String dataString1, String dataString2) {
    int mes1 = extrairMes(dataString1);
    int mes2 = extrairMes(dataString2);
    return Integer.compare(mes1, mes2);
    }
    public static void quickSortData(String[][] dados) {
    quickSortData(dados, 0, dados.length - 1);
    }

    private static void quickSortData(String[][] dados, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(dados, left, right);
            quickSortData(dados, left, pivotIndex - 1);
            quickSortData(dados, pivotIndex + 1, right);
        }
    }

    private static int partition(String[][] dados, int left, int right) {
        int pivotIndex = getPivotIndex(dados, left, right);
        String[] pivotValue = dados[pivotIndex];
        swap(dados, pivotIndex, right);
        int storeIndex = left;
        
        for (int i = left; i < right; i++) {
            String elemento1 = dados[i][1].trim();
            String elemento2 = pivotValue[1].trim();
            
            if (compararDatas(elemento1, elemento2) < 0) {
                swap(dados, i, storeIndex);
                storeIndex++;
            }
        }
        
        swap(dados, storeIndex, right);
        return storeIndex;
    }

    private static int getPivotIndex(String[][] dados, int left, int right) {
        int mid = left + (right - left) / 2;
        String[] leftValue = dados[left];
        String[] midValue = dados[mid];
        String[] rightValue = dados[right];
        
        String elemento1 = leftValue[1].trim();
        String elemento2 = midValue[1].trim();
        String elemento3 = rightValue[1].trim();
        
        if (compararDatas(elemento1, elemento2) < 0) {
            if (compararDatas(elemento2, elemento3) < 0) {
                return mid;
            } else if (compararDatas(elemento1, elemento3) < 0) {
                return right;
            } else {
                return left;
            }
        } else {
            if (compararDatas(elemento1, elemento3) < 0) {
                return left;
            } else if (compararDatas(elemento2, elemento3) < 0) {
                return right;
            } else {
                return mid;
            }
        }
    }

    private static void swap(String[][] dados, int i, int j) {
        String[] temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }

    private static int compararDatas(String data1, String data2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(data1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(data2, formatter);
        return dateTime1.compareTo(dateTime2);
    }


/////// CAMPO LENGTH /////
    public static void medioCaso() {
        String arquivoDeEntrdada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv"; 
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_length_quickSortMedianaDeTres_medioCaso.csv"; 

        String[][] data = readCSV(arquivoDeEntrdada);
        if (data != null) {
            quickSort(data, 0, data.length - 1);
            writeCSV(data, arquivoMedioCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    public static void melhorCaso() {
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_length_quickSortMedianaDeTres_medioCaso.csv"; 
        String arquivoMelhorCaso = "ProjetoLEDA2//Arquivos//passwords_length_quickSortMedianaDeTres_melhorCaso.csv";

        String[][] data = readCSV(arquivoMedioCaso);
        if (data != null) {
            quickSort(data, 0, data.length - 1);
            writeCSV(data, arquivoMelhorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    public static void piorCaso() {
        String arquivoDeEntrdadaPiorCaso = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_crescente.csv"; 
        String arquivoPiorCaso = "ProjetoLEDA2//Arquivos//passwords_length_quickSortMedianaDeTres_piorCaso.csv"; 
        

        String[][] data = readCSV(arquivoDeEntrdadaPiorCaso);
        if (data != null) {
            quickSort(data, 0, data.length - 1);
            writeCSV(data, arquivoPiorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    //////CAMPO MÊS ///////
    public static void medioCasoMes() {
        
        String filePath = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
        String[][] data = readCSV(filePath);
        quickSortMes(data, 0, data.length - 1);

        String outputFileName = "ProjetoLEDA2//Arquivos//passwords_data_month_quickSortMedianaDeTres_medioCaso.csv";
        writeCSV(data, outputFileName);
    }

    public static void melhorCasoMes() {
    
        String filePath = "ProjetoLEDA2//Arquivos//passwords_data_month_quickSortMedianaDeTres_medioCaso.csv";
        String[][] data = readCSV(filePath);
        quickSortMes(data, 0, data.length - 1);

        String outputFileName = "ProjetoLEDA2//Arquivos//passwords_data_month_quickSortMedianaDeTres_melhorCaso.csv";
        writeCSV(data, outputFileName);
    }
    public static void piorCasoMes() {
    
        String filePath = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_month_decrescente.csv";
        String[][] data = readCSV(filePath);
        quickSortMes(data, 0, data.length - 1);

        String outputFileName = "ProjetoLEDA2//Arquivos//passwords_data_month_quickSortMedianaDeTres_piorCaso.csv";
        writeCSV(data, outputFileName);
    }
    //////CAMPO DATA//////
    public static void medioCasoData() {
        String arquivoDeEntrdada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv"; 
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_data_quickSortMedianaDeTres_medioCaso.csv"; 
        
        String[][] data = readCSV(arquivoDeEntrdada);
        if (data != null) {
            quickSortData(data);
            writeCSV(data, arquivoMedioCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    public static void melhorCasoData() {
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_data_quickSortMedianaDeTres_medioCaso.csv"; 
        String arquivoMelhorCaso = "ProjetoLEDA2//Arquivos//passwords_data_quickSortMedianaDeTres_melhorCaso.csv";

        String[][] data = readCSV(arquivoMedioCaso);
        if (data != null) {
            quickSortData(data);
            writeCSV(data, arquivoMelhorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
        
    }
    public static void piorCasoData() {
        String arquivoPiorCaso = "ProjetoLEDA2//Arquivos//passwords_data_quickSortMedianaDeTres_piorCaso.csv";
        String arquivoDeEntrdadaPiorCaso = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_decrescente.csv";

        String[][] data = readCSV(arquivoDeEntrdadaPiorCaso);
        if (data != null) {
            quickSortData(data);
            writeCSV(data, arquivoPiorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
        
    }

}
