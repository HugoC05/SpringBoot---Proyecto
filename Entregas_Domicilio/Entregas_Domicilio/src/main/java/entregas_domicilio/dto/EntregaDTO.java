package entregas_domicilio.dto;

import entregas_domicilio.entidad.EstadoEntrega;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EntregaDTO {
    private Long id;
    @NotBlank(message = "La dirección es obligatoria") private String direccion;
    private EstadoEntrega estado;
    private LocalDateTime fecha;
    @NotNull(message = "El cliente es obligatorio") private Long clienteId;
    private Long repartidorId;
}
