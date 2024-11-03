package bistro.actionTest;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import bistro.util.HibernateUtil;

public class ServiceActionTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();


	}
