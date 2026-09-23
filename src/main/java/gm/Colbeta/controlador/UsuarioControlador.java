package gm.Colbeta.controlador;

import gm.Colbeta.modelo.Usuario;
import gm.Colbeta.servicio.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hole")
@CrossOrigin(origins = {"http://localhost:4200/", "http://colbeta-frontend2.onrender.com"})
public class UsuarioControlador {

   private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios(){
        List<Usuario> usuarios = this.usuarioServicio.listarUsuarios();
        logger.info("Usuarios obtenidos");
        return usuarios;
    }

    @PostMapping("/usuarios")
    public Usuario agregarUsuario(@RequestBody Usuario usuario){
        logger.info("Usuario a agregar: "+ usuario);
        return this.usuarioServicio.guardarUsuario(usuario);
    }

    @GetMapping("/usuarios/empresa/{idEmpresa}")
    public List<Usuario> obtenerUsuariosPorEmpresa(@PathVariable Integer idEmpresa){
        logger.info("Obteniendo usuarios para la empresa: " + idEmpresa);
        return this.usuarioServicio.listarUsuariosPorEmpresa(idEmpresa);
    }

    @GetMapping("usuarios/{id}")
    public void eliminarUsuarioPorId(@PathVariable Integer id){
        logger.info("borrando usuario de id"+id);
         this.usuarioServicio.eliminarUsuarioPorId(id);
    }

    @GetMapping("usuarios/identificacion/{Nidentificacion}")
    public Integer extraerIdxIdentificacion(@PathVariable Integer Nidentificacion){
        logger.info("extrayendo id por medioo de la indentificacion");
        return this.usuarioServicio.extraerIdxIdentificacion(Nidentificacion);
    }

}
