<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<form name='login'
	action="<c:url value='/login' />" method='POST'>
	
	<div>
		<div class="tag">
			<s:message code="usuarios.login.login" />
		</div>
		<div class="field">
			<form:input path="login_usuario" name="login_usuario"/>
			<form:errors path="login_usuario" class="formerrors" />
		</div>
	</div>

	<div>
		<div class="tag">
			<s:message code="usuarios.login.password" />
		</div>
		<div class="field">
			<form:password path="contrasinal_usuario" name="contrasinal_usuario" />
			<form:errors path="contrasinal_usuario" class="formerrors" />
		</div>
	</div>
	
	<div>
		<input type="submit" value=<s:message code="usuarios.login.button"/>>
	</div>

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

</form>

<c:if test="${not empty error}">
	<div class="formerrors"><s:message code="${error}"/></div>
</c:if>

<c:if test="${not empty msg}">
	<div class="msg"><s:message code="${msg}"/></div>
</c:if>