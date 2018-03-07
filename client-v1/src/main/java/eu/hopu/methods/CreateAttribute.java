package eu.hopu.methods;

import eu.hopu.client.NgsiRestClient;
import eu.hopu.model.ContextAttribute;
import eu.hopu.model.StatusCode;
import eu.hopu.model.UpdateContextAttribute;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class CreateAttribute {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the header
        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        // Create the attribute
        ContextAttribute attribute = new ContextAttribute();
        attribute.setType("Number");
        attribute.setValue("1.233212");

        // Create the method updateContextAttribute
        UpdateContextAttribute updateContextAttribute = new UpdateContextAttribute();

        // Add the attribute to create
        updateContextAttribute.setAttribute(attribute);

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        StatusCode statusCode = ngsiRestClient.appendContextAttribute(providerUrl, httpHeaders, "prueba_java_5", "CO", updateContextAttribute).get();
        System.out.println(statusCode.toString());
    }
}
