<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="uy.edu.fing.volandouy.webservices.PaqueteDto"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Compra Paquete de Ruta de Vuelo</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
	font-family: 'Arial', sans-serif;
}

.container {
	margin-top: 50px;
	max-width: 600px;
}

.form-container {
	background: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	margin-bottom: 30px;
	color: #343a40;
}

label {
	font-weight: bold;
}

.boton-registrar {
	width: 100%;
	margin-top: 20px;
}

.error-message {
	color: red;
	text-align: center;
	margin-top: 10px;
}

#error_message {
	color: #D8000C;
	background-color: #FFD2D2;
	border: 1px solid #D8000C;
	padding: 10px 20px;
	border-radius: 5px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	text-align: center;
	width: 100%;
	margin-top: 10px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>
		<div class="container">
		<div class="form-container">
			<h2>Comprar Paquete</h2>
			
			<%
				List<PaqueteDto> paquetes = (List<PaqueteDto>) request.getAttribute("paquetes");
				String compradosTodos = (String)request.getAttribute("compradosTodos");
				if (paquetes.size() > 0){
			%>
					<form action="CompraPaqueteServlet" method="post">
					
						<c:if test="${not empty errorMessage}">
							<div id="error_message">${errorMessage}</div>
						</c:if>
						
						<div class="form-group">
							<label for="paqueteSeleccionado">Paquetes</label>
							<select
								class="form-control" name="paqueteSeleccionado"
								id="paqueteSeleccionado" required>
								<%
								for (PaqueteDto paquete : paquetes) {
								%>
								<option value="<%=paquete.getNombre()%>">
									<%=paquete.getNombre()%> - $<%=paquete.getCostoAsociado()%>
								</option>
								<%
								}
								%>
							</select>
						</div>
			
						<button type="submit" class="btn btn-primary btn-block">Comprar
							paquete</button>
					</form>
			<%
				}else if(compradosTodos != null) {
			%>
					<p>Todos los paquetes que estaban disponibles fueron comprados</p>
			<%
				} else{
			%>
					<p>No hay paquetes disponibles para comprar en el sistema</p>
			<%
				}
			%>
			
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
