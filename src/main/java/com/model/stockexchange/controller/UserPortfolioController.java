package com.model.stockexchange.controller;

import com.model.stockexchange.dto.PortfolioResponseDto;
import com.model.stockexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserPortfolioController {

    @Autowired
    private UserService userService;

    //This endpoint will list the user details and stock details (stock symbol, quatity, order, price, status) that is holding by the given user
    @GetMapping("/portfolio/{userId}")
    public ResponseEntity<?> getPortfolio(@PathVariable("userId") Long userId) throws Exception {
        PortfolioResponseDto responseDto = userService.getPortfolio(userId);
        return new ResponseEntity<PortfolioResponseDto>(responseDto, HttpStatus.OK);
    }

}
