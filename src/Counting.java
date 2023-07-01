import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Counting extends BaseArquivo {
    
    static class Node {
        String[] data;
        Node next;
        
        public Node(String[] data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public static void countingSort(String[][] dados) {
        int n = dados.length;
        int maximo = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            String elemento = dados[i][2].trim();
            int valor = Integer.parseInt(elemento);
            if (valor > maximo) {
                maximo = valor;
            }
        }
        
        int[] contagem = new int[maximo + 1];
        for (int i = 0; i < n; i++) {
            String elemento = dados[i][2].trim();
            int valor = Integer.parseInt(elemento);
            contagem[maximo - valor]++;
        }
        
        for (int i = 1; i <= maximo; i++) {
            contagem[i] += contagem[i - 1];
        }
        
        Node[] dadosOrdenados = new Node[n];
        for (int i = n - 1; i >= 0; i--) {
            String elemento = dados[i][2].trim();
            int valor = Integer.parseInt(elemento);
            int indice = contagem[maximo - valor]--;
            
            Node node = new Node(dados[i]);
            dadosOrdenados[indice - 1] = node;
        }
        
        Node currentNode = null;
        for (int i = 0; i < n; i++) {
            Node node = dadosOrdenados[i];
            if (currentNode == null) {
                currentNode = node;
                dados[i] = currentNode.data;
            } else {
                currentNode.next = node;
                currentNode = currentNode.next;
                dados[i] = currentNode.data;
            }
        }
    }
    
    private static int extrairMes(String dataString) {
        String[] partes = dataString.split(" ")[0].split("/");
        if (partes.length >= 2) {
            return Integer.parseInt(partes[1]);
        } else {
            return -1;
        }
    }
    
    public static void countingSortMes(String[][] dados) {
        int n = dados.length;
        int maximo = 12;
        
        int[] contagem = new int[maximo + 1];
        for (int i = 0; i < n; i++) {
            if (dados[i] != null && dados[i].length > 1 && dados[i][1] != null) {
                String dataString = dados[i][1].trim();
                int mes = extrairMes(dataString);
                if (mes >= 1 && mes <= maximo) {
                    contagem[mes]++;
                }
            }
        }
        
        for (int i = 1; i <= maximo; i++) {
            contagem[i] += contagem[i - 1];
        }
        
        Node[] dadosOrdenados = new Node[n];
        for (int i = n - 1; i >= 0; i--) {
            if (dados[i] != null && dados[i].length > 1 && dados[i][1] != null) {
                String dataString = dados[i][1].trim();
                int mes = extrairMes(dataString);
                if (mes >= 1 && mes <= maximo) {
                    int indice = contagem[mes]--;
                    Node node = new Node(dados[i]);
                    dadosOrdenados[indice - 1] = node;
                }
            }
        }
        
        Node currentNode = null;
        for (int i = 0; i < n; i++) {
            Node node = dadosOrdenados[i];
            if (currentNode == null) {
                currentNode = node;
                dados[i] = currentNode.data;
            } else {
                currentNode.next = node;
                currentNode = currentNode.next;
                dados[i] = currentNode.data;
            }
        }
    }
        public static void countingSortData(String[][] dados) {
        int n = dados.length;
        
        // Encontra a data mínima e a data máxima
        String dataMinima = dados[0][1].trim();
        String dataMaxima = dados[0][1].trim();
        
        for (int i = 1; i < n; i++) {
            String data = dados[i][1].trim();
            if (compararDatas(data, dataMinima) < 0) {
                dataMinima = data;
            }
            if (compararDatas(data, dataMaxima) > 0) {
                dataMaxima = data;
            }
        }
        
        // Converte as datas em números inteiros
        int dataMinimaInt = converterDataParaInt(dataMinima);
        int dataMaximaInt = converterDataParaInt(dataMaxima);
        
        // Determina o tamanho do array de contagem
        int tamanhoContagem = dataMaximaInt - dataMinimaInt + 1;
        
        // Inicializa o array de contagem
        int[] contagem = new int[tamanhoContagem];
        
        // Conta a ocorrência de cada valor de data
        for (int i = 0; i < n; i++) {
            String data = dados[i][1].trim();
            int dataInt = converterDataParaInt(data);
            contagem[dataInt - dataMinimaInt]++;
        }
        
        // Atualiza o array de contagem com as posições finais dos valores ordenados
        for (int i = 1; i < tamanhoContagem; i++) {
            contagem[i] += contagem[i - 1];
        }
        
        // Cria um array auxiliar para armazenar os dados ordenados
        String[][] dadosOrdenados = new String[n][dados[0].length];
        
        // Preenche o array de dados ordenados com base nas posições finais dos valores
        for (int i = n - 1; i >= 0; i--) {
            String data = dados[i][1].trim();
            int dataInt = converterDataParaInt(data);
            int indice = contagem[dataInt - dataMinimaInt]--;
            dadosOrdenados[indice - 1] = dados[i];
        }
        
        // Copia os dados ordenados de volta para o array original
        System.arraycopy(dadosOrdenados, 0, dados, 0, n);
    }

    private static int converterDataParaInt(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dataRef = LocalDateTime.parse("01/01/2000 00:00:00", formatter);
        LocalDateTime dataConvertida = LocalDateTime.parse(data, formatter);
        return (int) ChronoUnit.SECONDS.between(dataRef, dataConvertida);
    }

    private static int compararDatas(String data1, String data2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(data1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(data2, formatter);
        return dateTime1.compareTo(dateTime2);
    }
    
    ////// CAMPO LENGTH //////
    public static void medioCaso() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_length_counting_medioCaso.csv";
        
        String[][] data = readCSV(arquivoDeEntrada);
        if (data != null) {
            countingSort(data);
            writeCSV(data, arquivoMedioCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    
    public static void melhorCaso() {
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_length_counting_medioCaso.csv";
        String arquivoMelhorCaso = "ProjetoLEDA2//Arquivos//passwords_length_counting_melhorCaso.csv";
        
        String[][] data = readCSV(arquivoMedioCaso);
        if (data != null) {
            countingSort(data);
            writeCSV(data, arquivoMelhorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    
    public static void piorCaso() {
        String arquivoPiorCaso = "ProjetoLEDA2//Arquivos//passwords_length_counting_piorCaso.csv";
        String arquivoDeEntradaPiorCaso = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_crescente.csv";
        
        String[][] data = readCSV(arquivoDeEntradaPiorCaso);
        if (data != null) {
            countingSort(data);
            writeCSV(data, arquivoPiorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    
    ////// CAMPO MÊS //////
    public static void medioCasoMes() {
        
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        countingSortMes(data);
        
        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_month_counting_medioCaso.csv";
        writeCSV(data, arquivoDeSaida);
        
    }
    
    public static void melhorCasoMes() {
        
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos//passwords_data_month_counting_medioCaso.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        countingSortMes(data);
        
        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_month_counting_melhorCaso.csv";
        writeCSV(data, arquivoDeSaida);
        
    }

    public static void piorCasoMes() {
        
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_month_decrescente.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        countingSortMes(data);
        
        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_month_counting_piorCaso.csv";
        writeCSV(data, arquivoDeSaida);
        
    }

    ////// CAMPO DATA //////
    public static void medioCasoData() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        countingSortData(data);

        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_counting_medioCaso.csv";
        writeCSV(data, arquivoDeSaida);
    }

    public static void melhorCasoData() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos//passwords_data_counting_medioCaso.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        countingSortData(data);

        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_counting_melhorCaso.csv";
        writeCSV(data, arquivoDeSaida);
    }

    public static void piorCasoData() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_decrescente.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        countingSortData(data);

        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_counting_piorCaso.csv";
        writeCSV(data, arquivoDeSaida);
    }
}