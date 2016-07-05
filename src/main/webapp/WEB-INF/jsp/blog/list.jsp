<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<p style="text-align:right;">
	<a href="<c:url value="addBlogForm"/>">
		<s:message code="blogs.list.add"/>
	</a>
</p>

<h1 style="text-align:center;"><s:message code="blogs.list.title"/></h1>

	<c:forEach var="blog" items="${blogList}" varStatus="status">	
		
			<c:url value="getEntradas" var="blogEntriesURL">
				<c:param name="id_blog" value="${blog.id_blog}" />
				<c:param name="usuario_blog" value="${blog.usuario_blog}" />
			</c:url>
			<h2>
				<a href="${blogEntriesURL}">${blog.titulo_blog}</a>
			</h2>
			
			<c:url value="/usuario/details" var="userDetailsURL">
				<c:param name="login_usuario" value="${blog.usuario_blog}" />
			</c:url>

			<p style="font-size:80%;">
				<a href="${userDetailsURL}">written by ${blog.usuario_blog}</a>
			</p>		
	</c:forEach>

</table>
<div style="margin: 10px 0;">
	<a href="<c:url value="/"/>"><s:message code="usuarios.details.back"/></a>
</div>
















<!-- <%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<h3><s:message code="blogs.list.title"/></h3>

<p>
	<a href="<c:url value="addBlogForm"/>">
		<s:message code="blogs.list.add"/>
	</a>
</p>

<table border="1">
	<tr>
		<th><s:message code="blogs.title"/></th>
		<th><s:message code="blogs.usuario"/></th>
		<th><s:message code="blogs.date"/></th>
		<th><s:message code="blogs.actions"/></th>
	</tr>

	<c:forEach var="blog" items="${blogList}" varStatus="status">

		<tr>
			<td>${blog.titulo_blog}</td>
			<td>${blog.usuario_blog}</td>
			<td>${blog.fecha_creacion_blog}</td>


			<c:url value="details" var="detailsURL">
				<c:param name="id_blog" value="${blog.id_blog}" />
			</c:url>

			<td>
				<a href="${detailsURL}"><s:message code="blogs.details"/></a>
			</td>
		</tr>

	</c:forEach>

</table>
<div style="margin: 10px 0;">
	<a href="<c:url value="/"/>"><s:message code="usuarios.details.back"/></a>
</div>-->
