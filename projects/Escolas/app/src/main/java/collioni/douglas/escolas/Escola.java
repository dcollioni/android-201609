package collioni.douglas.escolas;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lacomp01 on 17/09/2016.
 */
public class Escola {

    @SerializedName("Code")
    private String codigo;

    @SerializedName("Name")
    private String nome;

    @SerializedName("Address")
    private String endereco;

    @SerializedName("Phone")
    private String telefone;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return String.format("(%s) %s", codigo, nome);
    }
}
