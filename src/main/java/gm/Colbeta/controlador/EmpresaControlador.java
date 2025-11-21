package gm.Colbeta.controlador;


import gm.Colbeta.modelo.Empresa;
import gm.Colbeta.servicio.EmpresaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost/hole2
@RequestMapping("hole2")
@CrossOrigin(value = "http://localhost:4200")
public class EmpresaControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaControlador.class);

    @Autowired
    private EmpresaServicio empresaServicio;

    @GetMapping("/empresas")
    public List<Empresa> obtenerEmpresas(){
        List<Empresa> empresas = this.empresaServicio.listarEmpresas();
        logger.info("Empresas obtenidas");
        empresas.forEach((empresa -> logger.info(empresa.toString())));
        return empresas;
    }

    @PostMapping("/empresas")
     public  Empresa agregarEmpresa(@RequestBody Empresa empresa){
        logger.info("Empresa a agregar: "+empresa);
        return this.empresaServicio.guardarEmpresa(empresa);
    }

}
