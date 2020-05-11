package trails.mockito;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Nov 2019, 2:08 PM
 */
public class Stock
{
    private String stockId;
    private String name;
    private int quantity;

    public Stock(String stockId, String name, int quantity)
    {
        this.stockId = stockId;
        this.name = name;
        this.quantity = quantity;
    }

    public String getStockId()
    {
        return stockId;
    }

    public void setStockId(String stockId)
    {
        this.stockId = stockId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getTicker()
    {
        return name;
    }
}
