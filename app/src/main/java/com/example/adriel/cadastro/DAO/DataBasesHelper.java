package com.example.adriel.cadastro.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adriel on 05/01/16.
 */
public class DataBasesHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "cadastro";
    private static final int VERSAO = 1;

    public DataBasesHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de Usuário
        db.execSQL("create table usuarios(_id integer primary key autoincrement, "
                + "nome text not null, login text not null, senha text not null)");


        //Tabela de Produto
        db.execSQL("create table produtos(_id integer primary key autoincrement, "
                + "nome text not null, preco decimal not null)");

        //cadastro um Usuário.
        db.execSQL("insert into usuarios(nome, login, senha) values('Adriel', 'admin', '123')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuarios {

        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";

        public static final String[] COLUNAS = new String[]{
                _ID, NOME, LOGIN, SENHA
        };
    }

    public static class Produtos{

        public static final String TABELA = "produtos";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String PRECO = "preco";

        public static final String[] COLUNAS = new String[]{
                _ID, NOME, PRECO
        };
    }

}
