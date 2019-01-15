package com.ayt.model;


public class City {
    private Integer id;
    private Integer provinceId;
    private String cityName;
    private String description;
    private User user;
    public City() {
    }
    public City(Integer id, Integer provinceId, String cityName, String description, User user) {
        this.id = id;
        this.provinceId = provinceId;
        this.cityName = cityName;
        this.description = description;
        this.user = user;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", provinceId=" + provinceId +
                ", cityName='" + cityName + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }


}