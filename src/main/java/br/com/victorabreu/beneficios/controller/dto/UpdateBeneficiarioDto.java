package br.com.victorabreu.beneficios.controller.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class UpdateBeneficiarioDto {


    private UUID idBeneficiario;
    private String name;
    private int telefone;

    private Date dataNascimento;


}
