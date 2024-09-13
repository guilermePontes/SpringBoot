package com.lanchenlayer.repositories;

import com.lanchenlayer.entities.Produto;

import java.util.ArrayList;

public class ProdutoRepository {
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public void remover(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    private Produto filtrarProduto(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    public Produto buscarPorId(int id) {
        Produto produtoInDb = filtrarProduto(id);

        return produtoInDb;
    }

    public ArrayList<Produto> buscarTodos() {
        return produtos;
    }

    public void atualizarProduto(int id, Produto produto) {
        Produto produtoInDb = filtrarProduto(id);

        produtoInDb.setDescricao(produto.getDescricao());
        produtoInDb.setValor(produto.getValor());
        produtoInDb.setImagem(produto.getImagem());
    }
}
