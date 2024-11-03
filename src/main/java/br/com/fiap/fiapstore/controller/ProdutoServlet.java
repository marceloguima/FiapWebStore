package br.com.fiap.fiapstore.controller;

import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.factory.DaoFactory;
import br.com.fiap.fiapstore.model.Produto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {


    private ProdutoDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getProdutoDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        double preco = Double.valueOf(req.getParameter("preco"));
        LocalDate fabricacao = LocalDate.parse(req.getParameter("fabricacao"));
        int quantidade = Integer.valueOf(req.getParameter("quantidade"));

        Produto produto = new Produto(
                0,
                nome,
                preco,
                fabricacao,
                quantidade

                );

        try {
            dao.cadastrar(produto);
            req.setAttribute("mensagem", "produto cadastrado com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar produto");
        }

        req.getRequestDispatcher("cadastro-produto.jsp").forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}


