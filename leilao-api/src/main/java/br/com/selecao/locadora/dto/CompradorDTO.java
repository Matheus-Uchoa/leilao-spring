package br.com.selecao.locadora.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompradorDTO {
    @NotNull(message = "O ID da empresa não pode ser nulo.")
    private Long empresaId;

    @NotNull(message = "O ID do lote não pode ser nulo.")
    private Long loteId;

}
