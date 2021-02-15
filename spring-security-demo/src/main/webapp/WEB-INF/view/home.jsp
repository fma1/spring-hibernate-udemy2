<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2/13/2021
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>luv2code Company Home Page</title>
    </head>
    <body>
        <h2>luv2code Company Home Page</h2>
        <hr />

        Welcome to the luv2code Company Home Page

        <form:form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Logout" />
        </form:form>
    </body>
</html>
