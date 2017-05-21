package model.dao;

import connection.ConectaBanco2;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.ModeloVenda;


public class ControleVenda 
{
   int codProd;
    
    ConectaBanco2 conexao = new ConectaBanco2();
    
    public void adicionaItem(ModeloVenda mod) throws SQLException{
        achaCodProduto(mod.getNomeProd());
        conexao.conexao();
        try{
            PreparedStatement pst = conexao.conn.prepareStatement("insert into itensdavenda (id_venda, id_produto, qtd_produto_venda) values(?,?,?)");
            pst.setInt(1, mod.getIdVenda());
            pst.setInt(2, codProd);
            pst.setInt(3, mod.getQtdItemVenda());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Produto Adicionado!");
            conexao.desconecta();
        }catch (SQLException ex){
            conexao.desconecta();
            JOptionPane.showMessageDialog(null,"Erro ao realizar a venda: "+ ex);
        }
    }
    
    public void achaCodProduto (String nome) throws SQLException{
       conexao.conexao();
       conexao.executaSQL("select * from produto where descricao_produto='"+nome+"'");
        try {
            conexao.rs.first();
            codProd = conexao.rs.getInt("id_produto");
            conexao.desconecta();
       } catch (SQLException ex) {
           conexao.desconecta();
           JOptionPane.showMessageDialog(null,"Erro: "+ ex);
       }
       
    }
    
}

