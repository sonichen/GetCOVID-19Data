package project.domain;

public class CountryDetails {
    private  Integer iso_country;
    private String continent;
   private String capital_city;
    private  Double life_expectancy;
    private String abbreviation ;
    private  Integer  confirmed;
   private Integer population;
    private Integer  sq_km_area;
    private Integer  recovered;
   private String elevation_in_meters;
   private String  location;
   private Integer deaths;

    public Integer getIso_country() {
        return iso_country;
    }

    public void setIso_country(Integer iso_country) {
        this.iso_country = iso_country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCapital_city() {
        return capital_city;
    }

    public void setCapital_city(String capital_city) {
        this.capital_city = capital_city;
    }

    public Double getLife_expectancy() {
        return life_expectancy;
    }

    public void setLife_expectancy(Double life_expectancy) {
        this.life_expectancy = life_expectancy;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getSq_km_area() {
        return sq_km_area;
    }

    public void setSq_km_area(Integer sq_km_area) {
        this.sq_km_area = sq_km_area;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public String getElevation_in_meters() {
        return elevation_in_meters;
    }

    public void setElevation_in_meters(String elevation_in_meters) {
        this.elevation_in_meters = elevation_in_meters;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    @Override
    public String toString() {
        return "国家信息{" +
                "国家所属洲='" + continent + '\'' +
                ", 首都='" + capital_city + '\'' +
                ", 预期寿命=" + life_expectancy +
                ", 国家名称缩写='" + abbreviation + '\'' +
                ", 全国确诊数量=" + confirmed +
                ", 全国总人口=" + population +
                ", 国家领土大小=" + sq_km_area +
                ", 全国治愈人数=" + recovered +
                ", 海拔='" + elevation_in_meters + '\'' +
                ", 地理位置='" + location + '\'' +
                ", 全国死亡人数=" + deaths +
                '}';
    }
}
