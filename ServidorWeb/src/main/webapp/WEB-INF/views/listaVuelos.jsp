<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="uy.edu.fing.volandouy.webservices.VueloDto"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="./media/listaVueloStyles.css" />
<meta charset="UTF-8">
<title>Listar Vuelos</title>
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>

		<div class="container">
			<h1 class="aerolinea-ruta">Lista de Vuelos</h1>

			<%
			List<VueloDto> vuelosList = (List<VueloDto>) request.getAttribute("vuelosRuta");
			if (vuelosList.size() > 0) {
			%>
			<div class="vuelos">
				<%
				for (VueloDto vueloRuta : vuelosList) {
				%>

					<div class="vuelo">
						<img src="/ServidorWeb/Imagenes?nombreImagen=<%= vueloRuta.getImagen() != null ? vueloRuta.getImagen() : "noimg.jpg" %>" alt="Imagen de vuelo">
						<div class="informacion">
							<h3><%=vueloRuta.getNombre() %></h3>
							<a
								href="detallesVueloServlet?vuelo=<%=vueloRuta.getNombre() %>"
								class="btn-detalles">Ver Detalles</a>
						</div>
					</div>
				<%
				}
				%>
			</div>
			<%
			} else {
			%>
			<div class="col-12">
				<div class="alert alert-warning text-center">No se encontraron Vuelos</div>
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