package br.com.dio.desafio.dominio;

import java.time.LocalDate;
import java.util.*;

public class Bootcamp {
    private static int count = 0;

    private final int id;
    private final String nome;
    private final String descricao;
    private final LocalDate dataInicial;
    private final LocalDate dataFinal;

    private final Set<Dev> devsInscritos = new HashSet<>();
    private final Set<Conteudo> conteudos = new LinkedHashSet<>();

    public Bootcamp (String nome, String descricao) {
        this.id = count++;
        this.nome = nome;
        this.descricao = descricao;
        dataInicial = LocalDate.now();
        dataFinal = dataInicial.plusDays(45);
    }

    public int getId () { return this.id; }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public Set<Dev> getDevsInscritos() {
        return devsInscritos;
    }

    public Set<Conteudo> getConteudos() {
        return conteudos;
    }

    public void exibirRanking() {
        System.out.println("======= RANKING DO BOOTCAMP: " + this.nome.toUpperCase() + " =======");

        List<Dev> ranking = this.devsInscritos.stream()
                .sorted(Comparator.comparingDouble(Dev::calcularTotalXp).reversed())
                .toList();

        if (ranking.isEmpty()) {
            System.out.println("Nenhum dev inscrito para ranquear.");
        } else {
            for (int i = 0; i < ranking.size(); i++) {
                Dev dev = ranking.get(i);
                System.out.printf("%dº Lugar: %s - %.2f XP%n", (i + 1), dev.getNome(), dev.calcularTotalXp());
            }
        }
        System.out.println("==============================================");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bootcamp bootcamp = (Bootcamp) o;
        return id == bootcamp.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
