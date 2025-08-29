package br.com.dio.desafio.dominio;

import java.time.LocalDate;
import java.util.*;

public class Dev {
    private final int id;
    private static int count;
    private String nome;
    private final Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private final Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public Dev () {
        this.id = count++;
    }

    public int getId () {
        return id;
    }

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        Optional<Conteudo> conteudo = this.getConteudosInscritos().stream().findFirst();
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        // Usando a Stream API para um código mais limpo e funcional
        return this.getConteudosConcluidos()
                .stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();
    }

    public void gerarCertificado(Bootcamp bootcamp) {
        // Verifica se o conjunto de conteúdos concluídos pelo Dev contém todos os conteúdos do bootcamp
        if (this.conteudosConcluidos.containsAll(bootcamp.getConteudos())) {
            System.out.println("======================================================");
            System.out.println("CERTIFICADO DE CONCLUSÃO");
            System.out.println("Certificamos que " + this.nome.toUpperCase() + " concluiu com sucesso o");
            System.out.println("Bootcamp: " + bootcamp.getNome());
            System.out.println("Data de Conclusão: " + LocalDate.now());
            System.out.println("XP Total Adquirido: " + this.calcularTotalXp());
            System.out.println("======================================================");
        } else {
            System.err.println("ERRO: " + this.nome + ", você ainda não concluiu todos os conteúdos do Bootcamp " + bootcamp.getNome() + "!");
        }
    }

    public void exibirProgresso() {
        System.out.println("---------- Progresso do Dev: " + this.nome.toUpperCase() + " ----------");
        System.out.println("XP Total: " + this.calcularTotalXp());

        System.out.println("\n--- Conteúdos Inscritos ---");
        if (conteudosInscritos.isEmpty()) {
            System.out.println("Nenhum! Parabéns, você concluiu tudo!");
        } else {
            this.getConteudosInscritos().forEach(System.out::println);
        }

        System.out.println("\n--- Conteúdos Concluídos ---");
        if (conteudosConcluidos.isEmpty()) {
            System.out.println("Nenhum conteúdo concluído ainda.");
        } else {
            this.conteudosConcluidos.forEach(System.out::println);
        }
        System.out.println("-------------------------------------------\n");
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getNome () {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
