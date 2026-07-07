package gm.Colbeta.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Entity
@Data
//@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

    public Usuario(){}

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
    String eps;
    String ciudad;
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    String fechaIngreso;
}
