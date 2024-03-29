package com.swcir.swcirsystem.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PFISICAS")
public class PFisica {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="pfisId")
    private Integer pfisId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", nullable=false)
    private User user;

    private String name;

    //@Convert(converter=StringIntConverter.class)
    private Long cpf;

    @Column(name = "dataNasc")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    // @Convert(converter=StringDateConverter.class)
    private Date dataNasc;

    private String nitPisPasep;

    //@Convert(converter=StringIntConverter.class)
    private int tituloEleitoral;

    @OneToOne(mappedBy = "pfisica")
    private Contribuinte contribuinte;

    @OneToOne(mappedBy = "pfisica")
    private Dependente dependente;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setNitPisPasep(String nitPisPasep) {
        this.nitPisPasep = nitPisPasep;
    }
    
    public String getNitPisPasep() {
        return nitPisPasep;
    }

    public void setTituloEleitoral(int tituloEleitoral) {
        this.tituloEleitoral = tituloEleitoral;
    }

    public int getTituloEleitoral() {
        return tituloEleitoral;
    }    
}
