import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {
    @EmbeddedId
    private PurchaseListPK id;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;


    /**
     * Getters and setters
     */
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public PurchaseListPK getId() {
        return id;
    }

    public void setId(PurchaseListPK id) {
        this.id = id;
    }
}


