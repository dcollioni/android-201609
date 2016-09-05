package collioni.douglas.cidades;

/**
 * Created by lacomp01 on 03/09/2016.
 */
public class Cidade {
    private String nome;
    private int populacao;
    private int imagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public Cidade(String nome, int populacao, int imagem) {
        this.nome = nome;
        this.populacao = populacao;
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return nome;
    }
}