package eu.hopu.methods;

import eu.hopu.client.NgsiRestClient;
import eu.hopu.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateContextSubscription {

    public static void main(String[] args) throws ExecutionException, InterruptedException, URISyntaxException {
        NgsiRestClient ngsiRestClient = new NgsiRestClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the header
        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        // Create the suscribeContext
        SubscribeContext suscripcion = new SubscribeContext();

        // Create the List of Entities
        List<EntityId> listaEntidades = new LinkedList<EntityId>();

        // Create a Smartspot
        EntityId entidad = new EntityId();
        entidad.setType("SmartSpot");
        entidad.setId("Prueba_java");
        entidad.setIsPattern(false);

        // Add the Smartspot to a List of Entities
        listaEntidades.add(entidad);

        // Add the list of entities to the subscribeContext
        suscripcion.setEntityIdList(listaEntidades);

        // Create list of attributes
        List<String> atributos = new LinkedList<String>();
        atributos.add("SO2");
        atributos.add("H2S");
        atributos.add("O3");
        atributos.add("N2O");
        atributos.add("CO");

        // Add the list of attributes to the subscribeContext
        suscripcion.setAttributeList(atributos);

        // Add the duration of the new SubscribeContext
        suscripcion.setDuration("P1M");

        // Create a list of notifies
        List<NotifyCondition> notificaciones = new LinkedList<NotifyCondition>();

        // Create list of conditions
        List<String> condValues = new LinkedList<String>();
        condValues.add("SO2");
        condValues.add("H2S");
        condValues.add("O3");
        condValues.add("N2O");
        condValues.add("CO");

        // Create a notify
        NotifyCondition notify1 = new NotifyCondition();

        // Add the conditions to the notify
        notify1.setCondValues(condValues);

        // Change the type of notify
        notify1.setType(NotifyConditionEnum.ONCHANGE);

        // Add the notify to the list of notifies
        notificaciones.add(notify1);

        // Add the list of notifies to the subscription
        suscripcion.setNotifyConditionList(notificaciones);

        // Add the reference to the subscription
        URI uri = new URI("http://cygnus:5050/notify");
        suscripcion.setReference(uri);

        // Add the throttling to the subscription
        suscripcion.setThrottling("PT5S");

        ngsiRestClient.asyncRestTemplate = new AsyncRestTemplate();

        SubscribeContextResponse subscribeContextResponse = ngsiRestClient.appendContextSubscription(providerUrl, httpHeaders, suscripcion).get();
        System.out.println(subscribeContextResponse.toString());
    }
}
