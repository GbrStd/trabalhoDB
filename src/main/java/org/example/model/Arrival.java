package org.example.model;

public class Arrival {
    private String continente;
    private int codContinente;
    private String pais;
    private int codPais;
    private String uf;
    private int codUf;
    private String via;
    private int codVia;
    private int ano;
    private String mes;
    private int codMes;
    private int chegadas;

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public int getCodContinente() {
        return codContinente;
    }

    public void setCodContinente(int codContinente) {
        this.codContinente = codContinente;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCodPais() {
        return codPais;
    }

    public void setCodPais(int codPais) {
        this.codPais = codPais;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getCodUf() {
        return codUf;
    }

    public void setCodUf(int codUf) {
        this.codUf = codUf;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCodVia() {
        return codVia;
    }

    public void setCodVia(int codVia) {
        this.codVia = codVia;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getCodMes() {
        return codMes;
    }

    public void setCodMes(int codMes) {
        this.codMes = codMes;
    }

    public int getChegadas() {
        return chegadas;
    }

    public void setChegadas(int chegadas) {
        this.chegadas = chegadas;
    }

    @Override
    public String toString() {
        return "Arrival{" +
                "continente='" + continente + '\'' +
                ", codContinente=" + codContinente +
                ", pais='" + pais + '\'' +
                ", codPais=" + codPais +
                ", uf='" + uf + '\'' +
                ", codUf=" + codUf +
                ", via='" + via + '\'' +
                ", codVia=" + codVia +
                ", ano=" + ano +
                ", mes='" + mes + '\'' +
                ", codMes=" + codMes +
                ", chegadas=" + chegadas +
                '}';
    }
}
