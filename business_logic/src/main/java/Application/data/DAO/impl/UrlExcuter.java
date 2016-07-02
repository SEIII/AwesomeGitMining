package Application.data.DAO.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

public class UrlExcuter {
    
    
    public static String execURL(String urlstring) {
        return getGithubContentUsingURLConnection(
                "be3744c8c8b3c1f3b02ff66a3be0eac60f0940e6", urlstring);
    }

    private static String getGithubContentUsingURLConnection(String token,
            String url) {
        String newUrl = "https://" + url;
        String result = "";
        try {
            URL myURL = new URL(newUrl);
            URLConnection connection = myURL.openConnection();
            token = token + ":x-oauth-basic";
            String authString = "Basic "
                    + Base64.encodeBase64String(token.getBytes());
            connection.setRequestProperty("Authorization", authString);
            InputStream crunchifyInStream = connection.getInputStream();

            result = crunchifyGetStringFromStream(crunchifyInStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String crunchifyGetStringFromStream(
            InputStream crunchifyStream) throws IOException {
        if (crunchifyStream != null) {
            Writer crunchifyWriter = new StringWriter();

            char[] crunchifyBuffer = new char[8192];
            try {
                Reader crunchifyReader = new BufferedReader(
                        new InputStreamReader(crunchifyStream, "UTF-8"));
                int counter;
                while ((counter = crunchifyReader
                        .read(crunchifyBuffer)) != -1) {
                    crunchifyWriter.write(crunchifyBuffer, 0, counter);
                }
            } finally {
                crunchifyStream.close();
            }
            return crunchifyWriter.toString();
        } else {
            return "No Contents";
        }
    }
}
