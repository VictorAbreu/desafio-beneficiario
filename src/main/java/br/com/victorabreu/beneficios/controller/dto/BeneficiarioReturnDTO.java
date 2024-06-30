package br.com.victorabreu.beneficios.controller.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeneficiarioReturnDTO {

    private UUID idBeneficiario;
    private String name;
    private int telefone;
    private Date dataNascimento;
    private Date dataInclusao;
    private Date dataAtualizacao;
}
