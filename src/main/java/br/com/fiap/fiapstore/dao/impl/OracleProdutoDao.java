package br.com.fiap.fiapstore.dao.impl;

import br.com.fiap.fiapstore.dao.ConnectionManager;
import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.model.Produto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleProdutoDao implements ProdutoDao {

    private Connection conexao;

    @Override
    public void cadastrar(Produto produto) throws DBException {

        PreparedStatement stmt = null;

         conexao = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO TB_PRODUTO " +
                "(COD_PRODUTO, " +
                "NOME_PRODUTO," +
                "VALOR_PRODUTO, " +
                "DATA_FABRICACAO, " +
                " QTDE_PRODUTO)" +
                "VALUES (?,?,?,?,?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getCodProduto());
            stmt.setString(2, produto.getNome());
            stmt.setDouble(3, produto.getValor());
            stmt.setDate(4, Date.valueOf(produto.getDataFabricacao()));
            stmt.setInt(5, produto.getQuantidade());
            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
          //  System.out.println("Consulta SQL: " + sql); // Imprime a consulta SQL


        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar.");
        } finally {
           try {
               stmt.close();
               conexao.close();

           }catch (SQLException e){
             e.printStackTrace();
           }
        }
    }



    @Override
    public void atualizar(Produto produto) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE TB_PRODUTO SET " +
                    "NOME_PRODUTO = ?, " +
                    "VALOR_PRODUTO = ?, " +
                    "DATA_FABICACAO = ?," +
                    "QTDE_PRODUTO = ? " +
                    "WHERE COD_PRODUTO = ? ";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getCodProduto());
            stmt.setString(2, produto.getNome());
            stmt.setDouble(3, produto.getValor());
            stmt.setDate(4, Date.valueOf(produto.getDataFabricacao()));
            stmt.setInt(5, produto.getQuantidade());
            stmt.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void remover(int codigo) throws DBException {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM TB_PRODUTO WHERE COD_PRODUTO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            System.out.println("Produto removido com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Produto buscar(int codigo) {
        Produto produto = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_PRODUTO WHERE COD_PRODUTO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigoRetornado = rs.getInt("COD_PRODUTO");
                String nome = rs.getString("NOME_PRODUTO");
                double valor = rs.getDouble("VALOR_PRODUTO");
                LocalDate data = rs.getDate("DATA_FABRICACAO").toLocalDate();
                int qtde = rs.getInt("QTDE_PRODUTO");

                produto = new Produto(codigoRetornado, nome, valor, data,  qtde);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return produto;
    }

    @Override
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<Produto>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_PRODUTO";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            //percorre todos os registros encontrados
            while (rs.next()) {
                int codigo = rs.getInt("COD_PRODUTO");
                String nome = rs.getString("NOME_PRODUTO");
                double valor = rs.getDouble("VALOR_PRODUTO");
                //java.sql.Date data = rs.getDate("DATA_FABRICACAO");
                LocalDate data = rs.getDate("DATA_FABRICACAO") .toLocalDate();
                int qtde = rs.getInt("QTDE_PRODUTO");


                Produto produto = new Produto(codigo, nome, valor, data, qtde);
                lista.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
