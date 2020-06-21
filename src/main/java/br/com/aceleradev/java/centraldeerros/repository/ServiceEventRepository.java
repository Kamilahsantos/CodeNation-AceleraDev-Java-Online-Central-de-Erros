package br.com.aceleradev.java.centraldeerros.repository;

import br.com.aceleradev.java.centraldeerros.entity.ServiceEvent;
import br.com.aceleradev.java.centraldeerros.dto.ServiceEventDTO;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface ServiceEventRepository extends JpaRepository<ServiceEvent, Long>{

  Page<ServiceEventDTO> findAll(Specification<ServiceEvent> spec, Pageable pageable);
}
