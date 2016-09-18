package com.example.douglas.meuslivrosapp.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.example.douglas.meuslivrosapp.Livro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lacomp01 on 16/09/2016.
 * Classe que faz manipulações no banco de dados para a classe Livro
 */
public class LivroDao {

    // propriedade que manipula o banco
    private ObjectContainer db;

    public LivroDao(Db4oFactory db4o) {
        // inicializa a propriedade com o banco aberto
        this.db = db4o.db;
    }

    // insere um novo livro
    public void inserir(Livro livro) {
        // armazena o objeto no banco
        db.store(livro);
    }

    // lista os livros do banco
    public List<Livro> listar(final String filtro) {
        if (filtro.isEmpty()) {
            // busca no banco todos os objetos da classe Livro
            return db.query(Livro.class);
        } else {
            // busca no banco os livros cujo título contém o filtro passado
            return db.query(new Predicate<Livro>() {
                @Override
                public boolean match(Livro livro) {
                    return livro.getTitulo().toLowerCase().contains(filtro.toLowerCase()) ||
                           livro.getAutor().toLowerCase().contains(filtro.toLowerCase());
                }
            });
        }
    }

    // retorna o ID de um livro do banco
    public long buscarId(Livro livro) {
        return db.ext().getID(livro);
    }

    // busca um livro pelo ID
    public Livro buscar(long ID) {
        // busca o livro no banco pelo ID
        Livro livro = db.ext().getByID(ID);

        // popula os dados do banco e retorna o livro
        db.ext().activate(livro);
        return livro;
    }

    public void atualizar(long ID, Livro livro) {
        // busca o livro que já está salvo pelo ID
        Livro livroBD = buscar(ID);

        // atualiza as propriedades com os novos valores
        livroBD.setTitulo(livro.getTitulo());
        livroBD.setAutor(livro.getAutor());
        livroBD.setCategoria(livro.getCategoria());
        livroBD.setNumeroPaginas(livro.getNumeroPaginas());

        // armazena novamente no banco
        db.store(livroBD);
    }

    // exclui o livro do banco
    public void excluir(long ID) {
        Livro livro = buscar(ID);
        db.delete(livro);
        db.commit();
    }
}
