package com.br.TechMed.service.servicos.clinica;

import com.br.TechMed.dto.Clinica.ClinicaDTO;
import com.br.TechMed.dto.profissional.ProfissionalDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClinicaService {
    ClinicaDTO cadastrarClinica(ClinicaDTO clinicaDTO);
    List<ClinicaDTO> listarTodasClinicas();
    long contarClinicas();
    void atualizarStatusClinica(Long id);
    List<ClinicaDTO> listarClinicasAtivos();


}
