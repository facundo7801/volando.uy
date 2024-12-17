<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="uy.edu.fing.volandouy.webservices.PaqueteDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.ComercializaDto"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="./media/homeStyles.css" />
<meta charset="UTF-8">
<title>Detalles del Paquete</title>
<style>
body {
	font-family: 'Roboto', sans-serif;
	background-color: #f8f9fa;
	color: #343a40;
}

.container {
	margin-top: 30px;
}

h1 {
	text-align: center;
	margin-bottom: 20px;
	color: #007bff;
}

.card {
	background: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	margin-bottom: 20px;
	overflow: hidden;
}

.card img {
	max-width: 100%;
	height: auto;
	border-radius: 8px;
	margin-bottom: 15px;
	object-fit: cover;
}

/*.card ul {
	list-style-type: none;
	padding: 0;
}*/

/*.card li {
	margin-bottom: 10px;
}*/

.btn-volver {
	display: block;
	margin: 20px auto 0;
}

.details-list {
	list-style-type: none;
	padding: 0;
}

.details-list li {
	display: flex;
	justify-content: space-between;
	padding: 10px 0;
	border-bottom: 1px solid #eaeaea;
}

.details-list li p {
	margin: 0;
	font-weight: 500;
}

.details-list li strong {
	font-weight: 700;
	color: #007bff;
}

td, th {
	text-align: center
}

</style>
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>

		<div class="container">
			<h1>Detalles del Paquete</h1>
			<%
			PaqueteDto paquete = (PaqueteDto) request.getAttribute("paquete");//paquetes
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (paquete != null) {
				List<ComercializaDto> rutas = (List<ComercializaDto>) paquete.getRutas();
			%>
			<div class="card">      
                <img src="/ServidorWeb/Imagenes?nombreImagen=<%= paquete.getImagen() != null ? paquete.getImagen() : "noimg.jpg" %>" alt="Imagen de paquete">

				<ul class="details-list">
					<li><p>Nombre:</p> <strong><%=paquete.getNombre()%></strong></li>
					<li><p>Descripcion:</p> <strong><%=paquete.getDescripcion()%></strong></li>
					<li><p>Descuento:</p> <strong><%=paquete.getDescuento()%>%</strong></li>
					<li><p>Costo asociado:</p> <strong>$<%=paquete.getCostoAsociado()%></strong></li>
					<li><p>Período de validez:</p> <strong><%=paquete.getPeriodoValidez()%> dias</strong></li>
					<li><p>Fecha de alta:</p> <strong><%=dateFormat.format(paquete.getFechaAlta().toGregorianCalendar().getTime())%></strong></li>
				</ul>	
				
					<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Ruta</th>
							<%
								String clienteConsulta = (String)request.getAttribute("clienteConsulta");
								if (clienteConsulta == null){
							%>
								<th>Cantidad de pasajes disponibles</th>
							<%
								}else {
							%>
								<th>Pasajes del paquete</th>
							<%
								}
							%>
							<th>Tipo de asiento</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (rutas != null) {
							for (ComercializaDto ruta : rutas) {
						
						%>
						<tr> 
							<td> <%=ruta.getRuta() %> </td>
							<td> <%=ruta.getCantRutasDeVuelo()%></td>
							<td> <%= ruta.getTipoDeAsiento() %></td>
							<td><a href="consultaRutaServlet?nombre=<%=ruta.getRuta()%>&consultaPaquete=consultaPaquete"
										class="btn btn-primary">Ver Detalles</a>
								
						</tr>
							<%
							 }
						 }
						 %>
					
					</tbody>
					</table>
									
			</div>
			<%
			} else {
			%>
				<p>No se encontraron detalles del paquete</p>
			<%
			}
			%>
		</div>
	</div>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
			crossorigin="anonymous"></script>
</body>
</html>
