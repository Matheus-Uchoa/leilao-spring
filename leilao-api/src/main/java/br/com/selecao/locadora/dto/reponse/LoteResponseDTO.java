package br.com.selecao.locadora.dto.reponse;

import br.com.selecao.locadora.dto.UnidadeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoteResponseDTO {
    private Long id;
    private Integer numeroLote;
    private String descricao;
    private Integer quantidade;
    private Double valorInicial;
    private UnidadeDTO unidade;
    private LeilaoSimplificadoDTO leilao;
}
