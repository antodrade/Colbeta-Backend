package gm.Colbeta.servicio;

import gm.Colbeta.modelo.Eps;
import gm.Colbeta.repositorio.EpsRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpsService implements IEpsService {


    @Autowired
   private EpsRepositorio epsRepositorio;

    @Override
    public List<Eps> listarEps() {
       return epsRepositorio.findAll() ;
    }


}