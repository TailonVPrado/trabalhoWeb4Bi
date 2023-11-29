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

    @Column(nullable = false)
    private String descricao;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dataLcto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private boolean ativo = true;
}
