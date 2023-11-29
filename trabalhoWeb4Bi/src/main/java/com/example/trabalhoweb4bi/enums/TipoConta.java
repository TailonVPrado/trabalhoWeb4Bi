package com.example.trabalhoweb4bi.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoConta {
    @JsonProperty("Receita")
    RECEITA,
    @JsonProperty("Despesa")
    DESPESA
}
