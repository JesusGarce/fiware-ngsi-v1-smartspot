package methods;

import client.NgsiRestClient;
import model.UpdateContextSubscriptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class UpdateContextSubscription {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        model.UpdateContextSubscription updateSubscription = new model.UpdateContextSubscription();

        // Define the id subscription
        updateSubscription.setSubscriptionId("5a8c40f77a43a52d1e894969");

        // Create notifies

        //updateSubscription.setNotifyConditions();

        // Create duration
        updateSubscription.setDuration("P2M");

        // Create throttling
        // updateSubscription.setThrottling("1");

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        UpdateContextSubscriptionResponse updateContextSubscriptionResponse = ngsiRestClient.updateContextSubscription(providerUrl, httpHeaders, "5a8c40f77a43a52d1e894969", updateSubscription).get();
        System.out.println(updateContextSubscriptionResponse.toString());
    }

}
