package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LeilaoBO;
import br.com.selecao.locadora.dto.LeilaoDTO;
import br.com.selecao.locadora.entity.Leilao;
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
@RequestMapping(value = "/leiloes")
public class LeilaoService {
    @Autowired
    private LeilaoBO leilaoBO;
    private static final Logger logger = Logger.getLogger(EmpresaService.class);

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        try {
            return new ResponseEntity<>(leilaoBO.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar todas os leilões ", e);
            return new ResponseEntity<>("Erro ao buscar leilões", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        try {
            Leilao leilao = leilaoBO.buscarPorId(id);
            return ResponseEntity.ok(leilao);
        } catch (RuntimeException e) {
            logger.error("Erro ao buscar leilão com ID: " + id, e);
            return new ResponseEntity<>("Leilão não encontrada com o ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao buscar leilão com ID: " + id, e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody LeilaoDTO leilaoDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Leilao leilao = leilaoBO.salvar(leilaoDTO);
            return new ResponseEntity<>(leilao, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Erro ao salvar leilão", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar leilão", e);
            return new ResponseEntity<>("Erro inesperado ao salvar leilão", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable Long id, @Valid @RequestBody LeilaoDTO leilaoDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Leilao leilao = leilaoBO.editar(id, leilaoDTO);
            return ResponseEntity.ok(leilao);
        } catch (RuntimeException e) {
            logger.error("Erro ao editar leilão", e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar leilão", e);
            return new ResponseEntity<>("Erro inesperado ao editar o leilão", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        try {
            leilaoBO.deletar(id);
            return new ResponseEntity<>("Leilão deletado com sucesso", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            logger.error("Erro ao deletar leilão com ID: {}", id, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao deletar leilão com ID: {}", id, e);
            return new ResponseEntity<>("Erro inesperado ao deletar o leilão", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
