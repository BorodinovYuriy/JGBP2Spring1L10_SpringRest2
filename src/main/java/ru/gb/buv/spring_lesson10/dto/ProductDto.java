package ru.gb.buv.spring_lesson10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
    //Правильное ДТО
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Long cost;
    //Создавать ДТО должен Конвертер!!!
}
