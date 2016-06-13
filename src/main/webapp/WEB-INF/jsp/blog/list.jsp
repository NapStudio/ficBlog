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
