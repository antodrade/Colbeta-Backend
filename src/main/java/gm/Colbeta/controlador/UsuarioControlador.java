package gm.Colbeta.controlador;

import gm.Colbeta.modelo.Usuario;
import gm.Colbeta.servicio.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost/hole
@RequestMapping("hole")
@CrossOrigin(value = "http://localhost:4200")
public class UsuarioControlador {

   private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios(){
        List<Usuario> usuarios = this.usuarioServicio.listarUsuarios();
        logger.info("Usuarios obtenidos");
        usuarios.forEach((usuario -> logger.info(usuario.toString())));
        return usuarios;
    }

    @PostMapping("/usuarios")
    public Usuario agregarProducto(@RequestBody Usuario usuario){
        logger.info("Producto a agregar: "+ usuario);
        return this.usuarioServicio.guardarUsuario(usuario);
    }

}
