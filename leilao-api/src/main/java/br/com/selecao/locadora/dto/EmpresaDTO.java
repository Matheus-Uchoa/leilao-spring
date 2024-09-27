package br.com.selecao.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class EmpresaDTO {

    @NotBlank(message = "A razão social pode estar vazia ou em branco.")
    private String razaoSocial;

    @NotBlank(message = "O CNPJ não pode estar vazio ou em branco.")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ inválido. Deve seguir o formato 00.000.000/0000-00")
    private String cnpj;

    private String logradouro;

    private String municipio;

    private String numero;

    private String complemento;

    private String bairro;
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido. Deve seguir o formato 00000-000")
    private String cep;

    private String telefone;

    @NotBlank(message = "O E-mail não pode estar vazio ou em branco.")
    @Email(message = "Informe um e-mail válido")
    private String email;

    private String site;

    @NotBlank(message = "O Usuário não pode estar vazio ou em branco.")
    private String usuario;

    private String senha;
}
