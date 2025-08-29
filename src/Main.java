import br.com.dio.desafio.dominio.*;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final List<Bootcamp> bootcampsDisponiveis = new ArrayList<>();
    private static final List<Dev> todosOsDev = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        criarBootcampInicial();

        while (true) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> menuGerenciarDevs();
                case 2 -> listarBootcamps();
                case 3 -> selecionarBootcampDetalhes();
                case 4 -> selecionarBootcampRanking();
                case 0 -> {
                    System.out.println("Encerrando!");
                    scanner.close();
                    return;
                }
                default -> System.err.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n===== PLATAFORMA DE BOOTCAMPS DIO =====");
        System.out.println("1 - Gerenciar Desenvolvedores");
        System.out.println("2 - Listar Bootcamps Disponíveis");
        System.out.println("3 - Visualizar Detalhes de um Bootcamp");
        System.out.println("4 - Visualizar Ranking de um Bootcamp");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void menuGerenciarDevs () {
        System.out.println("\n--- Gerenciar Desenvolvedores ---");
        System.out.println("1 - Criar novo Dev");
        System.out.println("2 - Selecionar Dev existente");
        System.out.println("3 - Listar todos os Devs");
        System.out.println("4 - Inscrever Dev em um bootcamp");
        System.out.println("Escolha uma opção: ");
        int opcao = lerOpcao();

        switch (opcao) {
            case 1 ->
                    criarDev();
            case 2 ->
                    selecionarDev();
            case 3 ->
                    listarDevs();
            default ->
                    System.err.println("Opção inválida!");
        }
    }

    private static void criarDev () {
        System.out.println("Digite o nome do novo Dev: ");
        String nome = scanner.nextLine();
        Dev novoDev = new Dev();
        novoDev.setNome(nome);
        todosOsDev.add(novoDev);
        System.out.println("Dev " + nome + "criado com sucesso!");
    }

    private static void listarDevs () {
        System.out.println("\n--- Lista de Devs Cadastrados ---");
        if (todosOsDev.isEmpty()) {
            System.out.println("Nenhum dev cadastrado.");
            return;
        }
        for (int i = 0; i < todosOsDev.size(); i++) {
            System.out.println(i + " - " + todosOsDev.get(i).getNome());
        }
    }

    private static void selecionarDev () {
        if (todosOsDev.isEmpty()) {
            System.out.println("Nenhum dev cadastrado para selecionar. Crie um primeiro.");
            return;
        }
        listarDevs();
        System.out.print("Digite o número do Dev para gerenciar: ");
        int indice = lerOpcao();

        if (indice >= 0 && indice < todosOsDev.size()) {
            Dev devSelecionado = todosOsDev.get(indice);
            menuAcoesDev(devSelecionado);
        } else {
            System.out.println("Índice de Dev inválido!");
        }
    }

    private static void menuAcoesDev(Dev dev) {
        while (true) {
            System.out.println("\n--- Ações para o Dev: " + dev.getId() + " - " + dev.getNome().toUpperCase() + " ---");
            System.out.println("1 - Inscrever-se em um Bootcamp");
            System.out.println("2 - Progredir (Concluir um conteúdo)");
            System.out.println("3 - Ver meu progresso");
            System.out.println("4 - Tentar gerar certificado");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma ação: ");
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 ->
                        inscreverBootcamp(dev);
                case 2 -> {
                    dev.progredir();
                    System.out.println(dev.getNome() + " progrediu!");
                }
                case 3 ->
                    dev.exibirProgresso();
                case 4 ->
                    selecionarBootcampCertificar(dev);
                case 0 -> {
                    return;
                }
                default -> System.err.println("Opção inválida!");
            }
        }
    }

    private static void inscreverBootcamp (Dev dev) {
        listarBootcamps();
        if (bootcampsDisponiveis.isEmpty()) return;
        System.out.print("Digite o número do Bootcamp para se inscrever: ");
        int indice = lerOpcao();

        if (indice >= 0 && indice < bootcampsDisponiveis.size()) {
            Bootcamp bootcamp = bootcampsDisponiveis.get(indice);
            dev.inscreverBootcamp((bootcamp));
            System.out.println("Inscrição de " + dev.getNome() + " no bootcamp " + bootcamp.getNome() + "realizada com sucesso!");
        } else {
            System.err.println("Índice de Bootcamp inválido!");
        }
    }

    private static void selecionarBootcampCertificar(Dev dev) {
        listarBootcamps();
        if (bootcampsDisponiveis.isEmpty()) return;

        System.out.print("Digite o número do Bootcamp para gerar o certificado: ");
        int indice = lerOpcao();

        if (indice >= 0 && indice < bootcampsDisponiveis.size()) {
            dev.gerarCertificado(bootcampsDisponiveis.get(indice));
        } else {
            System.err.println("Índice de Bootcamp inválido!");
        }
    }

    private static void listarBootcamps() {
        System.out.println("\n--- Lista de Bootcamps Disponíveis ---");
        if (bootcampsDisponiveis.isEmpty()) {
            System.out.println("Nenhum bootcamp disponível no momento.");
            return;
        }
        for (Bootcamp bootcamp : bootcampsDisponiveis) {
            System.out.println("ID: " + bootcamp.getId() + " - " + bootcamp.getNome());
        }
    }

    private static int lerOpcao() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome o "Enter" que fica no buffer do scanner
            return opcao;
        } catch (java.util.InputMismatchException e) {
            System.err.println("Entrada inválida! Por favor, digite um número.");
            scanner.nextLine(); // Limpa o buffer
            return -1; // Retorna um valor inválido para repetir o loop
        }
    }

    private static void criarBootcampInicial() {
        Curso cursoJava = new Curso();
        cursoJava.setTitulo("Curso de Java Básico");
        cursoJava.setDescricao("Aprenda os fundamentos de Java.");
        cursoJava.setCargaHoraria(10);
        cursoJava.setDificuldade(NivelDificuldade.INICIANTE);

        Curso cursoSpring = new Curso();
        cursoSpring.setTitulo("Curso de Spring Boot");
        cursoSpring.setDescricao("Aprenda a criar APIs com Spring Boot.");
        cursoSpring.setCargaHoraria(20);
        cursoSpring.setDificuldade(NivelDificuldade.INTERMEDIARIO);

        DesafioCodigo desafioPOO = new DesafioCodigo();
        desafioPOO.setTitulo("Desafio de POO com Java");
        desafioPOO.setDescricao("Abstraia um Bootcamp usando Orientação a Objetos.");
        desafioPOO.setDificuldade(NivelDificuldade.AVANCADO);

        Bootcamp bootcamp = new Bootcamp("Bootcamp Super Java Developer", "Torne-se um especialista em Java do zero ao avançado.");
        bootcamp.getConteudos().add(cursoJava);
        bootcamp.getConteudos().add(cursoSpring);
        bootcamp.getConteudos().add(desafioPOO);

        bootcampsDisponiveis.add(bootcamp);
    }

    private static void selecionarBootcampDetalhes () {
        System.out.println("\n--- Detalhes do Bootcamp ---");
        Optional<Bootcamp> bootcampOptional = selecionarBootcamp();
        bootcampOptional.ifPresent((Main::exibirDetalhesBootcamp));
    }

    private static void exibirDetalhesBootcamp(Bootcamp bootcamp) {
        System.out.println("Nome: " + bootcamp.getNome());
        System.out.println("Descrição: " + bootcamp.getDescricao());
        System.out.println("Data de Início: " + bootcamp.getDataInicial());
        System.out.println("Data de término: " + bootcamp.getDataFinal());
    }

    private static Optional<Bootcamp> selecionarBootcamp() {
        listarBootcamps();
        if (bootcampsDisponiveis.isEmpty()) {
            return Optional.empty();
        }
        System.out.println("Digite o ID do bootcamp: ");
        int idBusca = lerOpcao();

        Optional<Bootcamp> bootcampEncontrado = bootcampsDisponiveis.stream()
                .filter(b -> b.getId() == idBusca)
                .findFirst();

        if (bootcampEncontrado.isEmpty()) {
            System.err.println("Nenhum bootcamp encontrado com o ID informado!");
        }

        return bootcampEncontrado;
    }

    private static void selecionarBootcampRanking () {
        System.out.println("\n--- Ranking do Bootcamp ---");
        Optional<Bootcamp> bootcampOptional = selecionarBootcamp();
        bootcampOptional.ifPresent((Bootcamp::exibirRanking));
    }
}



