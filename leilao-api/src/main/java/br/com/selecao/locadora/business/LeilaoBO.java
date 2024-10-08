package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.LeilaoDTO;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.repository.CompradorRepository;
import br.com.selecao.locadora.repository.EmpresaRepository;
import br.com.selecao.locadora.repository.LeilaoRepository;
import br.com.selecao.locadora.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeilaoBO {

    private final LeilaoRepository leilaoRepository;
    private final EmpresaRepository empresaRepository;
    private final LoteRepository loteRepository;
    private final CompradorRepository compradorRepository;

    @Autowired
    public LeilaoBO(LeilaoRepository leilaoRepository, EmpresaRepository empresaRepository, LoteRepository loteRepository, CompradorRepository compradorRepository) {
        this.leilaoRepository = leilaoRepository;
        this.empresaRepository = empresaRepository;
        this.loteRepository = loteRepository;
        this.compradorRepository = compradorRepository;
    }


    public List<Leilao> buscarTodos() {
        return leilaoRepository.findAll();
    }

    public Leilao buscarPorId(Long id) {
        Optional<Leilao> empresa = leilaoRepository.findById(id);
        return empresa.orElseThrow(() -> new RuntimeException("Leilao não encontrada com o ID: " + id));
    }

    public Leilao salvar(LeilaoDTO leilaoDTO) {
        Empresa vendedor = empresaRepository.findById(leilaoDTO.getVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado com o ID: " + leilaoDTO.getVendedor()));
        LocalDateTime inicioPrevisto = leilaoDTO.getInicioPrevisto().atTime(LocalTime.now());

        Leilao leilao = Leilao.builder()
                .codigo(leilaoDTO.getCodigo())
                .descricao(leilaoDTO.getDescricao())
                .vendedor(vendedor)
                .inicioPrevisto(inicioPrevisto)
                .build();
        return leilaoRepository.save(leilao);
    }

    public Leilao editar(Long id, LeilaoDTO leilaoDTO) {
        Leilao leilao = leilaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leilão não encontrado com o ID: " + id));

        Empresa vendedor = empresaRepository.findById(leilaoDTO.getVendedor())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o ID: " + leilaoDTO.getVendedor()));

        LocalDateTime inicioPrevisto = leilaoDTO.getInicioPrevisto().atTime(LocalTime.now());

        leilao.setCodigo(leilaoDTO.getCodigo());
        leilao.setDescricao(leilaoDTO.getDescricao());
        leilao.setInicioPrevisto(inicioPrevisto);
        leilao.setVendedor(vendedor);

        return leilaoRepository.save(leilao);
    }

    public void deletar(Long id) {
        Leilao leilao = leilaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leilão não encontrado com o ID: " + id));

        List<Lote> lotes = loteRepository.findByLeilao(leilao);

        boolean lotesComprados = lotes.stream()
                .anyMatch(lote -> compradorRepository.existsByLoteId(lote.getId()));

        if (lotesComprados) {
            throw new RuntimeException("Não é possível excluir o leilão, pois um ou mais lotes estão comprados.");
        }

        if (!lotes.isEmpty()) {
            loteRepository.deleteAll(lotes);
        }
        leilaoRepository.delete(leilao);
    }
}
