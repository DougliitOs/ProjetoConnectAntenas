package model.bean;

public class ModeloVenda 
{
    private int idVenda;
    private String data;
    private double valorVenda;
    private String nomeProd;
    private int qtdItemVenda;

    /**
     * @return the idVenda
     */
    public int getIdVenda() {
        return idVenda;
    }

    /**
     * @param idVenda the idVenda to set
     */
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the valorVenda
     */
    public double getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    /**
     * @return the nomeProd
     */
    public String getNomeProd() {
        return nomeProd;
    }

    /**
     * @param nomeProd the nomeProd to set
     */
    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    /**
     * @return the qtdItemVenda
     */
    public int getQtdItemVenda() {
        return qtdItemVenda;
    }

    /**
     * @param qtdItemVenda the qtdItemVenda to set
     */
    public void setQtdItemVenda(int qtdItemVenda) {
        this.qtdItemVenda = qtdItemVenda;
    }
}
