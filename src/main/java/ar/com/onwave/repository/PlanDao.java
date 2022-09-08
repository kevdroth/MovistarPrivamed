package ar.com.onwave.repository;

import ar.com.onwave.repository.model.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanDao extends JpaRepository<PlanModel, Long> {

    @Query("SELECT p FROM PlanModel p WHERE" + " CONCAT(p.internet, p.nombre, p.precio, p.sms, p.minutos)" +
            " LIKE %?1%")
    List<PlanModel> findAll(String keyword);

}
