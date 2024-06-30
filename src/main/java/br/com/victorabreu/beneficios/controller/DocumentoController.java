package br.com.victorabreu.beneficios.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorabreu.beneficios.controller.dto.DocumentoReturnDTO;
import br.com.victorabreu.beneficios.entities.Documento;
import br.com.victorabreu.beneficios.services.DocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Documento")
public class DocumentoController {

    private final DocumentoService documentoService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @PostMapping("/{idBeneficiario}")
    @Operation(summary = "Find", description = "Busca uma lista de Documentos apartir do id do Beneficiario", tags = "Documento")
    public ResponseEntity<List<DocumentoReturnDTO>> findAllDocumentosByBeneficiarioId(@RequestParam UUID idBeneficiario){
        List<DocumentoReturnDTO> ListaDocumentosDTO = this.documentoService.getAllByBeneficiarioId(idBeneficiario);

        return ResponseEntity.ok(ListaDocumentosDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping("/")
        @Operation(summary = "Listar", description = "Lista todos os documentos no banco.", tags = "Documento")
    public ResponseEntity<List<Documento>> getAllDocumentos(){
        List<Documento> doc = this.documentoService.getAllDocumentos();
        return ResponseEntity.ok(doc);
    }
}
