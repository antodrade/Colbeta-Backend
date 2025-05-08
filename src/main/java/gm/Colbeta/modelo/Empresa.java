package gm.Colbeta.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
Integer idEmpresa;
Integer nIdentificacion;
String tipoDoc;
String telefono;
String Municipio;
String Departamento;
String nombre;
String direccion;
String correo;
}
