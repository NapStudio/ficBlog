<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>



<h3><s:message code="usuarios.usuario"/> #${usuario.login_usuario}</h3>
<c:url value="updateUsuarioForm" var="updateURL">
	<c:param name="login_usuario" value="${usuario.login_usuario}" />
</c:url>


	<sec:authorize access="isAuthenticated()">	
		<c:set var="username">
			<sec:authentication property="principal.username" /> 
		</c:set>	
	</sec:authorize>

	<c:if test="${usuario.login_usuario eq username}">
		<h4 style="text-align:right;"><a href="${updateURL}"><s:message code="usuarios.update"/></a> &nbsp; </h4>	
	</c:if>		



<div style="text-align:center;">
	<div><i><s:message code="usuarios.login"/>:</i> ${usuario.login_usuario}</div>
	<div><i><s:message code="usuarios.nombre"/>:</i> ${usuario.nombre_usuario}</div>
	<div><i><s:message code="usuarios.apellidos"/>:</i> ${usuario.apellidos_usuario}</div>
	<div><i><s:message code="usuarios.nick"/>:</i> ${usuario.nick_usuario}</div>
</div>
<div style="text-align:right;" >
<c:url value="/blog/list" var="blogListURL">
	<c:param name="login_usuario" value="${usuario.login_usuario}" />
</c:url>
<a href="${blogListURL}"><s:message code="usuarios.blog"/></a>
</div>
<div style="margin: 10px 0;">
	<a href="<c:url value="/blog/list"/>"><s:message code="usuarios.details.back"/></a>
</div>