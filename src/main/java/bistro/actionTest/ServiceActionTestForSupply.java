package bistro.actionTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import bistro.bean.EmployeeBean;
import bistro.bean.SupplyBean;
import bistro.bean.SupplyOriBean;
import bistro.bean.UsersBean;

import java.sql.Timestamp;

public class ServiceActionTestForSupply {
    public static void main(String[] args) {
        // 创建 SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // 假设你要插入或更新用户
            String username = "toro"; // 假设这是你要插入的用户名
            UsersBean user;

            // 查询用户是否存在
            Query<UsersBean> query = session.createQuery("FROM UsersBean WHERE users_account = :username", UsersBean.class);
            query.setParameter("username", username);
            user = query.uniqueResult();

            if (user == null) {
                // 如果用户不存在，创建新用户
                user = new UsersBean();
                user.setUsers_account(username);
                user.setUsers_password("password123"); // 设置用户密码
                session.persist(user); // 使用 persist() 插入新用户
            } else {
                // 如果用户已经存在，使用 merge() 更新
                user.setUsers_password("newpassword123"); // 更新用户密码
                session.merge(user); // 使用 merge() 更新
            }

            // 确保 user.id 被设置
            int userId = user.getUsers_id(); // 确保在保存后获取正确的用户ID

            // 创建供应商
            SupplyOriBean supplyOri1 = new SupplyOriBean();
            supplyOri1.setSupplyOriName("大成");
            supplyOri1.setSupplyOriTel("0912345678");
            supplyOri1.setSupplyOriAddress("台南市中西區A街1號");
            supplyOri1.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            SupplyOriBean supplyOri2 = new SupplyOriBean();
            supplyOri2.setSupplyOriName("卜蜂");
            supplyOri2.setSupplyOriTel("0912345679");
            supplyOri2.setSupplyOriAddress("台南市中西區B街2號");
            supplyOri2.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            // 保存供应商
            session.persist(supplyOri1);
            session.persist(supplyOri2);

            // 创建员工
            EmployeeBean employee = new EmployeeBean();
            employee.setUser(user); // 直接设置用户关系
            employee.setEmployeeName("李翔");
            employee.setEmployeeGender("男");
            employee.setEmployeeBorn(new Timestamp(System.currentTimeMillis())); // 用当前时间替代，实际应为出生日期
            employee.setEmployeeTel("0964587890");
            employee.setEmployeeSeniority(5); // 5年资
            employee.setEmployeeSalary(75000); // 薪资
            employee.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            // 保存员工
            session.persist(employee);

            // 创建供货
            SupplyBean supply1 = new SupplyBean();
            supply1.setEmployeeBean(employee);
            supply1.setSupplyProduct("雞腿排");
            supply1.setSupplyCount(100);
            supply1.setSupplyPrice(5000);
            supply1.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            // 关联供应商
            supply1.getSupplyOriBeans().add(supplyOri1);
            supply1.getSupplyOriBeans().add(supplyOri2);

            SupplyBean supply2 = new SupplyBean();
            supply2.setEmployeeBean(employee);
            supply2.setSupplyProduct("产品2");
            supply2.setSupplyCount(200);
            supply2.setSupplyPrice(300);
            supply2.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            // 关联供应商
            supply2.getSupplyOriBeans().add(supplyOri1);

            // 保存供货
            session.persist(supply1);
            session.persist(supply2);

            // 提交事务
            transaction.commit();
            System.out.println("數據插入成功");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            System.out.println("數據插入失敗");
        } finally {
            if (session != null) session.close();
            sessionFactory.close();
        }
    }
}
