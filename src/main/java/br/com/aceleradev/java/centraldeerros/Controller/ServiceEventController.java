package br.com.aceleradev.java.centraldeerros.Controller;

import br.com.aceleradev.java.centraldeerros.Rules.StringEventRule;
import br.com.aceleradev.java.centraldeerros.Rules.BooleanEventRule;
import br.com.aceleradev.java.centraldeerros.Rules.EnumEventRule;
import br.com.aceleradev.java.centraldeerros.Service.ServiceEventService;
import br.com.aceleradev.java.centraldeerros.Mapper.ServiceEventMapper;
import br.com.aceleradev.java.centraldeerros.Entity.ServiceEvent;
import br.com.aceleradev.java.centraldeerros.DTO.ServiceEventDTO;

import static org.springframework.http.ResponseEntity.status;

import javax.validation.Valid;

import  org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

public class ServiceEventController {

  //listar eventos

  //buscar evento pelo id

  ////criar novo evento

  //atualizar novo evento

  //excluir evento


}
