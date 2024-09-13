package com.lanchenlayer.facade;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.ProdutoRepository;

import java.util.ArrayList;

public class ProdutoFacade {
    private ProdutoApplication produtoApplication;

    public ProdutoFacade(ProdutoApplication produtoApplication) {
        this.produtoApplication = produtoApplication;
    }

    public void adicionar(Produto produto) {
        this.produtoApplication.adicionar(produto);
    }

    public void remover(int id) {
        this.produtoApplication.remover(id);
    }

    public Produto buscarPorId(int id) {
        return this.produtoApplication.buscarPorId(id);
    }

    public ArrayList<Produto> buscarTodos() {
        return this.produtoApplication.buscarTodos();
    }

    public float vender(int id, int quantidade) {
        return this.produtoApplication.vender(id, quantidade);
    }

    public void atualizarProduto(int id, Produto produto) {
        this.produtoApplication.atualizarProduto(id, produto);
    }
}
