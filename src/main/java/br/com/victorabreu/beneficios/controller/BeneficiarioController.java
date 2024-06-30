package br.com.victorabreu.beneficios.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorabreu.beneficios.controller.dto.BeneficiarioReturnDTO;
import br.com.victorabreu.beneficios.controller.dto.CreateBeneficiarioDto;
import br.com.victorabreu.beneficios.controller.dto.UpdateBeneficiarioDto;
import br.com.victorabreu.beneficios.services.BeneficiarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Beneficiario")
public class BeneficiarioController {

    private final BeneficiarioService beneficiarioService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping()
    @Operation(summary = "Listar", description = "Lista todos os beneficiarios", tags = "Beneficiario")
    public ResponseEntity<List<BeneficiarioReturnDTO>> getAllBeneficiarios(){
        List<BeneficiarioReturnDTO> listbeneficiarios = this.beneficiarioService.getAllbeneficiarios();
        return ResponseEntity.ok(listbeneficiarios);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @PutMapping()
    @Operation(summary = "Update", description = "update um beneficiario", tags = "Beneficiario")
    public ResponseEntity<BeneficiarioReturnDTO> updateBeneficio(@RequestBody UpdateBeneficiarioDto updateBeneficiarioDto){

        BeneficiarioReturnDTO beneficiarioUpdate = this.beneficiarioService.updateBeneficiario(updateBeneficiarioDto);

        return ResponseEntity.ok(beneficiarioUpdate);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @PostMapping()
    @Operation(summary = "Create", description = "cria um novo beneficiario", tags = "Beneficiario")
    public ResponseEntity<Void> createNewBeneficiaro(@RequestBody CreateBeneficiarioDto beneficiarioDto){

        this.beneficiarioService.createNewbeneficiario(beneficiarioDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @DeleteMapping("/{idBeneficiario}")
    @Operation(summary = "Delete", description = "deleta um beneficiario", tags = "Beneficiario")
    public ResponseEntity<?> deleteBeneficiario(@RequestParam UUID idBeneficiario){
        this.beneficiarioService.deleteBeneficiarioById(idBeneficiario);

        return ResponseEntity.ok().build();
    }

}
