package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.UnidadeBO;
import br.com.selecao.locadora.dto.UnidadeDTO;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.validation.BeanValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.jboss.logging.Logger;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/unidades")
public class UnidadeService {

    @Autowired
    private UnidadeBO unidadeBO;
    private static final Logger logger = Logger.getLogger(UnidadeService.class);

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        try {
            return new ResponseEntity<>(unidadeBO.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar todas as unidades", e);
            return new ResponseEntity<>("Erro ao buscar unidades", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        try {
            Unidade unidade = unidadeBO.buscarPorId(id);
            return ResponseEntity.ok(unidade);
        } catch (RuntimeException e) {
            logger.error("Erro ao buscar unidade com ID: " + id, e);
            return new ResponseEntity<>("Unidade não encontrada com o ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao buscar unidade com ID: " + id, e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody UnidadeDTO unidadeDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            Unidade unidade = unidadeBO.salvar(unidadeDTO);
            return new ResponseEntity<>(unidade, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao salvar unidade", e);
            return new ResponseEntity<>("Erro ao salvar unidade", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable Long id,@Valid @RequestBody UnidadeDTO unidadeDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Unidade unidade = unidadeBO.editar(id, unidadeDTO);
            return ResponseEntity.ok(unidade);
        } catch (RuntimeException e) {
            logger.error("Erro ao editar unidade com ID: " + id, e);
            return new ResponseEntity<>("Unidade não encontrada com o ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao editar unidade com ID: " + id, e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        try {
            unidadeBO.deletar(id);
            return new ResponseEntity<>("Unidade deletada com sucesso", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            logger.error("Erro ao deletar unidade com ID: " + id, e);
            return new ResponseEntity<>("Unidade não encontrada com o ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao deletar unidade com ID: " + id, e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}