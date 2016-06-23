<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h3>
	<s:message code="blogs.add.blog" />
</h3>

<form:form action="addBlog" modelAttribute="blog">
	<div>
		<div class="tag">
			<s:message code="blogs.title" />
		</div>
		<div class="field">
			<form:input path="titulo_blog" />
			<form:errors path="titulo_blog" class="formerrors" />
		</div>
	</div>

	<div>
		<div class="tag">
			<s:message code="blogs.user" />
		</div>
		<div class="field">
			<form:input path="usuario_blog" />
			<form:errors path="usuario_blog" class="formerrors" />
		</div>
	</div>
	


	<div>
		<input type="submit" value=<s:message code="blogs.add.add"/>>
	</div>
</form:form>
