<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h3>
	<s:message code="blogs.add.blog" />
</h3>

<form:form action="addBlog" modelAttribute="blog">

	<sec:authorize access="isAuthenticated()">	
	
					<c:set var="username">
							<sec:authentication property="principal.username" /> 
					</c:set>	
					<form:input type="hidden" path="usuario_blog" value="${username}"/>
					
					
	</sec:authorize>


	<sec:authorize access="isAuthenticated()">	
					<div>
						<div class="tag">
							<s:message code="blogs.title" />
						</div>
						<div class="field">
							<form:input path="titulo_blog" />
							<form:errors path="titulo_blog" class="formerrors" />
						</div>
					</div>							

	</sec:authorize>
	


	<div>
		<input type="submit" value=<s:message code="blogs.add.add"/>>
	</div>
</form:form>
