package methods;

import client.NgsiClient;
import model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateSubscription {

    public static void main(String[] args) throws URISyntaxException, ExecutionException, InterruptedException {
        NgsiClient ngsiClient = new NgsiClient();
        String providerUrl = "http://172.18.0.1:1026";
        HttpHeaders httpHeaders = new HttpHeaders();

        // Create the headers
        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());

        // Create the subscription
        SubscribeContext suscripcion = new SubscribeContext();

        // Create the list of entities to subscribe
        List<EntityId> listaEntidades = new LinkedList<EntityId>();
        EntityId entidad = new EntityId();
        entidad.setType("SmartSpot");
        entidad.setId("Prueba_java");
        entidad.setIsPattern(false);

        listaEntidades.add(entidad);

        suscripcion.setEntityIdList(listaEntidades);

        // Create the list of attributes to subscribe
        List<String> atributos = new LinkedList<String>();
        atributos.add("SO2");
        atributos.add("H2S");
        atributos.add("O3");
        atributos.add("N2O");
        atributos.add("CO");

        suscripcion.setAttributeList(atributos);

        // Create the duration of subscription
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

        NotifyCondition notify1 = new NotifyCondition();
        notify1.setCondValues(condValues);
        notify1.setType(NotifyConditionEnum.ONCHANGE);

        // Add the notify
        notificaciones.add(notify1);

        // Add the list of notifies
        suscripcion.setNotifyConditionList(notificaciones);

        // Create reference
        URI uri = new URI("http://cygnus:5050/notify");
        suscripcion.setReference(uri);

        // Create throttling
        suscripcion.setThrottling("PT5S");

        ngsiClient.asyncRestTemplate = new AsyncRestTemplate();

        SubscribeContextResponse subscribeContextResponse = ngsiClient.subscribeContext(providerUrl,httpHeaders,suscripcion).get();
        System.out.println(subscribeContextResponse);

    }
}
