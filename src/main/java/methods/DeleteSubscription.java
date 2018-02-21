package methods;

import client.NgsiClient;
import model.UnsubscribeContext;
import model.UnsubscribeContextResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class DeleteSubscription {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        NgsiClient ngsiClient = new NgsiClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the headers

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        UnsubscribeContext unsubscribeContext = new UnsubscribeContext();

        ngsiClient.asyncRestTemplate = new AsyncRestTemplate();

        UnsubscribeContextResponse unsubscribeContextResponse = ngsiClient.unsubscribeContext(providerUrl,httpHeaders,"5a8c17bb7a43a52d1e894965").get();
        System.out.println(unsubscribeContext.toString());

    }
}
