package gm.Colbeta.servicio;

import gm.Colbeta.modelo.Empresa;
import gm.Colbeta.repositorio.EmpresaRepositorio;

import java.util.List;

public class EmpresaServicio implements  IEmpresaServicio{

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
