<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Update usuario with login: #${usuario.login_usuario}</h3>

<form:form action="updateUsuario" modelAttribute="usuario">
	<form:hidden path="login_usuario"/>
	
	<div>
		<div class="tag">Nombre</div>
		<div class="field"><form:input path="nombre_usuario"/></div>
	</div>
			
	<div>
		<div class="tag">Apellidos</div>
		<div class="field"><form:input path="apellidos_usuario"/></div>
	</div>		
		
	<div>
		<div class="tag">Nick</div>
		<div class="field"><form:input path="nick_usuario"/></div>
	</div>
			
	<div>
		<div class="tag">Login</div>
		<div class="field"><form:input path="login_usuario"/></div>
	</div>
	
	<div>
		<div class="tag">Contraseña</div>
		<div class="field"><form:input path="contraseña_usuario"/></div>
	</div>
		
	<div><input type="submit" value="Update"/></div>
</form:form>