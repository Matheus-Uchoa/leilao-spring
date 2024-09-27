package br.com.selecao.locadora.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeilaoDTO {

    @NotNull(message = "O código não pode estar nulo.")
    private Integer codigo;

    @NotBlank(message = "A descrição não pode estar vazia.")
    private String descricao;

    @NotNull(message = "O vendedor é obrigatório.")
    private Long vendedor;

    @NotNull(message = "A data de início prevista é obrigatória.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate inicioPrevisto;
}
