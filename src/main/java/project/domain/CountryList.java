package project.domain;

public class CountryList {
    private  Integer iso;
    private  String countryName;

    public Integer getIso() {
        return iso;
    }

    public void setIso(Integer iso) {
        this.iso = iso;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "国家:'" + countryName + '\'' ;
    }
}
