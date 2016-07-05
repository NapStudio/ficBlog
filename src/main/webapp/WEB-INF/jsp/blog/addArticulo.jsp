<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h3>
	<s:message code="blogs.add.blog" />
</h3>

	<sec:authorize access="isAuthenticated()">	
		<c:set var="username">
			<sec:authentication property="principal.username" /> 
		</c:set>	
	</sec:authorize>

<form:form action="addArticulo" modelAttribute="articulo">

	<form:input type="hidden" path="blog_articulo" value="${articulo.blog_articulo}"/>
	<div>
		<div class="tag">
			<s:message code="entrada.title" />
		</div>
		<div class="field">
			<form:input path="titulo_entrada" />
			<form:errors path="titulo_entrada" class="formerrors" />
		</div>
	</div>
	<h2>${blog_articulo}</h2>
	<div>
		<div class="tag">
			<s:message code="entrada.description" />
		</div>
		<div class="field">
			<form:input style="height:200px;width:600px" path="texto_articulo" />
			<form:errors path="texto_articulo" class="formerrors" />
		</div>
	</div>

	<div>
		<input type="submit" value=<s:message code="usuarios.add.add"/>>
	</div>
</form:form>
