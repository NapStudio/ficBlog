<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<h3><s:message code="blogs.list.title"/></h3>


<table border="1">
	<tr>
		<th><s:message code="blogs.title"/></th>
		<th><s:message code="blogs.usuario"/></th>
	</tr>

	<c:forEach var="blog" items="${blogList}" varStatus="status">

		<tr>
	
		
			<c:url value="getEntradas" var="blogEntriesURL">
				<c:param name="id_blog" value="${blog.id_blog}" />
			</c:url>

			<td>
				<a href="${blogEntriesURL}">${blog.titulo_blog}</a>
			</td>
			
			


			<c:url value="/usuario/details" var="userDetailsURL">
				<c:param name="login_usuario" value="${blog.usuario_blog}" />
			</c:url>

			<td>
				<a href="${userDetailsURL}">${blog.usuario_blog}</a>
			</td>		
			
		</tr>
		
			
			
			

	</c:forEach>

</table>
<div style="margin: 10px 0;">
	<a href="<c:url value="/"/>"><s:message code="usuarios.details.back"/></a>
</div>
