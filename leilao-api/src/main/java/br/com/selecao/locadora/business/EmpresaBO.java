package br.com.selecao.locadora.business;


import br.com.selecao.locadora.dto.EmpresaDTO;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaBO {

    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder; //passwordEncoder.matches(rawPassword, hashedPassword)

    @Autowired
    public EmpresaBO(EmpresaRepository empresaRepository, PasswordEncoder passwordEncoder) {
        this.empresaRepository = empresaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Empresa> buscarTodos(){
        return empresaRepository.findAll();
    }

    public Empresa buscarPorId(Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.orElseThrow(() -> new RuntimeException("Empresa não encontrada com o ID: " + id));
    }
    public Empresa salvar(EmpresaDTO empresaDTO) {
        validar(empresaDTO,null);
        String senhaHash = passwordEncoder.encode(empresaDTO.getSenha());

        Empresa empresa = Empresa.builder()
                .razaoSocial(empresaDTO.getRazaoSocial())
                .cnpj(empresaDTO.getCnpj())
                .logradouro(empresaDTO.getLogradouro())
                .municipio(empresaDTO.getMunicipio())
                .numero(empresaDTO.getNumero())
                .complemento(empresaDTO.getComplemento())
                .bairro(empresaDTO.getBairro())
                .cep(empresaDTO.getCep())
                .telefone(empresaDTO.getTelefone())
                .email(empresaDTO.getEmail())
                .site(empresaDTO.getSite())
                .usuario(empresaDTO.getUsuario())
                .senha(senhaHash)
                .build();

        return empresaRepository.save(empresa);
    }
    public Empresa editar(Long id, EmpresaDTO empresaDTO) {
        validar(empresaDTO, id);
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o ID: " + id));

        empresa.setRazaoSocial(empresaDTO.getRazaoSocial());
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setLogradouro(empresaDTO.getLogradouro());
        empresa.setMunicipio(empresaDTO.getMunicipio());
        empresa.setNumero(empresaDTO.getNumero());
        empresa.setComplemento(empresaDTO.getComplemento());
        empresa.setBairro(empresaDTO.getBairro());
        empresa.setCep(empresaDTO.getCep());
        empresa.setTelefone(empresaDTO.getTelefone());
        empresa.setEmail(empresaDTO.getEmail());
        empresa.setSite(empresaDTO.getSite());
        empresa.setUsuario(empresaDTO.getUsuario());

        if (empresaDTO.getSenha() != null && !empresaDTO.getSenha().isBlank()) {
            String senhaHash = passwordEncoder.encode(empresaDTO.getSenha());
            empresa.setSenha(senhaHash);
        }

        return empresaRepository.save(empresa);
    }

    public void deletar(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada com o ID: " + id));
        empresaRepository.delete(empresa);
    }

    public void validar(EmpresaDTO empresaDTO, Long id) {
        // Validação do CNPJ
        Optional<Empresa> empresaExistentePorCNPJ = empresaRepository.findByCnpj(empresaDTO.getCnpj());
        if (empresaExistentePorCNPJ.isPresent() && (id == null || !empresaExistentePorCNPJ.get().getId().equals(id))) {
            throw new RuntimeException("CNPJ já cadastrado por outra empresa.");
        }

        // Validação do Usuário
        Optional<Empresa> empresaExistentePorUsuario = empresaRepository.findByUsuario(empresaDTO.getUsuario());
        if (empresaExistentePorUsuario.isPresent() && (id == null || !empresaExistentePorUsuario.get().getId().equals(id))) {
            throw new RuntimeException("Usuário já cadastrado por outra empresa.");
        }
    }

}
