
package databean;

//import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("fundId, priceDate")
public class FundPriceHistoryBean {
	private int fundId;
	private String priceDate;
	private double price;

	public int getFundId() {
		return fundId;
	}

	public String getPriceDate() {
		return priceDate;
	}

	public double getPrice() {
		return price;
	}

	public void setFundId(int i) {
		fundId = i;
	}

	public void setExecuteDate(String s) {
		priceDate = s;
	}

	public void setPrice(double d) {
		price = d;
	}
}
