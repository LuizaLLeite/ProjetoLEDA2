import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class MergeSort extends BaseArquivo {

    public static void mergeSort(String[][] dados, int coluna) {
        if (dados.length > 1) {
            int meio = dados.length / 2;
    
            String[][] arrayEsquerdo = Arrays.copyOfRange(dados, 0, meio);
            String[][] arrayDireito = Arrays.copyOfRange(dados, meio, dados.length);
    
            mergeSort(arrayEsquerdo, coluna);
            mergeSort(arrayDireito, coluna);
    
            int i = 0;
            int j = 0;
            int k = 0;
    
            while (i < arrayEsquerdo.length && j < arrayDireito.length) {
                String elemento1 = arrayEsquerdo[i][coluna].trim();
                String elemento2 = arrayDireito[j][coluna].trim();
    
                int valor1 = elemento1.trim().isEmpty() ? 0 : Integer.parseInt(elemento1);
                int valor2 = elemento2.trim().isEmpty() ? 0 : Integer.parseInt(elemento2);
    
                if (valor1 > valor2) {
                    dados[k] = arrayEsquerdo[i];
                    i++;
                } else {
                    dados[k] = arrayDireito[j];
                    j++;
                }
                k++;
            }
    
            while (i < arrayEsquerdo.length) {
                dados[k] = arrayEsquerdo[i];
                i++;
                k++;
            }
    
            while (j < arrayDireito.length) {
                dados[k] = arrayDireito[j];
                j++;
                k++;
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
    
    private static void merge(String[][] dados, int esquerda, int meio, int direita) {
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;
    
        String[][] arrayEsquerdo = new String[n1][];
        String[][] arrayDireito = new String[n2][];
    
        for (int i = 0; i < n1; ++i) {
            arrayEsquerdo[i] = dados[esquerda + i];
        }
        for (int j = 0; j < n2; ++j) {
            arrayDireito[j] = dados[meio + 1 + j];
        }
    
        int i = 0, j = 0;
        int k = esquerda;
        while (i < n1 && j < n2) {
            int mes1 = extrairMes(arrayEsquerdo[i][1]);
            int mes2 = extrairMes(arrayDireito[j][1]);
            if (mes1 >= 1 && mes1 <= 12 && mes2 >= 1 && mes2 <= 12 && mes1 <= mes2) {
                dados[k] = arrayEsquerdo[i];
                i++;
            } else {
                dados[k] = arrayDireito[j];
                j++;
            }
            k++;
        }
    
        while (i < n1) {
            dados[k] = arrayEsquerdo[i];
            i++;
            k++;
        }

    while (j < n2) {dados[k] = arrayDireito[j];
        j++;
        k++;
    }
}

public static void mergeSortMes(String[][] dados, int esquerda, int direita) {
    if (esquerda < direita) {
        int meio = esquerda + (direita - esquerda) / 2;
        mergeSortMes(dados, esquerda, meio);
        mergeSortMes(dados, meio + 1, direita);
        merge(dados, esquerda, meio, direita);
    }
}

   public static void mergeSortData(String[][] dados, int coluna) {
        if (dados.length > 1) {
            int meio = dados.length / 2;

            String[][] arrayEsquerdo = Arrays.copyOfRange(dados, 0, meio);
            String[][] arrayDireito = Arrays.copyOfRange(dados, meio, dados.length);

            mergeSortData(arrayEsquerdo, coluna);
            mergeSortData(arrayDireito, coluna);

            int i = 0;
            int j = 0;
            int k = 0;

            while (i < arrayEsquerdo.length && j < arrayDireito.length) {
                String elemento1 = arrayEsquerdo[i][coluna].trim();
                String elemento2 = arrayDireito[j][coluna].trim();

                if (compareDates(elemento1, elemento2) <= 0) {
                    dados[k] = arrayEsquerdo[i];
                    i++;
                } else {
                    dados[k] = arrayDireito[j];
                    j++;
                }
                k++;
            }

            while (i < arrayEsquerdo.length) {
                dados[k] = arrayEsquerdo[i];
                i++;
                k++;
            }

            while (j < arrayDireito.length) {
                dados[k] = arrayDireito[j];
                j++;
                k++;
            }
        }
    }

    private static int compareDates(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(date1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date2, formatter);
        return dateTime1.compareTo(dateTime2);
    }

////// CAMPO LENGTH //////
public static void medioCaso() {
    String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv"; 
    String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_length_mergeSort_medioCaso.csv"; 
    
    String[][] data = readCSV(arquivoDeEntrada);
    if (data != null) {
        mergeSort(data, 2);
        writeCSV(data, arquivoMedioCaso);
    } else {
        System.out.println("O arquivo de entrada está vazio.");
    }
}
public static void melhorCaso() {
    String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_length_mergeSort_medioCaso.csv"; 
    String arquivoMelhorCaso = "ProjetoLEDA2//Arquivos//passwords_length_mergeSort_melhorCaso.csv";

    String[][] data = readCSV(arquivoMedioCaso);
    if (data != null) {
        mergeSort(data, 2);
        writeCSV(data, arquivoMelhorCaso);
    } else {
        System.out.println("O arquivo de entrada está vazio.");
    }
    
}
public static void piorCaso() {
    String arquivoPiorCaso = "ProjetoLEDA2//Arquivos//passwords_length_mergeSort_piorCaso.csv";
    String arquivoDeEntradaPiorCaso = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_crescente.csv";

    String[][] data = readCSV(arquivoDeEntradaPiorCaso);
    if (data != null) {
        mergeSort(data, 2);
        writeCSV(data, arquivoPiorCaso);
    } else {
        System.out.println("O arquivo de entrada está vazio.");
    }
    
}
    //////CAMPO MÊS////////
    public static void medioCasoMes() {
        
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        mergeSortMes(data, 0, data.length - 1);

        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_month_mergeSort_medioCaso.csv";
        writeCSV(data, arquivoDeSaida);
    }

    public static void melhorCasoMes() {
        
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos//passwords_data_month_mergeSort_medioCaso.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        mergeSortMes(data, 0, data.length - 1);

        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_month_mergeSort_melhorCaso.csv";
        writeCSV(data, arquivoDeSaida);
    }

    public static void piorCasoMes() {

        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_month_decrescente.csv";
        String[][] data = readCSV(arquivoDeEntrada);
        mergeSortMes(data, 0, data.length - 1);

        String arquivoDeSaida = "ProjetoLEDA2//Arquivos//passwords_data_month_mergeSort_piorCaso.csv";
        writeCSV(data, arquivoDeSaida);
    }

    //////CAMPO DATA////////
    public static void medioCasoData() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_data_mergeSort_medioCaso.csv";

        String[][] data = readCSV(arquivoDeEntrada);
        if (data != null) {
             mergeSortData(data, 1); 
            writeCSV(data, arquivoMedioCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }

    public static void melhorCasoData() {
        String arquivoMedioCaso = "ProjetoLEDA2//Arquivos//passwords_data_mergeSort_medioCaso.csv";
        String arquivoMelhorCaso = "ProjetoLEDA2//Arquivos//passwords_data_mergeSort_melhorCaso.csv";

        String[][] data = readCSV(arquivoMedioCaso);
        if (data != null) {
            mergeSortData(data, 1); // Call mergeSortData with the correct arguments
            writeCSV(data, arquivoMelhorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
}


    public static void piorCasoData() {
        String arquivoDeEntrada = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data_decrescente.csv";
        String arquivoPiorCaso = "ProjetoLEDA2//Arquivos//passwords_data_mergeSort_piorCaso.csv";

        String[][] data = readCSV(arquivoDeEntrada);
        if (data != null) {
             mergeSortData(data, 1); 
            writeCSV(data, arquivoPiorCaso);
        } else {
            System.out.println("O arquivo de entrada está vazio.");
        }
    }
    
}
