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
import model.bean.Cliente;

public class ClienteDAO
{
    Connection con;
    
    public ClienteDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public void create(Cliente cli) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome_cliente, data_nasc, rg, cpf, endereco, bairro, cidade, estado, cep, email, telefone, celular)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, cli.getNomeCli());
            stmt.setString(2, cli.getDataNasc());
            stmt.setString(3, cli.getRg());
            stmt.setString(4, cli.getCpf());
            stmt.setString(5, cli.getEnderecoCli());
            stmt.setString(6, cli.getBairro());
            stmt.setString(7, cli.getCidadeCli());
            stmt.setString(8, cli.getEstCli());
            stmt.setString(9, cli.getCepCli());
            stmt.setString(10, cli.getEmailCli());
            stmt.setString(11, cli.getTelefone());
            stmt.setString(12, cli.getCelular());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Cliente> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cli = new Cliente();

                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setNomeCli(rs.getString("nome_cliente")); 
                cli.setDataNasc(rs.getString("data_nasc"));
                cli.setRg(rs.getString("rg")); 
                cli.setCpf(rs.getString("cpf")); 
                cli.setEnderecoCli(rs.getString("endereco")); 
                cli.setBairro(rs.getString("bairro")); 
                cli.setCidadeCli(rs.getString("cidade")); 
                cli.setEstCli(rs.getString("estado")); 
                cli.setCepCli(rs.getString("cep")); 
                cli.setEmailCli(rs.getString("email")); 
                cli.setTelefone(rs.getString("telefone")); 
                cli.setCelular(rs.getString("celular")); 
                clientes.add(cli);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }
    public List<Cliente> readForDesc(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome_cliente LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cli = new Cliente();

                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setNomeCli(rs.getString("nome_cliente")); 
                cli.setDataNasc(rs.getString("data_nasc")); 
                cli.setRg(rs.getString("rg")); 
                cli.setCpf(rs.getString("cpf")); 
                cli.setEnderecoCli(rs.getString("endereco")); 
                cli.setBairro(rs.getString("bairro")); 
                cli.setCidadeCli(rs.getString("cidade")); 
                cli.setEstCli(rs.getString("estado")); 
                cli.setCepCli(rs.getString("cep")); 
                cli.setEmailCli(rs.getString("email")); 
                cli.setTelefone(rs.getString("telefone")); 
                cli.setCelular(rs.getString("celular")); 
                clientes.add(cli);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

    public void update(Cliente cli) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome_cliente = ?, data_nasc = ?, rg = ?, cpf = ?, endereco = ?, bairro = ?, cidade = ?, estado = ?, cep = ?, email = ?, telefone = ?, celular = ? WHERE id_cliente = ?");
            stmt.setString(1, cli.getNomeCli());
            stmt.setString(2, cli.getDataNasc());
            stmt.setString(3, cli.getRg());
            stmt.setString(4, cli.getCpf());
            stmt.setString(5, cli.getEnderecoCli());
            stmt.setString(6, cli.getBairro());
            stmt.setString(7, cli.getCidadeCli());
            stmt.setString(8, cli.getEstCli());
            stmt.setString(9, cli.getCepCli());
            stmt.setString(10, cli.getEmailCli());
            stmt.setString(11, cli.getTelefone());
            stmt.setString(12, cli.getCelular());
            stmt.setInt(13, cli.getIdCliente());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Cliente cli) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id_cliente = ?");
            stmt.setInt(1, cli.getIdCliente());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
