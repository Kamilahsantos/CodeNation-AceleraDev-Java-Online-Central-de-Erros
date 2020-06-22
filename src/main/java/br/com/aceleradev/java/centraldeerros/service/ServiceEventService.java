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

  private ServiceEventMapper serviceEventMapper;

  @Autowired
  ServiceEventRepository serviceEventRepository;


  public Page<ServiceEventDTO> findAll(Specification<ServiceEvent> spec, Pageable pageable){
    findAllEventstoPage();
    return serviceEventRepository.findAll(spec, pageable);
  }

  public void findAllEventstoPage(){
    List<ServiceEvent> events = serviceEventRepository.findAll();
    serviceEventMapper.toList(events);
  }

  public ServiceEvent save(ServiceEvent serviceEvent) {
    return serviceEventRepository.save(serviceEvent);
  }

  public ServiceEventDTO findById(Long id) {
    return serviceEventMapper.map(serviceEventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
      "Event not found.",ServiceEvent.class.getName())));
  }

  public ServiceEvent updateEvent (ServiceEventDTO eventDTO, Long id){
    ServiceEvent serviceEvent = serviceEventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
      "Event not found.",ServiceEvent.class.getName()));
    return serviceEventRepository.save(serviceEventMapper.updateEvent(eventDTO, serviceEvent));
  }

  public void deleteEvent(Long id){
    ServiceEvent event = serviceEventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
      "Event not found.",ServiceEvent.class.getName()));
    serviceEventRepository.deleteById(event.getId());
  }

}
