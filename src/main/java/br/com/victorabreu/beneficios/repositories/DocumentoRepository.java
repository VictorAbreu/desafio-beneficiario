package br.com.victorabreu.beneficios.repositories;

import br.com.victorabreu.beneficios.entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, UUID> {
    List<Documento> findAllByBeneficiarioId(UUID idBeneficiario);
}
