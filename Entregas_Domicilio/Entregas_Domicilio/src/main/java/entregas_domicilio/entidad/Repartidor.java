package entregas_domicilio.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "repartidores")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Repartidor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String nombre;
    @Column(nullable = false) private String telefono;
    @Column(nullable = false) private String vehiculo;
}
