package airQualityObserver;

import client.NgsiClient;
import model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.AsyncRestTemplate;
import smartspotModel.AttributeType;
import smartspotModel.AttributeValue;
import smartspotModel.SmartspotController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateUpdateAQO {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        NgsiClient ngsiClient = new NgsiClient();
        String providerUrl = "http://172.18.0.1:1026";

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("fiware-service", "smartspot");
        httpHeaders.add("fiware-servicepath", "/smartspot");
        httpHeaders.add("Content-Type","application/json");

        System.out.println("HTTP Headers: "+httpHeaders.toString());
        /* [...] */

        UpdateContext update = new UpdateContext();

        // Creamos el SmartSpot

        List<ContextAttribute> attributes = new LinkedList<ContextAttribute>();

        ContextAttribute dateObserved = new ContextAttribute("dateObserved","DateTime","2016-03-15T11:00:00/2016-03-15T12:00:00");
        ContextAttribute source = new ContextAttribute("source","Text","http://datos.madrid.es");
        ContextAttribute precipitation = new ContextAttribute("precipitation","Number","4.4224");
        ContextAttribute relativeHumidity = new ContextAttribute("relativeHumidity","Number","12.75044");
        ContextAttribute temperature = new ContextAttribute("temperature","Number","23.421274");
        ContextAttribute windDirection = new ContextAttribute("windDirection","Number","27.441");
        ContextAttribute windSpeed = new ContextAttribute("windSpeed","Number","120.224");
        ContextAttribute airQualityLevel = new ContextAttribute("airQualityLevel","Text","62.44277");
        ContextAttribute reliability = new ContextAttribute("reliability","Number","21.472244");
        ContextAttribute CO = new ContextAttribute("CO","Number","30.5555447");
        ContextAttribute NO = new ContextAttribute("NO","Number","7.2552");
        ContextAttribute NO2 = new ContextAttribute("NO2","Number","13.722244");
        ContextAttribute NOx = new ContextAttribute("NOx","Number","1.2220");
        ContextAttribute SO2 = new ContextAttribute("SO2","Number","23.4522255");
        ContextAttribute CO_Level = new ContextAttribute("CO_Level","Text","medium");
        ContextAttribute NO_Level = new ContextAttribute("NO_Level","Text","dangerous");
        ContextAttribute refPointOfInterest = new ContextAttribute("refPointOfInterest","Text","28079004-Pza. de España");
        ContextAttribute location_2 = new ContextAttribute("location_2","geo:point","-3.712247222222222, 40.423852777777775");

        List<ContextAttribute> locationAttributes = new LinkedList<ContextAttribute>();
        locationAttributes.add(new ContextAttribute("type","Text","Point"));
        locationAttributes.add(new ContextAttribute("coordinates","Text","[-3.712247222222222, 40.423852777777775]"));

        Location locationP = new Location("Point","[-3.712247222222222, 40.423852777777775]");

        ContextAttribute location = new ContextAttribute("location","json:geo",locationP);


        List<ContextAttribute> addressAtributes = new LinkedList<ContextAttribute>();
        addressAtributes.add(new ContextAttribute("addressCountry","Text","ES"));
        addressAtributes.add(new ContextAttribute("addressLocality","Text","Madrid"));
        addressAtributes.add(new ContextAttribute("streetAddress","Text","Plaza de Espana"));

        Address addressP = new Address("Madrid","ES","Plaza Espana");

        ContextAttribute address = new ContextAttribute("address","Address",addressP);


        attributes.add(dateObserved);
        attributes.add(source);
        attributes.add(precipitation);
        attributes.add(relativeHumidity);
        attributes.add(temperature);
        attributes.add(windDirection);
        attributes.add(windSpeed);
        attributes.add(airQualityLevel);
        attributes.add(reliability);
        attributes.add(CO);
        attributes.add(NO);
        attributes.add(NO2);
        attributes.add(NOx);
        attributes.add(SO2);
        attributes.add(CO_Level);
        attributes.add(NO_Level);
        attributes.add(refPointOfInterest);
        attributes.add(address);
        attributes.add(location);
        attributes.add(location_2);

        ContextElement contextElement = SmartspotController.createSmartspotAsContextElement("Prueba_AQO_3", "AirQualityObserved",attributes);

        // Añadimos el SmartSpot a la lista de elementos
        List<ContextElement> elements = new LinkedList<ContextElement>();
        elements.add(contextElement);

        // Añadimos la lista de elementos al UpdateContext
        update.setContextElements(elements);

        // COnfiguramos el updateContext
        update.setUpdateAction(UpdateAction.APPEND);

        ngsiClient.asyncRestTemplate = new AsyncRestTemplate();

        UpdateContextResponse updateContextResponse = ngsiClient.updateContext(providerUrl, httpHeaders, update).get();
        System.out.println(updateContextResponse.toString());

    }
}
