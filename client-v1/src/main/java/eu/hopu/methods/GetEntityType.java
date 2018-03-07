package eu.hopu.methods;

import eu.hopu.client.NgsiRestClient;
import eu.hopu.model.QueryContextResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class GetEntityType {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the headers

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        QueryContextResponse smartSpot = ngsiRestClient.getContextEntityType(providerUrl, httpHeaders, "SmartSpot").get();
        System.out.println(smartSpot.toString());

    }
}
