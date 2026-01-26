package gm.Colbeta.servicio;

import gm.Colbeta.modelo.Empresa;
import gm.Colbeta.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServicio implements  IEmpresaServicio{

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

    @Override
    public List<Empresa> listarEmpresas() {
        return this.empresaRepositorio.findAll();
    }

    @Override
    public Empresa guardarEmpresa(Empresa empresa) {
        return this.empresaRepositorio.save(empresa);
    }
}
