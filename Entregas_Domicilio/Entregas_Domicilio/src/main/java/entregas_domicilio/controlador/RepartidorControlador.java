package entregas_domicilio.controlador;
import entregas_domicilio.dto.RepartidorDTO;
import entregas_domicilio.servicio.RepartidorServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/repartidores")
public class RepartidorControlador {
    private final RepartidorServicio servicio;
    public RepartidorControlador(RepartidorServicio servicio) { this.servicio = servicio; }
    @GetMapping public List<RepartidorDTO> obtenerTodos() { return servicio.obtenerTodos(); }
    @GetMapping("/{id}") public RepartidorDTO obtenerPorId(@PathVariable Long id) { return servicio.obtenerPorId(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public RepartidorDTO crear(@Valid @RequestBody RepartidorDTO dto) { return servicio.crear(dto); }
    @PutMapping("/{id}") public RepartidorDTO actualizar(@PathVariable Long id, @Valid @RequestBody RepartidorDTO dto) { return servicio.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { servicio.eliminar(id); }
}
