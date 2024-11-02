package bistro.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bistro.bean.OrderDetailsBean;
import bistro.bean.OrdersBean;

public class OrderDetailsDao {
	private Session session;

	public OrderDetailsDao(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public boolean createOrderDetails(OrderDetailsBean saveBean) {
		session.persist(saveBean);
		return true;
	}
	
	
	public List<OrderDetailsBean> queryAllOrderDetails() {
		Query<OrderDetailsBean> query = session.createQuery("from OrderDetailsBean", OrderDetailsBean.class);
		return query.list();
	}

	public OrderDetailsBean queryOrderDetailsByID(Integer ID) {
		return session.get(OrderDetailsBean.class, ID);
	}

	public OrderDetailsBean queryOrderDetailsByOrders(OrdersBean orders) {
		return session.get(OrderDetailsBean.class, orders);
	}

	public boolean deleteOrderDetailsById(OrderDetailsBean deleteBean) {
		session.remove(deleteBean);
		return true;
	}

	boolean updateOrderDetails(OrderDetailsBean updateBean) {

		session.merge(updateBean);
		return true;

	}

}
