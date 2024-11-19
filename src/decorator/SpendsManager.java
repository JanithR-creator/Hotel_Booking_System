package decorator;

import config.HibernateUtil;
import entity.Bill;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

public class SpendsManager {
    public static String Nic;

    public SpendsManager() {

    }

    public double manager() {
        try (Session session = HibernateUtil.getSession()) {
            String sql = "SELECT * FROM Bill WHERE customer_id = :nic";
            NativeQuery<Bill> query = session.createNativeQuery(sql, Bill.class);
            query.setParameter("nic", Nic);
            Bill bill = query.getSingleResult();

            Spend spend = new Reservation(bill.getRoomCharge());
            spend = new Bar(spend, bill.getBarCharge());
            spend = new Gym(spend, bill.getGymCharge());
            spend = new Restaurant(spend, bill.getRestCharge());
            spend = new Spa(spend, bill.getSpaCharge());

            return spend.getAmount();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
