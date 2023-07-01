package GerarArquivosBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClassificarPasswords {

    public static void classificarArquivo() {
        String caminhoArquivoOriginal = "ProjetoLEDA2//Arquivos-Base//passwords.csv";
        String caminhoArquivoClassificado = "ProjetoLEDA2//Arquivos-Base//password_classifier.csv";
        
        try (BufferedReader lerArquivo = new BufferedReader(new FileReader(caminhoArquivoOriginal));
             BufferedWriter escreverArquivo = new BufferedWriter(new FileWriter(caminhoArquivoClassificado))) {
            
            String linha = lerArquivo.readLine();
            escreverArquivo.write(linha + ",class\n"); 
            
            while (linha != null) {
                linha = lerArquivo.readLine();
                if (linha != null) {
                    String[] dados = linha.split(",");
                    String senha = dados[1];
                    String classificacao = classificarSenha(senha);
                    escreverArquivo.write(linha + "," + classificacao + "\n"); 
                }
            }
            
            System.out.println("Arquivo classificado criado com sucesso.");
            
        } catch (IOException e) {
            System.out.println("Erro ao ler/gravar arquivo: " + e.getMessage());
        }
    }
    public static String classificarSenha(String senha) {
        int tamanho = senha.length();
       
    
        if (tamanho < 5 && (contemApenasLetrasMaiusculas(senha) || contemApenasLetrasMinusculas(senha) || contemApenasNumeros(senha) || contemApenasCaracteresEspeciais(senha))) {
            return "Muito Ruim";
        } else if (tamanho <= 5 && (contemApenasLetrasMaiusculas(senha) || contemApenasLetrasMinusculas(senha) ||contemApenasNumeros(senha) || contemApenasCaracteresEspeciais(senha))){
            return "Ruim";
        }  else if (tamanho <= 7 && contemLetraMaiusculaEMinuscula(senha) && contemNumeros(senha) && contemCaracteresEspeciais(senha)){
            return "Boa";
        } else if (tamanho <= 6 && contemLetraMaiusculaEMinuscula(senha) && (contemNumeros(senha)  ||  contemCaracteresEspeciais(senha))) {
            return "Fraca";
        } else if (tamanho >= 8 &&  contemLetraMaiusculaEMinuscula(senha) && contemNumeros(senha) && contemCaracteresEspeciais(senha)) {
            return "Muito Boa";
        } else {
            return "Sem Classificação";
        }
    }
    
    public static boolean contemApenasLetras(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean contemApenasNumeros(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean contemApenasCaracteresEspeciais(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean contemLetraMaiusculaEMinuscula(String senha) {
        boolean temMaiuscula = false;
        boolean temMinuscula = false;
        
        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            if (Character.isUpperCase(c)) {
                temMaiuscula = true;
            } else if (Character.isLowerCase(c)) {
                temMinuscula = true;
            }
            
            if (temMaiuscula && temMinuscula) {
                return true;
            }
        }
        
        return false;
    }
    public static boolean contemApenasLetrasMinusculas(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean contemApenasLetrasMaiusculas(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            if (!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean contemNumeros(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean contemCaracteresEspeciais(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }
}

