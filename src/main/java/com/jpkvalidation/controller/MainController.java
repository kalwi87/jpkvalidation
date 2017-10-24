package com.jpkvalidation.controller;
import com.jpkvalidation.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedInputStream;
import java.io.InputStream;


@RestController
@RequestMapping("/api")
public class MainController {


   @Autowired
   private ValidationService validationService;


    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @CrossOrigin(origins = "http://sprawdzjpk.com.pl/")
    @PostMapping
    @RequestMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String validationMessage;
        try {
            InputStream xml =  new BufferedInputStream(file.getInputStream());
            validationMessage = validationService.validateAgainstXSD(xml);
        } catch (Exception e) {
           LOG.warn(e.getMessage());
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(validationMessage, HttpStatus.OK);
    }

}
