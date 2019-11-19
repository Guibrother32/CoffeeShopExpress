/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConexaoMySQL;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author guico
 */
public class ProdutoDAO extends ConexaoMySQL {
    
    public void create(Produto p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produto (nome,preco,qtd) VALUES(?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getQtd());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public int salvarProdutosDAO(Produto p) {
        try {
            this.conectar();
            return this.insertSQL("INSERT INTO Produto ("
                    + "nome,"
                    + "preco,"
                    + "qtd"
                    + ") VALUES ("
                    + "'" + p.getNome() + "',"
                    + "'" + p.getPreco() + "',"
                    + "'" + p.getQtd() + "'"
                    + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }
        /**
     * Excluir um produto do banco
     *
     * @param pIdProduto
     * @return boolean
     */
    public boolean excluirProdutoDAO(int pIdProduto) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM Produto WHERE idProduto = '" + pIdProduto + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
    
    /**
     * Retornar uma lista de completa produtos
     *
     * @return listaModelProdutos
     */
    public ArrayList<Produto> retornarProdutosDAO() {
        ArrayList<Produto> listaModelProdutos = new ArrayList<>();
        Produto p = new Produto();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "idProduto, "
                    + "nome,"
                    + "preco,"
                    + "qtd "
                    + "FROM Produto;");

            while (this.getResultSet().next()) {
                p = new Produto();
                p.setIdProduto(this.getResultSet().getInt(1));
                p.setNome(this.getResultSet().getString(2));
                p.setPreco(this.getResultSet().getDouble(3));
                p.setQtd(this.getResultSet().getInt(4));
                listaModelProdutos.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelProdutos;
    }
    public Produto retornarProdutoDAO(int pIdProduto) {
        Produto modelProdutos = new Produto();
        try {
            this.conectar();
            this.executarSQL("SELECT "
//                    + "nome, "
//                    + "preco,"
//                    + "qtd"
                    +" * "
                    + "FROM Produto WHERE idProduto = '" + pIdProduto + "';");
            while (this.getResultSet().next()) {
                
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));
                modelProdutos.setNome(this.getResultSet().getString(2));
                modelProdutos.setPreco(this.getResultSet().getDouble(3));
                modelProdutos.setQtd(this.getResultSet().getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelProdutos;
    }
    
        /**
     * Alterar estoque de produtos no banco
     * @param pListaModelProdutoses
     * @return 
     */
    public boolean alterarEstoqueProdutosDAO(Produto p) {
        
        
        try {
            this.conectar();
                this.executarUpdateDeleteSQL(
                        "UPDATE Produto SET "
                        + "nome = '" + p.getNome()+ "',"
                        + "preco = " + p.getPreco()+ ","
                        + "qtd = " + p.getQtd()+ ""
                        + " WHERE idProduto = '" + p.getIdProduto() + "';"
                );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
    
    
    
    public int retornarProdutoQtdDAO(int pIdProduto) {
        Produto modelProdutos = new Produto();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    +" qtd  "
                    + "FROM Produto WHERE idProduto = '" + pIdProduto + "';");
            while (this.getResultSet().next()) {
                modelProdutos.setQtd(this.getResultSet().getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelProdutos.getQtd();
    }
    public Double retornarProdutoPrecoDAO(int pIdProduto) {
        Produto modelProdutos = new Produto();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    +" Preco  "
                    + "FROM Produto WHERE idProduto = '" + pIdProduto + "';");
            while (this.getResultSet().next()) {
                modelProdutos.setPreco(this.getResultSet().getDouble(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelProdutos.getPreco();
    }
    
}
