package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.LoteDTO;
import br.com.selecao.locadora.dto.UnidadeDTO;
import br.com.selecao.locadora.dto.reponse.LoteResponseDTO;
import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.repository.LeilaoRepository;
import br.com.selecao.locadora.repository.LoteRepository;
import br.com.selecao.locadora.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.selecao.locadora.dto.reponse.LeilaoSimplificadoDTO;
import br.com.selecao.locadora.dto.reponse.VendedorSimplificadoDTO;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoteBO {

    private final LoteRepository loteRepository;

    private final LeilaoRepository leilaoRepository;

    private final UnidadeRepository unidadeRepository;
    @Autowired
    public LoteBO(LoteRepository loteRepository, LeilaoRepository leilaoRepository, UnidadeRepository unidadeRepository) {
        this.loteRepository = loteRepository;
        this.leilaoRepository = leilaoRepository;
        this.unidadeRepository = unidadeRepository;
    }


    public List<LoteResponseDTO> buscarTodos() {
        List<Lote> lotes = loteRepository.findAll();

        return lotes.stream().map(lote -> {
            return LoteResponseDTO.builder()
                    .id(lote.getId())
                    .numeroLote(lote.getNumeroLote())
                    .descricao(lote.getDescricao())
                    .quantidade(lote.getQuantidade().intValue())
                    .valorInicial(lote.getValorInicial().doubleValue())
                    .unidade(new UnidadeDTO(lote.getUnidade().getNome()))
                    .leilao(LeilaoSimplificadoDTO.builder()
                            .codigo(lote.getLeilao().getCodigo())
                            .descricao(lote.getLeilao().getDescricao())
                            .vendedor(VendedorSimplificadoDTO.builder()
                                    .razaoSocial(lote.getLeilao().getVendedor().getRazaoSocial())
                                    .cnpj(lote.getLeilao().getVendedor().getCnpj())
                                    .build())
                            .build())
                    .build();
        }).collect(Collectors.toList());
    }

    public LoteResponseDTO buscarPorId(Long id) {
        Lote lote = loteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com o ID: " + id));

        // Constrói o LoteResponseDTO com dados simplificados
        return LoteResponseDTO.builder()
                .id(lote.getId())
                .numeroLote(lote.getNumeroLote())
                .descricao(lote.getDescricao())
                .quantidade(lote.getQuantidade().intValue())
                .valorInicial(lote.getValorInicial().doubleValue()) // Converte BigDecimal para Double
                .unidade(new UnidadeDTO(lote.getUnidade().getNome()))
                .leilao(LeilaoSimplificadoDTO.builder()
                        .codigo(lote.getLeilao().getCodigo())
                        .descricao(lote.getLeilao().getDescricao())
                        .vendedor(VendedorSimplificadoDTO.builder()
                                .razaoSocial(lote.getLeilao().getVendedor().getRazaoSocial())
                                .cnpj(lote.getLeilao().getVendedor().getCnpj())
                                .build())
                        .build())
                .build();
    }


    public Lote salvar(LoteDTO loteDTO) {
        Leilao leilao = leilaoRepository.findById(loteDTO.getLeilaoId())
                .orElseThrow(() -> new RuntimeException("Leilão não encontrado com o ID: " + loteDTO.getLeilaoId()));
        Unidade unidade = unidadeRepository.findById(loteDTO.getUnidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada com o ID: " + loteDTO.getUnidadeId()));

        Lote lote = Lote.builder()
                .numeroLote(loteDTO.getNumeroLote())
                .descricao(loteDTO.getDescricao())
                .quantidade(loteDTO.getQuantidade())
                .valorInicial(loteDTO.getValorInicial())
                .leilao(leilao)
                .unidade(unidade)
                .build();

        return loteRepository.save(lote);
    }

    public Lote editar(Long id, LoteDTO loteDTO) {
        Lote lote = loteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com o ID: " + id));

        Leilao leilao = leilaoRepository.findById(loteDTO.getLeilaoId())
                .orElseThrow(() -> new RuntimeException("Leilão não encontrado com o ID: " + loteDTO.getLeilaoId()));
        Unidade unidade = unidadeRepository.findById(loteDTO.getUnidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada com o ID: " + loteDTO.getUnidadeId()));

        lote.setNumeroLote(loteDTO.getNumeroLote());
        lote.setDescricao(loteDTO.getDescricao());
        lote.setQuantidade(loteDTO.getQuantidade());
        lote.setValorInicial(loteDTO.getValorInicial());
        lote.setLeilao(leilao);
        lote.setUnidade(unidade);

        return loteRepository.save(lote);
    }

    public void deletar(Long id) {
        try {
            Lote lote = loteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Lote não encontrado com o ID: " + id));

            loteRepository.delete(lote);

        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível excluir o lote com ID: " + id + " porque ele está relacionado a um ou mais compradores.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar excluir o lote com ID: " + id, e);
        }
    }

}
