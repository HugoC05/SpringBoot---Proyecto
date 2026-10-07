package entregas_domicilio.entidad;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Entrega {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String direccion;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private EstadoEntrega estado;
    @Column(nullable = false) private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "cliente_id", nullable = false) private Cliente cliente;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "repartidor_id") private Repartidor repartidor;
}
