package Principal.modelos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String bibliotecaTx; // Caminho do arquivo de texto

    public FileManager(String bibliotecaTx) {
        this.bibliotecaTx = bibliotecaTx; // Inicializa o caminho do arquivo
    }

    // Método para salvar dados em arquivo de texto
    public void salvarDados(List<String> dados){
        try (PrintWriter writer = new PrintWriter(new FileWriter(bibliotecaTx))){ // Tenta abrir o arquivo para escrita
            for(String linha : dados){
                writer.println(linha); // Escreve cada linha dos dados no arquivo
            }
            System.out.println("Dados salvos com sucesso!");
        }catch (IOException e){
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
    // Método para carregar dados de arquivo de texto
    public List<String> carregarDados(){
        List<String> dados = new ArrayList<>(); // Lista para armazenar os dados lidos
        try (FileInputStream fis = new FileInputStream(bibliotecaTx); // Tenta abrir o arquivo para leitura
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))){
            String linha;
            while ((linha = reader.readLine()) != null){ // Lê cada linha do arquivo
                dados.add(linha);  // Adiciona a linha à lista de dados
            }
        }catch (IOException e){ // Trata possíveis exceções de IO
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
        return dados; // Retorna os dados lidos do arquivo
    }
}
