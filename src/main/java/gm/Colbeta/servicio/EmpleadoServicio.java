package gm.Colbeta.servicio;

import gm.Colbeta.modelo.Empleado;
import gm.Colbeta.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServicio implements IEmpleadoServicio, UserDetailsService {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Empleado> listarEmpleados() {
        return this.empleadoRepositorio.findAll();
    }
    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        return this.empleadoRepositorio.save(empleado);
    }

    public Empleado conseguirEmpleadoPorUsername(String username){
        return this.empleadoRepositorio.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepositorio.findByUsername(username);
        if (empleado == null){
            throw new UsernameNotFoundException("No existe el empleado: "+username);
        }
        return User.builder()
                .username(empleado.getUsername())
                .password(empleado.getPassword())
                .roles(empleado.getRol())
                .build();
    }
}
