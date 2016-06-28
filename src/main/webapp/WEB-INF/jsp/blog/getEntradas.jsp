<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<h3><s:message code="blogs.list.title"/> #${blog.id_blog}</h3>

<table border="1">
	<tr>
		<th><s:message code="entrada.title"/></th>
		<th><s:message code="entrada.date"/></th>
	</tr>

	<c:forEach var="entrada" items="${entradaList}" varStatus="status">



		<c:if test="${entrada['class'] eq 'class asi.ficblog.model.articulo.Articulo'}">
		<tr>
			<td>${entrada.titulo_entrada}</td>
			<td>${entrada.fecha_publicacion_entrada}</td>
		</tr>
		
		<td>${entrada.texto_articulo}</td>
		
		</c:if>		
		<c:if test="${entrada['class'] eq 'class asi.ficblog.model.enlace.Enlace'}">
		
		<tr>
	    	<td>${entrada.titulo_entrada}</td>
			<td>${entrada.fecha_publicacion_entrada}</td>
		</tr>
		
		<tr>
			<td><a href=${entrada.url_enlace}>${entrada.url_enlace}</a></td>
			<td>${entrada.tipo_contenido_enlace}</td>
		</tr>
		</c:if>		

	</c:forEach>

</table>

<c:url value="details" var="detailsURL">
	<c:param name="id_blog" value="${id_blog}" />
</c:url>

<div style="margin: 10px 0;">
	<a href="regularList"><s:message code="blogs.details.back"/></a>
</div>








