package entregas_domicilio.repositorio;
import entregas_domicilio.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {}
