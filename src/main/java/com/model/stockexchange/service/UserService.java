package com.model.stockexchange.service;

import com.model.stockexchange.dto.AuthenticationRequest;
import com.model.stockexchange.dto.PortfolioResponseDto;
import com.model.stockexchange.entity.UserDetailsEntity;

public interface UserService {
    UserDetailsEntity registerUser(AuthenticationRequest authenticationRequest) throws Exception;

    UserDetailsEntity loginUser(AuthenticationRequest authenticationRequest) throws Exception;

    PortfolioResponseDto getPortfolio(Long userId) throws Exception;
}
