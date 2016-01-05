package com.example.adriel.cadastro.Model;

/**
 * Created by adriel on 05/01/16.
 */
public class Produto {
    private Integer _id;
    private String nome;
    private double preco;

    public Produto(){}

    public Produto(Integer Id, String Nome, double Preco){
        this._id = Id;
        this.nome = Nome;
        this.preco = Preco;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

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
}
