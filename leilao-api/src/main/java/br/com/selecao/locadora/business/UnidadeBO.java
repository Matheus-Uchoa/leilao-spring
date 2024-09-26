package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.UnidadeDTO;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeBO {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<Unidade> buscarTodos(){
        return unidadeRepository.findAll();
    }

    public Unidade buscarPorId(Long id) {
        Optional<Unidade> unidade = unidadeRepository.findById(id);
        // Retorna a unidade se encontrada, caso contrário, lança uma exceção ou retorna null
        return unidade.orElseThrow(() -> new RuntimeException("Unidade não encontrada com o ID: " + id));
    }

    public Unidade salvar(UnidadeDTO unidadeDTO) {
        Unidade unidade = Unidade.builder()
                .nome(unidadeDTO.getNome())
                .build();
        return unidadeRepository.save(unidade);
    }

    public Unidade editar(Long id, UnidadeDTO unidadeDTO) {
        Unidade unidadeExistente = unidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada com o ID: " + id));

        unidadeExistente.setNome(unidadeDTO.getNome());

        return unidadeRepository.save(unidadeExistente);
    }

    public void deletar(Long id) {
        Unidade unidadeExistente = unidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada com o ID: " + id));
        unidadeRepository.delete(unidadeExistente);
    }



}
