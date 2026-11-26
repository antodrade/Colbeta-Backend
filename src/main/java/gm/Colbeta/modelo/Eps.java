package gm.Colbeta.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "eps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eps {

    @Id
    @Column(name = "codigo_eps", length = 20)
    private String codigoEps;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String regimen;

    @Column(nullable = false)
    private Boolean activo;
}

