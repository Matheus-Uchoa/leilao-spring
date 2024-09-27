package br.com.selecao.locadora.service;


import br.com.selecao.locadora.business.LoteBO;
import br.com.selecao.locadora.dto.LoteDTO;
import br.com.selecao.locadora.dto.reponse.LoteResponseDTO;
import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.validation.BeanValidationUtil;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/lotes")
public class LoteService {
    private static final Logger logger = Logger.getLogger(LoteService.class);

    @Autowired
    private LoteBO loteBO;

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        try {
            return new ResponseEntity<>(loteBO.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os lotes", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        try {
            LoteResponseDTO loteDTO = loteBO.buscarPorId(id); // Busca o LoteResponseDTO
            return new ResponseEntity<>(loteDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Erro inesperado ao buscar lote", e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody LoteDTO loteDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            Lote loteSalvo = loteBO.salvar(loteDTO);
            return new ResponseEntity<>(loteSalvo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Erro ao salvar lote", e);
            return new ResponseEntity<>("Erro ao salvar lote", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar lote", e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable Long id, @Valid @RequestBody LoteDTO loteDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            Lote loteEditado = loteBO.editar(id, loteDTO);
            return ResponseEntity.ok(loteEditado);
        } catch (RuntimeException e) {
            logger.error("Erro ao editar lote com ID: " + id, e);
            return new ResponseEntity<>("Lote não encontrado com o ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao editar lote", e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        try {
            loteBO.deletar(id);
            return new ResponseEntity<>("Lote deletado com sucesso", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Erro inesperado ao deletar lote", e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
