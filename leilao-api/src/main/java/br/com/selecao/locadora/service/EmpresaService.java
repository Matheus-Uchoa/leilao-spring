package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.EmpresaBO;
import br.com.selecao.locadora.dto.EmpresaDTO;
import br.com.selecao.locadora.entity.Empresa;

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
@RequestMapping(value = "/empresas")
public class EmpresaService {

    @Autowired
    private EmpresaBO empresaBO;
    private static final Logger logger = Logger.getLogger(EmpresaService.class);

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        try {
            return new ResponseEntity<>(empresaBO.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar todas as empresas", e);
            return new ResponseEntity<>("Erro ao buscar empresas", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        try {
            Empresa empresa = empresaBO.buscarPorId(id);
            return ResponseEntity.ok(empresa);
        } catch (RuntimeException e) {
            logger.error("Erro ao buscar unidade com ID: " + id, e);
            return new ResponseEntity<>("Empresa não encontrada com o ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao buscar unidade com ID: " + id, e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody EmpresaDTO empresaDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            empresaBO.validar(empresaDTO, null);
            Empresa empresa = empresaBO.salvar(empresaDTO);
            return new ResponseEntity<>(empresa, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Erro ao salvar empresa", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Erro ao salvar empresa", e);
            return new ResponseEntity<>("Erro ao salvar empresa", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable Long id, @Valid @RequestBody EmpresaDTO empresaDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = BeanValidationUtil.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Empresa empresa = empresaBO.editar(id, empresaDTO);
            return ResponseEntity.ok(empresa);
        } catch (RuntimeException e) {
            logger.error("Erro ao salvar empresa", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Erro ao salvar empresa", e);
            return new ResponseEntity<>("Erro ao salvar empresa", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        try {
            empresaBO.deletar(id);
            return new ResponseEntity<>("Empresa deletada com sucesso", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro inesperado ao deletar unidade com ID: " + id, e);
            return new ResponseEntity<>("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}