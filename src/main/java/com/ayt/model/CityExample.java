package com.ayt.model;

import lombok.*;

/**
 * Description
 * Author ayt  on 20181231
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CityExample {
    private Integer id;

    private Integer provinceId;

    private String cityName;

    private String description;
    private User user;
}
