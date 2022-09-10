package ar.com.onwave.repository;

import ar.com.onwave.repository.model.BusinessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessDao extends JpaRepository<BusinessModel, Long> {
    @Query("SELECT b FROM BusinessModel b WHERE b.nombre LIKE %?1%")
    List<BusinessModel> findAllKeyword(String keyword);
    List<BusinessModel> findByNombreContainsAndActivo(String keyword, Boolean activo);
    List<BusinessModel> findByActivo(Boolean active);
    @Query("SELECT b FROM BusinessModel b WHERE b.activo=true ")
    List<BusinessModel> findAll(boolean isChecked);

}
