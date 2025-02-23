package com.multi_input.translator.config;

import com.multi_input.translator.service.dictionaries.Dictionary;
import com.multi_input.translator.service.dictionaries.EnglishDictionary;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class Configuration {

  @Bean("EnglishDictionary")
  Dictionary generateEnglishDictionary() {
    return new EnglishDictionary();
  }


}
