<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Issue</title>
</head>
<body>
<%@ page import = "java.sql.*"%>
<%@ page import = "javax.sql.*"%>
<%
	Connection connection = null;
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/defect","root","mythili");
	String sql="insert into create_issue values(?,?,?,?,?,?,?,?,?,?,?)";
	PreparedStatement ps = connection.prepareStatement(sql);
	ps.setString(1, request.getParameter("project_name"));
	ps.setString(2, request.getParameter("defect_type"));
	ps.setString(3, request.getParameter("defect_name"));
	ps.setString(4, request.getParameter("description"));
	ps.setString(5, request.getParameter("date"));
	ps.setString(6, request.getParameter("priority"));
	ps.setString(7, request.getParameter("severity"));
	ps.setString(8, request.getParameter("status"));
	ps.setString(9, request.getParameter("assigned_to"));
	ps.setString(10, request.getParameter("verified_by"));
	ps.setString(11, request.getParameter("duplicate"));
	ps.executeUpdate();	
	out.println("Inserted Succesfully");
	String redirectURL = "http://localhost:8080/Sample/dashboard.html";
    response.sendRedirect(redirectURL);
	ps.close();
	connection.close();
%>
</body>
</html>