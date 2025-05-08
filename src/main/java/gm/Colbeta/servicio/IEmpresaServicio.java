package gm.Colbeta.servicio;

import gm.Colbeta.modelo.Empresa;

import java.util.List;

public interface IEmpresaServicio {
    public List<Empresa> listarEmpresas();
    public Empresa guardarEmpresa(Empresa empresa);
}
