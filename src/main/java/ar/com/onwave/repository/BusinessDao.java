package ar.com.onwave.repository;

import ar.com.onwave.repository.model.BusinessModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessDao extends JpaRepository<BusinessModel, Long> {
}
