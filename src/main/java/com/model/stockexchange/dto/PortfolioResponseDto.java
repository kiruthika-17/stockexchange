package com.model.stockexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponseDto {

    private Long id;
    private String userName;
    private List<StockDetailsDto> stockDetails;
}
