package com.sao.satellite.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 Haz 2026
 * <p>
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Satellite {
    @SerializedName("OBJECT_NAME")
    private String name;
    @SerializedName("OBJECT_ID")
    private String id;
    @SerializedName("NORAD_CAT_ID")
    private Integer noradId;
    @SerializedName("INCLINATION")
    private Double inclination; //Eğim
    @SerializedName("MEAN_MOTION")
    private Double meanMotion; // Ortalama hareket
    @SerializedName("ECCENTRICITY")
    private Double eccentricity;

    @Override
    public String toString() {
        return "Satellite{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", noradId=" + noradId +
                ", inclination=" + inclination +
                ", meanMotion=" + meanMotion +
                ", eccentricity=" + eccentricity +
                '}';
    }
}
