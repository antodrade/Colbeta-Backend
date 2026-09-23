package gm.Colbeta.repositorio;

import gm.Colbeta.modelo.Usuario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByIdEmpresa(Integer idEmpresa);
    Optional<Usuario> findByNidentificacion(Integer Nidentificacion);
}
