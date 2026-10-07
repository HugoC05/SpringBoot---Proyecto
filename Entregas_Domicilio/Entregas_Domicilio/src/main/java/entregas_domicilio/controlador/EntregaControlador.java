package entregas_domicilio.controlador;
import entregas_domicilio.dto.EntregaDTO;
import entregas_domicilio.servicio.EntregaServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entregas")
public class EntregaControlador {
    private final EntregaServicio servicio;
    public EntregaControlador(EntregaServicio servicio) { this.servicio = servicio; }
    @GetMapping public List<EntregaDTO> obtenerTodos() { return servicio.obtenerTodos(); }
    @GetMapping("/{id}") public EntregaDTO obtenerPorId(@PathVariable Long id) { return servicio.obtenerPorId(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public EntregaDTO crear(@Valid @RequestBody EntregaDTO dto) { return servicio.crear(dto); }
    @PutMapping("/{id}") public EntregaDTO actualizar(@PathVariable Long id, @Valid @RequestBody EntregaDTO dto) { return servicio.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { servicio.eliminar(id); }
}
