package methods;

import client.NgsiRestClient;
import model.UpdateContextElement;
import model.UpdateContextElementResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;
import smartspotModel.AttributeType;
import smartspotModel.AttributeValue;
import smartspotModel.SmartspotController;

import java.util.concurrent.ExecutionException;

public class UpdateEntity {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        //Create the updateContext method
        UpdateContextElement updateContextElement = new UpdateContextElement();

        //Create the attribute list
        updateContextElement.setContextAttributes(SmartspotController.createAttributeList(
                new AttributeValue(AttributeType.Number,"6.359215"),
                new AttributeValue(AttributeType.Number,"12.596653"),
                new AttributeValue(AttributeType.Number,"21.23653"),
                new AttributeValue(AttributeType.Number,"5.29211"),
                new AttributeValue(AttributeType.Integer,"9"),
                new AttributeValue(AttributeType.Number,"21.05995"),
                new AttributeValue(AttributeType.Number,"16.952223"),
                new AttributeValue(AttributeType.Number,"12.2599"),
                new AttributeValue(AttributeType.Integer,"83"),
                new AttributeValue(AttributeType.Integer,"6"),
                new AttributeValue(AttributeType.Integer,"14"),
                new AttributeValue(AttributeType.Number,"23.5459"),
                new AttributeValue(AttributeType.Number,"12.6633"),
                new AttributeValue(AttributeType.Number,"21.6966")
        ));

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        UpdateContextElementResponse updateContextElementResponse = ngsiRestClient.updateContextElement(providerUrl, httpHeaders, "Prueba_convenience_java", updateContextElement).get();
        System.out.println(updateContextElement.toString());

    }
}
