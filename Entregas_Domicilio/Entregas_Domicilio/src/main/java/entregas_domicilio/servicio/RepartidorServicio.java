package entregas_domicilio.servicio;

import entregas_domicilio.dto.RepartidorDTO;
import entregas_domicilio.entidad.Repartidor;
import entregas_domicilio.repositorio.RepartidorRepositorio;
import entregas_domicilio.excepcion.RecursoNoEncontradoExcepcion;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepartidorServicio {
    private final RepartidorRepositorio repositorio;
    public RepartidorServicio(RepartidorRepositorio repositorio) { this.repositorio = repositorio; }
    public List<RepartidorDTO> obtenerTodos() { return repositorio.findAll().stream().map(this::convertirDTO).toList(); }
    public RepartidorDTO obtenerPorId(Long id) { return convertirDTO(buscarEntidad(id)); }
    public RepartidorDTO crear(RepartidorDTO dto) {
        Repartidor r = Repartidor.builder().nombre(dto.getNombre()).telefono(dto.getTelefono()).vehiculo(dto.getVehiculo()).build();
        return convertirDTO(repositorio.save(r));
    }
    public RepartidorDTO actualizar(Long id, RepartidorDTO dto) {
        Repartidor r = buscarEntidad(id);
        r.setNombre(dto.getNombre()); r.setTelefono(dto.getTelefono()); r.setVehiculo(dto.getVehiculo());
        return convertirDTO(repositorio.save(r));
    }
    public void eliminar(Long id) { buscarEntidad(id); repositorio.deleteById(id); }
    private Repartidor buscarEntidad(Long id) { return repositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Repartidor no encontrado")); }
    private RepartidorDTO convertirDTO(Repartidor r) { return RepartidorDTO.builder().id(r.getId()).nombre(r.getNombre()).telefono(r.getTelefono()).vehiculo(r.getVehiculo()).build(); }
}
