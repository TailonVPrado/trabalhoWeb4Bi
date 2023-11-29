package com.example.trabalhoweb4bi.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Data
@Table(name = "CONTASPAGREC")
public class ContasPagRec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @CreationTimestamp
    @Column
    private Date dataLcto;

    @ManyToOne
    private Categoria categoria;
}
