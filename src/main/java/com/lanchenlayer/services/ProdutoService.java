package com.lanchenlayer.services;

import com.lanchenlayer.entities.Produto;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class ProdutoService {
    private String caminhoDestino = "C:\\Users\\aluno\\LancheNLayer\\src\\main\\resources\\images\\";

    public static String getFileExtension(String filePath) {
        String fileName = new File(filePath).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "No extension" : fileName.substring(dotIndex + 1);
    }

    public boolean salvarImagem(Produto produto) {
        Path path = Paths.get(produto.getImagem());

        Path pastaDestino = Paths.get(String.format("%s%d.%s", caminhoDestino, produto.getId(), getFileExtension(produto.getImagem())));

        if (Files.exists(path)) {
            try {
                Files.copy(path, pastaDestino, StandardCopyOption.REPLACE_EXISTING);
                produto.setImagem(pastaDestino.getFileName().toString());
                return true;
            } catch (Exception ex) {
                return false;
            }
        }

        return false;
    }

    private String buscarCaminhoArquivoPorId(int id) {
        File diretorio = new File(caminhoDestino);
        File[] matches = diretorio.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(String.valueOf(id));
            }
        });
        return Arrays.stream(matches).findFirst().get().getAbsolutePath();
    }

    public void removerImagem(int id) {
        Path path = Paths.get(buscarCaminhoArquivoPorId(id));

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarImagem(Produto produto) {
        removerImagem(produto.getId());
        salvarImagem(produto);
    }
}
