package bistro.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bistro.bean.CommentBean;
import bistro.bean.OrderDetailsBean;


public class CommentDao {

	private Session session;

	public CommentDao(Session session) {
		this.session = session;
	}
	
	public Session getSession() {
		return session;
	}
	
	
	public List<CommentBean> findAllComment(){
		Query<CommentBean> query = session.createQuery("from CommentBean",CommentBean.class);
		return query.list();
	}
	
	public List<CommentBean> findCommentByOrderid(int orderId){
		String hql="From CommentBean WHERE order_id = :orderId";
		Query<CommentBean> query = session.createQuery(hql,CommentBean.class);
		query.setParameter(hql, orderId);
		return query.list();																												
	}
	
	
	public CommentBean findCommentByid(int id) {
		session.get(CommentBean.class,id);
		
		return null;
		
	}
	
	
	
	
}
