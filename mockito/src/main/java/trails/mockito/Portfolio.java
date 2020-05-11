package trails.mockito;

import java.util.List;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Nov 2019, 2:08 PM
 */
public class Portfolio
{
    private StockService stockService;
    private List<Stock> stocks;

    public StockService getStockService()
    {
        return stockService;
    }

    public void setStockService(StockService stockService)
    {
        this.stockService = stockService;
    }

    public List<Stock> getStocks()
    {
        return stocks;
    }

    public void setStocks(List<Stock> stocks)
    {
        this.stocks = stocks;
    }

    public double getMarketValue()
    {
        double marketValue = 0.0;

        for (Stock stock : stocks) {
            marketValue += stockService.getPrice(stock) * stock.getQuantity();
        }
        return marketValue;
    }
}
