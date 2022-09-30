package ar.com.onwave.repository;

import ar.com.onwave.repository.model.LineModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineRepository extends JpaRepository<LineModel, Long> {
    List<LineModel> findByNumeroContainsAndActivo(String keyword, Boolean activo);
    List<LineModel> findByActivo(Boolean active);
    Page<LineModel> findLineModelsByActivo(Boolean active, Pageable pageable);
}
