package GerarArquivosBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TransformarData {
    public static void transformarDataArquivo() {
        SimpleDateFormat dataInicial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dataAtualizada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String caminhoArquivoOriginal = "ProjetoLEDA2//Arquivos-Base//password_classifier.csv";
        File arquivoOriginal = new File(caminhoArquivoOriginal);
        String caminhoArquivoFormatado = "ProjetoLEDA2//Arquivos-Base//passwords_formated_data.csv";

        try (BufferedReader lerArquivo = new BufferedReader(new FileReader(arquivoOriginal))) {
            String primeiraLinha = lerArquivo.readLine();
            escreverPrimeiraLinha(caminhoArquivoFormatado, primeiraLinha);

            String linha = lerArquivo.readLine();
            System.out.println("Criando arquivo.");

            while (linha != null) {
                String[] campos = linha.split(",");
                inverterArray(campos); 

                String data = campos[1];

                if (!data.equals("Sem Classificação")) {
                    campos[1] = dataAtualizada.format(dataInicial.parse(data));
                }

                inverterArray(campos);

                String linhaFormatada = String.join(",", campos);
                try (BufferedWriter escreverArquivo = new BufferedWriter(new FileWriter(caminhoArquivoFormatado, true))) {
                    escreverArquivo.write(linhaFormatada);
                    escreverArquivo.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                linha = lerArquivo.readLine();
            }

            System.out.println("\nArquivo criado com sucesso no caminho: " + caminhoArquivoFormatado);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Erro na análise de data: " + e.getMessage());
        }
    }

    private static void escreverPrimeiraLinha(String caminhoArquivoFormatado, String primeiraLinha) {
        try (BufferedWriter escreverArquivo = new BufferedWriter(new FileWriter(caminhoArquivoFormatado))) {
            escreverArquivo.write(primeiraLinha);
            escreverArquivo.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inverterArray(String[] array) {
        int inicio = 0;
        int fim = array.length - 1;
        while (inicio < fim) {
            String temp = array[inicio];
            array[inicio] = array[fim];
            array[fim] = temp;
            inicio++;
            fim--;
        }
    }
}