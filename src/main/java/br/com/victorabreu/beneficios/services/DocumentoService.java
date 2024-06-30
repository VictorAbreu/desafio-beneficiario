package br.com.victorabreu.beneficios.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.victorabreu.beneficios.controller.dto.DocumentoDTO;
import br.com.victorabreu.beneficios.controller.dto.DocumentoReturnDTO;
import br.com.victorabreu.beneficios.entities.Documento;
import br.com.victorabreu.beneficios.repositories.DocumentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository documentoRepository;


    public void createDocumentoS(UUID idBeneficiario, List<DocumentoDTO> listDocumentoDTO){

//        if(listDocumentoDTO.isEmpty())
//        }
        Date now = Date.from(Instant.now());
        listDocumentoDTO.stream().forEach(
                doc -> {
                    Documento newDocumento = new Documento();
                    newDocumento.setTipoDocumento(doc.getTipoDocumento());
                    newDocumento.setBeneficiarioId(idBeneficiario);
                    newDocumento.setDescricao(doc.getDescricao());
                    newDocumento.setDataInclusao(now);
                    newDocumento.setDataAtualizacao(now);
                    this.documentoRepository.save(newDocumento);

                });
    }

    public List<DocumentoReturnDTO> getAllByBeneficiarioId(UUID idBeneficiario) {
        List<DocumentoReturnDTO> listaDocumentosDTO = new ArrayList<>();
        List<Documento> listaDocumentos = this.documentoRepository.findAllByBeneficiarioId(idBeneficiario);

        if(listaDocumentos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        listaDocumentos.stream().forEach(
                doc -> {
                    DocumentoReturnDTO dto = DocumentoReturnDTO.builder()
                            .idDocumento(doc.getIdDocumento())
                            .BeneficiarioId(doc.getBeneficiarioId())
                            .tipoDocumento(doc.getTipoDocumento())
                            .descricao(doc.getDescricao())
                            .dataAtualizacao(doc.getDataAtualizacao())
                            .dataInclusao(doc.getDataInclusao())
                            .build();
                    listaDocumentosDTO.add(dto);

                }
        );
        return listaDocumentosDTO;
    }

    public List<Documento> getAllDocumentos() {
        return this.documentoRepository.findAll();
    }
}
