package beSen.rpc.transport;

import beSen.rpc.proto.Peer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 客户端
 *
 * @author 康盼Java开发工程师
 */
public class HttpTransportClient implements TransportClient {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static final int EOF = -1;

    private String url;


    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":"  + peer.getPort();
    }

    @Override
    public InputStream write(InputStream inputStream) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(3000);
        httpURLConnection.setReadTimeout(3000);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.connect();

        OutputStream outputStream = httpURLConnection.getOutputStream();
        int n;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while (EOF != (n = inputStream.read(buffer))) {
            outputStream.write(buffer,0,n);
        }
        int responseCode = httpURLConnection.getResponseCode();
        if (HttpURLConnection.HTTP_OK == responseCode) {
            return httpURLConnection.getInputStream();
        }
        return httpURLConnection.getErrorStream();
    }

    @Override
    public void close() {

    }


}
