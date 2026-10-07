package entregas_domicilio.repositorio;
import entregas_domicilio.entidad.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EntregaRepositorio extends JpaRepository<Entrega, Long> {}
