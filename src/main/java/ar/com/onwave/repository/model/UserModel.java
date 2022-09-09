package ar.com.onwave.repository.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="usuario")
public class UserModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotNull
    boolean activo;

    @OneToMany
    @JoinColumn(name="id_usuario")
    private List<RolModel> roles;
}