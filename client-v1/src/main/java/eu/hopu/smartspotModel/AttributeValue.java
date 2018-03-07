package eu.hopu.smartspotModel;

public class AttributeValue {

    private AttributeType type;
    private String value;

    public AttributeValue(AttributeType type, String value){
        this.type = type;
        this.value = value;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "client.smartspotModel.AttributeValue{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
