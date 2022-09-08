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
@Table(name = "plan")
public class PlanModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String nombre;

    @NotNull
    double precio;

    @NotNull
    String internet;

    @NotNull
    String minutos;

    @NotNull
    String sms;

    @NotNull
    int estado;

}
