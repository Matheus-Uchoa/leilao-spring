package br.com.selecao.locadora.entity.comprador;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompradorId implements Serializable {

    @Column(name = "empresa_id")
    private Long empresaId;

    @Column(name = "lote_id")
    private Long loteId;

    public CompradorId() {}

    public CompradorId(Long empresaId, Long loteId) {
        this.empresaId = empresaId;
        this.loteId = loteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompradorId that = (CompradorId) o;
        return Objects.equals(empresaId, that.empresaId) &&
                Objects.equals(loteId, that.loteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empresaId, loteId);
    }
}