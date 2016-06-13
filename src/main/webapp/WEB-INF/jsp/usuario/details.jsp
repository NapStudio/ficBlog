<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<h3><s:message code="usuarios.usuario"/> #${usuario.login_usuario}</h3>
		
<div><i><s:message code="usuarios.login"/>:</i> ${usuario.login_usuario}</div>
<div><i><s:message code="usuarios.nombre"/>:</i> ${usuario.nombre_usuario}</div>
<div><i><s:message code="usuarios.apellidos"/>:</i> ${usuario.apellidos_usuario}</div>
<div><i><s:message code="usuarios.nick"/>:</i> ${usuario.nick_usuario}</div>

<div style="margin: 10px 0;">
	<a href="<c:url value="list"/>"><s:message code="usuarios.details.back"/></a>
</div>