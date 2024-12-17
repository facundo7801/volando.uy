<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="uy.edu.fing.volandouy.webservices.ReservaDto"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="./media/listaReservasStyles.css" />
<meta charset="UTF-8">
<title>Lista de Reservas</title>
</head>
<body>

	<div>

		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>
		<div class="container">
			<h1>Lista de Reservas</h1>
			<div class="reservas">
	
				<%
				List<ReservaDto> reservas = (List<ReservaDto>) request.getSession().getAttribute("reservasList");
				if (reservas.size() > 0) {
				%>
					<c:forEach var="res" items="${reservasList}">
		
						<div class="reserva">
							<!--<img src="uploads/#" alt="El vuelo no posee imagen"> -->
							<div class="informacion">
								<h3>Reserva de ${res.cliente} en ${res.vuelo}</h3>
								<c:if test="${clienteConsulta == null}">
									<a
										href="<c:url value='detallesReservaServlet?clienteReserva=${res.cliente}' />">Mostrar
										mas detalles</a>
								</c:if>
		
								<!-- Consulta directa del cliente  -->
								<c:if test="${clienteConsulta != null}">
									<a
										href="<c:url value='detallesReservaServlet?vueloReservaCliente=${res.vuelo}&clienteConsulta=consultaDirecta' />">Mostrar
										mas detalles</a>
								</c:if>
		
							</div>
						</div>
		
					</c:forEach>
				<%
				} else {
				%>
					<div class="col-12">
						<div class="alert alert-warning text-center">
							No posee reservas
						</div>
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
</html> 