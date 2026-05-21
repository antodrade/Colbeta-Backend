package gm.Colbeta.servicio;

import gm.Colbeta.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
    public List<Empleado> listarEmpleados();
    public Empleado guardarEmpleado(Empleado empleado);

}
