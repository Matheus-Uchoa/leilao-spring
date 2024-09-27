package br.com.selecao.locadora.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompradorResponseDTO {
    private Long empresaId;
    private String razaoSocial;
    private String cnpj;

    private Long leilaoId;
    private Integer codigoLeilao;
    private String descricaoLeilao;


    private Long loteId;
    private String descricao;
    private Integer numeroLote;
    private Double valorInicial;
}
