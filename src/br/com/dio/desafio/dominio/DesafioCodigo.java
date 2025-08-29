package br.com.dio.desafio.dominio;

public class DesafioCodigo extends Conteudo {

    private NivelDificuldade dificuldade;

    @Override
    public double calcularXp() {
        // XP padrão + um bônus pelo desafio, multiplicado pela dificuldade
        return (XP_PADRAO + 30d) * getDificuldade().getMultiplicadorXp();
    }

    private NivelDificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(NivelDificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    @Override
    public String toString() {
        return "DesafioDeCodigo{" +
                "titulo='" + getTitulo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", dificuldade=" + dificuldade +
                '}';
    }
}