package entregas_domicilio.controlador;
import entregas_domicilio.dto.ClienteDTO;
import entregas_domicilio.servicio.ClienteServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {
    private final ClienteServicio servicio;
    public ClienteControlador(ClienteServicio servicio) { this.servicio = servicio; }
    @GetMapping public List<ClienteDTO> obtenerTodos() { return servicio.obtenerTodos(); }
    @GetMapping("/{id}") public ClienteDTO obtenerPorId(@PathVariable Long id) { return servicio.obtenerPorId(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public ClienteDTO crear(@Valid @RequestBody ClienteDTO dto) { return servicio.crear(dto); }
    @PutMapping("/{id}") public ClienteDTO actualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) { return servicio.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { servicio.eliminar(id); }
}
