package ar.com.onwave.repository;

import ar.com.onwave.repository.model.LineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineDao extends JpaRepository<LineModel, Long> {
    @Query("SELECT l FROM LineModel l WHERE l.numero LIKE %?1%")
    List<LineModel> findAll(String keyword);
}
