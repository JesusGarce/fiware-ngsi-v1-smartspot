package eu.hopu;

import eu.hopu.client.NgsiRestClient;
import eu.hopu.model.AppendContextElement;
import eu.hopu.model.AppendContextElementResponse;
import eu.hopu.model.ContextAttribute;
import eu.hopu.model.StatusCode;
import eu.hopu.smartspotModel.AttributeType;
import eu.hopu.smartspotModel.AttributeValue;
import eu.hopu.smartspotModel.SmartspotController;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ManageDevices {


    public void createDevice(String url) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();

        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the headers

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        AppendContextElement contextElement = new AppendContextElement();

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

        contextElement.setAttributeList(attributeList);

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        AppendContextElementResponse appendContextElementResponse = ngsiRestClient.appendContextElement(url, httpHeaders, "Prueba_servidor", contextElement).get();

        System.out.println(appendContextElementResponse.getErrorCode());
    }

    public void updateDevice() {

    }

    public void deleteDevice(String url) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();

        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the headers

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        StatusCode statusCode = ngsiRestClient.deleteContextElement(url, httpHeaders, "Prueba_java_convenience").get();
        System.out.println(statusCode);
    }

}
