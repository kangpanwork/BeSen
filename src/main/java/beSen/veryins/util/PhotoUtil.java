package beSen.veryins.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PhotoUtil {

    public static void downPhoto(String urlPath, String photoPath) throws Exception {
        URL url = new URL(urlPath);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.connect();
        if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
            InputStream inputStream = httpURLConnection.getInputStream();
            File file = new File(photoPath);
            OutputStream outputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            httpURLConnection.disconnect();
        }
    }

    public byte[] getPhotoByte(String urlPath) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        URL url = new URL(urlPath);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.connect();
        if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, len);
            }
            byteArrayOutputStream.flush();
            inputStream.close();
            httpURLConnection.disconnect();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
