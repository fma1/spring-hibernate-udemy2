<%--suppress HtmlUnknownTarget --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Student Registration Form</title>
</head>
<body>
    <%--@elvariable id="student" type="com.luv2code.springdemo.Student"--%>
    <form:form method="post" action="processForm" modelAttribute="student">
        First name: <form:input path="firstName" />
        <br /><br />
        Last name: <form:input path="lastName" />
        <br /><br />
        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>