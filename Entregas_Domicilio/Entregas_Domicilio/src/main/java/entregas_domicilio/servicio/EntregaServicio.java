package entregas_domicilio.servicio;

import entregas_domicilio.dto.EntregaDTO;
import entregas_domicilio.entidad.*;
import entregas_domicilio.repositorio.ClienteRepositorio;
import entregas_domicilio.repositorio.EntregaRepositorio;
import entregas_domicilio.repositorio.RepartidorRepositorio;
import entregas_domicilio.excepcion.RecursoNoEncontradoExcepcion;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntregaServicio {
    private final EntregaRepositorio repositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final RepartidorRepositorio repartidorRepositorio;

    public EntregaServicio(EntregaRepositorio repositorio, ClienteRepositorio clienteRepositorio, RepartidorRepositorio repartidorRepositorio) {
        this.repositorio = repositorio; this.clienteRepositorio = clienteRepositorio; this.repartidorRepositorio = repartidorRepositorio;
    }
    public List<EntregaDTO> obtenerTodos() { return repositorio.findAll().stream().map(this::convertirDTO).toList(); }
    public EntregaDTO obtenerPorId(Long id) { return convertirDTO(buscarEntrega(id)); }
    public EntregaDTO crear(EntregaDTO dto) {
        Cliente cliente = clienteRepositorio.findById(dto.getClienteId()).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Cliente no encontrado"));
        Repartidor repartidor = obtenerRepartidor(dto.getRepartidorId());
        Entrega entrega = Entrega.builder().direccion(dto.getDireccion()).estado(dto.getEstado() != null ? dto.getEstado() : EstadoEntrega.PENDIENTE).fecha(LocalDateTime.now()).cliente(cliente).repartidor(repartidor).build();
        return convertirDTO(repositorio.save(entrega));
    }
    public EntregaDTO actualizar(Long id, EntregaDTO dto) {
        Entrega entrega = buscarEntrega(id);
        Cliente cliente = clienteRepositorio.findById(dto.getClienteId()).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Cliente no encontrado"));
        entrega.setDireccion(dto.getDireccion());
        if (dto.getEstado() != null) entrega.setEstado(dto.getEstado());
        entrega.setCliente(cliente); entrega.setRepartidor(obtenerRepartidor(dto.getRepartidorId()));
        return convertirDTO(repositorio.save(entrega));
    }
    public void eliminar(Long id) { buscarEntrega(id); repositorio.deleteById(id); }
    private Entrega buscarEntrega(Long id) { return repositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Entrega no encontrada")); }
    private Repartidor obtenerRepartidor(Long id) { return id == null ? null : repartidorRepositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Repartidor no encontrado")); }
    private EntregaDTO convertirDTO(Entrega e) { return EntregaDTO.builder().id(e.getId()).direccion(e.getDireccion()).estado(e.getEstado()).fecha(e.getFecha()).clienteId(e.getCliente().getId()).repartidorId(e.getRepartidor() != null ? e.getRepartidor().getId() : null).build(); }
}
