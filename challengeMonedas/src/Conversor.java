import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Conversor {

    private static final String API_KEY = "b8f11b10c944001821f282e2"; // Tu clave de API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Metodo para hacer la solicitud HTTP a la API
    public String getConversionRate(String fromCurrency, String toCurrency) {
        String url = BASE_URL + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metodo para procesar la respuesta JSON y extraer la tasa de conversión
    public double parseJsonResponse(String jsonResponse) {
        if (jsonResponse != null) {
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            double conversionRate = jsonObject.get("conversion_rate").getAsDouble();
            System.out.println("Tasa de cambio: " + conversionRate);
            return conversionRate;  // Retorna la tasa para la conversión
        } else {
            System.out.println("No se pudo obtener la tasa de cambio.");
            return 0;
        }
    }

    // Metodo para realizar la conversión
    public double convertValues(double amount, double conversionRate) {
        return amount * conversionRate;
    }
}

