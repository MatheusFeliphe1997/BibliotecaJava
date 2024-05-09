package Principal;

import Principal.modelos.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("8. Listar todos os livros emprestados");
            System.out.println("9. Sair");
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
                    pesquisarLivro(scanner);
                    break;
                case 4:
                    listarLivros();
                    break;
                case 5:
                    listarLivrosOrdenados();
                    break;
                case 6:
                    emprestarLivro(scanner);
                    break;
                case 7:
                    devolverLivro(scanner);
                    break;
                case 8:
                    listarLivrosEmprestados();
                    break;
                case 9:
                    salvarDados(); // Salvar dados no arquivo ao sair do programa
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }

    // Método para adicionar um livro
    private static void adicionarLivro(Scanner scanner) {
        // Solicita ao usuário informações sobre o livro
        System.out.println("Digite o título do livro:");
        String titulo = scanner.nextLine();

        System.out.println("Digite o nome do autor:");
        String nomeAutor = scanner.nextLine();
        Autor autor = new Autor(nomeAutor);

        System.out.println("Digite o nome do gênero:");
        String nomeGenero = scanner.nextLine();
        Genero genero = new Genero(nomeGenero);

        System.out.println("Digite o nome da editora:");
        String nomeEditora = scanner.nextLine();
        Editora editora = new Editora(nomeEditora);

        System.out.println("Digite o ano de publicação:");
        int anoPublicacao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Livro livro = new Livro(titulo, autor, genero, editora, anoPublicacao);
        livros.add(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    // Método para remover um livro
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
            System.out.println("Lista de livros para remover:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private static void pesquisarLivro(Scanner scanner) {
        System.out.println("Digite o título do livro que deseja pesquisar:");
        String tituloPesquisa = scanner.nextLine().toLowerCase();

        boolean encontrado = false;
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(tituloPesquisa)) {
                System.out.println("Livro encontrado:");
                System.out.println(livro);
                encontrado = true;
            }
        }

        if (!encontrado) {
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

    private static void listarLivrosEmprestados() {
        if (emprestimos.isEmpty()) {
            System.out.println("Não há livros emprestados.");
        } else {
            System.out.println("Lista de livros emprestados:");
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println("Livro: " + emprestimo.getLivro().getTitulo() + " | Data do empréstimo: " + emprestimo.getDataEmprestimo());
            }
        }
    }

    // Método para listar todos os livros ordenados
    private static void listarLivrosOrdenados() {
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
        } else {
            Collections.sort(livros, Comparator.comparing(Livro::getTitulo));
            System.out.println("Lista de livros ordenados por título:");
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
                Emprestimo emprestimo = new Emprestimo(livroEmprestar, new Date());
                emprestimos.add(emprestimo);
                System.out.println("Livro emprestado com sucesso!");
            }
        } else {
            System.out.println("Livro não encontrado.");
            System.out.println("Lista de livros para emprestar.");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
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
            boolean encontrado = false;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getLivro().equals(livroDevolver)) {
                    emprestimos.remove(emprestimo);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Este livro não está emprestado no momento.");
            } else {
                Devolucao devolucao = new Devolucao(livroDevolver, new Date());
                devolucoes.add(devolucao);
                System.out.println("Livro devolvido com sucesso!");
            }
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    // Método para processar empréstimos
    private static void processarEmprestimo(String tituloLivro, long timestamp) {
        Livro livro = buscarLivroPorTitulo(tituloLivro);
        if (livro != null) {
            Emprestimo emprestimo = new Emprestimo(livro, new Date(timestamp));
            emprestimos.add(emprestimo);
        } else {
            System.out.println("Livro não encontrado para empréstimo: " + tituloLivro);
        }
    }

    // Método para processar devoluções
    private static void processarDevolucao(String tituloLivro, long timestamp) {
        Livro livro = buscarLivroPorTitulo(tituloLivro);
        if (livro != null) {
            // Verificar se o livro já está na lista de devoluções
            boolean encontrado = false;
            for (Devolucao devolucao : devolucoes) {
                if (devolucao.getLivro().getTitulo().equalsIgnoreCase(tituloLivro)) {
                    encontrado = true;
                    break;
                }
            }
            // Se o livro já estiver na lista de devoluções, apenas remova o empréstimo correspondente
            if (encontrado) {
                Emprestimo emprestimoRemover = null;
                for (Emprestimo emprestimo : emprestimos) {
                    if (emprestimo.getLivro().getTitulo().equalsIgnoreCase(tituloLivro)) {
                        emprestimoRemover = emprestimo;
                        break;
                    }
                }
                if (emprestimoRemover != null) {
                    emprestimos.remove(emprestimoRemover);
                }
            } else {
                // Se o livro não estiver na lista de devoluções, adicione a devolução
                Devolucao devolucao = new Devolucao(livro, new Date(timestamp));
                devolucoes.add(devolucao);
            }
        } else {
            System.out.println("Livro não encontrado para devolução: " + tituloLivro);
        }
    }
    // Método auxiliar para buscar um livro pelo título
    private static Livro buscarLivroPorTitulo(String tituloLivro) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
                return livro;
            }
        }
        return null;
    }

    // Método para salvar os dados dos livros, empréstimos e devoluções em um arquivo
    private static void salvarDados() {
        List<String> dados = new ArrayList<>();
        // Salvar dados dos livros
        for (Livro livro : livros) {
            String linha = livro.getTitulo() + "," +
                    livro.getAutor().getNome() + "," +
                    livro.getGenero().getNome() + "," +
                    livro.getEditora().getNome() + "," +
                    livro.getAnoPublicacao();
            dados.add(linha);
        }
        // Salvar dados dos empréstimos

        for (Emprestimo emprestimo : emprestimos) {
            String linha = emprestimo.getLivro().getTitulo() + "," +
                    "Emprestado dia: " + emprestimo.getDataEmprestimo().getTime();
            dados.add(linha);
        }

        // Salvar dados das devoluções
        for (Devolucao devolucao : devolucoes) {
            String linha = devolucao.getLivro().getTitulo() + "," +
                    "Devolvido dia: " + devolucao.getDataDevolucao().getTime();
            dados.add(linha);
        }

        fileManager.salvarDados(dados);
    }


    // Método para carregar os dados dos livros, empréstimos e devoluções
    private static void carregarDados() {
        List<String> dados = fileManager.carregarDados();

        for (String linha : dados) {
            String[] partes = linha.split(",");
            if (partes.length >= 5) {
                // Carregar dados dos livros
                String titulo = partes[0];
                String nomeAutor = partes[1];
                String nomeGenero = partes[2];
                String nomeEditora = partes[3];
                int anoPublicacao = Integer.parseInt(partes[4]);
                Autor autor = new Autor(nomeAutor);
                Genero genero = new Genero(nomeGenero);
                Editora editora = new Editora(nomeEditora);
                Livro livro = new Livro(titulo, autor, genero, editora, anoPublicacao);
                livros.add(livro);
            } else if (partes.length == 2) {
                // Carregar dados dos empréstimos e devoluções
                String tituloLivro = partes[0].trim();
                String dataString = partes[1].trim();
                long timestamp;
                if (dataString.startsWith("Emprestado dia:")) {
                    timestamp = Long.parseLong(dataString.replace("Emprestado dia:", "").trim());
                    processarEmprestimo(tituloLivro, timestamp);
                } else if (dataString.startsWith("Devolvido dia:")) {
                    timestamp = Long.parseLong(dataString.replace("Devolvido dia:", "").trim());
                    processarDevolucao(tituloLivro, timestamp);
                } else {
                    System.out.println("Formato de data inválido: " + dataString);
                    continue; // Pular para a próxima linha de dados
                }
            } else {
                System.out.println("Dados incompletos para linha: " + linha);
            }
        }
    }




}
