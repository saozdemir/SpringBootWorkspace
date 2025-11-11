package tr.tsk.hvkk.mys.reportrequestapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.tsk.hvkk.mys.reportrequestapi.dto.Personnel;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readAllBytes;

public class ReportRequestService {

    private static String API_URL = "http://localhost:8080/api/generate-report/personnel";


    public byte[] sendReportRequest(List<Personnel> personnelList) throws IOException {
        /** Gelen personel listesini json formatına dönüştürüyor.*/
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(personnelList);

        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/pdf");


        // ---- İsteği Gönderme ----
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }


        // ---- Yanıtı Alma ----
        int responseCode = connection.getResponseCode();
        System.out.println("Sunucu Yanıt Kodu: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // Başarılı yanıt (200 OK)
            try (InputStream inputStream = connection.getInputStream()) {
                System.out.println("Rapor başarıyla alındı.");
                return readAllBytes(inputStream);
            }
        } else {
            // Hata durumunda hata mesajını oku ve exception fırlat
            try (InputStream errorInputStream = connection.getErrorStream()) {
                String errorResponse = new String(readAllBytes(errorInputStream), StandardCharsets.UTF_8);
                throw new IOException("Sunucu hatası: " + responseCode + " - " + errorResponse);
            }
        }

    }

    public void saveReportToFile(byte[] pdfBytes) {
        try (FileOutputStream fos = new FileOutputStream("generated-report-client.pdf")) {
            fos.write(pdfBytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] readAllBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024]; // 1 KB'lık buffer
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}
