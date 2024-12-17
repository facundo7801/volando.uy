<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="uy.edu.fing.volandouy.webservices.ReservaDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.PasajeDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.RutaDeVueloDto"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.GregorianCalendar"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<meta charset="UTF-8">
<title>Detalles CheckIn</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
	
	
<style>
body {
	font-family: 'Roboto', sans-serif;
	background-color: #f8f9fa;
	color: #343a40;
}

.container {
	margin-top: 30px;
	max-width: 900px;
}

h1 {
	text-align: center;
	margin-bottom: 30px;
	color: #007bff;
}

.reserva-details {
	background: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	margin-bottom: 30px;
}

.details-section {
	margin-bottom: 20px;
	border-bottom: 1px solid #e3e3e3;
	padding-bottom: 15px;
}

.details-section h3 {
	color: #007bff;
	margin-bottom: 15px;
}

.details-grid {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
	gap: 20px;
}

.details-item {
	display: flex;
	flex-direction: column;
	gap: 5px;
}

.details-item label {
	font-weight: bold;
	color: #555;
}

.passenger-list {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

.passenger-list li {
	margin-bottom: 5px;
	color: #555;
}

.btn-volver {
	display: block;
	width: 100%;
	max-width: 200px;
	margin: 30px auto;
	padding: 10px;
	font-weight: bold;
	text-align: center;
	background-color: #007bff;
	color: #ffffff;
	text-decoration: none;
	border-radius: 5px;
	transition: background-color 0.3s ease;
}

.btn-volver:hover {
	background-color: #0056b3;
}

.passenger-table {
	border-collapse: collapse;
    border: none;
}

.passenger-table th, .passenger-table td {
	border: 1px solid #dee2e6;
	padding: 8px;
}

.passenger-table thead {
	border-bottom: none;
}

.passenger-table tbody tr:first-child td {
	border-top: none;
}



</style>	
	
</head>
<body>
		<div>
			<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
			</div>
		<div class="container">
			<h1>Detalles del CheckIn</h1>
			<%
			ReservaDto reservaConsultada = (ReservaDto) request.getAttribute("reservaCheckIn");
			RutaDeVueloDto rutaConsultada = (RutaDeVueloDto) request.getAttribute("rutaCheckIn");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			%>
			<div class="reserva-details">
				<div class="details-section">
					<h3>Información CheckIn</h3>
					<div class="details-grid">
						<div class="details-item">
							<label>Fecha en que se realizó el CheckIn:</label> <strong><%=dateFormat.format(reservaConsultada.getFechaCheckIn().toGregorianCalendar().getTime())%></strong>
						</div>
						
						<div class="details-item">
							<label>Hora embarque:</label> <strong><%=reservaConsultada.getEmbarqueHoraString()%></strong>
						</div>
					</div>
				</div>

				<div class="details-section">
					<h3>Pasajeros</h3>

					<table class="table passenger-table">
					    <thead>
					        <tr>
					            <th>Número de Pasajero</th>
					            <th>Nombre</th>
					            <th>Apellido</th>
					            <th>Número de Asiento</th>
					        </tr>
					    </thead>
					    <tbody>
					        <%
					        List<PasajeDto> pasajeros = reservaConsultada.getPasajeros();
					        if (pasajeros != null && !pasajeros.isEmpty()) {
					            int pasajeroNum = 1; // Contador de pasajeros
					            for (PasajeDto pasajero : pasajeros) {
					        %>
					        <tr>
					            <td><%= pasajeroNum++ %></td> <!-- Número del pasajero -->
					            <td><%= pasajero.getNombre() %></td> <!-- Nombre del pasajero -->
					            <td><%= pasajero.getApellido() %></td> <!-- Apellido del pasajero -->
					            <td><%= pasajero.getNumeroAsiento() %></td>
					        </tr>
					        <%
					            }
					        } else {
					        %>
					        <tr>
					            <td colspan="3">No hay pasajeros disponibles</td> <!-- Mensaje cuando no hay pasajeros -->
					        </tr>
					        <%
					        }
					        %>
					    </tbody>
					</table>
					

				</div>
				
				<a href="TarjetaDeEmbarqueServlet?nickNameClienteCheckIn=<%=reservaConsultada.getCliente()%>&vueloNombreCheckIn=<%=reservaConsultada.getVuelo()%>" target="_blank" class="btn btn-success">Descargar Tarjeta de Embarque en PDF</a>

			</div>
			

		</div>
			
		</div>
		
		
		<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
		
</body>
</html>