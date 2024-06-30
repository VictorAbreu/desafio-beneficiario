package br.com.victorabreu.beneficios.services;

import br.com.victorabreu.beneficios.controller.dto.BeneficiarioReturnDTO;
import br.com.victorabreu.beneficios.controller.dto.CreateBeneficiarioDto;
import br.com.victorabreu.beneficios.controller.dto.UpdateBeneficiarioDto;
import br.com.victorabreu.beneficios.entities.Beneficiario;
import br.com.victorabreu.beneficios.repositories.BeneficiarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;

    private final DocumentoService documentoService;

    @Transactional

    public void createNewbeneficiario(CreateBeneficiarioDto beneficiarioDto){

        Optional<Beneficiario> beneficiarioOpcional = this.beneficiarioRepository.findByName(beneficiarioDto.getName());
        if(beneficiarioOpcional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Date now = Date.from(Instant.now());
        Beneficiario newBeneficiario = new Beneficiario();
        newBeneficiario.setName(beneficiarioDto.getName());
        newBeneficiario.setTelefone(beneficiarioDto.getTelefone());
        newBeneficiario.setDataNascimento(beneficiarioDto.getDataNascimento());
        newBeneficiario.setDataInclusao(now);
        newBeneficiario.setDataAtualizacao(now);

        Beneficiario savedBeneficiario = this.beneficiarioRepository.save(newBeneficiario);

        if(!beneficiarioDto.getDocumento().isEmpty()){
            this.documentoService.createDocumentoS(savedBeneficiario.getIdBeneficiario(), beneficiarioDto.getDocumento());
        }

    }

    public List<BeneficiarioReturnDTO> getAllbeneficiarios() {

        List<BeneficiarioReturnDTO> listaBeneficariosRetorno = new ArrayList<>();
        List<Beneficiario> all = this.beneficiarioRepository.findAll();
        if(all.isEmpty()){
            return listaBeneficariosRetorno;
        }

        all.forEach( beneficiario -> {
            BeneficiarioReturnDTO dto = BeneficiarioReturnDTO.builder()
                    .idBeneficiario(beneficiario.getIdBeneficiario())
                    .name(beneficiario.getName())
                    .telefone(beneficiario.getTelefone())
                    .dataNascimento(beneficiario.getDataNascimento())
                    .dataAtualizacao(beneficiario.getDataAtualizacao())
                    .dataInclusao(beneficiario.getDataInclusao())
                    .build();
            listaBeneficariosRetorno.add(dto);
        });

        return listaBeneficariosRetorno;

    }

    public void deleteBeneficiarioById(UUID idBeneficiario) {
        Optional<Beneficiario> beneficiarioOptional = this.beneficiarioRepository.findById(idBeneficiario);
        if(!beneficiarioOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.beneficiarioRepository.delete(beneficiarioOptional.get());

    }

    public BeneficiarioReturnDTO updateBeneficiario(UpdateBeneficiarioDto updateBeneficiarioDto) {
        Optional<Beneficiario> beneficiarioOptional = this.beneficiarioRepository.findById(updateBeneficiarioDto.getIdBeneficiario());
        if(!beneficiarioOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Beneficiario beneficiarioOriginal = beneficiarioOptional.get();
        beneficiarioOriginal.setDataAtualizacao(Date.from(Instant.now()));
        beneficiarioOriginal.setName(updateBeneficiarioDto.getName());
        beneficiarioOriginal.setTelefone(updateBeneficiarioDto.getTelefone());
        beneficiarioOriginal.setDataNascimento(updateBeneficiarioDto.getDataNascimento());
        Beneficiario beneficiarioUpdated = this.beneficiarioRepository.save(beneficiarioOriginal);

        return BeneficiarioReturnDTO.builder()
                .name(beneficiarioUpdated.getName())
                .telefone(beneficiarioUpdated.getTelefone())
                .dataNascimento(beneficiarioUpdated.getDataNascimento())
                .dataInclusao(beneficiarioUpdated.getDataInclusao())
                .dataAtualizacao(beneficiarioUpdated.getDataAtualizacao())
                .build();


    }
}
