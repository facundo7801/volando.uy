<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="/WEB-INF/template/head.jsp" />
<title>Alta de Ruta</title>
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

label {
	font-weight: bold;
}

.boton-registrar {
	width: 100%;
	margin-top: 20px;
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

.large-text {
	width: 100%; /* O puedes usar otro valor, como 80% o en píxeles */
	height: 100px; /* Aumenta la altura si es necesario */
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
				<h1>Alta de Ruta</h1>
				<form action="AltaRutaServlet" method="post"
					enctype="multipart/form-data" onsubmit="return validateForm();">
					
					<%
					String errorMessage = (String)request.getAttribute("errorMessage");
					if (errorMessage == null){
					%>
						<div id="error_message">
						</div>
					<%
					} else {
					%>
						<div id="error_message" class="error_message">
                        	<%=errorMessage %>
						</div>
					<%
					}
					%>
					
					<div class="form-group">
						<label for="nombre">Nombre (único):</label> <input type="text"
							class="form-control" id="nombre" name="nombre" required>
					</div>

					<div class="form-group">
						<label for="descripcioncorta">Descripción corta:</label> <input
							type="text" class="form-control" id="descripcioncorta"
							name="descripcioncorta" required>
					</div>

					<div class="form-group">
						<label for="descripcion">Descripción:</label> <input type="text"
							class="form-control large-text" id="descripcion"
							name="descripcion" required>
					</div>

					<div class="form-group">
						<label for="hora">Hora:</label> <input type="time"
							class="form-control" id="hora" name="hora" required>
					</div>

					<div class="form-group">
						<label for="costoTu">Costo Turista:</label> <input type="text"
							class="form-control" id="costoTu" name="costoTu" required>
					</div>


					<div class="form-group">
						<label for="costoEj">Costo Ejecutivo:</label> <input type="text"
							class="form-control" id="costoEj" name="costoEj" required>
					</div>

					<div class="form-group">
						<label for="costoEq">Costo Equipaje:</label> <input type="text"
							class="form-control" id="costoEq" name="costoEq" required>
					</div>

					<div class="form-group">
						<label for="ciudadO">Ciudad Origen:</label> <select
							class="form-control" id="ciudadO" name="ciudadO" required>
							<%
							List<String> ciudades = (List<String>) request.getSession().getAttribute("ciudades");
							if (ciudades != null && !ciudades.isEmpty()) {
								for (String ciudad : ciudades) {
							%>
							<option value="<%=ciudad%>"><%=ciudad%></option>
							<%
							}
							} else {
							%>
							<option value="">No hay ciudades disponibles</option>
							<%
							}
							%>
						</select>
					</div>



					<div class="form-group">
						<label for="ciudadD">Ciudad Destino:</label> <select
							class="form-control" id="ciudadD" name="ciudadD" required>
							<%
							// Reutilizamos el mismo atributo "ciudades" para Ciudad Destino
							if (ciudades != null && !ciudades.isEmpty()) {
								for (String ciudad : ciudades) {
							%>
							<option value="<%=ciudad%>"><%=ciudad%></option>
							<%
							}
							} else {
							%>
							<option value="">No hay ciudades disponibles</option>
							<%
							}
							%>
						</select>
					</div>

					<div class="form-group">
						<label for="categorias[]">Categorias:</label> <select
							class="form-control" id="categorias[]" name="categorias[]"
							multiple required>
							<%
							List<String> categorias = (List<String>) request.getSession().getAttribute("categorias");
							if (categorias != null && !categorias.isEmpty()) {
								for (String categoria : categorias) {
							%>
							<option value="<%=categoria%>"><%=categoria%></option>
							<%
							}
							} else {
							%>
							<option value="">No hay categorías disponibles</option>
							<%
							}
							%>
						</select>
					</div>

					<div class="form-group">
						<label for="imagen">Imagen (opcional):</label> <input type="file"
							class="form-control-file" id="imagen" name="imagen">
					</div>
					
					<div class="form-group">
						<label for="urlVideo">Video URL (opcional):</label> <input type="text"
							class="form-control" id="urlVideo" name="urlVideo">
					</div>

					<input type="submit" class="btn btn-primary boton-registrar"
						value="Registrar">
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
		
	</script>

	<script>
		function validateForm() {
			const nombre = document.getElementById("nombre").value;
			const descripcionCorta = document.getElementById("descripcioncorta").value;
			const ciudadO = document.getElementById("ciudadO").value;
			const ciudadD = document.getElementById("ciudadD").value;
			const errorMessage = document.getElementById("error_message");
			/*if (nombre === "" || descripcionCorta === "") {
				document.getElementById("error_message").innerText = "Por favor, completa todos los campos requeridos.";
				return false; // Prevenir el envío si hay errores
			}

			if (ciudadO === "" || ciudadD === "") {
				document.getElementById("error_message").innerText = "Por favor, completa todos los campos requeridos.";
				return false;
			}

			if (ciudadO === ciudadD) {
				document.getElementById("error_message").innerText = "El origen no puede ser el mismo que el destino";
				return false;
			}*/
			
			if (!validarNombre(nombre)) {
				errorMessage.textContent = 'El nombre de la ruta contiene caracteres no permitidos';
				errorMessage.className = 'error_message';
				return false;
			}
			
			return true; // Permitir el envío si todo está bien
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
</body>
</html>
