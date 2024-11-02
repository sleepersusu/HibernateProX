package bistro.pointsRecordServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.MembersDetailBean;
import bistro.bean.PointPrizesBean;
import bistro.bean.PointsRecordBean;
import bistro.util.HibernateUtil;


@WebServlet("/DeletePointsRecordServlet.do")
public class DeletePointsRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

    private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 取得要刪除的 PointsRecord ID
        int pointsRecords_id = Integer.parseInt(request.getParameter("recordsId"));
        
        // 使用 Hibernate session 進行刪除操作
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // 查找要刪除的 PointsRecordBean
            PointsRecordBean pointsRecord = session.get(PointsRecordBean.class, pointsRecords_id);
            if (pointsRecord != null) {
                // 刪除該記錄
                session.delete(pointsRecord);
                session.getTransaction().commit();
	            response.sendRedirect(request.getContextPath() + "/ShowAllPointsRecordServlet.do");
            } else {
                out.println("Record not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred while deleting the record.");
        } finally {
            out.close();
        }
    }
}
