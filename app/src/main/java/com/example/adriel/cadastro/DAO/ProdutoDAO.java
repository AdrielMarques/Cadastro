package com.example.adriel.cadastro.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.adriel.cadastro.Model.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriel on 05/01/16.
 */
public class ProdutoDAO {
    private DataBasesHelper dataBasesHelper;
    private SQLiteDatabase database;

    public ProdutoDAO(Context context){
        dataBasesHelper = new DataBasesHelper(context);

    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBasesHelper.getWritableDatabase();
        }

        return database;
    }

    private Produto criarProduto(Cursor cursor){
        Produto model = new Produto(
                cursor.getInt(cursor.getColumnIndex(DataBasesHelper.Produtos._ID)),
                cursor.getString(cursor.getColumnIndex(DataBasesHelper.Produtos.NOME)),
                cursor.getDouble(cursor.getColumnIndex(DataBasesHelper.Produtos.PRECO))
        );
        return model;
    }

    public List<Produto> listarProdutos(){
        Cursor cursor = getDatabase().query(DataBasesHelper.Produtos.TABELA,
                DataBasesHelper.Produtos.COLUNAS, null, null, null, null, null);

        List<Produto> produtos = new ArrayList<Produto>();
        while(cursor.moveToNext()){
            Produto model = criarProduto(cursor);
            produtos.add(model);
        }

        cursor.close();
        return produtos;
    }

    public long salvarProduto(Produto produto){
        ContentValues valores = new ContentValues();

        valores.put(DataBasesHelper.Produtos.NOME, produto.getNome());
        valores.put(DataBasesHelper.Produtos.PRECO, produto.getPreco());

        if (produto.get_id() != null){
            return getDatabase().update(DataBasesHelper.Produtos.TABELA, valores, "_id = ?",
                    new String[]{produto.get_id().toString()});
        }

        return getDatabase().insert(DataBasesHelper.Produtos.TABELA, null, valores);

    }

    public boolean removerProduto(int id){
        return getDatabase().delete(DataBasesHelper.Produtos.TABELA,
                "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Produto buscarProdutoProId(int id){
        Cursor cursor = getDatabase().query(DataBasesHelper.Produtos.TABELA,
                DataBasesHelper.Produtos.COLUNAS, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);

        if (cursor.moveToNext()){
            Produto model = criarProduto(cursor);
            cursor.close();
            return model;
        }
        return  null;
    }

    public void fechar(){
        dataBasesHelper.close();
        database = null;
    }

}
