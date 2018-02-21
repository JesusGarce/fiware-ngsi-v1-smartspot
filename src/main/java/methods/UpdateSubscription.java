package methods;

import client.NgsiClient;
import model.UpdateContext;
import model.UpdateContextSubscription;
import model.UpdateContextSubscriptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class UpdateSubscription {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NgsiClient ngsiClient = new NgsiClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        UpdateContextSubscription updateSubscription = new UpdateContextSubscription();

        // Define the id subscription
        updateSubscription.setSubscriptionId("5a8c17bb7a43a52d1e894965");

        // Create notifies

        //updateSubscription.setNotifyConditions();

        // Create duration
        updateSubscription.setDuration("P2M");

        // Create throttling
       // updateSubscription.setThrottling("1");

        ngsiClient.asyncRestTemplate = new AsyncRestTemplate();

        UpdateContextSubscriptionResponse updateContextSubscriptionResponse = ngsiClient.updateContextSubscription(providerUrl,httpHeaders,updateSubscription).get();
        System.out.println(updateContextSubscriptionResponse);
    }
}
