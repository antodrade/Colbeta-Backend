package gm.Colbeta.repositorio;

import gm.Colbeta.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
    Empleado findByUsername(String username);
}
