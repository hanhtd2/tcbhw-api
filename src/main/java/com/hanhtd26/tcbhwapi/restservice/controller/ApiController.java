package com.hanhtd26.tcbhwapi.restservice.controller;

import com.hanhtd26.tcbhwapi.constant.CommonConstants;
import com.hanhtd26.tcbhwapi.restservice.entity.PushEntity;
import com.hanhtd26.tcbhwapi.restservice.entity.QueryEntity;
import com.hanhtd26.tcbhwapi.restservice.entity.QueryReturnEntity;
import com.hanhtd26.tcbhwapi.restservice.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This Class is simple to understand as Controller of required POST method
 */
@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * This method accepted JSON data from users as requested format
     * when users want to insert new or append data to application
     * @param pushEntity
     * @return
     */
    @RequestMapping(value = "/push", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Object> push(@Valid @RequestBody PushEntity pushEntity) {
        return ResponseEntity.status(HttpStatus.OK).body(
                Collections.singletonMap(CommonConstants.STATUS, apiService.push(pushEntity))
                );
    }

    /**
     * This method receives query request from users with requested format
     * when user want to get info from inserted data which added or appended before
     * if poolId is out of known poolIds it will return warning message but still OK status
     * @param queryEntity
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Object> query(@Valid @RequestBody QueryEntity queryEntity) {
        QueryReturnEntity queryReturnEntity = apiService.query(queryEntity);
        if (null != queryReturnEntity) {
            return new ResponseEntity<>(queryReturnEntity, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    Collections.singletonMap(CommonConstants.STATUS, "poolId is not existed!")
            );
        }
    }

    /**
     * This method handles Exception when user try to provide missing params
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * This method handles Exception when users try to provide wrong type of data.
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Map<String, String> handleNotReadableExceptions(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getCause().getMessage());
        return errors;
    }
}
