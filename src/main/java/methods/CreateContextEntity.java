package methods;

import client.NgsiClient;
import model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateContextEntity {

    public static void main(String[] args) throws ExecutionException, InterruptedException, URISyntaxException {

        NgsiClient ngsiClient = new NgsiClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the header
        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        // Create the registerContext
        RegisterContext registerContext = new RegisterContext();

        // Create the smartSpot
        EntityId entidad = new EntityId();
        entidad.setIsPattern(false);
        entidad.setId("Prueba_contexto");
        entidad.setType("SmartSpot");

        // Add the smartSpot to a list of entities
        List<EntityId> listaEntidades = new LinkedList<EntityId>();

        listaEntidades.add(entidad);

        // Create a list of ContextRegistrations
        List<ContextRegistration> listaContext = new LinkedList<ContextRegistration>();

        // Create a new ContextRegistration
        ContextRegistration cr = new ContextRegistration();
        cr.setEntityIdList(listaEntidades);

        // Add URI to a ContextRegistration
        URI uri = new URI("http://172.18.0.1:1026/v2/entities");

        cr.setProvidingApplication(uri);

        // Add the ContextRegistration to a list of ContextRegistration
        listaContext.add(cr);

        // Add the list of ContextRegistration to the method
        registerContext.setContextRegistrationList(listaContext);

        // Add the duration of the new ContextEntity
        registerContext.setDuration("P1M");

        ngsiClient.asyncRestTemplate = new AsyncRestTemplate();

        RegisterContextResponse registerContextResponse = ngsiClient.registerContext(providerUrl, httpHeaders, registerContext).get();
        System.out.println(registerContextResponse.toString());

    }
}
