package gm.Colbeta.servicio;

import gm.Colbeta.modelo.Usuario;

import java.util.List;

public interface IUsuarioServicio {
    public List<Usuario> listarUsuarios();
    public Usuario guardarUsuario(Usuario usuario);
}


