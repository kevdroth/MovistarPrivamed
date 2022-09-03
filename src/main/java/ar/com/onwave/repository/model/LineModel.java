package ar.com.onwave.repository.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "linea")
public class LineModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String numero;

    @ManyToOne
    @JoinColumn(name="id_plan")
    private PlanModel planModel;

    @ManyToOne
    @JoinColumn(name="id_equipo")
    private EquipmentModel equipmentModel;

    @ManyToOne
    @JoinColumn(name="id_empleado")
    private EmployeeModel employeeModel;

}
