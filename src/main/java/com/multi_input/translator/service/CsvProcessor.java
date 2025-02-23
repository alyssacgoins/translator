package com.multi_input.translator.service;

import com.multi_input.translator.Translation;
import com.multi_input.translator.repository.TranslationRepositoryService;
import com.multi_input.translator.service.translator.Translator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Process input csv files by translating contents & writing to output Excel doc.
 */
//@Service
@Slf4j
public class CsvProcessor {

  private static final String BODY_TEXT_CSV = "body-text.csv";
  private static final String COMMA = ",";

  @Autowired
  Translator translator;

  @Autowired
  TranslationRepositoryService repositoryService;

  public Translation saveToRepo(String translation) {
    return repositoryService.saveTranslation("en");
  }

  /**
   * Return a map of words retrieved from body-text.csv and their corresponding translations.
   *
   * @return Map<String, String></String,>
   */
  public Map<String, String> getTranslationMapFromCsv() {
    Map<String, String> translations = new HashMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(BODY_TEXT_CSV))) {
      String line = br.readLine();

      // Send a list to translator to translate.
      List<String> output = new ArrayList<>(Arrays.asList(line.split(COMMA)));

      // Translate list
      translator.translateList(output);

    } catch (IOException e) {
      //todo
      log.error("PROCESSING_ERROR_001", e);
    }
    return translations;
  }

}
