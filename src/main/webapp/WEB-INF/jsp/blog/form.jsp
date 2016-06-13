<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Add a new employee</title>
	</head>
	<body>
		<h3>Add a new employee</h3>
		
		<form:form action="addBlog" modelAttribute="blog">
			<div><form:input path="Título"/></div>
			<div><form:input path="usuario"/></div>
			<div><input type="submit" value="Add"/></div>
		</form:form>		
		
	</body>
</html>
