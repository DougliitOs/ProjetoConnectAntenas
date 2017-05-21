package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Produto;
import model.bean.ModeloVenda;

public class VendaDAO 
{
     int codProd;
    
    Connection con;
    
    
    public void adicionaItem(ModeloVenda v)
    {
        Produto p = new Produto();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        //achaCodProduto(p.getDescricao());
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement("insert into itensdavenda(id_venda, id_produto, qtd_produto_venda) values(?,?,?)");
            stmt.setInt(1, v.getIdVenda());
            stmt.setInt(2, codProd);
            stmt.setInt(3, v.getQtdItemVenda());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Produto Adicionado! ");
            
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Erro ao realizar a venda: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public int achaCodProduto (String nome) throws SQLException
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement("select * from produto where descricao_produto='" + nome + "'");
        rs = stmt.executeQuery();
        try {
            rs.first();
            codProd = rs.getInt("id_produto");
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return codProd;
    }
    
}
