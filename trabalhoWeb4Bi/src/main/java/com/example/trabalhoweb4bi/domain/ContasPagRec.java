package com.example.trabalhoweb4bi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
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
    @Column(nullable = false, updatable = false)
    private Date dataLcto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private boolean ativo = true;

    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    @Column(nullable = false, columnDefinition = "numeric(10,2)")
    private BigDecimal valor;

}
