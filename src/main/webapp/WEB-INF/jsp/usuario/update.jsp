<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Update usuario with login: #${usuario.login_usuario}</h3>

<form:form action="updateUsuario" modelAttribute="usuario">
	<!--<form:hidden path="login_usuario"/>-->
	
	<div>
		<div class="tag">Nombre</div>
		<div class="field"><form:input path="nombre_usuario"/>
			<form:errors path="nombre_usuario" class="formerrors"/> </div>
	</div>
			
	<div>
		<div class="tag">Apellidos</div>
		<div class="field"><form:input path="apellidos_usuario"/>
			<form:errors path="apellidos_usuario" class="formerrors"/>
		</div>
	</div>		
		
	<div>
		<div class="tag">Nick</div>
		<div class="field"><form:input path="nick_usuario"/>
			<form:errors path="nick_usuario" class="formerrors"/></div>
	</div>
			
	<div>
		<div class="tag">Login</div>
		<div class="field"><form:input path="login_usuario"/>
			<form:errors path="login_usuario" class="formerrors"/></div>
	</div>
	
	<div>
		<div class="tag">contrasinal</div>
		<div class="field"><form:input path="contrasinal_usuario"/>
			<form:errors path="contrasinal_usuario" class="formerrors"/></div>
	</div>
		
	<div><input type="submit" value="Update"/></div>
</form:form>