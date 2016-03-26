package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import beans.Student;

/**
 * Servlet implementation class StudentsRegistration
 */
@WebServlet("/reg")
public class StudentsRegistration extends HttpServlet {
	
	SessionFactory sf;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		Configuration cfg = new Configuration();
//		cfg.configure("resources/oracle.cfg.xml");
		cfg.configure("resources/mysql.cfg.xml");
		sf = cfg.buildSessionFactory();
		System.out.println("Session Factory init..");
		
		
		super.init(config);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		int marks =Integer.parseInt( req.getParameter("marks"));
		
		Student st = new Student(0, name, email, marks);
		
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		
		int pk = (Integer)s.save(st);
		t.commit();
		s.close();
		
		resp.getWriter().println("Registration Success.. your ID is : " +pk);
		

	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		sf.close();
		
	}
    
}
