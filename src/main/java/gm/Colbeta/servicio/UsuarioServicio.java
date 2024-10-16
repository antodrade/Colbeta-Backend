package gm.Colbeta.servicio;

import gm.Colbeta.modelo.Usuario;
import gm.Colbeta.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}


