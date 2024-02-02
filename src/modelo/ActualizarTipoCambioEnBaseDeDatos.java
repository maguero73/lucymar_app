package modelo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ActualizarTipoCambioEnBaseDeDatos {

    public static void EjecutarActualizacion() throws IOException{
    	
        String apiKey = "de9dac612a7a0ea789b51169"; // Reemplaza con tu clave de API de Open Exchange Rates
        String url = "https://open.er-api.com/v6/latest?app_id=" + apiKey;

        try {
            String tipoCambio = obtenerTipoCambioDesdeAPI(url);
            actualizarTipoCambioEnBaseDeDatos(tipoCambio);

        } catch (SQLException e) {
            ((Throwable) e).printStackTrace();
        }
    }
   

    private static String obtenerTipoCambioDesdeAPI(String urlString) throws IOException {
    	  URL url = new URL(urlString);
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();

          // Configurar la solicitud HTTP
          connection.setRequestMethod("GET");
          connection.setRequestProperty("Content-Type", "application/json");

          // Leer la respuesta de la API
          BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


          // Leer y procesar la respuesta JSON
          JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();
          String tipoCambio = jsonResponse.getAsJsonObject("rates").get("ARS").getAsString();   
          
          reader.close();
          
          return tipoCambio;
    }



    private static void actualizarTipoCambioEnBaseDeDatos(String tipoCambio) throws SQLException {
        String urlBaseDatos = "jdbc:oracle:thin:@localhost:51521:xe";
        String usuario = "SYSTEM";
        String contraseña = "oracle";

        try (Connection connection = DriverManager.getConnection(urlBaseDatos, usuario, contraseña)) {
            String sql = "UPDATE LM_GASTOS SET TIPO_CAMBIO = ? WHERE CODIGO_MONEDA = 'USD'";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, Double.parseDouble(tipoCambio));
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Tipo de cambio actualizado en la base de datos.");
                } else {
                    System.out.println("No se pudo actualizar el tipo de cambio en la base de datos.");
                }
            }
        }
        
        try (Connection connection = DriverManager.getConnection(urlBaseDatos, usuario, contraseña)) {
            String sql = "UPDATE LM_INGRESOS SET TIPO_CAMBIO = ? WHERE CODIGO_MONEDA = 'USD'";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, Double.parseDouble(tipoCambio));
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Tipo de cambio actualizado en la base de datos.");
                } else {
                    System.out.println("No se pudo actualizar el tipo de cambio en la base de datos.");
                }
            }
        }
    }
}
