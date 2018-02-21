package airQualityObserver;

public class Address {

    private String addressLocality;

    private String addressCountry;

    private String streetAddress;

    public Address(){

    }

    public Address(String addressLocality, String addressCountry, String streetAddress){
        this.addressCountry = addressCountry;
        this.addressLocality = addressLocality;
        this.streetAddress = streetAddress;
    }

    public String getAddressLocality() {
        return addressLocality;
    }

    public void setAddressLocality(String addressLocality) {
        this.addressLocality = addressLocality;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
