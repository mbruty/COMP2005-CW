import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import types.ResponseObj;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class DataAccess {
    private static final String URL = "http://intelligent-social-robots-ws.com/restaurant-data.json";
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String get() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(URL))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseObj deserialize(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, ResponseObj.class);
    }
}
