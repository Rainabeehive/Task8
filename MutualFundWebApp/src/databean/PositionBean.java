
package databean;

//import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("customerId, fundId")
public class PositionBean {
	private int    customerId;
	private int    fundId;
	private int shares;
	
    public int getCustomerId()              { return customerId;         }
    public int    getFundId()          { return fundId;     }
    public int getShares()          { return shares;     }
    
	public void   setCustomerId(int i)      { customerId = i;            }
	public void   setFundId(int i)     { fundId = i;        }
	public void   setShares(int i)  { shares = i;        }
}
