package bistro.members.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bistro.bean.MembersBean;
import bistro.bean.MembersDetailBean;
import bistro.service.MembersService;
import bistro.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/reateMembersServlet.do")
public class reateMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("useraccount"));
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		String useraccount = request.getParameter("useraccount");
		System.out.println(useraccount);
		String userpwd = request.getParameter("userpwd");
		System.out.println(userpwd);
		String username = request.getParameter("username");
		System.out.println(username);
		Short usersex = Short.parseShort(request.getParameter("usersex"));
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String useraddress = request.getParameter("useraddress");
		String userphone = request.getParameter("userphone");
		String useremail = request.getParameter("useremail");
		Short userfavor = Short.parseShort(request.getParameter("userfavor"));
		
		InputStream inputStream = null;
		
		try {
			LocalDate userDate = LocalDate.parse(request.getParameter("userbirthday"), formatterDate);
			Integer userage = userDate.getYear() - 1911;
			Date userbirthday = Date.valueOf(userDate);
			MembersService mbservice = new MembersService(session);
			MembersBean mbBean = new MembersBean();
			mbBean.setMember_account(useraccount);
			mbBean.setMember_password(userpwd);
			MembersDetailBean mbDetailBean = new MembersDetailBean();

			Part file = request.getPart("userimg");
			int filesize = (int) file.getSize();// 取檔案大小
			byte[] userimg = new byte[filesize];// 設定記憶體空間依檔案大小
			inputStream = file.getInputStream();// 獲取檔案的串流
			userimg = inputStream.readAllBytes();// 由串流將Byte資料寫入

			mbDetailBean.setMembersD_name(username);
			mbDetailBean.setMembersD_sex(usersex);
			mbDetailBean.setMembersD_birthday(userbirthday);
			mbDetailBean.setMembersD_favor(userfavor);
			mbDetailBean.setMembersD_address(useraddress);
			mbDetailBean.setMembersD_phone(userphone);
			mbDetailBean.setMembersD_email(useremail);
			mbDetailBean.setMembersD_img(userimg);

			mbDetailBean.setMembersD_age(userage);
			
			mbBean.setMembersDetailBean(mbDetailBean);//設置Bean關聯
			mbDetailBean.setMembersBean(mbBean);//設置Bean關聯
			
			boolean insertState = mbservice.createMembers(mbBean);
			if (insertState) {
				response.sendRedirect(request.getContextPath() + "/MembersMainServlet.do");
			} else {
				out.println("新增會員失敗...");
			}

		} catch (IOException e) {
		    out.println("檔案處理異常");
		    e.printStackTrace();
		} catch (ServletException e) {
			out.println("getPart異常");
			e.printStackTrace();
		} finally {
		    if (inputStream != null) {
		        try {
		            inputStream.close();
		        } catch (IOException e) {
		        	out.println("inputStream發生未知錯誤");
		            e.printStackTrace();
		        }
		    }
		    out.close();
		}

	}

}
