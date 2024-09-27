package br.com.selecao.locadora.entity.comprador;

import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.entity.Lote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "comprador")
public class Comprador implements Serializable {

    @EmbeddedId
    private CompradorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empresaId")
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("loteId")
    @JoinColumn(name = "lote_id", referencedColumnName = "id")
    private Lote lote;

}


