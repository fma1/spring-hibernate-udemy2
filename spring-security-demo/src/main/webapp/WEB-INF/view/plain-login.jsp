<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2/14/2021
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>My Custom Login Page</title>
    </head>
    <body>
        <h3>My Custom Login Page</h3>
        <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
            <p>
                <label>
                    User name: <input type="text" name="username" />
                </label>
            </p>
            <p>
                <label>
                    Password: <input type="password" name="password" />
                </label>
            </p>

            <input type="submit" value="Login" />
        </form:form>
    </body>
</html>
