<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Update blog with login: #${blog.id_blog}</h3>

<form:form action="updateBlog" modelAttribute="blog">
	<!--<form:hidden path="id_blog"/>-->
	
	<div>
		<div class="tag">Titulo</div>
		<div class="field"><form:input path="titulo_blog"/></div>
	</div>
			
	<div>
		<div class="tag">Usuario</div>
		<div class="field"><form:input path="usuario_blog"/></div>
	</div>	
		
	<div><input type="submit" value="Update"/></div>
</form:form>