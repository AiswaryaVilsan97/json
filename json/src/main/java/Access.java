import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 

public class Access extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		String empl_name =req.getParameter("username");
		String role= req.getParameter("password");
		
		PrintWriter out= res.getWriter();
		Employee emp= new Employee();
		Connection c=null;
	    PreparedStatement ps=null;
	    Statement st=null;
	    ResultSet r=null;
		
		 try {
			 if(emp.validate(empl_name,role)) {
				Class.forName("com.mysql.jdbc.Driver");
			 c=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "Current-Root-Password");

			if("role".equals("admin")) {
				System.out.println("CONNECTION SUCCESSFUL");

								
		 st= c.createStatement();
			
			r = st.executeQuery("SELECT * FROM employee.employee");
			 while (r.next()) {
		         JSONArray jsonr= new JSONArray();
			     JSONObject jo= new JSONObject();
			     jo.put("empl_id", r.getLong("empl_id"));
					jo.put("id", r.getLong("id"));
					jo.put("empl_name", r.getString("empl_name"));
					jo.put("phone_number", r.getLong("phone_number"));
					jo.put("place", r.getString("place"));
					jo.put("role", r.getString("role"));
			     jsonr.add(jo);
			     out.println(jsonr);
		
			}}else {
				 ps= c.prepareStatement("SELECT * FROM organisation.employeedetails  where empl_name=?");   
		         ps.setString(3,empl_name);
		         ResultSet rs = ps.executeQuery();
		         while (rs.next()) {
		         JSONArray jsonr= new JSONArray();
			     JSONObject jo= new JSONObject();
			     jo.put("empl_id", r.getLong("empl_id"));
					jo.put("id", r.getLong("id"));
					jo.put("empl_name", r.getString("empl_name"));
					jo.put("phone_number", r.getLong("phone_number"));
					jo.put("place", r.getString("place"));
					jo.put("role", r.getString("role"));
			        jsonr.add(jo);
			     out.println(jsonr);
		         }
			}		
		 }
		 }
		 catch(Exception e) {
			
			e.printStackTrace();
		}}}
