package com.multi_input.translator.controller;

import com.multi_input.translator.repository.TranslationRepositoryService;
import com.multi_input.translator.service.CsvProcessor;
import com.multi_input.translator.service.languages.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslateController {

//  @Autowired
//  CsvProcessor processor;

  @Autowired
  TranslationRepositoryService service;

  @GetMapping("/translate")
  public void getTranslation() {
  // todo call translation
    //processor.getTranslationMapFromCsv();
    service.saveTranslation("en");
  }

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @PostMapping("/save-translation")
  public void saveTranslation(@RequestParam(name = "user") String username) {
    // todo call save translation
  }

  @DeleteMapping("/delete-translation")
  public void deleteTranslation(@RequestParam(name = "user") String username) {
    // todo delete translation
  }

}
