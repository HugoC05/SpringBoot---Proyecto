package entregas_domicilio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteDTO {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio") private String nombre;
    @NotBlank(message = "El teléfono es obligatorio") private String telefono;
    @NotBlank(message = "La dirección es obligatoria") private String direccion;
}
