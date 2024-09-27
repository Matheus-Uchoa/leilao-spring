package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.CompradorDTO;
import br.com.selecao.locadora.dto.reponse.CompradorResponseDTO;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.entity.comprador.Comprador;
import br.com.selecao.locadora.entity.comprador.CompradorId;
import br.com.selecao.locadora.repository.CompradorRepository;
import br.com.selecao.locadora.repository.EmpresaRepository;
import br.com.selecao.locadora.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompradorBO {

    private final CompradorRepository compradorRepository;
    private final LoteRepository loteRepository;
    private final EmpresaRepository empresaRepository;

    @Autowired
    public CompradorBO(CompradorRepository compradorRepository, LoteRepository loteRepository, EmpresaRepository empresaRepository) {
        this.compradorRepository = compradorRepository;
        this.loteRepository = loteRepository;
        this.empresaRepository = empresaRepository;
    }

    public List<CompradorResponseDTO> buscarTodos() {
        List<Comprador> compradores = compradorRepository.findAll();

        return compradores.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }


    public CompradorResponseDTO buscarPorId(Long empresaId, Long loteId) {
        Comprador comprador = compradorRepository.findById(new CompradorId(empresaId, loteId))
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        return converterParaDTO(comprador);
    }


    public Comprador salvar(CompradorDTO compradorDTO) {
        Lote lote = loteRepository.findById(compradorDTO.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com o ID: " + compradorDTO.getLoteId()));

        boolean loteJaComprado = compradorRepository.existsByLoteId(lote.getId());

        if (loteJaComprado) {
            throw new RuntimeException("O lote já foi comprado por outra empresa dentro deste leilão.");
        }

        Empresa empresa = empresaRepository.findById(compradorDTO.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o ID: " + compradorDTO.getEmpresaId()));

        CompradorId compradorId = new CompradorId(empresa.getId(), lote.getId());

        Comprador comprador = Comprador.builder()
                .id(compradorId)
                .empresa(empresa)
                .lote(lote)
                .build();

        return compradorRepository.save(comprador);
    }

    public Comprador editar(Long empresaId, Long loteId, CompradorDTO compradorDTO) {
        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com o ID: " + loteId));

        CompradorId compradorId = new CompradorId(empresaId, loteId);
        Comprador compradorExistente = compradorRepository.findById(compradorId)
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado com a combinação de Empresa ID: " + empresaId + " e Lote ID: " + loteId));

        Empresa empresa = empresaRepository.findById(compradorDTO.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o ID: " + compradorDTO.getEmpresaId()));

        boolean loteJaComprado = compradorRepository.existsByLoteIdAndEmpresaIdNot(lote.getId(), empresaId);

        if (loteJaComprado) {
            throw new RuntimeException("O lote já foi comprado por outra empresa.");
        }

        compradorExistente.setEmpresa(empresa);
        compradorExistente.setLote(lote);

        return compradorRepository.save(compradorExistente);
    }

    public void deletar(Long empresaId, Long loteId) {
        CompradorId compradorId = new CompradorId(empresaId, loteId);
        Comprador compradorExistente = compradorRepository.findById(compradorId)
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado com a combinação de Empresa ID: " + empresaId + " e Lote ID: " + loteId));

        try {
            // Exclui apenas o registro da tabela comprador
            compradorRepository.delete(compradorExistente);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao tentar excluir o comprador. Verifique as restrições de integridade do banco de dados.");
        }
    }


    public CompradorResponseDTO converterParaDTO(Comprador comprador) {
        return CompradorResponseDTO.builder()
                .empresaId(comprador.getEmpresa().getId())
                .razaoSocial(comprador.getEmpresa().getRazaoSocial())
                .cnpj(comprador.getEmpresa().getCnpj())
                .leilaoId(comprador.getLote().getLeilao().getId())
                .codigoLeilao(comprador.getLote().getLeilao().getCodigo())
                .descricaoLeilao(comprador.getLote().getLeilao().getDescricao())
                .loteId(comprador.getLote().getId())
                .descricao(comprador.getLote().getDescricao())
                .numeroLote(comprador.getLote().getNumeroLote())
                .valorInicial(comprador.getLote().getValorInicial().doubleValue())
                .build();
    }


}
