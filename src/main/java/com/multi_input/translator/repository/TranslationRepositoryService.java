package com.multi_input.translator.repository;

import com.multi_input.translator.Translation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationRepositoryService {

  @Autowired
  private TranslationRepository repository;

  /**
   * Return translation created from input parameters and saved to H2 DB.
   *
   * @param word String
//   * @param translated String
//   * @param srcLang source language
//   * @param lang translation language
   * @return Translation
   */
  public Translation saveTranslation(String word) {
    Translation translation = new Translation(word);

    return repository.save(translation);
  }

  /**
   * Return true if database contains Translation entry corresponding to input id.
   *
   * @param id String
   * @return boolean
   */
  public boolean translationExists(Long id) {
    return repository.existsById(id);
  }

  /**
   * Return Optional of Translation corresponding to input ID, or Optional of empty if id
   *  does not con
   *
   * @param id String
   * @return Optional<Translation></Translation>
   */
  public Optional<Translation> getTranslation(Long id) {
    return repository.findById(id);
  }

}
