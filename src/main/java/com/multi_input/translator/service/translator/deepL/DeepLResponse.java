package com.multi_input.translator.service.translator.deepL;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multi_input.translator.service.languages.Language;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * POJO representing DeepL API translation response.
 */
@Getter
@NoArgsConstructor
public class DeepLResponse {

  @JsonProperty("translations")
  private List<Translation> translations;

  /**
   * Return word from first (and only) translation entry.
   *
   * @return translation
   */
  public String getTranslation() {
    return translations.get(0).getWord();
  }

  /**
   * Return source language from first (and only) translation entry.
   * @return Language
   */
  public Language getSourceLang() {
    return Language.valueOf(translations.get(0).getSourceLang());
  }

  /**
   * POJO representing translation entry within DeepL API translation response.
   */
  @Getter
  @NoArgsConstructor
  public static class Translation {

    @JsonProperty("detected_source_language")
    private String sourceLang;

    @JsonProperty("text")
    private String word;
  }

}

