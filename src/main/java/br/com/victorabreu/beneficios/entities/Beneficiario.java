package br.com.victorabreu.beneficios.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "beneficiarios")
@Data
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idBeneficiario;

    private String name;
    private int telefone;

    private Date dataNascimento;
    private Date dataInclusao;
    private Date dataAtualizacao;
}
