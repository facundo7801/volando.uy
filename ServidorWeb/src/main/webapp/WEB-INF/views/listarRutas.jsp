<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="uy.edu.fing.volandouy.webservices.RutaDeVueloDto"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Rutas de Vuelo</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="./media/listarRutas.css" />
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>
		<div class="container">
			<h1>Lista de Rutas de Vuelo</h1>
			<div class="row">
				<%
				List<RutaDeVueloDto> rutas = (List<RutaDeVueloDto>) request.getSession().getAttribute("rutas");
				if (rutas != null && !rutas.isEmpty()) {
					for (RutaDeVueloDto ruta : rutas) {
				%>
				<div class="col-md-6 col-lg-4 mb-4">
					<div class="card shadow-sm h-100">
						<img src="/ServidorWeb/Imagenes?nombreImagen=<%= ruta.getImagen() != null ? ruta.getImagen() : "noimg.jpg" %>" class="card-img-top imagen-ruta" alt="Imagen de ruta">
						<div class="card-body d-flex flex-column">
							<h5 class="card-title"><%=ruta.getNombre()%></h5>
							<p class="card-text"><%=ruta.getResumen()%></p>
							<a href="consultaRutaServlet?nombre=<%=ruta.getNombre()%>"
								class="btn btn-info mt-auto ver-detalles">Ver Detalles</a>
						</div>
					</div>
				</div>
				<%
				}
				} else {
				%>
				<div class="col-12">
					<div class="alert alert-warning text-center">No hay rutas
						disponibles</div>
				</div>
				<%
				}
				%>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>