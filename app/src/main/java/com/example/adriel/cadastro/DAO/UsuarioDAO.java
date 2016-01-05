package com.example.adriel.cadastro.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.adriel.cadastro.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriel on 05/01/16.
 */
public class UsuarioDAO {
    private DataBasesHelper dataBasesHelper;
    private SQLiteDatabase database;

    public UsuarioDAO(Context context){
        dataBasesHelper = new DataBasesHelper(context);

    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBasesHelper.getWritableDatabase();
        }

        return database;
    }

    private Usuario criarUsuario(Cursor cursor){
        Usuario model = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DataBasesHelper.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DataBasesHelper.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBasesHelper.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DataBasesHelper.Usuarios.SENHA))
        );
        return model;
    }

    public List<Usuario> listarUsuarios(){
        Cursor cursor = getDatabase().query(DataBasesHelper.Usuarios.TABELA,
                DataBasesHelper.Usuarios.COLUNAS, null, null, null, null, null);

        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(cursor.moveToNext()){
            Usuario model = criarUsuario(cursor);
            usuarios.add(model);
        }

        cursor.close();
        return usuarios;
    }

    public long salvarUsuario(Usuario usuario){
        ContentValues valores = new ContentValues();

        valores.put(DataBasesHelper.Produtos.NOME, usuario.getNome());
        valores.put(DataBasesHelper.Produtos.PRECO, usuario.getLogin());
        valores.put(DataBasesHelper.Produtos.PRECO, usuario.getSenha());

        if (usuario.get_id() != null){
            return getDatabase().update(DataBasesHelper.Usuarios.TABELA, valores, "_id = ?",
                    new String[]{usuario.get_id().toString()});
        }

        return getDatabase().insert(DataBasesHelper.Usuarios.TABELA, null, valores);

    }

    public boolean removerUsuarios(int id){
        return getDatabase().delete(DataBasesHelper.Usuarios.TABELA,
                "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Usuario buscaUsuarioProId(int id){
        Cursor cursor = getDatabase().query(DataBasesHelper.Usuarios.TABELA,
                DataBasesHelper.Usuarios.COLUNAS, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);

        if (cursor.moveToNext()){
            Usuario model = criarUsuario(cursor);

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
