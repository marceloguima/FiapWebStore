package br.com.fiap.fiapstore.teste;

import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.factory.DaoFactory;
import br.com.fiap.fiapstore.model.Produto;
import java.util.List;
import java.awt.*;
import java.io.LineNumberInputStream;
import java.sql.Date;
import java.time.LocalDate;

public class ProdutoDaoTeste {
    public static void main(String[] args) {

        //cadastrar um produto
        ProdutoDao dao = DaoFactory.getProdutoDao();
        Produto produto = new Produto(
               4,
               "Gabinete yz",
               89.99,
                LocalDate.of(2024, 10, 9),
               15
       );

       try {
            dao.cadastrar(produto);
       } catch (DBException e) {
            throw new RuntimeException(e);
       }

        //buscar um produto pelo código e atualizar
//       produto = dao.buscar(0);
//        produto.setNome("Mouse wireless");
//        produto.setValor(77.99);
//       // produto.setQuantidade(30);
//       try {
//            dao.atualizar(produto);
//
//        } catch (DBException e) {
//           e.printStackTrace();
//        }

        //Listar os produtos
        List<Produto> lista = dao.listar();
        for(Produto item : lista) {
            System.out.println(
                    item.getNome() + " " +
                            item.getQuantidade() + " " +
                            item.getValor());
        }

        //Remover produto
//        try {
//            dao.remover(0);
//
//        } catch (DBException e) {
//            e.printStackTrace();
//        }
    }
}

