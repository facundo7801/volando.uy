<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="./media/loginStyles.css" />
<title>Iniciar Sesión</title>
</head>
<body>
	<section class="bg-image">
		<!-- Tarjeta del formulario -->
		<div class="card">
			<div class="form-container">
				<h2>Iniciar Sesión</h2>
				<form class="form-personalizado" action="loginServlet" method="post"
					onsubmit="return validarFormulario();">
					<c:if test="${not empty errorMessage}">
						<div id="error_message">${errorMessage}</div>
					</c:if>

					<!-- Nickname/Email input -->
					<div class="form-outline">
						<label for="userName">Nickname/Email</label> <input type="text"
							id="userName" name="userName" required />
					</div>

					<!-- Password input -->
					<div class="form-outline">
						<label for="userPassword">Contraseña</label> <input
							type="password" id="userPassword" name="userPassword" required />
					</div>


					<div class="botones">
						<button type="submit" class="btn-ingresar">Ingresar</button>
					</div>




					<div class="opciones">
						<a href="homeServlet">Ingresar como visitante</a> | <a
							href="RegistrarUsuarioServlet">Registrar Usuario</a>
					</div>
				</form>
			</div>
		</div>
	</section>
	<%@ include file="../template/footer.jsp"%>
	<script>
		// Verificar el estado del botón en localStorage al cargar la página
		document.addEventListener("DOMContentLoaded", function() {
			if (localStorage.getItem("cargarDatosDeshabilitado") === "true") {
				var botonCargarDatos = document
						.getElementById("btn-cargar-datos");
				botonCargarDatos.disabled = true;
				botonCargarDatos.innerText = "Carga completa";
			}
		});

		// Función para manejar el clic en "Cargar Datos"
		function cargarDatos() {
			var botonCargarDatos = document.getElementById("btn-cargar-datos");
			botonCargarDatos.disabled = true;
			botonCargarDatos.innerText = "Cargando...";

			// Almacenar el estado del botón en localStorage
			localStorage.setItem("cargarDatosDeshabilitado", "true");

			// Redirigir al servlet para cargar los datos
			window.location.href = 'DataLoaderServlet';
		}
	</script>


</body>
</html>
