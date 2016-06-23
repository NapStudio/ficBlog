<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<h3><s:message code="usuarios.list.title"/></h3>

<p>
	<a href="<c:url value="addUsuarioForm"/>">
		<s:message code="usuarios.list.add"/>
	</a>
</p>

<table border="1">
	<tr>
		<th><s:message code="usuarios.nombre"/></th>
		<th><s:message code="usuarios.apellidos"/></th>
		<th><s:message code="usuarios.login"/></th>
		<th><s:message code="usuarios.actions"/></th>
	</tr>

	<c:forEach var="usuario" items="${usuarioList}" varStatus="status">

		<tr>
			<td>${usuario.nombre_usuario}</td>
			<td>${usuario.apellidos_usuario}</td>
			<td>${usuario.login_usuario}</td>

			<c:url value="updateUsuarioForm" var="updateURL">
				<c:param name="login_usuario" value="${usuario.login_usuario}" />
			</c:url>

			<c:url value="delete" var="deleteURL">
				<c:param name="login_usuario" value="${usuario.login_usuario}" />
			</c:url>

			<c:url value="details" var="detailsURL">
				<c:param name="login_usuario" value="${usuario.login_usuario}" />
			</c:url>

			<td><a href="${updateURL}"><s:message code="usuarios.update"/></a> &nbsp; 
				<a href="${deleteURL}"><s:message code="usuarios.delete"/></a> &nbsp;
				<a href="${detailsURL}"><s:message code="usuarios.details"/></a>
			</td>
		</tr>

	</c:forEach>

</table>
<div style="margin: 10px 0;">
	<a href="<c:url value="/"/>"><s:message code="usuarios.details.back"/></a>
</div>