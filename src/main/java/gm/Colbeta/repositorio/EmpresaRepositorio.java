package gm.Colbeta.repositorio;

import gm.Colbeta.modelo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Integer> {
}
