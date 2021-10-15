package com.model.stockexchange.repo;

import com.model.stockexchange.entity.StockExchangeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockExchangeRepo extends JpaRepository<StockExchangeDetails, Integer> {


    @Query("select sed from StockExchangeDetails sed where sed.optionId=1 and sed.symbol =?1 and sed.price <=?2 order by sed.price asc")
    List<StockExchangeDetails> fetchSellingStocksBySymbolAndPrice(String symbol, double price);

    @Query("select sed from StockExchangeDetails sed where sed.optionId=0 and sed.symbol =?1 and sed.price >=?2 order by sed.price desc")
    List<StockExchangeDetails> findBuyingStockBySymbolAndPrice(String symbol, double price);

    @Query("select sed from StockExchangeDetails sed where sed.userId =?1")
    List<StockExchangeDetails> findStockDetailsByUserId(Long userId);
}
