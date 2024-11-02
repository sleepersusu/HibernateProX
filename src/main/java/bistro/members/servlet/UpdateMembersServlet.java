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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig // 處理多類型資料如圖片要增加此宣告
@WebServlet("/UpdateMembersServlet.do")
public class UpdateMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		InputStream inputStream = null;
		try {
			Part filePart = request.getPart("edituserimg");
			System.out.println("edituserimg:"+request.getPart("edituserimg"));
			int filesize = (int) filePart.getSize();
			byte[] userimg = new byte[filesize];
			System.out.println("檔案大小:" + filesize);
			inputStream = filePart.getInputStream();
			userimg = inputStream.readAllBytes();

			Integer userid = Integer.parseInt(request.getParameter("edituserid"));
			String useraccount = request.getParameter("edituseraccount");
			String userpwd = request.getParameter("edituserpwd");
			String username = request.getParameter("editusername");
			Short usersex = Short.parseShort(request.getParameter("editusersex"));

			DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate userDate = LocalDate.parse(request.getParameter("edituserbirthday"), formatterDate);
			Date userbirthday = Date.valueOf(userDate);
			Integer userage = userDate.getYear() - 1911;

			Short userfavor = Short.parseShort(request.getParameter("edituserfavor"));
			String useraddress = request.getParameter("edituseraddress");
			String userphone = request.getParameter("edituserphone");
			String useremail = request.getParameter("edituseremail");

			MembersService mbService = new MembersService(session);
			MembersBean mbBean = new MembersBean();
			MembersBean membersById = mbService.findMembersById(userid);
			MembersDetailBean mbDetailById = membersById.getMembersDetailBean();
			
			membersById.setMembers_id(userid);
			membersById.setMember_account(useraccount);
			membersById.setMember_password(userpwd);

//			mbDetailById.setMembers_id(userid);
			mbDetailById.setMembersD_name(username);
			mbDetailById.setMembersD_sex(usersex);
			mbDetailById.setMembersD_birthday(userbirthday);
			mbDetailById.setMembersD_favor(userfavor);
			mbDetailById.setMembersD_address(useraddress);
			mbDetailById.setMembersD_phone(userphone);
			mbDetailById.setMembersD_email(useremail);
			mbDetailById.setMembersD_age(userage);
			if(filesize!=0) {
				mbDetailById.setMembersD_img(userimg);
			}else {
				mbDetailById.setMembersD_img(mbDetailById.getMembersD_img());
			}
			
			membersById.setMembersDetailBean(mbDetailById);// 設置Bean關聯
			mbDetailById.setMembersBean(membersById);// 設置Bean關聯

			boolean UpdateState = mbService.updateMembers(membersById);

			if (UpdateState) {
				response.sendRedirect(request.getContextPath() + "/MembersMainServlet.do");
			} else {
				out.println("編輯會員資訊失敗");
			}
		} catch (IOException e) {
			out.println("檔案處理異常");
			e.printStackTrace();
		} catch (ServletException e) {
			out.println("檔案上傳異常");
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
