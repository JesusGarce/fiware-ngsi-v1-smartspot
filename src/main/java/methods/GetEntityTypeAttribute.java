package methods;

import client.NgsiRestClient;
import model.QueryContextResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class GetEntityTypeAttribute {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the headers

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        QueryContextResponse queryContextResponse = ngsiRestClient.getContextEntityTypeAttribute(providerUrl, httpHeaders, "SmartSpot", "CO").get();
        System.out.println(queryContextResponse.toString());

    }
}
