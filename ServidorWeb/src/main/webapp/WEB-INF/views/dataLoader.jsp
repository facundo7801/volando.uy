<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>main - VolandoUY</title>
</head>
<body>
	<h1>Bienvenido a VolandoUY</h1>

	<div class="container">
		<!-- Botón para ir al Login -->
		<form action="loginServlet" method="get">
			<button type="submit">Ir al Login</button>
		</form>

		<!-- Botón para cargar datos (llama al servlet de carga de datos) -->
		<form action="DataLoaderServlet" method="post">
			<button type="submit">Cargar Datos</button>
		</form>
	</div>

	<!-- Mensaje opcional -->
	<div class="mensaje">
		<p>${mensaje}</p>
	</div>
</body>
</html>