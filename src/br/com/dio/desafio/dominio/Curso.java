package br.com.dio.desafio.dominio;

public class Curso extends Conteudo {

    private int cargaHoraria;
    private NivelDificuldade dificuldade; // Novo atributo

    // Construtor e outros getters/setters permanecem os mesmos...
    public Curso() {}

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    private NivelDificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(NivelDificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    @Override
    public double calcularXp() {
        return (XP_PADRAO * getCargaHoraria()) * getDificuldade().getMultiplicadorXp();
    }

    @Override
    public String toString() {
        return "Curso{" +
                "titulo='" + getTitulo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                ", dificuldade=" + dificuldade + // Adicionado
                '}';
    }
}