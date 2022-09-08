package ar.com.onwave.repository;

import ar.com.onwave.repository.model.BusinessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessDao extends JpaRepository<BusinessModel, Long> {
    @Query("SELECT b FROM BusinessModel b WHERE b.nombre LIKE %?1% and b.estado=0")
    List<BusinessModel> findAll(String keyword);

}
