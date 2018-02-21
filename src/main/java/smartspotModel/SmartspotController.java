package smartspotModel;

import model.ContextAttribute;
import model.ContextElement;

import java.util.LinkedList;
import java.util.List;

public class SmartspotController {


    public static List<ContextAttribute> createAttributeList(AttributeValue co, AttributeValue h2s, AttributeValue no2,
                                                             AttributeValue o3, AttributeValue so2, AttributeValue batteryLevel,
                                                             AttributeValue humidity0, AttributeValue humidity1, AttributeValue neardeviceshour,
                                                             AttributeValue neardevicesminute, AttributeValue neardevicestenminutes,
                                                             AttributeValue noise, AttributeValue temperature0, AttributeValue temperature1){

        // Crear atributos
        ContextAttribute attributeCO = new ContextAttribute("CO",co.getType().name(),co.getValue());
        ContextAttribute attributeH2S = new ContextAttribute("H2S",h2s.getType().name(),h2s.getValue());
        ContextAttribute attributeNO2 = new ContextAttribute("NO2",no2.getType().name(),no2.getValue());
        ContextAttribute attributeO3 = new ContextAttribute("O3",o3.getType().name(),o3.getValue());
        ContextAttribute attributeSO2 = new ContextAttribute("SO2",so2.getType().name(),so2.getValue());
        ContextAttribute attributeBattery = new ContextAttribute("batteryLevel",batteryLevel.getType().name(),batteryLevel.getValue());
        ContextAttribute attributeHum0 = new ContextAttribute("humidity0",humidity0.getType().name(),humidity0.getValue());
        ContextAttribute attributeHum1 = new ContextAttribute("humidity1",humidity1.getType().name(),humidity1.getValue());
        ContextAttribute attributeNDH = new ContextAttribute("nearDevicesHour",neardeviceshour.getType().name(),neardeviceshour.getValue());
        ContextAttribute attributeNDM = new ContextAttribute("nearDevicesMinute",neardevicesminute.getType().name(),neardevicesminute.getValue());
        ContextAttribute attributeNDTM = new ContextAttribute("nearDevicesTenMinutes",neardevicestenminutes.getType().name(),neardevicestenminutes.getValue());
        ContextAttribute attributeNoise = new ContextAttribute("noise",noise.getType().name(),noise.getValue());
        ContextAttribute attributeTemp0 = new ContextAttribute("temperature0",temperature0.getType().name(),temperature0.getValue());
        ContextAttribute attributeTemp1 = new ContextAttribute("temperature1",temperature1.getType().name(),temperature1.getValue());

        // Crear lista atributos
        List<ContextAttribute> listaAtributos = new LinkedList<ContextAttribute>();
        listaAtributos.add(attributeCO);
        listaAtributos.add(attributeH2S);
        listaAtributos.add(attributeNO2);
        listaAtributos.add(attributeO3);
        listaAtributos.add(attributeSO2);
        listaAtributos.add(attributeBattery);
        listaAtributos.add(attributeHum0);
        listaAtributos.add(attributeHum1);
        listaAtributos.add(attributeNDH);
        listaAtributos.add(attributeNDM);
        listaAtributos.add(attributeNDTM);
        listaAtributos.add(attributeNoise);
        listaAtributos.add(attributeTemp0);
        listaAtributos.add(attributeTemp1);

        return listaAtributos;

    }

    public static ContextElement createSmartspotAsContextElement(String id, String type,
                                                                 List<ContextAttribute> listaAtributos){
        ContextElement contextElement = new ContextElement();
        contextElement.setId(id);
        contextElement.setType(type);
        //contextElement.setPattern("false");
        contextElement.setContextAttributeList(listaAtributos);

        return contextElement;

        }


}
