package util;

import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class VerifyRecaptcha {

    private static final String SECRET_KEY = "6Lc5g7wsAAAAAJZOgNkv971dpH9nbF5FhJoKzqqy";

    public static boolean verify(String response) {
        try {
            URL url = new URL("https://www.google.com/recaptcha/api/siteverify");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String params = "secret=" + SECRET_KEY + "&response=" + response;

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            StringBuilder result = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }

            in.close();

            // ✅ تحليل بسيط بدون JSON
            String json = result.toString();

            if (json.contains("\"success\": true")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}