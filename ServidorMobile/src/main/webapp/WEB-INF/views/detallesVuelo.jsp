<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="uy.edu.fing.volandouy.webservices.VueloDto"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="./media/detallesVueloStyles.css" />
<meta charset="UTF-8">
<title>Consulta Vuelo</title>
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>

		<div class="container">
			<%
			String urlCompleta = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/ServidorWeb";
			VueloDto datavuelo = (VueloDto) request.getSession().getAttribute("datosVuelo");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
			%>
			<h1>Detalles del Vuelo</h1>
			<div class="card">
				<img src="/ServidorMobile/Imagenes?nombreImagen=<%= datavuelo.getImagen() != null ? datavuelo.getImagen() : "noimg.jpg" %>" alt="Imagen de vuelo">
				<ul class="details-list">
					<li><p>Nombre:</p> <strong><%=datavuelo.getNombre()%></strong></li>
					<li><p>Fecha del vuelo:</p> <strong><%=dateFormat.format(datavuelo.getFecha().toGregorianCalendar().getTime())%></strong></li>
					<li><p>Hora del vuelo:</p> <strong><%=formatoHora.format(datavuelo.getFecha().toGregorianCalendar().getTime())%></strong></li>
					<li><p>Duración:</p> <strong><%=datavuelo.getDuracionString()%></strong></li>
					<li><p>Asientos Turista:</p> <strong><%=datavuelo.getCantMaxAsTurista()%></strong></li>
					<li><p>Asientos Ejecutivo:</p> <strong><%=datavuelo.getCantMaxAsEjecutivo()%></strong></li>
					<li><p>Fecha de Alta:</p> <strong><%=dateFormat.format(datavuelo.getFechaAlta().toGregorianCalendar().getTime())%></strong></li>
					<li><p>Aerolínea:</p> <strong><%=datavuelo.getAerolinea()%></strong></li>
					<li><p>Ruta:</p> <strong><%=datavuelo.getRuta()%></strong></li>

				</ul>
					<c:if test="${tipo == 'cliente'}">
						<c:if test="${vueloReservado == 'si'}">
							<div class="opcionesClass">
								<a href="ListarReservasServlet"
									class="btn btn-primary btn-success">Consultar mi Reserva</a>
							</div>
						</c:if>
					</c:if>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>