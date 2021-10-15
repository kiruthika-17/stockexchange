package com.model.stockexchange.service;

import com.model.stockexchange.dto.RequestDto;

public interface StockExchangeService {
    void buy(RequestDto requestDto);

    void sell(RequestDto requestDto);
}
