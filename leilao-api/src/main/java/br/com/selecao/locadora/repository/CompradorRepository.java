package br.com.selecao.locadora.repository;

import br.com.selecao.locadora.entity.comprador.Comprador;
import br.com.selecao.locadora.entity.comprador.CompradorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, CompradorId> {
    boolean existsByLoteId(Long loteId);
    boolean existsByLoteIdAndEmpresaIdNot(Long loteId, Long empresaId);

}
