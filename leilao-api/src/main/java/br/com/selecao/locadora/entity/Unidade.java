package br.com.selecao.locadora.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "unidade")
@SequenceGenerator(name = "seq_unidade", sequenceName = "seq_unidade", allocationSize = 1)
public class Unidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Alterando a estratégia para auto incremento
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    private Long id;


    @Column(name = "NOME")
    private String nome;

    @Column(name = "createdat", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}

