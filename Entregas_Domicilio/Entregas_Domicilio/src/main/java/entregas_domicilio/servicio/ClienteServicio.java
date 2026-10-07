package entregas_domicilio.servicio;

import entregas_domicilio.dto.ClienteDTO;
import entregas_domicilio.entidad.Cliente;
import entregas_domicilio.repositorio.ClienteRepositorio;
import entregas_domicilio.excepcion.RecursoNoEncontradoExcepcion;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServicio {
    private final ClienteRepositorio repositorio;
    public ClienteServicio(ClienteRepositorio repositorio) { this.repositorio = repositorio; }
    public List<ClienteDTO> obtenerTodos() { return repositorio.findAll().stream().map(this::convertirDTO).toList(); }
    public ClienteDTO obtenerPorId(Long id) { return convertirDTO(buscarEntidad(id)); }
    public ClienteDTO crear(ClienteDTO dto) {
        Cliente cliente = Cliente.builder().nombre(dto.getNombre()).telefono(dto.getTelefono()).direccion(dto.getDireccion()).build();
        return convertirDTO(repositorio.save(cliente));
    }
    public ClienteDTO actualizar(Long id, ClienteDTO dto) {
        Cliente cliente = buscarEntidad(id);
        cliente.setNombre(dto.getNombre()); cliente.setTelefono(dto.getTelefono()); cliente.setDireccion(dto.getDireccion());
        return convertirDTO(repositorio.save(cliente));
    }
    public void eliminar(Long id) { buscarEntidad(id); repositorio.deleteById(id); }
    private Cliente buscarEntidad(Long id) { return repositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Cliente no encontrado")); }
    private ClienteDTO convertirDTO(Cliente c) { return ClienteDTO.builder().id(c.getId()).nombre(c.getNombre()).telefono(c.getTelefono()).direccion(c.getDireccion()).build(); }
}
