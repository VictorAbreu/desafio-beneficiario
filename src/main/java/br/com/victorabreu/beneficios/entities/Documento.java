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
@Table(name = "documentos")
@Data
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idDocumento;

    private UUID beneficiarioId;

    private String descricao;

    private String tipoDocumento;

    private Date dataInclusao;
    private Date dataAtualizacao;
}
