<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="uy.edu.fing.volandouy.webservices.RutaDeVueloDto"%>
<%@page import="uy.edu.fing.volandouy.webservices.AerolineaDto"%>
<%@ page import="java.util.List"%>
	
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="./media/homeStyles.css" />
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>
		<!-- los class vuelos y vuelo tendrian que llamarse rutas y ruta y habria que cambiar el nombre de la var por las dudas -->
		<div class="vuelos">

			<%
			List<RutaDeVueloDto> rutasHome = (List<RutaDeVueloDto>)request.getSession().getAttribute("rutasHome");
			for (RutaDeVueloDto ruta : rutasHome) {
			%>

				<div class="vuelo">
					<img src="/ServidorWeb/Imagenes?nombreImagen=<%= ruta.getImagen() != null ? ruta.getImagen() : "noimg.jpg" %>" alt="Imagen de ruta">
					
					<div class="informacion">
						<h3><%=ruta.getNombre() %></h3>
						<p><%=ruta.getResumen() %></p>
						<a href="consultaRutaServlet?rutaHome=<%=ruta.getNombre() %>&aerolineaHome=<%=ruta.getAerolinea().getNickName() %>&consultaHome=si">Leer más</a>
					</div>
				</div>

			<%
			}
			%>

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>