package souza.vitor.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author vitor
 */

@Entity
@Table(name = "redemt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RedeMT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_redeMT")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sub")
    @JsonIgnore
    private SubEstacao subestacao;

    @Column(name = "codigo", length = 5, nullable = false)
    private String codigo;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "tensao_nominal", precision = 5, scale = 2)
    private BigDecimal tensaoNominal;
}