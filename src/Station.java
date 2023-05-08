public class Station {
    private int stationId;
    private String stationName;
    private String city;
    private String country;

    public Station(int stationId, String stationName, String city,  String country) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.city = city;
        this.country = country;
    }

    public Station() {

    }

    public int getStationId() {
        return stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public String getCity() {
        return city;
    }


    public String getCountry() {
        return country;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Station" +
                "\n, stationId=" + stationId +
                "\n, stationName=" + stationName +
                "\n, city=" + city +
                "\n, country=" + country +
                '\n';
    }
}
