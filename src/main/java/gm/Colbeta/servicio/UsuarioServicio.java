package gm.Colbeta.servicio;

import gm.Colbeta.modelo.Usuario;
import gm.Colbeta.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> listarUsuarios() {
        return this.usuarioRepositorio.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario){
       return this.usuarioRepositorio.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuariosPorEmpresa(Integer idEmpresa) {
        return this.usuarioRepositorio.findByIdEmpresa(idEmpresa);
    }

    public void eliminarUsuarioPorId(Integer id){
         this.usuarioRepositorio.deleteById(id);
    }

    public Integer extraerIdxIdentificacion(Integer Nidentificacion){
        Optional<Usuario> usuario =
        this.usuarioRepositorio.findByNidentificacion(Nidentificacion);
        return usuario.map(Usuario::getIdUser).orElse(null);
    }
}
