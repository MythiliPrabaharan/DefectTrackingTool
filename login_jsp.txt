<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log-In</title>
</head>
<body>
<%@ page import = "java.sql.*"%>
<%@ page import = "javax.sql.*"%>
<%	try
	{
		//boolean status = false;
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/defect","root","mythili");
		String sql="select * from signup where user_name = ? and password = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, request.getParameter("user_name"));
		ps.setString(2, request.getParameter("password"));
		ResultSet rs = ps.executeQuery();

		if(rs.next())
		{
		out.println("<center> Logged-In Succesfully </center>");
		String redirectURL = "http://localhost:8080/Sample/create_project.html";
        response.sendRedirect(redirectURL);
		}
		
		else
		{
			out.println("<br> <br>");
			out.println("<center><font color = red size = 50px face = helvetica><i>Invalid Credentials</i></font> </center>");
			out.println("<div class=row align=center>");
			out.println("<div class=col s12>");
			out.println("<br> <br> <br>");
			out.println("<label style=font-size:30px><a href=signup.html><font color=blue>SignUp?</font></a></label> &nbsp &nbsp &nbsp &nbsp &nbsp");
			out.println("<label style=font-size:30px><a href=login.html><font color=blue>LogIn?</font></a></label>");
			out.println("</div>");
			out.println("</div>");
    	}	
		ps.close();
		connection.close();
}
	catch(Exception e)
	{	
		out.println("Incorrect Data");
	}
%>

</body>
</html>