package br.com.aceleradev.java.centraldeerros.controller;

import br.com.aceleradev.java.centraldeerros.service.UserAccountService;
import br.com.aceleradev.java.centraldeerros.auth.TokenAuthenticationService;
import br.com.aceleradev.java.centraldeerros.response.CredentialsResponse;
import br.com.aceleradev.java.centraldeerros.response.TokenAuthenticationResponse;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/v1")
@Slf4j
public class AccountLoginController {

  @Autowired
  UserAccountService userAccountService;

  private static final org.slf4j.Logger log =
    org.slf4j.LoggerFactory.getLogger(AccountLoginController.class);

  @ApiOperation(value = "Method for login and generate a token for use the api")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "successfully logged in"),
    @ApiResponse(code = 403, message = "you don't have authorization to complete this request"),
    @ApiResponse(code = 500, message = "an internal error occurred, it was not possible to complete this request")
  }

  )
  @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
  public ResponseEntity<TokenAuthenticationResponse> login(@Valid @RequestBody CredentialsResponse credentialsResponse) {
    try {
      if (userAccountService.hasAccount(credentialsResponse)) {
        String token = TokenAuthenticationService.create(credentialsResponse.getUsername());
        log.info("successfully logged in");
        return new ResponseEntity<>(new TokenAuthenticationResponse(token), HttpStatus.OK);

      }
      log.info("you don't have authorization to complete this request");
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    } catch (Exception e) {
      log.info("an internal error occurred, it was not possible to complete this request");
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}
