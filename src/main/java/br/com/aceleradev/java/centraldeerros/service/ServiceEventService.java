package br.com.aceleradev.java.centraldeerros.service;

import br.com.aceleradev.java.centraldeerros.repository.ServiceEventRepository;
import br.com.aceleradev.java.centraldeerros.mapper.ServiceEventMapper;
import br.com.aceleradev.java.centraldeerros.entity.ServiceEvent;
import br.com.aceleradev.java.centraldeerros.dto.ServiceEventDTO;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ServiceEventService {

  @Autowired
  ServiceEventRepository serviceEventRepository;

  private ServiceEventMapper serviceEventMapper;


  public void deleteServiceEvent(Long id){
    ServiceEvent serviceEvent = serviceEventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
      " Service Event not found.",ServiceEvent.class.getName()));
    serviceEventRepository.deleteById(serviceEvent.getId());
  }

  public ServiceEvent updateServiceEvent (ServiceEventDTO serviceEventDTO, Long id){
    ServiceEvent serviceEvent = serviceEventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
      "Service Event not found.",ServiceEvent.class.getName()));
    return serviceEventRepository.save(serviceEventMapper.updateEvent(serviceEventDTO, serviceEvent));
  }

  public ServiceEventDTO findById(Long id) {
    return serviceEventMapper.map(serviceEventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
      "Service Event not found.",ServiceEvent.class.getName())));
  }

  public ServiceEvent save(ServiceEvent serviceEvent) {
    return serviceEventRepository.save(serviceEvent);
  }

  public void findAllServiceEventsPage(){
    List<ServiceEvent> serviceEvents = serviceEventRepository.findAll();
    serviceEventMapper.toList(serviceEvents);
  }

  public Page<ServiceEventDTO> findAll(Specification<ServiceEvent> spec, Pageable pageable){
    findAllServiceEventsPage();
    return serviceEventRepository.findAll(spec, pageable);
  }


}
