package br.com.dio.desafio.dominio;

public enum NivelDificuldade {
    INICIANTE(1.0),
    INTERMEDIARIO(1.2),
    AVANCADO(1.5);

    private final double multiplicadorXp;

    NivelDificuldade(double multiplicadorXp) {
        this.multiplicadorXp = multiplicadorXp;
    }

    public double getMultiplicadorXp() {
        return multiplicadorXp;
    }
}