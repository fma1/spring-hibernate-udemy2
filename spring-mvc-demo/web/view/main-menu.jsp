<!DOCTYPE html>
<html lang="en">
<head>
    <title>Spring MVC Demo - Home Page</title>
</head>
<body>
<h2>Spring MVC Demo - Home Page</h2>
<img src="${pageContext.request.contextPath}/static/images/256px-Tux.svg.png"  alt="computer"/>
<hr>
<a href="${pageContext.request.contextPath}/hello/showForm">Hello World Form</a>
<br /><br />
<a href="${pageContext.request.contextPath}/student/showForm">Student Form</a>
</body>
</html>
