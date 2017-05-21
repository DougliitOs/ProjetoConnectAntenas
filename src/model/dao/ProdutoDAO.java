package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author Samuelson
 */
public class ProdutoDAO {

    Connection con;
    
    public ProdutoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public void create(Produto p) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produto (descricao_produto, preco_custo, preco_venda, qtd_produto)VALUES(?,?,?,?)");
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getPrecoCusto());
            stmt.setDouble(3, p.getPrecoVenda());
            stmt.setInt(4, p.getQtd());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Produto> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id_produto"));
                produto.setDescricao(rs.getString("descricao_produto")); 
                produto.setPrecoCusto(rs.getDouble("preco_custo"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQtd(rs.getInt("qtd_produto"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    public List<Produto> readForDesc(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao_produto LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id_produto"));
                produto.setDescricao(rs.getString("descricao_produto"));         
                produto.setPrecoCusto(rs.getDouble("preco_custo"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQtd(rs.getInt("qtd_produto"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }

    public void update(Produto p) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET descricao_produto = ?, preco_custo = ?,preco_venda = ?, qtd_produto = ? WHERE id_produto = ?");
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getPrecoCusto());
            stmt.setDouble(3, p.getPrecoVenda());
            stmt.setInt(4, p.getQtd());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Produto p) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE id_produto = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
