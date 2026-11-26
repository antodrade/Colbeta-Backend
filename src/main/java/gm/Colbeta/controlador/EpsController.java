package gm.Colbeta.controlador;

import gm.Colbeta.modelo.Eps;
import gm.Colbeta.servicio.EpsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("hole3")
@CrossOrigin(origins = {"http://localhost:4200", "http://colbeta-frontend2.onrender.com"})
public class EpsController {
    private static final Logger logger = LoggerFactory.getLogger(EpsController.class);


    @Autowired
    private EpsService epsService;

    @GetMapping("/eps")
    public List<Eps> obtenerEps(){
        List<Eps> epss = this.epsService.listarEps();
                logger.info("epss obtenidas");
                return epss;

    }


}