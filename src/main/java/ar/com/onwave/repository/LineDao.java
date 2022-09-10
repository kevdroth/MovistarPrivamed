package ar.com.onwave.repository;

import ar.com.onwave.repository.model.LineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineDao extends JpaRepository<LineModel, Long> {
    @Query("SELECT l FROM LineModel l WHERE l.numero LIKE %?1% and l.activo=true ")
    List<LineModel> findAll(String keyword);
    List<LineModel> findByNumeroAndActivo(String numero, Boolean activo);

    @Query("SELECT l FROM LineModel l WHERE l.activo=true ")
    List<LineModel> findAll(boolean isChecked);

    List<LineModel> findByNumeroContainsAndActivo(String keyword, Boolean activo);
    List<LineModel> findByActivo(Boolean active);
}
