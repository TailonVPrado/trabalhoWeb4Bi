package com.example.trabalhoweb4bi.domain;

import com.example.trabalhoweb4bi.enums.TipoConta;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CATEGORIA")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;
}
