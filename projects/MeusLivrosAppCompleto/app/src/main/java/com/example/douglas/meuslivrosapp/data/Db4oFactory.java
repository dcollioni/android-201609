package com.example.douglas.meuslivrosapp.data;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oDatabase;

/**
 * Created by lacomp01 on 16/09/2016.
 */
public class Db4oFactory {

    // constante que define o nome do arquivo do banco
    private final String DB4O_FILE = "bd.db4o";

    // propriedade que manipula o banco
    public ObjectContainer db;

    // propriedade que guarda o diretório de criação do banco
    private String dir;

    // constrói um novo objeto passando o diretório
    public Db4oFactory(String dir) {
        this.dir = dir;
    }

    // abre uma conexão com o banco de dados
    public void abrirConexao() {
        // monta o caminho físico para criação do banco
        String banco = dir + "/" + DB4O_FILE;

        // cria uma nova conexão com o banco
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), banco);
    }

    // fecha a conexão com o banco de dados
    public void fecharConexao() {
        // verifica se não é nulo
        if (db != null) {
            // fecha a conexão
            db.close();
        }
    }
}
