package com.model.stockexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDetailsDto {

    private String symbol;
    private StatusEnum status;
    private Integer quantity;
    private Double price;
    private String order;
}
