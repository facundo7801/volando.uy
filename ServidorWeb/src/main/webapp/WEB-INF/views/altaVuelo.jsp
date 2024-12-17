<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/template/head.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Alta de Vuelo</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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

.error_message {
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

		<div class="container form-container">
			<h1>Alta de Vuelo</h1>
			<form action="altaVueloServlet" method="post"
				enctype="multipart/form-data" onsubmit="return validateForm();">
				
				<%
				String errorMessage = (String)request.getAttribute("error");
				if (errorMessage == null) {
				%>
					<div id="error_message">
					</div>
				<%
				}else {
				%>
					<div id="error_message" class="error_message">
                        <%=errorMessage %>
					</div>
				<%
				}
				%>
				
				<div class="form-group">
					<label for="nombre">Nombre del Vuelo (único):</label> <input
						type="text" class="form-control" id="nombre" name="nombre"
						required>
				</div>

				<div class="form-group">
					<label for="ruta">Ruta de Vuelo:</label> <select
						class="form-control" id="ruta" name="ruta" disabled>
						<option value="${ruta.nombre}" selected>${ruta.nombre}</option>
					</select>
				</div>

				<div class="form-group">
					<label for="fecha">Fecha de Vuelo:</label> <input type="date"
						class="form-control" id="fecha" name="fecha" required>
				</div>

				<div class="form-group">
					<label for="duracion">Duración (HH:MM):</label> <input type="time"
						class="form-control" id="duracion" name="duracion" required>
				</div>

				<div class="form-group">
					<label for="cantMaxAsTurista">Cantidad Máxima de Asientos
						Turista:</label> <input type="number" class="form-control"
						id="cantMaxAsTurista" name="cantMaxAsTurista" min="0" required>
				</div>

				<div class="form-group">
					<label for="cantMaxAsEjecutivo">Cantidad Máxima de Asientos
						Ejecutivo:</label> <input type="number" class="form-control"
						id="cantMaxAsEjecutivo" name="cantMaxAsEjecutivo" min="0" required>
				</div>



				<div class="form-group">
					<label for="imagen">Imagen (opcional):</label> <input type="file"
						class="form-control-file" id="imagen" name="imagen">
				</div>

				<input type="submit" class="btn btn-primary" value="Registrar Vuelo">
			</form>
		</div>
	</div>

	<script>
		function validateForm() {
			debugger;
			//const fechaInput = document.getElementById("fecha");
			const errorMessage = document.getElementById("error_message");
			//const currentDate = new Date();
			//const selectedDate = new Date(fechaInput.value);
			const nombre = document.getElementById("nombre").value;
			
			if(!validarNombre(nombre)) {
				errorMessage.textContent = 'El nombre del vuelo contiene caracteres no permitidos';
				errorMessage.className = 'error_message';
				return false;
			}

			return true;
		}
		
		//Evita que se envien caracteres invalidos en el nombre del vuelo
		const caracteresInvalidosVuelo = /[{}[\]|\\^`]/;
		
		function validarNombre(nombre){
			 if (caracteresInvalidosVuelo.test(nombre)) {
			        return false;
			    }
			    return true;
		}
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
