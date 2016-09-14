package collioni.douglas.produtos;

import java.io.Serializable;

/**
 * Created by lacomp01 on 10/09/2016.
 */
public class Produto implements Serializable {
    private String nome;
    private double preco;
    private int qtdEstoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Produto(String nome, double preco, int qtdEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    @Override
    public String toString() {
        return nome;
    }
}
