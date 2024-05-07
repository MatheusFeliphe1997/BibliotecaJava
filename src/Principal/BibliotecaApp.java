package Principal; // Declaração do pacote para o código

import Principal.modelos.*; // Importa todas as classes do pacote "modelos" dentro do pacote "Principal".

import java.util.ArrayList; // Importa a classe ArrayList da biblioteca Java util para criar listas.
import java.util.Date; // Importa a classe Date da biblioteca Java util para trabalhar com datas.
import java.util.List; // Importa a interface List da biblioteca Java util para definir tipos de lista.
import java.util.Scanner; // Importa a classe Scanner da biblioteca Java util para ler entradas do usuário.

// Classe principal do aplicativo da biblioteca
public class BibliotecaApp {
    // Lista para armazenar objetos da classe Livro
    private static List<Livro> livros = new ArrayList<>();
    // Objeto FileManager para gerenciar arquivos
    private static FileManager fileManager = new FileManager("livros.txt");
    // Lista para armazenar objetos da classe Emprestimo
    private static List<Emprestimo> emprestimos = new ArrayList<>();
    // Lista para armazenar objetos da classe Devolucao
    private static List<Devolucao> devolucoes = new ArrayList<>();

    // Método principal da classe
    public static void main(String[] args) {
        // Carrega dados do arquivo ao iniciar o programa
        carregarDados();
        // Cria um objeto Scanner para ler entradas do usuário
        Scanner scanner = new Scanner(System.in);
        // Variável para controlar o loop do menu
        int opcao;
        // Loop do menu principal
        do {
            // Exibe o menu de opções
            System.out.println("BIBLIOTECA");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Remover livro");
            System.out.println("3. Pesquisar livro");
            System.out.println("4. Listar todos os livros");
            System.out.println("5. Listar livros ordenados");
            System.out.println("6. Empréstimo de livro");
            System.out.println("7. Devolução de livro");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            // Lê a opção do usuário
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Switch para tratar as opções do menu
            switch (opcao) {
                case 1:
                    adicionarLivro(scanner);
                    break;
                case 2:
                    removerLivro(scanner);
                    break;
                case 3:
                    // Implemente a operação de pesquisar livro aqui...
                    break;
                case 4:
                    listarLivros();
                    break;
                case 5:
                    // Implemente a operação de listar livros ordenados aqui...
                    break;
                case 6:
                    emprestarLivro(scanner);
                    break;
                case 7:
                    devolverLivro(scanner);
                    break;
                case 8:
                    salvarDados(); // Salvar dados no arquivo ao sair do programa
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 8);
    }

    // Método para adicionar um livro
    private static void adicionarLivro(Scanner scanner) {
        // Solicita ao usuário informações sobre o livro
        System.out.println("Digite o título do livro:");
        String titulo = scanner.nextLine();

        System.out.println("Digite o nome do autor:");
        String nomeAutor = scanner.nextLine();
        Autor autor = new Autor(nomeAutor); // Cria um novo objeto Autor
        autor.setNome(nomeAutor); // Define o nome do autor usando o método setNome

        System.out.println("Digite o nome do gênero:");
        String nomeGenero = scanner.nextLine();
        Genero genero = new Genero(nomeGenero); // Cria um novo objeto Genero

        System.out.println("Digite o nome da editora:");
        String nomeEditora = scanner.nextLine();
        Editora editora = new Editora(nomeEditora); // Cria um novo objeto Editora

        System.out.println("Digite o ano de publicação:");
        int anoPublicacao = scanner.nextInt();// Lê o ano de publicação do livro
        scanner.nextLine(); // Limpar o buffer do scanner

        // Cria um novo objeto Livro com os dados informados e o adiciona à lista de livros
        Livro livro = new Livro(titulo, autor, genero, editora, anoPublicacao);
        livros.add(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    // Método para remover um livro
    private static void removerLivro(Scanner scanner) {
        System.out.println("Digite o título do livro que deseja remover:");
        String titulo = scanner.nextLine();
        Livro livroRemover = null;
        // Procura o livro na lista de livros pelo título fornecido
        for (Livro livro : livros) {
            // Remove o livro da lista, se encontrado
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livroRemover = livro;
                break;
            }
        }
        if (livroRemover != null) {
            livros.remove(livroRemover);
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    // Método para listar todos os livros
    private static void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
        } else {
            System.out.println("Lista de livros:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    // Método para realizar o empréstimo de um livro
    private static void emprestarLivro(Scanner scanner) {
        System.out.println("Digite o título do livro que deseja emprestar:");
        String titulo = scanner.nextLine();
        Livro livroEmprestar = null;
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livroEmprestar = livro;
                break;
            }
        }
        if (livroEmprestar != null) {
            // Verifica se o livro já está emprestado
            boolean emprestado = false;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getLivro().equals(livroEmprestar)) {
                    emprestado = true;
                    break;
                }
            }
            if (emprestado) {
                System.out.println("Este livro já está emprestado. Por favor, escolha outro livro.");
            } else {
                // Realiza o empréstimo e adiciona a data atual
                Emprestimo emprestimo = new Emprestimo(livroEmprestar, new Date());
                emprestimos.add(emprestimo);
                System.out.println("Livro emprestado com sucesso!");
            }
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    // Método para realizar a devolução de um livro
    private static void devolverLivro(Scanner scanner) {
        System.out.println("Digite o título do livro que deseja devolver:");
        String titulo = scanner.nextLine();
        Livro livroDevolver = null;
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livroDevolver = livro;
                break;
            }
        }
        if (livroDevolver != null) {
            Devolucao devolucao = new Devolucao(livroDevolver, new Date());
            devolucoes.add(devolucao);
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    // Método para salvar os dados dos livros em um arquivo
    private static void salvarDados() {
        List<String> dados = new ArrayList<>();
        for (Livro livro : livros) {
            String linha = livro.getTitulo() + "," +
                    livro.getAutor().getNome() + "," +
                    livro.getGenero().getNome() + "," +
                    livro.getEditora().getNome() + "," +
                    livro.getAnoPublicacao();
            dados.add(linha);
        }
        fileManager.salvarDados(dados);
    }

    // Método para carregar os dados dos livros de um arquivo
    private static void carregarDados() {
        List<String> dados = fileManager.carregarDados(); // Carrega os dados do arquivo usando o objeto FileManager
        for (String linha : dados) { // Percorre as linhas de dados carregadas
            String[] partes = linha.split(",");  // Divide a linha de dados em partes separadas por vírgula
            if (partes.length >= 5) { // Verifica se há pelo menos cinco elementos no array partes
                String titulo = partes[0]; // Obtém o título do livro a partir dos dados carregados
                String nomeAutor = partes[1]; // Obtém o nome do autor
                Autor autor = new Autor(nomeAutor); // Criar um objeto Autor com o nome do autor
                Genero genero = new Genero(partes[2]); // Cria um novo objeto Genero com o nome do gênero
                Editora editora = new Editora(partes[3]); // Cria um novo objeto Editora com o nome da editora
                try {
                    int anoPublicacao = Integer.parseInt(partes[4]); // Tenta converter a string do ano de publicação em um número inteiro
                    Livro livro = new Livro(titulo, autor, genero, editora, anoPublicacao); // Cria um novo objeto Livro com os dados obtidos
                    livros.add(livro); // Adiciona o livro à lista de livros
                } catch (NumberFormatException e) {
                    System.out.println("Ano de publicação inválido para o livro: " + linha); // Informa ao usuário que o ano de publicação é inválido
                }
            } else {
                System.out.println("Dados incompletos para o livro: " + linha); // Informa ao usuário que os dados para o livro são incompletos
            }
        }
    }
}