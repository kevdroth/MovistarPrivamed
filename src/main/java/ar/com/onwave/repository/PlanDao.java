package ar.com.onwave.repository;

import ar.com.onwave.repository.model.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanDao extends JpaRepository<PlanModel, Long> {

    @Query("SELECT p FROM PlanModel p WHERE" + " CONCAT(p.internet, p.nombre, p.precio, p.sms, p.minutos)" +
            " LIKE %?1% and p.activo=true ")
    List<PlanModel> findAll(String keyword);

    @Query("SELECT p FROM PlanModel p WHERE p.activo=true ")
    List<PlanModel> findAll(boolean isChecked);

    List<PlanModel> findByNombreContainsAndActivo(String keyword, Boolean activo);
    List<PlanModel> findByActivo(Boolean active);

}
