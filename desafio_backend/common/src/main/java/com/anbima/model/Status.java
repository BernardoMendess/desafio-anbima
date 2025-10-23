package com.anbima.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    RECEBIDO("RECEBIDO"),
    ENTREGUE("ENTREGUE");

    public final String nome;
}
