<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="uy.edu.fing.volandouy.webservices.ReservaDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.PasajeDto"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<meta charset="UTF-8">
<title>Detalles de la Reserva</title>
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
	/*margin: 30px auto;*/
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

.contenedorToolTip {
	display: flex;
    justify-content: center;
    align-items: center;
}

.tooltipCheckInDiv {
	margin: 0;
	padding: 0;
    position: relative;
    display: inline-block;
}

.tooltipCheckIn {
    visibility: hidden;
    background-color: #333;
    color: #fff;
    text-align: center;
    border-radius: 5px;
    padding: 5px;
    position: absolute;
    bottom: 100%; /* Aparece arriba del enlace */
    left: 50%;
    transform: translateX(-50%);
    z-index: 1;
    opacity: 0;
    transition: opacity 0.3s;
    font-size: 0.9rem;
    white-space: nowrap;
    max-width: 200px;
}

.tooltipCheckIn::after {
    content: '';
    position: absolute;
    top: 100%; /* Flecha apunta al enlace */
    left: 50%;
    transform: translateX(-50%);
    border-width: 5px;
    border-style: solid;
    border-color: #333 transparent transparent transparent;
}

.tooltipCheckInDiv:hover .tooltipCheckIn {
    visibility: visible;
    opacity: 1;
}

/* Media queries for responsive design */
@media (max-width: 1024px) { /* Tablets */
	.container {
		margin: 20px;
	}
	
	.reserva-details {
		padding: 20px;
	}
	
	.details-section h3 {
		font-size: 1.1rem;
	}
	
	.details-grid {
		grid-template-columns: 1fr; /* Una sola columna en tablets */
	}
	
	.btn-volver {
		max-width: 100%;
		font-size: 1rem;
		padding: 12px;
	}
}

@media (max-width: 480px) { /* Celulares */
	.container {
		margin: 10px;
		padding: 0 10px;
	}

	.reserva-details {
		padding: 15px;
	}

	h1 {
		font-size: 1.5rem;
		margin-bottom: 20px;
	}

	.details-section h3 {
		font-size: 1rem;
		margin-bottom: 10px;
	}
	
	.details-grid {
		grid-template-columns: 1fr; /* Una sola columna en móviles */
		gap: 10px;
	}

	.details-item label {
		font-size: 0.9rem;
	}

	.passenger-list li {
		font-size: 0.9rem;
	}

	.btn-volver {
		max-width: 100%;
		font-size: 0.9rem;
		padding: 10px;
	}
	
	.passenger-table th, .passenger-table td {
		font-size: 0.85rem;
		padding: 6px;
	}
}

</style>
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>

		<div class="container">
			<h1>Detalles de la Reserva</h1>
			<%
			ReservaDto reservaConsultada = (ReservaDto) request.getAttribute("reservaConsultada");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (reservaConsultada != null) {
			%>
			<div class="reserva-details">
				<div class="details-section">
					<h3>Información General</h3>
					<div class="details-grid">
						<div class="details-item">
							<label>Fecha Reservada:</label> <strong><%=dateFormat.format(reservaConsultada.getFechaReserva().toGregorianCalendar().getTime())%></strong>
						</div>
						<div class="details-item">
							<label>Tipo de Asiento:</label> <strong><%=reservaConsultada.getTipoAsiento()%></strong>
						</div>
						<div class="details-item">
							<label>Cantidad de Equipaje Extra:</label> <strong><%=reservaConsultada.getUniEquipajeExtra()%></strong>
						</div>
						<div class="details-item">
							<label>Costo Total:</label> <strong><%=reservaConsultada.getCosto()%></strong>
						</div>
					</div>
				</div>

				<div class="details-section">
					<h3>Pasajeros</h3>
					
					
					
					<table class="table passenger-table">
					    <thead>
					        <tr>
					            <th>Número</th>
					            <th>Nombre</th>
					            <th>Apellido</th>
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

				<div class="details-section">
					<h3>Información Extra</h3>
					<div class="details-grid">
						<div class="details-item">
							<label>Cliente:</label> <strong><%=reservaConsultada.getCliente()%></strong>
						</div>
						<div class="details-item">
							<label>Aerolínea:</label> <strong><%=reservaConsultada.getAerolinea()%></strong>
						</div>
						<div class="details-item">
							<label>Ruta de Vuelo:</label> <strong><%=reservaConsultada.getRuta()%></strong>
						</div>
						<div class="details-item">
							<label>Vuelo:</label> <strong><%=reservaConsultada.getVuelo()%></strong>
						</div>
						<div class="details-item">
							<label>Paquete Utilizado:</label> <strong> <%
 if (reservaConsultada.getPaquete() == null) {
 %> No se utilizó paquete <%
 } else {
 %> <%=reservaConsultada.getPaquete()%> <%
 }
 %>
							</strong>
						</div>

					</div>
				</div>
				
				
						<%
						if (reservaConsultada.getEmbarqueUrl() != null) {
						%>
							<div>
								<div class="tooltipCheckInDiv">
									<span class="tooltipCheckIn">El CheckIn ya fue realizado</span>
									<a href="#"
										class="btn btn-primary btn-volver disabled" 
										tabindex="-1" 
										aria-disabled="true" 
										style="background-color: #cccccc; color: #666666; cursor: not-allowed;">
										Realizar CheckIn
									</a>
								</div>
							</div>
						<%
						}else {
						%>
							<a href="detallesReservaServlet?clienteReserva=<%=reservaConsultada.getCliente() %>&realizarCheckIn=Si"
								class="btn btn-primary btn-volver">Realizar CheckIn</a>
						<%
						}
						%>
				
			</div>
			<%
			} else {
			%>
			<p>No se encontraron detalles de la Reserva.</p>
			<%
			}
			%>

			<!--  <a href="javascript:history.back()" class="btn-volver">Volver</a> -->
			
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
