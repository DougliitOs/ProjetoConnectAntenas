package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConectaBanco2 
{
    public Statement stm; //responsavel por preparar e realizar pesquisas no banco de dados 
    public ResultSet rs; //responsável por armazenar o resultado de uma pesquisa passada para o statement
    private final String driver = "com.mysql.jdbc.Driver"; //responsável por identificar o servidor de banco de dados
    private final String caminho = "jdbc:mysql://localhost:3306/sistemaconnectantenas"; //responsavel por setar o local do banco de dados
    private final String usuario = "root"; //user do bd
    private final String senha = "Kevin12122015";
    public Connection conn; // responsável por realizar a conexão com o banco de dados
    
    public void conexao()   //método responsável por realizar a conexão com o banco
    {
        try { //tentativa inicial
            System.setProperty("jdbc.Drivers", driver); //seta a propriedade do driver de conexão
            conn = DriverManager.getConnection(caminho, usuario, senha); //realiza a conexão com o banco de dados
            //JOptionPane.showMessageDialog(null, "Conectado com sucesso!"); //imprime uma caixa de texto de mensagem
        } catch (SQLException ex) { //excessão
            JOptionPane.showMessageDialog(null, "Erro de conexão! \n Erro: " + ex.getMessage());
        }
    }
    
    public void executaSQL(String sql) throws SQLException
    {
        try{
                stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
                rs = stm.executeQuery(sql);
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro de ExecutaSQL! \nErro: " + ex.getMessage());
        }
     }        
    
    
    public void desconecta() 
    {
        try {   //método para fechar a conexão com o banco de dados
           if(conn != null) 
                conn.close(); //fecha a conexão
           //JOptionPane.showMessageDialog(null, "Desconectado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexão! \n Erro: " + ex.getMessage());
        }
    }
}