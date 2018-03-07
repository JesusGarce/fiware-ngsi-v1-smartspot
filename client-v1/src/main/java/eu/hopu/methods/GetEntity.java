package eu.hopu.methods;

import eu.hopu.client.NgsiClient;
import eu.hopu.model.EntityId;
import eu.hopu.model.QueryContext;
import eu.hopu.model.QueryContextResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GetEntity {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        NgsiClient ngsiClient = new NgsiClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the headers
        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        // Create the entity
        EntityId entidad = new EntityId();
        entidad.setId("Prueba_java");
        entidad.setType("SmartSpot");
        entidad.setIsPattern(false);

        // Add the entity to a list of entities
        List<EntityId> entidades = new LinkedList<EntityId>();
        entidades.add(entidad);

        // Create the method QueryContext
        QueryContext queryContext = new QueryContext();

        // Add the list of entities to a QueryContext
        queryContext.setEntityIdList(entidades);

        ngsiClient.asyncRestTemplate = new AsyncRestTemplate();

        QueryContextResponse queryContextResponse = ngsiClient.queryContext(providerUrl, httpHeaders, queryContext).get();
        System.out.println(queryContextResponse);


    }
}
