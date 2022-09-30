package ar.com.onwave.repository.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "factura")
public class InvoiceModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String vencimiento;

    @NotNull
    Double monto;

    Double extra;

    @NotNull
    int puntoventa;

    @NotNull
    int numero;

    @NotNull
    boolean activo;

    @ManyToOne
    @JoinColumn(name="id_tipo_factura")
    private TypeFCModel typeFCModel;

    @ManyToOne
    @JoinColumn(name="id_letra_factura")
    private LetterFCModel letterFCModel;

}
