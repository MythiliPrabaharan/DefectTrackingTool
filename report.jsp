<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>
<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
<!-- Compiled and minified JavaScript -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="styles/styles.css">
<title>Report</title>
<script>
var tableToExcel = (function()
 		{
 			var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
        , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
        , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
      return function(table, name) {
        if (!table.nodeType) table = document.getElementById(table)
        var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
        window.location.href = uri + base64(format(template, ctx))
      }
    })()
</script>
</head>
<body>
<nav>
	<div class="nav-wrapper blue">
		<a href="#" class="brand-logo center">PeopleZ Defect Tracking Tool</a>
	</div>
</nav>
<nav>
         <div class="nav-wrapper">
            <div class="col s12">
               <a href="signup.html" class="breadcrumb">Sign Up</a>
               <a href="login.html" class="breadcrumb">Log In</a>
               <a href="create_project.html" class="breadcrumb">Create Project</a>
               <a href="create_issue.html" class="breadcrumb">Create Issues</a>
               <a href="dashboard.html" class="breadcrumb">Dash Board</a>
               <a href="" class="breadcrumb">Report</a>
               &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
               <a href="http://localhost:8080/Sample/home.html" class="btn-floating btn-large tooltipped waves-teal waves-light center" data-position="bottom" data-delay="50" data-tooltip="Log out" onclick='window.alert("Logged Out Successfully");'><i class="material-icons right">account_circle</i></a>
            </div>
         </div>
      </nav>
<%@ page import = "java.sql.*"%>
<%@ page import = "javax.sql.*"%>
<%	
try
{
	Connection connection = null;
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/defect","root","mythili");
	String sql="select * from create_issue";
	Statement st = connection.createStatement();
	ResultSet rs = st.executeQuery(sql);
%>
<div class="w3-container">
	<div class="row"> <!-- Form Card -->
    	<div class="col s8 offset-s2">
        	<div class="card light-blue">
           		<div class="card-content Black-text">
              		<div class="row">
    					<div class="col s12">
      						<div class="card">
            					<div class="card-content">
            						<div class="card blue lighten-1">    
            							<div class="card-content White-text"><span class="card-title White-text center"><font face="cursive" size="5">Report</font></span></div>
	        						</div>
	        		<div class="row">
      					<div class="input-field col s12">
                             <div id="striped" class="section scrollspy">
                             	<table class="centered striped bordered" id="report_table">
                             		<thead>
          								<tr>
              								<th>Defect Name</th>
              								<th>Description</th>
              								<!--<th>Status</th>-->
              								<th>Assigned To</th>
              								<th>Verified By</th>
              								<th> Duplicate Status</th>
          								</tr>
        							</thead>
        							<%while(rs.next()){%>
        							<tbody>
          								<tr>
            								<td><%= rs.getString("defect_name") %></td>
            								<td><%= rs.getString("description") %></td>
            								<!--<td><%= rs.getString("status") %></td>-->
            								<td><%= rs.getString("assigned_to") %></td>
            								<td><%= rs.getString("verified_by") %></td>
            								<td><%= rs.getString("duplicate") %></td>
          								</tr>
        							</tbody>
        							<%}%>
        						</table>
        					</div>
        				</div>
        				<div class="row" align="center">
                    		<div class="col s12">
                    			<br/>
                    				<button class="btn-large waves-effect waves-light" id="print" name="action" onClick="window.print()">Print the page<i class="material-icons left">print</i></button> &nbsp &nbsp &nbsp
									<button class="btn-large waves-effect waves-light" id="excel" name="action" onclick = "tableToExcel('report_table', 'W3C Example Table')">Export to Excel<i class="material-icons left">view_module</i></button>
                    		</div>
                    	</div>
					</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%rs.close();
	connection.close();
}
catch(Exception e)
{	
	out.println("Incorrect Data");
}
%>
</body>
</html>