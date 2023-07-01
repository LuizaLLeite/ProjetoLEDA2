package GerarArquivosBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FiltrarSenhas {

    public static void filtrarArquivo(){
        String caminhoArquivoOriginal = "ProjetoLEDA2//Arquivos-Base//password_classifier.csv";
        String caminhoArquivoFiltrado = "ProjetoLEDA2//Arquivos-Base//passwords_classifier.csv";
        String[] senhasBoas = {"Boa", "Muito Boa"};
        
        try (BufferedReader lerArquivo = new BufferedReader(new FileReader(caminhoArquivoOriginal));
             BufferedWriter escreverArquivo = new BufferedWriter(new FileWriter(caminhoArquivoFiltrado))) {
            
            String linha = lerArquivo.readLine();
            escreverArquivo.write(linha + "\n"); 
            
            while (linha != null) {
                linha = lerArquivo.readLine();
                if (linha != null) {
                    String[] dados = linha.split(",");
                    String classificacao = dados[4];
                    if (classificacao.equals(senhasBoas[0]) || classificacao.equals(senhasBoas[1])) {
                        escreverArquivo.write(linha + "\n");
                    }
                }
            }
            
            System.out.println("Arquivo filtrado criado com sucesso.");
            
        } catch (IOException e) {
            System.out.println("Erro ao ler/gravar arquivo: " + e.getMessage());
        }
    }
}