package beSen.bsSimpleServer.HttpClient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class BsHttpClient {

    private RequestEntity requestEntity;

    public BsHttpClient(RequestEntity requestEntity) {
        this.requestEntity = requestEntity;
    }

    public void service() throws IOException {
        switch (requestEntity.getMethod()) {
            case "get":
                get();
                break;
            case "post":
                post();
                break;
        }
    }

    private void get() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestEntity.getUrl());
        httpclient.execute(httpGet, new BsResponseHandler());

    }

    private void post() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestEntity.getUrl());
        // 配置信息
        RequestConfig requestConfig = RequestConfig.custom()
                // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(5000)
                // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(5000)
                // socket读写超时时间(单位毫秒)
                .setSocketTimeout(5000)
                // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(true).build();
        httpPost.setConfig(requestConfig);

        StringEntity entity = new StringEntity(requestEntity.getParameter(), "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        httpclient.execute(httpPost, new BsResponseHandler());
    }

    private class BsResponseHandler implements ResponseHandler<String> {
        @Override
        public String handleResponse(HttpResponse httpResponse) throws IOException {
            HttpEntity httpEntity = httpResponse.getEntity();
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            long contentLength = httpEntity.getContentLength();
            String responseResult = EntityUtils.toString(httpEntity, Charset.forName("UTF-8"));
            requestEntity.setResponseResult(responseResult);
            requestEntity.setContentLength(contentLength);
            requestEntity.setStatusCode(statusCode);
            return responseResult;
        }
    }
}
