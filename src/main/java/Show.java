import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/show")
public class Show extends HttpServlet
{
String id,s;
Connection con;
Statement st;
ResultSet rs;
PrintWriter out;
public void init()
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/app","root","nsnatanesh@2005S");
st=con.createStatement();
}
catch(Exception e)
{
}
}
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
res.setContentType("text/html");
out=res.getWriter();
id=req.getParameter("id");
try
{
int a=0;
rs=st.executeQuery("select * from app where id="+id+";");
while(rs.next())
{
a=1;
s=rs.getString(1);
while(s.length()<4)
{
s="0"+s;
}
s="<tr><th>Id:</th><td>"+s+"</td></tr>";
s+="<tr><th>Name:</th><td>"+rs.getString(2)+"</td></tr>";
s+="<tr><th>Age:</th><td>"+rs.getString(3)+"</td></tr>";
s+="<tr><th>DOB:</th><td>"+rs.getString(4)+"</td></tr>";
s+="<tr><th>Blood Group:</th><td>"+rs.getString(5)+"</td></tr>";
s+="<tr><th>Address:</th><td>"+rs.getString(6)+"</td></tr>";
s+="<tr><th>Phone No:</th><td>"+rs.getString(7)+"</td></tr>";
s+="<tr><th>Email Id:</th><td>"+rs.getString(8)+"</td></tr>";
s+="<tr><th>Gender:</th><td>"+rs.getString(9)+"</td></tr>";
s+="<tr><th>Course:</th><td>"+rs.getString(10)+"</td></tr>";
}
if(a==1)
{
out.println(
"<html>"+
"<head>"+
"<title>Application Form</title>"+
"<style>"+
"*{"+
"background-color:black;"+
"color:white;"+
"}"
+ "th{"
+ "background-color:#ffc300;"
+ "border-radius:20px;"
+ "}"
+ "</style>"+
"</head>"+
"<body>"+
"<h1 align=center style=\"color:#ffc300\">Details</h1>"+
"<table cellpadding=15px align=center style=text-align:left>"+
s+
"</table>"+
"</body>"+
"</html>"
);
}
else
{
out.println(
"<html>"+
"<head>"+
"<title>Show</title>"+
"<style>"+
"*{"+
"background-color:black;"+
"color:#ffc300;"+
"}"
+ "body"
+ "{"
+ "display:flex;"
+ "align-items:center;"
+ "justify-content:center;"
+ "}"
+ "</style>"+
"</head>"+
"<body>"+
"<h1 align=center>Invalid Id</h1>"+
"</body>"+
"</html>"
);
}
}
catch(Exception e)
{
out.println(""+e);
}
}
public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
doGet(req,res);
}
}