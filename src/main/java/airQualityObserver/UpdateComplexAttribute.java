package airQualityObserver;

import client.NgsiRestClient;
import model.ContextAttribute;
import model.StatusCode;
import model.UpdateContextAttribute;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class UpdateComplexAttribute {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        Address addressP = new Address("Madrid","ES","Plaza Callao");

        ContextAttribute atributo = new ContextAttribute();
        atributo.setType("Address");
        atributo.setValue(addressP);

        UpdateContextAttribute updateContextAttribute = new UpdateContextAttribute();
        updateContextAttribute.setAttribute(atributo);

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();


        StatusCode statusCode = ngsiRestClient.updateContextAttribute(providerUrl, httpHeaders, "Prueba_AQO_2", "address", updateContextAttribute).get();
        System.out.println(statusCode.toString());


    }

}
