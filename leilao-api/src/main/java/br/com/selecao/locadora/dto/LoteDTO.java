package br.com.selecao.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoteDTO {

    private Integer numeroLote;

    @NotBlank(message = "A descrição do lote é obrigatória.")
    private String descricao;

    @NotNull(message = "A quantidade é obrigatória.")
    @Positive(message = "A quantidade deve ser maior que zero.")
    private BigDecimal quantidade;

    @Positive(message = "O valor inicial deve ser maior que zero.")
    private BigDecimal valorInicial;

    @NotNull(message = "A unidade é obrigatória.")
    private Long unidadeId;

    @NotNull(message = "O leilão é obrigatório.")
    private Long leilaoId;
}
