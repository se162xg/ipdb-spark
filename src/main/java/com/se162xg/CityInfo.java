package com.se162xg;

public class CityInfo {
    private String[] data;
    private int size;

    public CityInfo(String[] data) {
        this.data = data;
	this.size = (null != this.data) ? data.length : 0;
    }

    private String get(int index) {
        return (null != this.data) && this.size >= index ? this.data[index-1] : "";
    }

    public String getCountryName() {
        return this.get(1);
    }

    public String getRegionName() {
        return this.get(2);
    }

    public String getCityName() {
        return this.get(3);
    }

    public String getOwnerDomain() {
        return this.get(4);
    }

    public String getIspDomain() {
        return this.get(5);
    }

    public String getLatitude() {
        return this.get(6);
    }

    public String getLongitude() {
        return this.get(7);
    }

    public String getTimezone() {
        return this.get(8);
    }

    public String getUtcOffset() {
        return this.get(9);
    }

    public String getChinaAdminCode() {
        return this.get(10);
    }

    public String getIddCode() {
        return this.get(11);
    }

    public String getCountryCode() {
        return this.get(12);
    }

    public String getContinentCode() {
        return this.get(13);
    }

    public String getIDC() {
        return this.get(14);
    }

    public String getBaseStation() {
        return this.get(15);
    }

    public String getCountryCode3() {
        return this.get(16);
    }

    public String getEuropeanUnion() {
        return this.get(17);
    }

    public String getCurrencyCode() {
        return this.get(18);
    }

    public String getCurrencyName() {
        return this.get(19);
    }

    public String getAnycast() {
        return this.get(20);
    }
}
