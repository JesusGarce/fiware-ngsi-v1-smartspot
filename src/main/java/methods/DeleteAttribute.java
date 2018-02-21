package methods;

import client.NgsiRestClient;
import model.StatusCode;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class DeleteAttribute {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        //httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        StatusCode statusCode = ngsiRestClient.deleteContextAttribute(providerUrl, httpHeaders, "Prueba_AQO_3", "address").get();
        System.out.println(statusCode.toString());

    }

}
