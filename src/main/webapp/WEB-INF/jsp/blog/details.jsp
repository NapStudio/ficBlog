<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<h3><s:message code="blogs.blog"/> #${blog.id_blog}</h3>
		
<div><i><s:message code="blogs.id"/>:</i> ${blog.id_blog}</div>
<div><i><s:message code="blogs.title"/>:</i> ${blog.titulo_blog}</div>
<div><i><s:message code="blogs.usuario"/>:</i> ${blog.usuario_blog}</div>
<div><i><s:message code="blogs.date"/>:</i> ${blog.fecha_creacion_blog}</div>

<c:url value="getEntradas" var="getEntradas">
	<c:param name="id_blog" value="${blog.id_blog}" />
</c:url>


<div style="margin: 10px 0;">
	<a href="${getEntradas}"><s:message code="blogs.details.entradas"/></a>
</div>

<div style="margin: 10px 0;">
	<a href="<c:url value="list"/>"><s:message code="blogs.details.back"/></a>
</div>