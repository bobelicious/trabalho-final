package br.com.serratec.trabalhofinal.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.trabalhofinal.exceptions.EnumException;

public enum StatusPedido {
    FINALIZADO(1,"Finalizado"), NAOFINALIZADO(2,"Não finalizado");

    private Integer codigo;
    private String status;

    private StatusPedido(Integer codigo, String status) {
        this.codigo = codigo;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCodigo() {
        return codigo;
    }

    @JsonCreator
    public static StatusPedido verifica(Integer valor) throws EnumException{
        for (StatusPedido status : values()) {
            if(valor.equals(status.getCodigo())){
                return status;
            }
        }
        throw new EnumException("Status inválido");
    }
}
