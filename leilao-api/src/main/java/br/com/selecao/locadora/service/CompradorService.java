package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.CompradorBO;
import br.com.selecao.locadora.dto.CompradorDTO;
import br.com.selecao.locadora.dto.reponse.CompradorResponseDTO;
import br.com.selecao.locadora.entity.comprador.Comprador;
import br.com.selecao.locadora.validation.BeanValidationUtil;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compradores")
public class CompradorService {

    @Autowired
    private CompradorBO compradorBO;
    private static final Logger logger = Logger.getLogger(UnidadeService.class);


    @GetMapping
    public ResponseEntity<List<CompradorResponseDTO>> buscarTodos() {
        try {
            List<CompradorResponseDTO> compradores = compradorBO.buscarTodos();
            return new ResponseEntity<>(compradores, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar todas os compradores", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{empresaId}/{loteId}")
    public ResponseEntity<CompradorResponseDTO> buscarPorId(@PathVariable Long empresaId, @PathVariable Long loteId) {
        try {
            CompradorResponseDTO comprador = compradorBO.buscarPorId(empresaId, loteId);
            return new ResponseEntity<>(comprador, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Comprador não encontrado com empresaId: " + empresaId + " e loteId: " + loteId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao buscar comprador com empresaId: " + empresaId + " e loteId: " + loteId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody CompradorDTO compradorDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            Comprador compradorSalvo = compradorBO.salvar(compradorDTO);
            return new ResponseEntity<>(compradorSalvo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Erro ao salvar comprador: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar comprador", e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("{empresaId}/{loteId}")
    public ResponseEntity<Object> editarComprador(@PathVariable Long empresaId, @PathVariable Long loteId, @Valid @RequestBody CompradorDTO compradorDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            Comprador compradorAtualizado = compradorBO.editar(empresaId, loteId, compradorDTO);
            return ResponseEntity.ok(compradorAtualizado);
        } catch (RuntimeException e) {
            logger.error("Erro ao editar comprador", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Erro inesperado ao editar comprador", e);
            return new ResponseEntity<>("Erro inesperado ao editar comprador", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{empresaId}/{loteId}")
    public ResponseEntity<Object> deletarComprador(@PathVariable Long empresaId, @PathVariable Long loteId) {
        try {
            compradorBO.deletar(empresaId, loteId);
            return new ResponseEntity<>("Comprador deletado com sucesso.", HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Erro ao deletar comprador", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao deletar comprador", e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
