package com.java.ravito_plan.race.domain.dto;

public record RaceFoodDto(Long id, String brandName, String name, int carbohydrates, int calories,
                          int proteins, boolean electrolytes, String link, String comment,
                          String type) {

}
