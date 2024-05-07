package Principal.modelos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String bibliotecaTx;

    public FileManager(String bibliotecaTx) {
        this.bibliotecaTx = bibliotecaTx;
    }

    // Método para salvar dados em arquivo de texto
    public void salvarDados(List<String> dados){
        try (PrintWriter writer = new PrintWriter(new FileWriter(bibliotecaTx))){
            for(String linha : dados){
                writer.println(linha);
            }
            System.out.println("Dados salvos com sucesso!");
        }catch (IOException e){
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
    // Método para carregar dados de arquivo de texto
    public List<String> carregarDados(){
        List<String> dados = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(bibliotecaTx);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))){
            String linha;
            while ((linha = reader.readLine()) != null){
                dados.add(linha);
            }
        }catch (IOException e){
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
        return dados;
    }
}
