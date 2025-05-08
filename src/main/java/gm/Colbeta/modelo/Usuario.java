package gm.Colbeta.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idUser;
    Integer nidentificacion;
    String name1;
    String name2;
    String lastname1;
    String lastname2;
    String tipoDoc;
    String numDoc;
    String sexo;
    String fechaNac;
    String dirUsuario;
    String telUsuario;
    String celUsuario;
    String email;
    String direccion;
    String EPS;
    String ciudad;
    Date fechaIngreso;
}
