package ar.com.onwave.repository.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipo")
public class EquipmentModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String registrado;

    @NotNull
    String imeiRegistrado;

    @NotNull
    String marcaTrafica;

    @NotNull
    String modeloTrafica;

    @NotNull
    String imeiTrafica;

    @NotNull
    String sim;

    @NotNull
    boolean activo;

}
