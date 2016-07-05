<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3 style="text-align:center;"><s:message code="blogs.blog"/> #${id_blog}</h3>

<table border="1">

	<sec:authorize access="isAuthenticated()">	
		<c:set var="username">
			<sec:authentication property="principal.username" /> 
		</c:set>	
	</sec:authorize>
	<c:set var="blogOwner">
		${usuario_blog}
	</c:set>	
	
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
				<h7><fmt:formatDate pattern="yyyy-MM-dd HH:mm"  value="${entrada.fecha_publicacion_entrada.time}" /></h7>
					<div style="text-align:right;" >
						<h7 >${entrada.me_gusta_entrada} <s:message code="entrada.like"/></h7>	
						<c:url value="articulo/meGusta" var="meGustaArticuloURL">
							<c:param name="id_articulo" value="${entrada.id_articulo}" />
							<c:param name="login_usuario" value="${username}" />
						</c:url>		
						<a href="${meGustaArticuloURL}"><s:message code="entrada.give.like"/></a> &nbsp;
					</div>
				<p>${entrada.texto_articulo}</p> &nbsp;
			</div>
			<c:if test="${blogOwner eq username}">
				<div style="text-align:right;" >
					<c:url value="articulo/delete" var="deleteArticuloURL">
						<c:param name="id_articulo" value="${entrada.id_articulo}" />
					</c:url>		
					<a href="${deleteArticuloURL}"><s:message code="entrada.delete"/></a> &nbsp;
				</div>
			</c:if>		
		</div>	
		</c:if>		
		<c:if test="${entrada['class'] eq 'class asi.ficblog.model.enlace.Enlace'}">		
			<div>
		    	<h2>${entrada.titulo_entrada}</h2>
		    	<div>
					<h7><fmt:formatDate pattern="yyyy-MM-dd HH:mm"  value="${entrada.fecha_publicacion_entrada.time}" /></h7>
					<h7>|   ${entrada.tipo_contenido_enlace}</h7>
					<div style="text-align:right;" >
						<h7 >${entrada.me_gusta_entrada}  <s:message code="entrada.like"/></h7>	
						<c:url value="enlace/meGusta" var="meGustaEnlaceURL">
							<c:param name="id_enlace" value="${entrada.id_enlace}" />
							<c:param name="login_usuario" value="${username}" />
						</c:url>		
						<a href="${meGustaEnlaceURL}"><s:message code="entrada.give.like"/></a> &nbsp;
					</div>
				</div>
				<p><a href=${entrada.url_enlace}>${entrada.url_enlace}</a></p>				
				<c:if test="${blogOwner eq username}">
					<div style="text-align:right;" >
						<c:url value="enlace/delete" var="deleteEnlaceURL">
							<c:param name="id_enlace" value="${entrada.id_enlace}" />
						</c:url>		
						<a href="${deleteEnlaceURL}"><s:message code="entrada.delete"/></a> &nbsp;
					</div>	
				</c:if>		
			</div>
		</c:if>		

	</c:forEach>

</table>

<div style="margin: 10px 0;">
	<a href="list"><s:message code="blogs.details.back"/></a>
</div>








