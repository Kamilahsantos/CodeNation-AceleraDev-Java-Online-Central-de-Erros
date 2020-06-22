package br.com.aceleradev.java.centraldeerros.controller;

import br.com.aceleradev.java.centraldeerros.service.ServiceEventService;
import br.com.aceleradev.java.centraldeerros.mapper.ServiceEventMapper;
import br.com.aceleradev.java.centraldeerros.entity.ServiceEvent;
import br.com.aceleradev.java.centraldeerros.dto.ServiceEventDTO;
import br.com.aceleradev.java.centraldeerros.rules.BooleanEventRule;
import br.com.aceleradev.java.centraldeerros.rules.EnumEventRule;
import br.com.aceleradev.java.centraldeerros.rules.StringEventRule;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import static org.springframework.http.ResponseEntity.status;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;


import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;


@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/event")
public class ServiceEventController {
  @Autowired
  ServiceEventService serviceEventService;
  private ServiceEventMapper serviceEventMapper;

  private static final org.slf4j.Logger log =
    org.slf4j.LoggerFactory.getLogger(ServiceEventController.class);

  @ApiOperation(value = "Method get the list of events in this API and filter the results ")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "successfully get list of events"),
    @ApiResponse(code = 500, message = "an internal error occurred, it was not possible to complete this request")
  }

  )
  @GetMapping(produces = "application/json")
  public ResponseEntity<Page<ServiceEventDTO>> getEvent(@RequestParam(value = "level", required = false) String level,
                                                 @RequestParam(value = "environment", required = false) String environment,
                                                 @RequestParam(value = "origin", required = false) String origin,
                                                 @RequestParam(value = "description", required = false) String description,
                                                 @RequestParam(value = "isArchived", required = false) Boolean isArchived,
                                                 @PageableDefault(value = 100, page = 0, direction =
                                                   Direction.ASC,
                                                   sort = "id") Pageable pageable) {
    try {
      Specification<ServiceEvent> specifications = Specification.where(new EnumEventRule("levelEnum", level))
        .and(new StringEventRule("environment", environment))
        .and(new StringEventRule("origin", origin))
        .and(new StringEventRule("description", description))
        .and(new BooleanEventRule("isArchived", isArchived));
      log.info("successfully get list of events");
      return new ResponseEntity<>((serviceEventService.findAll(specifications, pageable)), HttpStatus.OK);
    } catch (Exception e) {
      log.info("an internal error occurred, it was not possible to complete this request");
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "Method get an event by id ")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "successfully get the event for the id {}::"),
    @ApiResponse(code = 500, message = "an internal error occurred, it was not possible to complete this request")
  }

  )
  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<ServiceEventDTO> getEvent(@PathVariable(value = "id") Long id) {
    try {
      log.info("successfully get the event for the id {}::", id);
      return new ResponseEntity<>(serviceEventService.findById(id), HttpStatus.OK);
    } catch (Exception e) {
      log.info("event with id {} not found", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @ApiOperation(value = "Method create a new event ")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "successfully created event"),
    @ApiResponse(code = 500, message = "an internal error occurred, it was not possible to complete this request")
  }

  )
  @PostMapping(produces = "application/json")
  public ResponseEntity<ServiceEventDTO> create(@Valid @RequestBody ServiceEvent serviceEvent) {
    try {
      this.serviceEventService.save(serviceEvent);
      log.info("successfully created event");
      return status(HttpStatus.CREATED).body(serviceEventMapper.map(serviceEvent));
    } catch (Exception e) {
      log.info("an internal error occurred, it was not possible to complete this request");
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "Method update an event ")
  @ApiResponses(value = {
    @ApiResponse(code = 202, message = "successfully update the event for the id {}::"),
    @ApiResponse(code = 404, message = "event with id {} not found")
  }

  )
  @PatchMapping("{id}")
  public ResponseEntity<ServiceEventDTO> updateEvent(@Valid @RequestBody ServiceEventDTO serviceEventDTO, @PathVariable Long id){
    try {
      ServiceEvent eventUpdated = serviceEventService.updateEvent(serviceEventDTO, id);
      log.info("successfully update the event for the id {}::", id);
      return status(HttpStatus.ACCEPTED).body(serviceEventMapper.map(eventUpdated));
    } catch (Exception e) {
      log.info("event with id {} not found", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @ApiOperation(value = "Method delete an event ")
  @ApiResponses(value = {
    @ApiResponse(code = 204, message = "successfully delete the event for the id {}::"),
    @ApiResponse(code = 404, message = "event with id {} not found")
  }

  )
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
    try {
      this.serviceEventService.deleteEvent(id);
      log.info("successfully delete the event for the id {}::", id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      log.info("event with id {} not found", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
