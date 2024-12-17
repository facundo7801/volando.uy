<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="./media/reservarStyles.css" />
<meta charset="UTF-8">
<title>Reservar</title>
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>

		<div class="container">
			<div class="form-container">
				<h1>Reserva de Vuelo</h1>
				<form id="reservaForm" method="POST" action="reservarServlet">

					<c:if test="${not empty errorMessage}">
						<div id="error_message">${errorMessage}</div>
					</c:if>

					<input type="hidden" id="primerNombre" name="primerNombre"
						value="${nombreCliente}"> <input type="hidden"
						id="segundoNombre" name="segundoNombre" value="${apellidoCliente}">

					<div class="grid-layout">
						<div class="form-group">
							<label for="aerolineaReserva">Aerolínea</label> <select
								class="form-control" name="aerolineaReserva"
								id="aerolineaReserva" disabled>
								<option value="${datosVuelo.aerolinea}" selected>${datosVuelo.aerolinea}</option>
							</select> <input type="hidden" name="aerolineaReserva"
								value="${datosVuelo.aerolinea}">
						</div>

						<div class="form-group">
							<label for="rutaReserva">Ruta de Vuelo</label> <select
								class="form-control" name="rutaReserva" id="rutaReserva"
								disabled>
								<option value="${datosVuelo.ruta}" selected>${datosVuelo.ruta}</option>
							</select> <input type="hidden" name="rutaReserva"
								value="${datosVuelo.ruta}">
						</div>

						<div class="form-group">
							<label for="vueloReserva">Vuelo</label> <select
								class="form-control" name="vueloReserva" id="vueloReserva"
								disabled>
								<option value="${datosVuelo.nombre}" selected>${datosVuelo.nombre}</option>
							</select> <input type="hidden" name="vueloReserva"
								value="${datosVuelo.nombre}">
						</div>

						<div class="form-group">
							<label for="tipoAsientoReserva">Tipo de Asiento</label> <select
								id="tipoAsientoReserva" name="tipoAsientoReserva"
								class="form-control" required>
								<option value="TURISTA" selected>Turista</option>
								<option value="EJECUTIVO">Ejecutivo</option>
							</select>
						</div>

						<div class="form-group">
							<label for="cantidadPasajesReserva">Cantidad de Pasajes</label> <input
								type="number" id="cantidadPasajesReserva"
								name="cantidadPasajesReserva" class="form-control" min="1"
								value="1" placeholder="1" required>
						</div>

						<div class="form-group">
							<label for="equipajeExtraReserva">Unidades de Equipaje
								Extra</label> <input type="number" id="equipajeExtraReserva"
								name="equipajeExtraReserva" class="form-control" min="0"
								value="0" placeholder="0" required>
						</div>

						<div class="form-group">
							<label for="metodoPagoReserva">Método de Pago</label> <select
								id="metodoPagoReserva" name="metodoPagoReserva"
								class="form-control" required>
								<option value="general" selected>General</option>
								<c:forEach var="paquete" items="${paquetesReserva}">
									<option value="${paquete}">${paquete}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<h3>Información de Pasajeros</h3>
					<div class="grid-layout">
						<div class="form-group">
							<label for="pasajeroReserva">Número de Pasajeros</label> <select
								id="pasajeroReserva" name="pasajeroReserva" class="form-control"
								required>
								<!-- Este select es dinámico -->
							</select>
						</div>

						<div class="form-group">
							<label for="pasajeroNombreReserva">Nombre del Pasajero</label> <input
								type="text" id="pasajeroNombreReserva"
								name="pasajeroNombreReserva" class="form-control">
						</div>

						<div class="form-group">
							<label for="pasajeroApellidoReserva">Apellido del
								Pasajero</label> <input type="text" id="pasajeroApellidoReserva"
								name="pasajeroApellidoReserva" class="form-control">
						</div>
					</div>

					<input type="hidden" id="pasajerosFijosJson"
						name="pasajerosFijosJson">

					<button type="submit" class="btn btn-submit">Reservar</button>
				</form>
			</div>
		</div>
	</div>

	<script>
		document
				.addEventListener(
						'DOMContentLoaded',
						function() {
							const nombrePrimero = document
									.getElementById('primerNombre').value;
							const apellidoPrimero = document
									.getElementById('segundoNombre').value;

							let datosPasajeros = {
								1 : {
									nombre : nombrePrimero,
									apellido : apellidoPrimero
								}
							};

							let pasajeroViejo = 1;

							function actualizarPasajeros() {
								let cantidadPasajes = parseInt(document
										.getElementById('cantidadPasajesReserva').value);
								const selectPasajeros = document
										.getElementById('pasajeroReserva');

								selectPasajeros.innerHTML = '';

								if (cantidadPasajes < 1) {
									cantidadPasajes = 1;
								} else {
									guardarDatosPasajero();
								}

								for (let i = 1; i <= cantidadPasajes; i++) {
									const option = document
											.createElement('option');
									option.value = i;
									option.textContent = "Pasajero " + i;
									selectPasajeros.appendChild(option);
								}

								if (cantidadPasajes === 1) {
									selectPasajeros.options[0].selected = true;
								}

								actualizarInfoPasajero();
							}

							function actualizarInfoPasajero() {
								const pasajeroSeleccionado = parseInt(document
										.getElementById('pasajeroReserva').value);
								const pasajeroNombre = document
										.getElementById('pasajeroNombreReserva');
								const pasajeroApellido = document
										.getElementById('pasajeroApellidoReserva');

								if (datosPasajeros[pasajeroSeleccionado]) {
									pasajeroNombre.value = datosPasajeros[pasajeroSeleccionado].nombre;
									pasajeroApellido.value = datosPasajeros[pasajeroSeleccionado].apellido;
								} else {
									pasajeroNombre.value = '';
									pasajeroApellido.value = '';
								}

								if (pasajeroSeleccionado === 1) {
									pasajeroNombre.disabled = true;
									pasajeroApellido.disabled = true;
								} else {
									pasajeroNombre.disabled = false;
									pasajeroApellido.disabled = false;
								}
							}

							function guardarDatosPasajero() {
								const pasajeroSeleccionado = pasajeroViejo;
								if (pasajeroSeleccionado !== 1) {
									const pasajeroNombre = document
											.getElementById('pasajeroNombreReserva').value;
									const pasajeroApellido = document
											.getElementById('pasajeroApellidoReserva').value;

									datosPasajeros[pasajeroSeleccionado] = {
										nombre : pasajeroNombre,
										apellido : pasajeroApellido
									};
								}

								let cantidadPasajes = parseInt(document
										.getElementById('cantidadPasajesReserva').value);
								for (let i = 1; i <= cantidadPasajes; i++) {
									if (!datosPasajeros[i]) {
										datosPasajeros[i] = {
											nombre : '',
											apellido : ''
										};
									}
								}

								pasajeroViejo = parseInt(document
										.getElementById('pasajeroReserva').value);
							}

							document.getElementById('cantidadPasajesReserva')
									.addEventListener('input',
											actualizarPasajeros);

							document.getElementById('pasajeroReserva')
									.addEventListener('change', function() {
										guardarDatosPasajero();
										actualizarInfoPasajero();
									});

							document
									.getElementById('reservaForm')
									.addEventListener(
											'submit',
											function(event) {
												const equipajeExtra = document
														.getElementById('equipajeExtraReserva').value;

												if (equipajeExtra < 0) {
													alert('El número de unidades de equipaje extra debe ser 0 o mayor.');
													event.preventDefault();
												}

												pasajeroViejo = parseInt(document
														.getElementById('pasajeroReserva').value);
												guardarDatosPasajero();

												const cantidadPasajes = document
														.getElementById('cantidadPasajesReserva').value;
												let pasajerosFijos = {};
												for (let i = 1; i <= cantidadPasajes; i++) {
													pasajerosFijos[i] = datosPasajeros[i];
												}

												document
														.getElementById('pasajerosFijosJson').value = JSON
														.stringify(pasajerosFijos);
											});

							actualizarPasajeros();
						});
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>