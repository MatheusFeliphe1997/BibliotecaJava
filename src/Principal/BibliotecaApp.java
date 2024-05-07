package Principal;

import Principal.modelos.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class BibliotecaApp {
    private static List<Livro> livros = new ArrayList<>();
    private static FileManager fileManager = new FileManager("livros.txt");
    private static List<Emprestimo> emprestimos = new ArrayList<>();
    private static List<Devolucao> devolucoes = new ArrayList<>();

    public static void main(String[] args) {
        carregarDados(); // Carregar dados do arquivo ao iniciar o programa
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
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
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

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

    private static void adicionarLivro(Scanner scanner) {
        System.out.println("Digite o título do livro:");
        String titulo = scanner.nextLine();

        System.out.println("Digite o nome do autor:");
        String nomeAutor = scanner.nextLine();
        Autor autor = new Autor(); // Cria um novo objeto Autor
        autor.setNome(nomeAutor); // Define o nome do autor usando o método setNome

        System.out.println("Digite o nome do gênero:");
        String nomeGenero = scanner.nextLine();
        Genero genero = new Genero(nomeGenero);

        System.out.println("Digite o nome da editora:");
        String nomeEditora = scanner.nextLine();
        Editora editora = new Editora(nomeEditora);

        Livro livro = new Livro(titulo, autor, genero, editora);
        livros.add(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    private static void removerLivro(Scanner scanner) {
        System.out.println("Digite o título do livro que deseja remover:");
        String titulo = scanner.nextLine();
        Livro livroRemover = null;
        for (Livro livro : livros) {
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
    private static void salvarDados() {
        List<String> dados = new ArrayList<>();
        for (Livro livro : livros) {
            String linha = livro.getTitulo() + "," +
                    livro.getAutor().getNome() + "," +
                    livro.getGenero().getNome() + "," +
                    livro.getEditora().getNome();
            dados.add(linha);
        }
        fileManager.salvarDados(dados);
    }

    private static void carregarDados() {
        List<String> dados = fileManager.carregarDados(); // Aqui estamos carregando os dados do arquivo
        for (String linha : dados) {
            String[] partes = linha.split(",");
            String titulo = partes[0];
            Autor autor = new Autor(); // Criar um objeto Autor vazio
            autor.setNome(partes[1]); // Definir o nome do autor usando o método setNome
            Genero genero = new Genero(partes[2]);
            Editora editora = new Editora(partes[3]);
            Livro livro = new Livro(titulo, autor, genero, editora);
            livros.add(livro);
        }
    }
}