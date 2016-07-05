<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<h3 style="text-align:center;"><s:message code="blogs.blog"/> #${id_blog}</h3>

<table border="1">

	<sec:authorize access="isAuthenticated()">	
		<c:set var="username">
			<sec:authentication property="principal.username" /> 
		</c:set>	
	</sec:authorize>

	
	<c:if test="${usuario_blog eq username}">
		<div>
			<div><s:message code="entrada.new"/>   
				<c:url value="addArticuloForm" var="articuloURL">
					<c:param name="id_blog" value="${id_blog}" />
				</c:url>		
				<a href="${articuloURL}">  <s:message code="entrada.articulo.new"/> </a> 
				<s:message code="entrada.or"/>
				<c:url value="addEnlaceForm" var="enlaceURL">
					<c:param name="id_blog" value="${id_blog}" />
				</c:url>		
				<a href="${enlaceURL}"><s:message code="entrada.enlace.new"/></a> 
			</div>			
			<div style="text-align:right;" >
				<c:url value="delete" var="deleteURL">
					<c:param name="id_blog" value="${id_blog}" />
				</c:url>		
				<a href="${deleteURL}"><s:message code="blogs.delete"/></a>
			</div>	
		</div>	
	</c:if>	
	
<hr>
	
	<c:forEach var="entrada" items="${entradaList}" varStatus="status">



		<c:if test="${entrada['class'] eq 'class asi.ficblog.model.articulo.Articulo'}">
		<div>
			<div>
				<h2>${entrada.titulo_entrada}</h2>
				<h7>${entrada.fecha_publicacion_entrada}</h7>			
				<p>${entrada.texto_articulo}</p> &nbsp;
			</div>
			<div style="text-align:right;" >
				<c:url value="articulo/delete" var="deleteArticuloURL">
					<c:param name="id_articulo" value="${entrada.id_articulo}" />
				</c:url>		
				<a href="${deleteArticuloURL}"><s:message code="entrada.delete"/></a> &nbsp;
			</div>	
		</div>	
		</c:if>		
		<c:if test="${entrada['class'] eq 'class asi.ficblog.model.enlace.Enlace'}">		
			<div>
		    	<h2>${entrada.titulo_entrada}</h2>
		    	<div>
					<h7>${entrada.fecha_publicacion_entrada}</h7>
					<h7>${entrada.tipo_contenido_enlace}</h7>
				</div>
				<p><a href=${entrada.url_enlace}>${entrada.url_enlace}</a></p>
				<div style="text-align:right;" >
					<c:url value="enlace/delete" var="deleteEnlaceURL">
						<c:param name="id_enlace" value="${entrada.id_enlace}" />
					</c:url>		
					<a href="${deleteEnlaceURL}"><s:message code="entrada.delete"/></a> &nbsp;
				</div>	
			</div>
		</c:if>		

	</c:forEach>

</table>

<div style="margin: 10px 0;">
	<a href="list"><s:message code="blogs.details.back"/></a>
</div>








