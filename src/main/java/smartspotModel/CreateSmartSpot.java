package smartspotModel;

import model.ContextAttribute;
import model.ContextElement;

import java.util.LinkedList;
import java.util.List;

public class CreateSmartSpot {


    public static ContextAttribute createContextAttribute(String name, String type, String value){
        ContextAttribute contextAttribute = new ContextAttribute();
        contextAttribute.setName(name);
        contextAttribute.setType(type);
        contextAttribute.setValue(value);

        return contextAttribute;
    }

    public static ContextElement createContextElement(List<ContextAttribute> contextAttributeList, String id, String type ){
        ContextElement contextElement = new ContextElement();
        contextElement.setId(id);
        contextElement.setType(type);
        //contextElement.setPattern("false");
        contextElement.setContextAttributeList(contextAttributeList);

        return contextElement;
    }

    public static List<ContextAttribute> createAttributeList(){
        ContextAttribute co = createContextAttribute("CO","Number","10.6265523");
        ContextAttribute h2s = createContextAttribute("H2S","Number", "3.87472597094806");
        ContextAttribute no2 = createContextAttribute("NO2","Number", "1.87472597094806");
        ContextAttribute o3 = createContextAttribute("O3","Number", "4.2263266");
        ContextAttribute so2 = createContextAttribute("SO2","Number", "6.6522");
        ContextAttribute batteryLevel = createContextAttribute("batteryLevel","Integer", "8.59965");
        ContextAttribute humidity0 = createContextAttribute("humidity0","Number", "16.6568595");
        ContextAttribute humidity1 = createContextAttribute("humidity1","Number", "21.2332221");
        ContextAttribute neardeviceshour = createContextAttribute("nearDevicesHour","Integer", "86");
        ContextAttribute neardevicesminute = createContextAttribute("nearDevicesMinute","Integer", "3");
        ContextAttribute neardevicestenminutes = createContextAttribute("nearDevicesTenMinutes","Integer", "10");
        ContextAttribute noise = createContextAttribute("noise","Number", "23.2394922");
        ContextAttribute temperature0 = createContextAttribute("temperature0","Number", "7.5233");

        // Crear lista atributos
        List<ContextAttribute> listaAtributos = new LinkedList<ContextAttribute>();
        listaAtributos.add(co);
        listaAtributos.add(h2s);
        listaAtributos.add(no2);
        listaAtributos.add(o3);
        listaAtributos.add(so2);
        listaAtributos.add(batteryLevel);
        listaAtributos.add(humidity0);
        listaAtributos.add(humidity1);
        listaAtributos.add(neardeviceshour);
        listaAtributos.add(neardevicesminute);
        listaAtributos.add(neardevicestenminutes);
        listaAtributos.add(noise);
        listaAtributos.add(temperature0);

        return listaAtributos;
    }

    public static ContextElement createSmartSpot(String id, String type ){
        // Create attributes
        List<ContextAttribute> listaAtributos = createAttributeList();

        // Create contextElements
        ContextElement contextElement = createContextElement(listaAtributos,id,type);
        return contextElement;

    }

}
