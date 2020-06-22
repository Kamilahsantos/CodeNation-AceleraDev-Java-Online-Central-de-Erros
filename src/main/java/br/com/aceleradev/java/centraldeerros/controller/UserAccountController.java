package br.com.aceleradev.java.centraldeerros.controller;

import br.com.aceleradev.java.centraldeerros.service.UserAccountService;
import br.com.aceleradev.java.centraldeerros.mapper.UserAccountMapper;
import br.com.aceleradev.java.centraldeerros.entity.UserAccount;
import br.com.aceleradev.java.centraldeerros.dto.UserAccountDTO;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/account")
public class UserAccountController  {


  @Autowired
  private UserAccountMapper userAccountMapper;
  UserAccountService userAccountService;

  private static final org.slf4j.Logger log =
    org.slf4j.LoggerFactory.getLogger(AccountLoginController.class);

  @ApiOperation(value = "This method creates a new account")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "user created successfully"),
    @ApiResponse(code = 500, message = "an internal error occurred, it was not possible to complete this request")
  }

  )
  @PostMapping(produces = "application/json")
  public ResponseEntity<UserAccountDTO> saveAccount(@Valid @RequestBody UserAccount userAccount){
    try{
      log.info("user created successfully");
      return ResponseEntity.status(HttpStatus.CREATED).body(userAccountMapper.map(userAccountService.save(userAccount)));
    } catch (Exception e){
      log.info("an internal error occurred, it was not possible to complete this request");
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "Method get an user by id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success when searching for user with id {}"),
    @ApiResponse(code = 404, message = "user with id {} not found"),
    @ApiResponse(code = 500, message = "an internal error occurred, it was not possible to complete this request")
  }

  )
  @GetMapping("/{id}")
  public ResponseEntity<UserAccountDTO> getAccount(@PathVariable(value = "id") Long id) {
    try {
      Optional<UserAccount> account = userAccountService.findById(id);
      if (account.isPresent()) {
        log.info("Success when searching for user with id {}",id);
        return new ResponseEntity<UserAccountDTO>(userAccountMapper.map(account.get()), HttpStatus.OK);
      }
      log.info("user with id {} not found",id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      log.info("an internal error occurred, it was not possible to complete this request");
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "method update an account")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "user with id {} updated successfully"),
    @ApiResponse(code = 404, message = "user with id {} not found"),
    @ApiResponse(code = 500, message = "an internal error occurred, it was not possible to complete this request")
  }

  )
  @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<UserAccount> updateAccount(@Valid @RequestBody UserAccount accountUpdated, @PathVariable(value = "id") Long id) {
    try {
      Optional<UserAccount> account = userAccountService.findById(id);
      if (account.isPresent()) {
        accountUpdated.setId(account.get().getId());
        accountUpdated.setCreatedAt(account.get().getCreatedAt());
        log.info("user with id {} updated successfully",id);
        return new ResponseEntity<UserAccount>(userAccountService.save(accountUpdated), HttpStatus.OK);
      }
      log.info("user with id {} not found",id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      log.info("an internal error occurred, it was not possible to complete this request");
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }







}
