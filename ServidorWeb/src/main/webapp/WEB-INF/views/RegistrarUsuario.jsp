<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registro de Usuario</title>
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

.btn-primary {
	width: 100%;
	margin-top: 20px;
}

/* Mensaje de error */
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
	<div class="container">
		<div class="form-container">
			<h1>Registro de Usuario</h1>
			<form action="RegistrarUsuarioServlet" method="post"
				enctype="multipart/form-data" onsubmit="return validateForm();">
				<%
				String errorMessage = (String)request.getAttribute("errorMessage");
				if (errorMessage == null){
				%>
					<div id="error_message">
					</div>
				<%
				}else {
				%>
					<div id="error_message" class="error_message">
						<c:if test="${not empty errorMessage}">
							${errorMessage}
						</c:if>
					</div>
				<%
				}
				%>
				<!-- Mensaje de error -->
				
				<div class="form-group">
					<label for="nickname">Nickname (único):</label> <input type="text"
						class="form-control" id="nickname" name="nickname" required>
					<span id="nicknameMessage"></span>
				</div>

				<div class="form-group">
					<label for="nombre">Nombre:</label> <input type="text"
						class="form-control" id="nombre" name="nombre" required>
				</div>

				<div class="form-group">
					<label for="email">Correo Electrónico (único):</label> <input
						type="email" class="form-control" id="email" name="email" required>
					<span id="emailMessage"></span>
				</div>

				<div class="form-group">
					<label for="password">Contraseña:</label> <input type="password"
						class="form-control" id="password" name="password" required>
				</div>

				<div class="form-group">
					<label for="confirmPassword">Confirmar Contraseña:</label> <input
						type="password" class="form-control" id="confirmPassword"
						name="confirmPassword" required>
				</div>

				<div class="form-group">
					<label for="imagen">Imagen (opcional):</label> <input type="file"
						class="form-control-file" id="imagen" name="imagen">
				</div>

				<h3>Tipo de Usuario:</h3>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="cliente"
						name="tipoUsuario" value="cliente" required> <label
						class="form-check-label" for="cliente">Cliente</label>
				</div>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="aerolinea"
						name="tipoUsuario" value="aerolinea"> <label
						class="form-check-label" for="aerolinea">Aerolínea</label>
				</div>

				<div id="clienteFields" style="display: none;">
					<h4>Datos del Cliente</h4>
					<div class="form-group">
						<label for="apellido">Apellido:</label> <input type="text"
							class="form-control" id="apellido" name="apellido">
					</div>

					<div class="form-group">
						<label for="fechaNacimiento">Fecha de Nacimiento:</label> <input
							type="date" class="form-control" id="fechaNacimiento"
							name="fechaNacimiento">
					</div>

					<div class="form-group">
						<label for="nacionalidad">Nacionalidad:</label> <input type="text"
							class="form-control" id="nacionalidad" name="nacionalidad">
					</div>

					<div class="form-group">
						<label for="tipoDocumento">Tipo de Documento:</label> <select
							class="form-control" id="tipoDocumento" name="tipoDocumento"
							required>
							<option value="CI">Cédula de Identidad (CI)</option>
							<option value="PASAPORTE">Pasaporte</option>
							<option value="OTRO">Otro</option>
						</select>
					</div>

					<div class="form-group">
						<label for="numeroDocumento">Número de Documento:</label> <input
							type="text" class="form-control" id="numeroDocumento"
							name="numeroDocumento">
					</div>
				</div>

				<div id="aerolineaFields" style="display: none;">
					<h4>Datos de la Aerolínea</h4>
					<div class="form-group">
						<label for="descripcion">Descripción:</label>
						<textarea class="form-control" id="descripcion" name="descripcion"></textarea>
					</div>

					<div class="form-group">
						<label for="sitioWeb">Sitio Web (opcional):</label> <input
							type="text" class="form-control" id="sitioWeb" name="sitioWeb">
					</div>
				</div>

				<input type="submit" class="btn btn-primary" value="Registrar">
				
			</form>
		</div>
	</div>

	<script>
		const clienteRadio = document.getElementById('cliente');
		const aerolineaRadio = document.getElementById('aerolinea');
		const clienteFields = document.getElementById('clienteFields');
		const aerolineaFields = document.getElementById('aerolineaFields');

		clienteRadio.addEventListener('change', function() {
			clienteFields.style.display = 'block';
			aerolineaFields.style.display = 'none';
		});

		aerolineaRadio.addEventListener('change', function() {
			aerolineaFields.style.display = 'block';
			clienteFields.style.display = 'none';
		});

		function validateForm() {
			const password = document.getElementById('password').value;
			const confirmPassword = document.getElementById('confirmPassword').value;
			const errorMessage = document.getElementById('error_message');
			const fechaNacimientoStr = document
					.getElementById('fechaNacimiento').value;
			const today = new Date();
			const nickname = document.getElementById('nickname').value;
			const email = document.getElementById('email').value;

			if (!validarNicknameEmail(nickname)) {
				errorMessage.textContent = 'El nickname contiene caracteres no permitidos';
				errorMessage.className = 'error_message';
				return false;
			}
			
			if (!validarNicknameEmail(email)) {
				errorMessage.textContent = 'El email contiene caracteres no permitidos';
				errorMessage.className = 'error_message';
				return false;
			}
			
			if (password !== confirmPassword) {
				errorMessage.textContent = 'Las contraseñas no coinciden. Por favor, inténtalo de nuevo';
				errorMessage.className = 'error_message';
				return false; // Evita el envío del formulario
			}

			if (fechaNacimientoStr) {
			    const [year, month, day] = fechaNacimientoStr.split('-').map(Number);

			    // Comparar la fecha de nacimiento con la fecha actual (año, mes y día)
			    if (
			      year > today.getFullYear() ||
			      (year === today.getFullYear() && month - 1 > today.getMonth()) ||
			      (year === today.getFullYear() && month - 1 === today.getMonth() && day > today.getDate())
			    ) {
			      errorMessage.textContent = 'La fecha de nacimiento no puede ser una fecha futura';
			      errorMessage.className = 'error_message';
			      return false; // Evita el envío del formulario
			    }
			  }

			errorMessage.textContent = ''; // Limpia el mensaje de error
			return true; // Permite el envío del formulario
		}
		
		//Evita que se envien caracteres invalidos en el nickname y email
		const caracteresInvalidos = /[{}[\]|\\^`]/;
		
		function validarNicknameEmail(texto){
			 if (caracteresInvalidos.test(texto)) {
			        return false;
			    }
			    return true;
		}
		
		//AJAX
		function verificarDisponibilidad(tipo, valor, idMensaje, idElemento) {
			const valorElemento = document.getElementById(idElemento).value;
			const errorMessage = document.getElementById('error_message');
			
			if (validarNicknameEmail(valorElemento)) {
				errorMessage.textContent = '';
				errorMessage.className = '';
				const xhttp = new XMLHttpRequest();
				
			    xhttp.onload = function() {
			        const response = JSON.parse(this.responseText);
			        if (response.disponible) {
			            document.getElementById(idMensaje).textContent = "Disponible";
			            document.getElementById(idMensaje).style.color = "green";
			        } else {
			        	const elemento = document.getElementById(idElemento);
			        	if (elemento.value.trim() === "") {
			        		document.getElementById(idMensaje).textContent = "";
			        	}else{
				            document.getElementById(idMensaje).textContent = "No disponible";
				            document.getElementById(idMensaje).style.color = "red";
			        	}
			        }
			        
			        
			    };
	
			    xhttp.open("GET", 'VerificarUsuarioAJAX?tipoVerificacion=' + tipo + '&valor=' + valor, true);
			    xhttp.send();
			}else {
				errorMessage.textContent = 'El ' + idElemento + ' contiene caracteres no permitidos';
			    errorMessage.className = 'error_message';
			    document.getElementById(idMensaje).textContent = "No disponible";
	            document.getElementById(idMensaje).style.color = "red";
			}
		}
		
		document.getElementById("nickname").addEventListener("keyup", function() {
		    verificarDisponibilidad("nicknameVerificacion", this.value, "nicknameMessage", "nickname");
		});
		
		document.getElementById("email").addEventListener("keyup", function() {
		    verificarDisponibilidad("emailVerificacion", this.value, "emailMessage", "email");
		});
	</script>
</body>
</html>
