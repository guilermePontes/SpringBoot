package com.lanchenlayer;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.facade.ProdutoFacade;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 */
public class Console {
    private static ProdutoRepository produtoRepository;
    private static ProdutoService produtoService;
    private static ProdutoApplication produtoApplication;
    private static ProdutoFacade produtoFacade;
    private static Scanner scanner;

    public static void resolverDependencias() {
        produtoRepository = new ProdutoRepository();
        produtoService = new ProdutoService();
        produtoApplication = new ProdutoApplication(produtoRepository, produtoService);
        produtoFacade = new ProdutoFacade(produtoApplication);
        scanner = new Scanner(System.in);
    }

    public static void inicializarProdutos() {
        produtoFacade.adicionar(new Produto(1, "Cachorro quente", 4.00f, "C:\\Users\\aluno\\imagens\\cachorroquente.jpg"));
        produtoFacade.adicionar(new Produto(2, "X-Salada", 5.00f, "C:\\Users\\aluno\\imagens\\xsalada.jpg"));
    }

    public static void listarProdutos() {
        System.out.println("Id    |    Descrição   |   Valor");

        ArrayList<Produto> produtos = produtoFacade.buscarTodos();

        produtos.forEach(c -> {
            System.out.println(c.getId()+"   |     " +c.getDescricao() + "   |     " + c.getValor());
        });
    }

    public static void cadastrarProduto() {
        System.out.println("Informe o ID do produto: ");
        int id = scanner.nextInt();

        System.out.println("Informe a descrição do produto: ");
        String descricao = scanner.next();

        System.out.println("Informe o valor do produto: ");
        float valor = scanner.nextFloat();

        System.out.println("Informe o caminho da imagem do produto: ");
        String imagem = scanner.next();

        Produto produto = new Produto(id, descricao, valor, imagem);
        produtoFacade.adicionar(produto);
    }

    public static void exibirMenu() {
        System.out.println("\n1 - Novo produto");
        System.out.println("2 - Atualizar produto");
        System.out.println("3 - Listar produtos");
        System.out.println("4 - Vender");
        System.out.println("5 - Remover produto");
        System.out.println("6 - Sair");
    }

    public static int solicitarInputUsuario() {
        System.out.println("Informe a opção do menu desejado: ");
        return scanner.nextInt();
    }

    public static void venderProduto() {
        System.out.println("Informe o produto desejada: ");
        int id = scanner.nextInt();

        System.out.println("Informe a quantidade desejada: ");
        int quantidade = scanner.nextInt();

        System.out.println("Total: " + produtoFacade.vender(id, quantidade));
    }

    private static void atualizarProduto() {
        System.out.println("Informe o ID do produto que deseja atualizar: ");
        int id = scanner.nextInt();

        System.out.println("Informe a nova descrição do produto: ");
        String descricao = scanner.next();

        System.out.println("Informe o novo valor do produto: ");
        float valor = scanner.nextFloat();

        System.out.println("Informe o novo caminho da imagem do produto: ");
        String imagem = scanner.next();

        Produto produto = new Produto(id, descricao, valor, imagem);
        produtoFacade.atualizarProduto(id, produto);
    }

    public static void removerProduto() {
        System.out.println("Informe o ID do produto que deseja remover: ");
        int id = scanner.nextInt();

        produtoFacade.remover(id);
    }

    public static void rodar() {
        int opcaoMenu = 0;

        do {
            exibirMenu();
            opcaoMenu = solicitarInputUsuario();
            switch (opcaoMenu) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    atualizarProduto();
                    break;
                case 3:
                    listarProdutos();
                    break;
                case 4:
                    venderProduto();
                    break;
                case 5:
                    removerProduto();
                    break;
            }

        } while (opcaoMenu != 6);
    }

    public static void main(String[] args) {
        resolverDependencias();
        inicializarProdutos();
        rodar();
    }
}
