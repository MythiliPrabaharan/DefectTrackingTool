<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign-Up</title>
</head>
<body>
<%@ page import = "java.sql.*"%>
<%@ page import = "javax.sql.*"%>
<%Connection connection = null;
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/defect","root","mythili");
	String sql="insert into project (project_name) values(?)";
	PreparedStatement ps = connection.prepareStatement(sql);
	ps.setString(1, request.getParameter("project_name"));
	ps.executeUpdate();
	out.println("Inserted Succesfully");
	String redirectURL = "http://localhost:8080/Sample/create_issue.html";
    response.sendRedirect(redirectURL);
	ps.close();
	connection.close();
%>
</body>
</html>