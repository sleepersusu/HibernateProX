package bistro.service;

import java.util.List;

import org.hibernate.Session;

import bistro.bean.OrderDetailsBean;
import bistro.bean.OrdersBean;
import bistro.dao.OrderDetailsDao;

public class OrderDetailsService {

	OrderDetailsDao dao;

	public OrderDetailsService(Session session) {
		dao = new OrderDetailsDao(session);

	}
	
	
	public boolean createOrderDetails(OrderDetailsBean saveBean) {
		return dao.createOrderDetails(saveBean);
	}

	public List<OrderDetailsBean> queryAllOrderDetails() {

		return dao.queryAllOrderDetails();

	}
	
	
	public OrderDetailsBean queryOrderDetailsByID(Integer ID) {
		return dao.queryOrderDetailsByID(ID);
	}
	
	public OrderDetailsBean queryOrderDetailsByOrders(OrdersBean orders) {
		return dao.queryOrderDetailsByOrders(orders);
	}
	
	public boolean deleteOrderDetailsById(OrderDetailsBean deleteBean) {
		
		return dao.deleteOrderDetailsById(deleteBean);
	}
	
	public boolean updateOrderDetails(OrderDetailsBean updateBean) {

		return dao.updateOrderDetails(updateBean);
		

	}
	
	

	
	
	
	
	
	
	
}
