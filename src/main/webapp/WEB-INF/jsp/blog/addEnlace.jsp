<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h3>
	<s:message code="blogs.add.blog" />
</h3>
<form:form action="addEnlace" modelAttribute="enlace">

	<form:input type="hidden" path="blog_enlace" value="${enlace.blog_enlace}"/>
	<div>
		<div class="tag">
			<s:message code="entrada.title" />
		</div>
		<div class="field">
			<form:input path="titulo_entrada" />
			<form:errors path="titulo_entrada" class="formerrors" />
		</div>
	</div>
	<select name="tipo_contenido_enlace">
	    <option value="Página web">Página web</option>
	    <option value="Video">Video</option>
	    <option value="Imagen" selected>Imagen</option>
	    <option value="Audio">Audio</option>
  	</select>
	<h2>${blog_articulo}</h2>
	<div>
		<div class="tag">
			<s:message code="entrada.enlace.url" />
		</div>
		<div class="field">
			<form:input path="url_enlace" />
			<form:errors path="url_enlace" class="formerrors" />
		</div>
	</div>

	<div>
		<input type="submit" value=<s:message code="usuarios.add.add"/>>
	</div>
</form:form>
