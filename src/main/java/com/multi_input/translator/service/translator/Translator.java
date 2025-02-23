package com.multi_input.translator.service.translator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi_input.translator.service.dictionaries.Dictionary;
import com.multi_input.translator.service.dictionaries.EnglishDictionary;
import com.multi_input.translator.service.translator.deepL.DeepLRequestProcessor;
import com.multi_input.translator.service.translator.deepL.DeepLResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Process body-text.csv into translated pairs and save pairs in output Excel doc.
 */
@Service
@Slf4j
public class Translator {

  private static final String COMMA = ",";
  private static final String BODY_TEXT_CSV = "body-text.csv";
  private static final String EMPTY_STRING = "";

  @Autowired
  DeepLRequestProcessor deepLRequestProcessor = new DeepLRequestProcessor();

  EnglishDictionary englishDictionary = new EnglishDictionary();

  private final Dictionary sourceLangDictionary;

  public Translator(final Dictionary dictionary) {
    this.sourceLangDictionary = dictionary;
  }

  /**
   * Return a map of words from input list and their corresponding translations.
   *
   * @return Map<String, String></String,>
   */
  public Map<String, String> translateList(final List<String> words) {
    Map<String, String> translations = new HashMap<>();

    // Translate each word in line
    for (String word : words) {
      Optional<String> translationResponse = translate(word);

      // Continue to next entry if no response retrieved.
      if (translationResponse.isEmpty()) {
        continue;
      }
      String translation = translationResponse.get();

      // If the initial word is English, ignore this pair.
      if (srcWordIsEnglish(word, translation)) {
        continue;
      }

      log.info("Value: {}", word);
      translations.put(word, translation);
    }
    return translations;
  }


  /**
   * Return true if input word is English.
   *
   * @param word String
   * @param translation String
   * @return boolean
   */
  protected boolean srcWordIsEnglish(String word, String translation) {
    return translation.equalsIgnoreCase(word) && sourceLangDictionary.isLang(word);
  }

  /**
   * Return Optional of input translated word, Optional of empty if translation not retrieved.
   *
   * @param word String
   * @return Optional<String></String>
   */
  public Optional<String> translate(String word) {
    Optional<String> translation = Optional.empty();
    String httpText = deepLRequestProcessor.sendDeepLRequestForWord(word);

    try {
      ObjectMapper mapper = new ObjectMapper();
      DeepLResponse response = mapper.readValue(httpText, DeepLResponse.class);

      //todo more cases here.
      if (!response.getTranslation().equals("Error")) {
        translation = Optional.of(response.getTranslation());
      }

      log.info("Src_Lang: {}, Text: {}", response.getTranslations().get(0).getSourceLang(),
          translation);
    } catch (JsonProcessingException e) {
      //todo
      log.error("Error");
    }

    return translation;
  }

}
