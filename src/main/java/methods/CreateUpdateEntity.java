package methods;

import client.NgsiClient;

import model.*;
import org.springframework.http.*;
import org.springframework.web.client.AsyncRestTemplate;
import smartspotModel.AttributeType;
import smartspotModel.AttributeValue;
import smartspotModel.SmartspotController;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateUpdateEntity {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        NgsiClient ngsiClient = new NgsiClient();
        String providerUrl = "http://192.168.1.203:1026";

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        UpdateContext update = new UpdateContext();

        // Create the attribute list

        List<ContextAttribute> atributos = SmartspotController.createAttributeList(
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

        ContextElement contextElement = SmartspotController.createSmartspotAsContextElement("Prueba_java_1", "SmartSpot",atributos);

        // Add the element to a list of elementes
        List<ContextElement> elements = new LinkedList<ContextElement>();
        elements.add(contextElement);

        // Add the list of elements to the ContextElement
        update.setContextElements(elements);

        // Configure the updateContext
        update.setUpdateAction(UpdateAction.APPEND);

        ngsiClient.asyncRestTemplate = new AsyncRestTemplate();

        UpdateContextResponse updateContextResponse = ngsiClient.updateContext(providerUrl, httpHeaders, update).get();
        System.out.println(updateContextResponse.toString());

    }
}
