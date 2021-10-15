package com.model.stockexchange.service;

import com.model.stockexchange.dto.AuthenticationRequest;
import com.model.stockexchange.dto.PortfolioResponseDto;
import com.model.stockexchange.dto.StatusEnum;
import com.model.stockexchange.dto.StockDetailsDto;
import com.model.stockexchange.entity.StockExchangeDetails;
import com.model.stockexchange.entity.UserDetailsEntity;
import com.model.stockexchange.repo.StockExchangeRepo;
import com.model.stockexchange.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockExchangeRepo stockExchangeRepo;

    @Override
    public UserDetailsEntity registerUser(AuthenticationRequest authenticationRequest) throws Exception {

        UserDetailsEntity existingUser = userRepository.findByUsername(authenticationRequest.getUserName());

        if(Objects.nonNull(existingUser)){
            throw new Exception("UserName is not available!");
        }
        UserDetailsEntity entity = new UserDetailsEntity();
        entity.setUsername(authenticationRequest.getUserName());
        entity.setPassword(authenticationRequest.getPassword());
        entity = userRepository.save(entity);
        return entity;
    }

    @Override
    public UserDetailsEntity loginUser(AuthenticationRequest authenticationRequest) throws Exception {

        UserDetailsEntity existingUser = userRepository.findByUsername(authenticationRequest.getUserName());

        if(Objects.isNull(existingUser)){
            throw new Exception("User not available!");
        }
        return existingUser;
    }

    @Override
    public PortfolioResponseDto getPortfolio(Long userId) throws Exception {

        UserDetailsEntity userDetails = userRepository.getById(userId);

        if(Objects.isNull(userDetails)){
            throw new Exception("User not found!");
        }

        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto();
        portfolioResponseDto.setId(userId);
        portfolioResponseDto.setUserName(userDetails.getUsername());

        List<StockDetailsDto> stockDetailsDtos = new ArrayList<>();
        List<StockExchangeDetails> stockExchangeDetailsList = stockExchangeRepo.findStockDetailsByUserId(userId);

        if(!stockExchangeDetailsList.isEmpty()){
            for(StockExchangeDetails stockExchangeDetails : stockExchangeDetailsList){
                StockDetailsDto stockDetailsDto = new StockDetailsDto();
                stockDetailsDto.setSymbol(stockExchangeDetails.getSymbol());
                stockDetailsDto.setOrder(stockExchangeDetails.getOption()==0 ? "Buy" : "Sell");
                stockDetailsDto.setStatus(StatusEnum.getEnumByValue(stockExchangeDetails.getStatus()));
                stockDetailsDto.setQuantity(stockExchangeDetails.getQuantity());
                stockDetailsDto.setPrice(stockExchangeDetails.getPrice());
                stockDetailsDtos.add(stockDetailsDto);
            }
        }
        portfolioResponseDto.setStockDetails(stockDetailsDtos);
        return portfolioResponseDto;
    }
}
