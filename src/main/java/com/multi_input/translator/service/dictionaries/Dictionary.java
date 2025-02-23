package com.multi_input.translator.service.dictionaries;

import com.multi_input.translator.service.languages.Language;
import java.net.http.HttpResponse;

public interface Dictionary {

  boolean isLang(String word);

  HttpResponse<String> sendDictionaryRequest(Language lang, Language srcLang, String word);

}
