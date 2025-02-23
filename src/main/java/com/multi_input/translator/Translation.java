package com.multi_input.translator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * POJO representing translation
 */
@Getter
@Entity
public class Translation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  String word;

  public Translation(String word) {
    this.word = word;
//    this.translated = translated;
//    this.lang = lang;
//    this.srcLang = srcLang;
  }


//  private String translated;
//  private String lang;
//  private String srcLang;

  @Override
  public String toString() {
    return String.format(
        "Translation[id=%d, word='%s']",
        id, word);
  }

}
