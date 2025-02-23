package com.multi_input.translator.service.dictionaries;

import static com.multi_input.translator.service.languages.Language.EN;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.multi_input.translator.service.languages.Language;
import org.springframework.stereotype.Component;

/**
 * Executes dictionary API requests against input words.
 */
@Slf4j
@NoArgsConstructor
@Component
public class EnglishDictionary implements Dictionary{

  private final Language lang = EN;

  /**
   * Return true if input word is in input language.
   *
   * @param word String word (e.g. "hello")
   * @return boolean
   */
  @Override
  public boolean isLang(String word) {
    boolean isLang = false;
    switch (lang) {
      case EN:
        isLang = isEnglishWord(word);
        break;
      case DE:
        // ToDo: build this out
        break;
      default:
        // ToDo: add specific error code.
        log.error("Language {} not currently supported.", lang);
    }
    return isLang;
  }

  @Override
  public HttpResponse<String> sendDictionaryRequest(Language lang, Language srcLang, String word) {
    return null;
  }

  /**
   * Return true if input word is an English word.
   *
   * @param word String
   * @return boolean
   */
  protected boolean isEnglishWord(String word) {
    boolean isWord = false;
    // ToDo: utilize caching here, so we do not need to send repeat requests.
    HttpResponse<String> result = sendDictionaryRequest(word);
    if (result.statusCode() == HttpURLConnection.HTTP_OK) {
      isWord = true;
    }

    return isWord;
  }

  /**
   * Return the HttpResponse
   *
   * @param word String
   * @return HttpResponse<String></String>
   */
  //todo make private
  public HttpResponse<String> sendDictionaryRequest(String word) {
    //ToDo: find better way to do this
    HttpResponse<String> response = null;
    try {
      String url =
          "https://api.dictionaryapi.dev/api/v2/entries/" + lang.toString().toLowerCase() + "/"
              + word;

      HttpClient client = HttpClient.newBuilder().build();
      HttpRequest req = HttpRequest.newBuilder().uri(new URI(url)).build();
      response = client.send(req, BodyHandlers.ofString());

    } catch (URISyntaxException e) {
      // ToDo: add specific error code.
      log.error("Error connecting to English-language dictionary.");
    } catch (IOException | InterruptedException e) {
      // ToDo: add specific error code.
      log.error("Error connecting to English-language dictionary 2.");
    }
    return response;
  }

}
