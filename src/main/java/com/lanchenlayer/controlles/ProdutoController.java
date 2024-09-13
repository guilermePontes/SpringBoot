package com.lanchenlayer.controlles;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.facade.ProdutoFacade;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProdutoController {
    private static ProdutoRepository produtoRepository;
    private static ProdutoService produtoService;
    private static ProdutoApplication produtoApplication;
    private static ProdutoFacade produtoFacade;

    public static void resolverDependencias() {
        produtoRepository = new ProdutoRepository();
        produtoService = new ProdutoService();
        produtoApplication = new ProdutoApplication(produtoRepository, produtoService);
        produtoFacade = new ProdutoFacade(produtoApplication);
    }
    public static void inicializarProdutos() {
        produtoFacade.adicionar(new Produto(1, "Cachorro quente", 4.00f, "C:\\Users\\aluno\\imagens\\cachorroquente.jpg"));
        produtoFacade.adicionar(new Produto(2, "X-Salada", 5.00f, "C:\\Users\\aluno\\imagens\\xsalada.jpg"));
    }

    public ProdutoController() {
        resolverDependencias();
        inicializarProdutos();
    }

    @GetMapping("/")
    public ResponseEntity<String> ExibirTodos(){
        ArrayList<Produto> produtos = produtoFacade.buscarTodos();

        StringBuilder resposta = new StringBuilder("Lista de Produtos:\n");
        for (Produto produto : produtos) {
            resposta.append("ID: ").append(produto.getId())
                    .append(", Descrição: ").append(produto.getDescricao())
                    .append(", Preço: R$").append(produto.getValor())
                    .append(", Imagem: ").append(produto.getImagem())
                    .append("\n");
        }
        return ResponseEntity.ok(resposta.toString());
    }
    @GetMapping("/Id/{id}")
    public ResponseEntity<String> ExibirPorId(@PathVariable int id){

        Produto produto = produtoFacade.buscarPorId(id);

        return ResponseEntity.ok(new StringBuilder("Lista de Produtos:\n").append("ID: ").append(produto.getId())
                .append(", Descrição: ").append(produto.getDescricao())
                .append(", Preço: R$").append(produto.getValor())
                .append(", Imagem: ").append(produto.getImagem())
                .append("\n").toString());
    }
}
