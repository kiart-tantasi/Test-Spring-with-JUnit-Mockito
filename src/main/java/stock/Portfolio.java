package stock;

import java.util.List;

public class Portfolio {
    private StockService stockService;
    private List<Stock> stocks;

    public StockService getStockService() {
        return stockService;
    }

    // set service (not constructor)
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    // getter and setter (stocks)
    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    // custom method
    public double getMarketValue(){
        double marketValue = 0.0;

        // looping through list to calculate sum
        for(Stock stock:stocks) {
            marketValue += stockService.getPrice(stock) * stock.getQuantity();
        }

        return marketValue;
    }
}
