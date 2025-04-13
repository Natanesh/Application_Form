import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/app")
public class app extends HttpServlet
{
int x=0;
String name,age,dob,bg,address,phno,email,gender,course="",button,s="",sl="";
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
name=req.getParameter("name");
age=req.getParameter("age");
dob=req.getParameter("dob");
bg=req.getParameter("bg");
address=req.getParameter("address");
phno=req.getParameter("phno");
email=req.getParameter("email");
gender=req.getParameter("gender");
for(int i=1;i<=3;i++)
{
String c1=req.getParameter("c"+(""+i));
if(i==1 && !(course.equals("")))
{
course="";
}
if(c1!=null)
{
if(i!=3)
{
course+=c1+",";
}
else
{
course+=c1;
}
}
}
if(course.length()>0)
{
if(course.charAt(course.length()-1)==',')
{
course=course.substring(0,course.length()-1);
}
}
button=req.getParameter("button");
res.setContentType("text/html");
out=res.getWriter();
if(button.equals("Add"))
{
String id="000";
try
{
rs=st.executeQuery("select id from app;");
while(rs.next())
{
x=Integer.parseInt(rs.getString(1));
}
x++;
id+=x;
while(id.length()>4)
{
id=id.substring(1,id.length());
}
st.executeUpdate("insert into app values("+id+",'"+name+"',"+age+",'"+dob+"','"+bg+"','"+address+"',"+phno+",'"+email+"','"+gender+"','"+course+"');");
course="";
out.println(
"<html>"+
"<head>"+
"<title>Application</title>"
+ "<style>"+
"*{"+
"background-color:black;"+
"color:#ffc300;"+
"}"
+ "</style>"+
"</head>"+
"<body>"+
"<h1 align=center>Data Added Successfully!Thank You...</h1>"+ 
"<h1 style=\"color:white\">Your Id is :"+id+"</h1>"+
"</body>"+
"</html>"
);
}
catch(Exception e)
{
out.println("<html>"+
		"<head>"+
		"<title>Application</title>"
		+ "<style>"+
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
		"<h1 align=center>Please fill the form</h1>"+ 
		"</body>"+
		"</html>");
}
}
else if(button.equals("Show"))
{
out.println("<html>"+
"<head>"+
"<title>Application</title>"
+ "<style>"+
"*{"+
"background-color:black;"+
"color:white;"+
"}"
+ "input[type=number]{"
+ " background-color: white;\r\n"
+ "    color: black;\r\n"
+ "    font-weight: bold;\r\n"
+ "    height: 30px;\r\n"
+ "    width: 250px;\r\n"
+ "   border: 0;\r\n"
+ "    border-radius:5px;"
+ "margin-left:5px;"
+ "}"
+ "input[type=\"submit\"]"
+ "{\r\n"
+ "    background-color: #ffc300;\r\n"
+ "    border: 0;\r\n"
+ "    width: 80px;\r\n"
+ "    height: 35px;\r\n"
+ "    color: black;\r\n"
+ "    font-size: 15px;\r\n"
+ "    font-weight: bold;\r\n"
+ "    cursor: pointer;\r\n"
+ "    border-radius:20px;\r\n"
+ "margin-left:5px;"
+ "}\r\n"
+ ""
+ "body"
+ "{"
+ "display:flex;"
+ "align-items:center;"
+ "justify-content:center;"
+ "}"
+ "</style>"+
"</head>"+
"<body>"+
"<form action=/Application/show method=post>"+
"<table align=center>"+
"<tr>"
+ "<th colspan=3><h1>Enter user id</h1>"
+ "</tr>"+
"<tr>"+
"<th>Id:</th>"+
"<td><input type=number name=id placeholder="+"Enter_Id"+" required />"+
"<input type=submit name=Ok value=Ok />"+
"</td>"+
"</tr>"+
"</table>"+
"</form>"+
"</body>"+
"</html>");
}
else if(button.equals("Show All"))
{
try
{
int j=0;
rs=st.executeQuery("select * from app;");
while(rs.next())
{
s+=rs.getString(1);
while(s.length()<4)
{
s="0"+s;
}
s="<td>"+s+"</td>";
s+="<td>"+rs.getString(2)+"</td>";
s+="<td>"+rs.getString(3)+"</td>";
s+="<td>"+rs.getString(4)+"</td>";
s+="<td>"+rs.getString(5)+"</td>";
s+="<td>"+rs.getString(6)+"</td>";
s+="<td>"+rs.getString(7)+"</td>";
s+="<td>"+rs.getString(8)+"</td>";
s+="<td>"+rs.getString(9)+"</td>";
s+="<td>"+rs.getString(10)+"</td>";
sl+="<tr>"+s+"</tr>";
s="";
j++;
}
if(j==0)
{
sl="<tr border=0px><td colspan=10 border=0px align=center>No Record Found</td></tr>";
}
out.println(
"<html>"+
"<head>"+
"<title>Details</title>"+
"<style>"+
"*{"+
"background-color:black;"+
"color:white;"+
"}"
+ "th{"
+ "color:#ffc300;"
+ "text-align:center;"
+ "}"
+ "table{"
+ "border:1px solid white;"
+ "border-radius:20px;"
+ "}"
+ "tr"
+ "{"
+ "border:1px solid white;"
+ "</style>"
+ "</head>"
+"<body>"+
"<h1 align=center style=\"color:#ffc300;\">Details</h1>"+
"<table border=1px cellpadding=15px align=center style=text-align:left>"+
"<tr>"+
"<th>Id</th>"+
"<th>Name</th>"+
"<th>Age</th>"+
"<th>DOB</th>"+
"<th>Blood Group</th>"+
"<th>Address</th>"+
"<th>Phone No</th>"+
"<th>Email Id</th>"+
"<th>Gender</th>"+
"<th>Course</th>"+
"</tr>"+
sl+
"</table>"+
"</body>"+
"</html>");
sl="";
}
catch(Exception e)
{
}
}
else if(button.equals("Update"))
{
try
{
rs=st.executeQuery("select id from app where phno="+phno+";");
int z=0;
String us,id="";
while(rs.next())
{
id=rs.getString(1);
z++;
}
if(z==0)
{
us="Invalid Phone Number";
}
else
{
st.executeUpdate("update app set id="+id+",name='"+name+"',age="+age+",dob='"+dob+"',bg='"+bg+"',address='"+address+"',phno="+phno+",email='"+email+"',gender='"+gender+"',course='"+course+"' where id="+id+";");
us="Updated Successfully!...";
}
course="";
out.println(
"<html>"+
"<head>"+
"<title>Application Form</title>"
+ "<style>"+
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
"<h1 align=center>"+us+"</h1>"+
"</body>"+
"</html>"
);
}
catch(Exception e)
{
out.println("<html>"+
		"<head>"+
		"<title>Application Form</title>"
		+ "<style>"+
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
		"<h1 align=center>Please fill the form</h1>"+ 
		"</body>"+
		"</html>");
}
}
else if(button.equals("Delete"))
{
String ds,id="";
phno=req.getParameter("phno");
try
{
rs=st.executeQuery("select id from app where phno="+phno+";");
int z=0;
while(rs.next())
{
id=rs.getString(1);
z=1;
}
if(z==0)
{
ds="Invalid Phone Number";
}
else
{
st.executeUpdate("delete from app where id="+id+";");
ds="Deleted Successfully!...";
}
out.println(
"<html>"+
"<head>"+
"<title>Delete</title>"+
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
"<h1 align=center>"+ds+"</h1>"+
"</body>"+
"</html>"
);
}
catch(Exception e)
{
out.println("<html>"+
		"<head>"+
		"<title>Application Form</title>"
		+ "<style>"+
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
		"<h1 align=center>Please Enter Phone Number</h1>"+ 
		"</body>"+
		"</html>");
}
}
}
public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
doGet(req,res);
}
}