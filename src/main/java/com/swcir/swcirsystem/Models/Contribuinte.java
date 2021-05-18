package com.swcir.swcirsystem.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CONTRIBUINTE")
public class Contribuinte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="contId")
    private int contId;

    private String ocupacao;
    
    private String natOcupacao;

    private int numReciboAnterior;

    @OneToMany(mappedBy="contribuinte")
    private Set<Pagamento> pagamento;

    @OneToMany(mappedBy = "contribuinte")
    private Set<Rendimento> rendimento;

    @OneToMany(mappedBy = "contribuinte")
    private Set<Bem> bem;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pfisId", referencedColumnName = "pfisId")
    private PFisica pfisica;

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getNatOcupacao() {
        return natOcupacao;
    }

    public void setNatOcupacao(String natOcupacao) {
        this.natOcupacao = natOcupacao;
    }

    public int getNumReciboAnterior() {
        return numReciboAnterior;
    }

    public void setNumReciboAnterior(int numReciboAnterior) {
        this.numReciboAnterior = numReciboAnterior;
    }

}
