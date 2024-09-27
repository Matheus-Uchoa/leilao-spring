package br.com.selecao.locadora.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendedorSimplificadoDTO {
    private String razaoSocial;
    private String cnpj;
}
