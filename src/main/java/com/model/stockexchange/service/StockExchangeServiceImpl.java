package com.model.stockexchange.service;

import com.model.stockexchange.dto.Constants;
import com.model.stockexchange.dto.RequestDto;
import com.model.stockexchange.dto.StatusEnum;
import com.model.stockexchange.entity.StockExchangeDetails;
import com.model.stockexchange.repo.StockExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockExchangeServiceImpl  implements StockExchangeService{

    @Autowired
    private StockExchangeRepo stockExchangeRepo;

    @Override
    public void buy(RequestDto requestDto) {

        StockExchangeDetails buyingStock = placeOrderInExchange(Constants.BUY, requestDto);

        buyingStock = stockExchangeRepo.save(buyingStock);

        List<StockExchangeDetails> availableSellingStocks = stockExchangeRepo.fetchSellingStocksBySymbolAndPrice(requestDto.getSymbol(),
                requestDto.getPrice());

        int buyingQuantity = requestDto.getQuantity();

        // This will take existing sell orders and will do process based on stock prices and quantities and will update the orders with status
        if(buyingQuantity != 0 && !availableSellingStocks.isEmpty()){
            for(StockExchangeDetails sellingStock : availableSellingStocks){
                int availableQuantityToSell = sellingStock.getQuantity();
                if(buyingQuantity < availableQuantityToSell){
                    int remQuantityToBuy = 0;
                    int remQuantityToSell = availableQuantityToSell - buyingQuantity;
                    updateRemainingQuantityInDb(buyingStock, remQuantityToBuy, StatusEnum.COMPLETED.getId());
                    updateRemainingQuantityInDb(sellingStock, remQuantityToSell, StatusEnum.PENDING.getId());
                }
                else {
                    int remQuantityToSell = 0;
                    int remQuantityToBuy = buyingQuantity - availableQuantityToSell;
                    buyingQuantity = remQuantityToBuy;
                    updateRemainingQuantityInDb(buyingStock, remQuantityToBuy, StatusEnum.PENDING.getId());
                    updateRemainingQuantityInDb(sellingStock, remQuantityToSell, StatusEnum.COMPLETED.getId());
                }
            }
        }
    }

    private StockExchangeDetails placeOrderInExchange(byte optionId, RequestDto requestDto) {
        StockExchangeDetails stockExchangeDetails = new StockExchangeDetails();
        stockExchangeDetails.setOption(optionId); // here optionId id buy(0)/sell(1)
        stockExchangeDetails.setUserId(requestDto.getId());
        stockExchangeDetails.setSymbol(requestDto.getSymbol());
        stockExchangeDetails.setQuantity(requestDto.getQuantity());
        stockExchangeDetails.setPrice(requestDto.getPrice());
        stockExchangeDetails.setStatus(StatusEnum.PLACED.getId());
        return stockExchangeDetails;
    }

    @Override
    public void sell(RequestDto requestDto) {

        StockExchangeDetails sellingStock = placeOrderInExchange(Constants.SELL, requestDto);

        sellingStock = stockExchangeRepo.save(sellingStock);

        List<StockExchangeDetails> stockExchangeDetailsList = stockExchangeRepo.findBuyingStockBySymbolAndPrice(
                requestDto.getSymbol(), requestDto.getPrice());


        int sellingQuantity = requestDto.getQuantity();;

        // This will take existing buy orders and will do process based on stock prices and quantities and will update the orders with status
        if(sellingQuantity != 0 && !stockExchangeDetailsList.isEmpty()){
            for(StockExchangeDetails buyingStock : stockExchangeDetailsList){
                int availableQuantityToBuy = buyingStock.getQuantity();
                if(availableQuantityToBuy > sellingQuantity){
                    int remQuantityToBuy = availableQuantityToBuy - sellingQuantity;
                    int remQuantityToSell = 0;
                    updateRemainingQuantityInDb(buyingStock, remQuantityToBuy, StatusEnum.PENDING.getId());
                    updateRemainingQuantityInDb(sellingStock, remQuantityToSell, StatusEnum.COMPLETED.getId());
                }
                else {
                    int remQuantityToSell = sellingQuantity - availableQuantityToBuy;
                    sellingQuantity = remQuantityToSell;
                    int remQuantityToBuy = 0;
                    updateRemainingQuantityInDb(buyingStock, remQuantityToBuy, StatusEnum.COMPLETED.getId());
                    updateRemainingQuantityInDb(sellingStock, remQuantityToSell, StatusEnum.PENDING.getId());
                }
            }
        }
    }

    private void updateRemainingQuantityInDb(StockExchangeDetails stock, int remQuantityToBuy,
                                             Byte status) {
        stock.setQuantity(remQuantityToBuy);
        stock.setStatus(status);
        stockExchangeRepo.save(stock);
    }
}
