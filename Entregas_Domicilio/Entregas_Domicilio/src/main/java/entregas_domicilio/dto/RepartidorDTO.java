package entregas_domicilio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RepartidorDTO {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio") private String nombre;
    @NotBlank(message = "El teléfono es obligatorio") private String telefono;
    @NotBlank(message = "El vehículo es obligatorio") private String vehiculo;
}
