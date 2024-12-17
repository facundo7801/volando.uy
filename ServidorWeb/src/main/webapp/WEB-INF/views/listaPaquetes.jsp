<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="uy.edu.fing.volandouy.webservices.UsuarioDto"%>
<%@page import="uy.edu.fing.volandouy.webservices.PaqueteDto"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="./media/listarRutas.css" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Paquetes de Rutas de Vuelo</title>

</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>
		<div class="container">
			<h1>Lista de Paquetes de Rutas de Vuelo</h1>
			<div class="row">
					<%
					List<PaqueteDto> paquetes = (List<PaqueteDto>) request.getAttribute("paquetes");
					if (paquetes != null && !paquetes.isEmpty()) {
						for (PaqueteDto paquete : paquetes) {
					%>
							<div class="col-md-6 col-lg-4 mb-4">
								<div class="card shadow-sm h-100">
									<img src="/ServidorWeb/Imagenes?nombreImagen=<%= paquete.getImagen() != null ? paquete.getImagen() : "noimg.jpg" %>" class="card-img-top imagen-paquete" alt="Imagen de paquete">
			
									<div class="card-body d-flex flex-column">					
										<h5 class="card-title"><%=paquete.getNombre()%> </h5>
										<p class="card-text"><%=paquete.getDescripcion()%></p>
										<% 
										String clienteConsulta = (String)request.getAttribute("clienteConsulta");
										if (clienteConsulta == null) { 
										%>
											<a href="consultaPaqueteServlet?nombre=<%=paquete.getNombre()%>" class="btn btn-info mt-auto ver-detalles">Ver detalles</a>
										<%
										}else{
										%>
											<a href="consultaPaqueteServlet?nombre=<%=paquete.getNombre()%>&clienteConsulta=clienteConsulta" class="btn btn-info mt-auto ver-detalles">Ver detalles</a>
										<%
										}
										%>
									</div>	
								</div>
							</div>
					<%
						}
					} else {
						String clienteConsulta = (String)request.getAttribute("clienteConsulta");
						if (clienteConsulta != null) {
					%>
							<div class="col-12">
								<div class="alert alert-warning text-center">
									No posee paquetes comprados
								</div>
							</div>
						<%
						}else {
						%>
							<div class="col-12">
								<div class="alert alert-warning text-center">
									No hay paquetes disponibles
								</div>
							</div>
						<%
						}
						%>
					<%
					}
					%>					
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
