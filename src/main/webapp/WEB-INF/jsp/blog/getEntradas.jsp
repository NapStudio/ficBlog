<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<h3><s:message code="blogs.list.title"/> #${id_blog}</h3>

<table border="1">
	<tr>
		<th><s:message code="entrada.title"/></th>
		<th><s:message code="entrada.date"/></th>
		<th><s:message code="entrada.me_gusta"/></th>
	</tr>

	<c:forEach var="entrada" items="${entradaList}" varStatus="status">



		<c:if test="${entrada['class'] eq 'class asi.ficblog.model.articulo.Articulo'}">
		<tr>
			<td>${entrada.titulo_entrada}</td>
			<td>${entrada.fecha_publicacion_entrada}</td>
			<td>${entrada.me_gusta_entrada}</td>
		</tr>
		</c:if>		
		<c:if test="${entrada['class'] eq 'class asi.ficblog.model.enlace.Enlace'}">
		<tr>
	    	<td>${entrada.titulo_entrada}</td>
			<td>${entrada.fecha_publicacion_entrada}</td>
			<td>${entrada.me_gusta_entrada}</td>
		</tr>
		</c:if>		

	</c:forEach>

</table>

<c:url value="details" var="detailsURL">
	<c:param name="id_blog" value="${id_blog}" />
</c:url>

<div style="margin: 10px 0;">
	<a href="${detailsURL}"><s:message code="blogs.details.back"/></a>
</div>








