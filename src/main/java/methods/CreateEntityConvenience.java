package methods;

import client.NgsiRestClient;
import model.AppendContextElement;
import model.AppendContextElementResponse;
import model.ContextAttribute;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;
import smartspotModel.AttributeType;
import smartspotModel.AttributeValue;
import smartspotModel.SmartspotController;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateEntityConvenience {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the headers

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        // Create the contextElement

        AppendContextElement contextElement = new AppendContextElement();

        // Create a list of attributes

        List<ContextAttribute> attributeList = SmartspotController.createAttributeList(
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
        );

        // Add the list of attributes to the contextElement

        contextElement.setAttributeList(attributeList);

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        AppendContextElementResponse appendContextElementResponse = ngsiRestClient.appendContextElement(providerUrl, httpHeaders, "Prueba_convenience_java", contextElement).get();
        System.out.println(appendContextElementResponse.toString());
    }
}
