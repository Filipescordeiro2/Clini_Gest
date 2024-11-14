package com.br.TechMed.controller.profissional;

import com.br.TechMed.dto.agenda.AgendaDetalhadaDTO;
import com.br.TechMed.dto.profissional.EspecialidadeProfissionalDTO;
import com.br.TechMed.dto.profissional.LoginSenhaProfissionalDTO;
import com.br.TechMed.dto.profissional.ProfissionalDTO;
import com.br.TechMed.service.servicos.profissional.EspecialidadeProfissionalService;
import com.br.TechMed.service.servicos.profissional.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos profissionais no sistema.
 */
@RestController
    @RequestMapping("/profissionais")
@CrossOrigin(origins = "http://localhost:3000") // Permite apenas o domínio do frontend
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private EspecialidadeProfissionalService especialidadeProfissionalService;

    /**
     * Cria um novo profissional.
     *
     * @param profissionalDTO Dados do profissional a ser criado.
     * @return Dados do profissional criado.
     */
    @PostMapping
    public ResponseEntity<ProfissionalDTO> createProfissional(@RequestBody ProfissionalDTO profissionalDTO) {
        ProfissionalDTO createdProfissional = profissionalService.cadastrarProfissional(profissionalDTO);
        return ResponseEntity.ok(createdProfissional);
    }

    /**
     * Autentica um profissional pelo login e senha.
     *
     * @param loginSenhaDTO Dados de login e senha do profissional.
     * @return os dados do profissional autenticado.
     */
    @PostMapping("/autenticar")
    public ResponseEntity<ProfissionalDTO> autenticarProfissional(@RequestBody LoginSenhaProfissionalDTO loginSenhaDTO) {
        ProfissionalDTO profissionalAutenticado = profissionalService.autenticarProfissional(loginSenhaDTO);
        return ResponseEntity.ok(profissionalAutenticado);
    }

    /**
     * Recupera a agenda de um profissional.
     *
     * @param profissionalId o ID do profissional.
     * @return a lista de agendas do profissional.
     */
    @GetMapping("/agenda")
    public ResponseEntity<List<AgendaDetalhadaDTO>> getAgendaByProfissional(
            @RequestParam(required = false) Long profissionalId,
            @RequestParam Long clinicaId,
            @RequestParam(required = false) String statusAgenda,
            @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) LocalTime hora,
            @RequestParam(required = false) String nomeProfissional,
            @RequestParam(required = false) String nomeEspecialidade) {
        List<AgendaDetalhadaDTO> agenda = profissionalService.getAgendaByProfissional(profissionalId, clinicaId, statusAgenda, data, hora, nomeProfissional,nomeEspecialidade);
        return ResponseEntity.ok(agenda);
    }

    /**
     * Retorna a quantidade de profissionais cadastrados.
     *
     * @return a quantidade de profissionais cadastrados
     */
    @GetMapping("/contar")
    public ResponseEntity<Long> contarProfissionais() {
        long quantidadeProfissionais = profissionalService.contarProfissionais();
        return ResponseEntity.ok(quantidadeProfissionais);
    }

    @PatchMapping("/inativarProfissional/{id}")
    public void updateStatus(@PathVariable("id") Long id) {
        profissionalService.atualizarStatusProfissional(id);
    }

    @GetMapping("/especialidadesProfissional")
    public ResponseEntity<List<EspecialidadeProfissionalDTO>> buscarEspecialidadePorProfissionalId(@RequestParam Long profissionalId) {
        List<EspecialidadeProfissionalDTO> especialidades = especialidadeProfissionalService.buscarEspecialidadePorProfissionalId(profissionalId);
        return ResponseEntity.ok(especialidades);
    }

    /**
     * Lista todos os profissionais ativos.
     *
     * @return a lista de profissionais ativos
     */
    @GetMapping("/ativos")
    public ResponseEntity<List<ProfissionalDTO>> listarProfissionaisAtivos() {
        List<ProfissionalDTO> profissionaisAtivos = profissionalService.listarProfissionaisAtivos();
        return ResponseEntity.ok(profissionaisAtivos);
    }

}