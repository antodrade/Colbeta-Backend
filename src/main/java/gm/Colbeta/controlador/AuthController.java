package gm.Colbeta.controlador;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.Colbeta.dto.UsuarioLogin;
import gm.Colbeta.modelo.Empleado;
import gm.Colbeta.security.JwtUtil;
import gm.Colbeta.servicio.EmpleadoServicio;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200/", "http://colbeta-frontend2.onrender.com"})
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;


    private final Empleado empleado = new Empleado();

    @Autowired
    EmpleadoServicio empleadoServicio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody UsuarioLogin request) {
        System.out.println("Password enviado desde Postman encriptado: " + passwordEncoder.encode(request.getPassword()));
// Si puedes obtener el usuario de la DB aquí solo para probar:
        System.out.println("Hash en DB: " + empleadoServicio.conseguirEmpleadoPorUsername(request.getUsername()));
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return jwtUtil.generateToken(request.getUsername());
    }

    @PostMapping ("/register")
    public void register(@RequestBody Empleado request){

        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setUsername(request.getUsername());
        nuevoEmpleado.setPassword(request.getPassword());
        nuevoEmpleado.setNombreReal(request.getNombreReal());
        nuevoEmpleado.setRol(request.getRol());
        empleadoServicio.guardarEmpleado(nuevoEmpleado);
    }

}