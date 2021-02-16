<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2/13/2021
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
        <hr />

        User: <security:authentication property="principal.username" />
        <br />
        Role(s): <security:authentication property="principal.authorities" />
        <hr />

        <p>
            <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
            (Only for Manager peeps)
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
            (Only for Admin peeps)
        </p>

        <form:form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Logout" />
        </form:form>
    </body>
</html>
