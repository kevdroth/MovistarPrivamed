package ar.com.onwave.repository.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="rol")
public class RolModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotNull
    boolean activo;

}