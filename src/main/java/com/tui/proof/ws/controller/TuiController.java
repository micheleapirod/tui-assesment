package com.tui.proof.ws.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tui.proof.command.OrderCommand;
import com.tui.proof.dto.TuiOrderDto;
import com.tui.proof.exception.SchemaException;
import com.tui.proof.exception.TuiUserException;
import com.tui.proof.search.SearchQ;
import com.tui.proof.service.SearchService;
import com.tui.proof.service.UserSearchConfigurationService;
import com.tui.proof.utils.AppUtils;
import com.tui.proof.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Log4j2
@RestController
public class TuiController {

  private final OrderCommand orderCommand;
  private final Validator validator;
  private final SearchService searchService;
  private final UserSearchConfigurationService configurationService;


  @Autowired
  public TuiController(OrderCommand orderCommand, Validator validator, SearchService searchService, UserSearchConfigurationService configurationService) {
    this.orderCommand = orderCommand;
    this.validator = validator;
    this.searchService = searchService;
    this.configurationService = configurationService;
  }

  /**
   * @param tuiOrderDto
   * @return
   * @throws SchemaException
   * @throws JsonProcessingException
   */
  @PostMapping("/create")
  public  ResponseEntity<TuiOrderDto> createOrder(@RequestBody TuiOrderDto tuiOrderDto) throws SchemaException, JsonProcessingException {
    validator.validate(tuiOrderDto);
    TuiOrderDto response = orderCommand.execute(tuiOrderDto);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * @param q
   * @param value
   * @return
   */
  @GetMapping("/search")
  public ResponseEntity<?> searchOrder(@RequestParam String q, @RequestParam String value, @NotNull @RequestHeader HttpHeaders headers) throws TuiUserException {

    boolean isAuthorized = configurationService.isEnabled(headers);
    if (!isAuthorized) {
      throw new TuiUserException();
    }
    List<TuiOrderDto> orders = searchService.search(SearchQ.builder()
                    .key(q)
                    .value(value)
                    .build());
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }
}
