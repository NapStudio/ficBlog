<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h3>
	<s:message code="usuarios.add.usuario" />
</h3>

<form:form action="addUsuario" modelAttribute="usuario">
	<div>
		<div class="tag">
			<s:message code="usuarios.login" />
		</div>
		<div class="field">
			<form:input path="login_usuario" />
			<form:errors path="login_usuario" class="formerrors" />
		</div>
	</div>

	<div>
		<div class="tag">
			<s:message code="usuarios.nombre" />
		</div>
		<div class="field">
			<form:input path="nombre_usuario" />
			<form:errors path="nombre_usuario" class="formerrors" />
		</div>
	</div>
	
	<div>
		<div class="tag">
			<s:message code="usuarios.apellidos" />
		</div>
		<div class="field">
			<form:input path="apellidos_usuario" />
			<form:errors path="apellidos_usuario" class="formerrors" />
		</div>
	</div>
	
	<div>
		<div class="tag">
			<s:message code="usuarios.nick" />
		</div>
		<div class="field">
			<form:input path="nick_usuario" />
			<form:errors path="nick_usuario" class="formerrors" />
		</div>
	</div>
		
	<div>
		<div class="tag">
			<s:message code="usuarios.contrasena" />
		</div>
		<div class="field">
			<form:input path="contraseña_usuario" />
			<form:errors path="contraseña_usuario" class="formerrors" />
		</div>
	</div>

	<div>
		<input type="submit" value=<s:message code="usuarios.add.add"/>>
	</div>
</form:form>
