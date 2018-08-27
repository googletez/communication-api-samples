package com.sendmessage;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.io.CharStreams;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Collections;
import org.json.JSONObject;

/**
 * Class for making a request to Send Message API's http endpoint.
 */
public final class OauthClient {

  private static final Charset UTF8 = Charset.forName("UTF-8");
  private static final int HTTP_TIMEOUT_MS = 10000;
  private static final String JWT_PATH = "service-account-key.json";
  private static final String API_SCOPE =
      "https://www.googleapis.com/auth/nbupaymentsmerchants";
  private static final String POST_JSON = "sendmessage.json";
  private static final String API_URL =
      "https://nbupayments.googleapis.com/v1/merchantCommunications:send";

  public static void main(String args[]) {
    HttpTransport httpTransport;
    HttpRequestFactory httpRequestFactory;
    try {
      httpTransport = GoogleNetHttpTransport.newTrustedTransport();
      GoogleCredential credential = getGoogleCredential(httpTransport);
      httpRequestFactory = httpTransport.createRequestFactory(credential);

      doPostApplication(httpRequestFactory);
    } catch (IOException e) {
      System.out.println("Error in posting data.");
      e.printStackTrace();
    } catch (GeneralSecurityException e) {
      System.out.println("Error in posting data.");
      e.printStackTrace();
    }
  }

  /**
   * Creates a {@link HttpContent} from the json file.
   */
  private static HttpContent getContent() {
    return new FileContent("application/json", new File(POST_JSON));
  }

  /**
   * Creates a new credential using the service account json file and Oauth scope.
   */
  private static GoogleCredential getGoogleCredential(HttpTransport httpTransport)
      throws IOException {
    return GoogleCredential.fromStream(
        new FileInputStream(new File(JWT_PATH)),
        httpTransport,
        JacksonFactory.getDefaultInstance())
        .createScoped(Collections.singleton(API_SCOPE));
  }

  /**
   * Gets the specification of an application from the Google servers.
   */
  private static void doPostApplication(HttpRequestFactory requestFactory) throws IOException {
    GenericUrl url = new GenericUrl(API_URL);
    HttpRequest httpRequest = requestFactory.buildPostRequest(url, getContent());
    doHttpRequest(httpRequest);
  }

  /**
   * Executes HTTP request against the API and prints response.
   */
  private static void doHttpRequest(HttpRequest httpRequest) throws IOException {
    // Set read timeout.
    httpRequest.setReadTimeout(HTTP_TIMEOUT_MS).setThrowExceptionOnExecuteError(false);
    HttpResponse httpResponse = httpRequest.execute();
    System.out.println("Status code: " + httpResponse.getStatusCode());
    String responseContent = inputStreamToString(httpResponse.getContent());

    // Parse and print formatted response.
    JSONObject response = new JSONObject(responseContent);
    System.out.println("response: " + response);
  }

  /**
   * Reads input stream contents using the UTF-8 charset into a string.
   */
  private static String inputStreamToString(InputStream in) throws IOException {
    return CharStreams.toString(new InputStreamReader(in, UTF8));
  }
}
