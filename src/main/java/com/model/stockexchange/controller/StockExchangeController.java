package com.model.stockexchange.controller;

import com.model.stockexchange.dto.RequestDto;
import com.model.stockexchange.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockExchangeController {

    @Autowired
    private StockExchangeService stockExchangeService;

    // This endpoint will handle stock buying process, it will require jwt token in headers
    @PostMapping("/buy")
    public ResponseEntity<?> buyStock(@RequestBody RequestDto requestDto) {
        stockExchangeService.buy(requestDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    // This endpoint will handle stock selling process, it will require jwt token in headers
    @PostMapping("/sell")
    public ResponseEntity<?> sellStock(@RequestBody RequestDto requestDto) {
        stockExchangeService.sell(requestDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
