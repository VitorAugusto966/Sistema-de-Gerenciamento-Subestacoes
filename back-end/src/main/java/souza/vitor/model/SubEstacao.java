package souza.vitor.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sub_estacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubEstacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sub")
    private Long id;

    @Column(name = "codigo", length = 3, nullable = false)
    private String codigo;

    @Column(name = "nome", length = 100)
    private String nome;


    @Column(name = "latitude", precision = 15, scale = 13, nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 15, scale = 13, nullable = false)
    private BigDecimal longitude;

    @OneToMany(mappedBy = "subestacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RedeMT> redesMT;
    
}
