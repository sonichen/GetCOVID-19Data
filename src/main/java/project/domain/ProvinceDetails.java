package project.domain;

public class ProvinceDetails {
    private Integer iso_pro;
    private String  provinceName;
    private Integer recovered;
    private Integer confirmed;
    private String  updated;
    private  Double latitude;
    private  Double longitude ;
    private  Integer deaths;

    public Integer getIso_pro() {
        return iso_pro;
    }

    public void setIso_pro(Integer iso_pro) {
        this.iso_pro = iso_pro;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    @Override
    public String toString() {
        return "{" +
                "地区名='" + provinceName + '\'' +
                ", 地区治愈人数=" + recovered +
                ", 地区确诊人数=" + confirmed +
                ", 数据更新日期='" + updated + '\'' +
                ", 纬度=" + latitude +
                ", 经度=" + longitude +
                ", 死亡人数=" + deaths +
                '}';
    }
}
