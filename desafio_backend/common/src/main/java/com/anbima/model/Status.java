package com.anbima.model;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum Status {
    RECEBIDO("RECEBIDO"),
    ENTREGUE("ENTREGUE");

    public final String nome;
}
