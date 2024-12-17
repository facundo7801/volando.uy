<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="uy.edu.fing.volandouy.webservices.RutaDeVueloDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.PaqueteDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.LupaDto"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<link rel="stylesheet" href="./media/homeStyles.css" />
<meta charset="UTF-8">
<title>Resultados</title>
<style>
.Resultados {
	display: flex;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	padding: 20px;
	gap: 20px;
}

.Resultados img {
	max-width: 200px;
	height: auto;
	border-radius: 10px;
}

.Resultados .informacionResultados {
	flex: 1;
	display: flex;
	flex-direction: column;
	
}

.Resultados .informacionResultados h3 {
	margin: 0;
	font-size: 1.2rem;
	font-weight: bold;
	padding-bottom: 5px;
}

.Resultados .informacionResultados p {
	margin: 0;
	padding: 0;
	color: #555;
}

.Resultados .informacionResultados a {
	margin: 0;
	padding: 0;
	color: #0066cc;
	text-decoration: none;
}

.Resultados .informacionResultados a:hover {
	text-decoration: underline;
}

.form-groupo {
    margin-bottom: 15px;
}

.form-groupo label {
    display: block;
    margin-bottom: 5px;
}

.form-groupo select {
    width: 100%; /* Puedes ajustar el ancho según prefieras */
}

.form-grupo input{
	margin-top: 0.5rem;
}

/* Estilos responsive */
@media (max-width: 1024px) { /* Para tablets y pantallas más pequeñas */
    .Resultados {
        flex-direction: column; /* Cambiar a columna para adaptarse mejor a pantallas más pequeñas */
        padding: 15px; /* Reducir el padding */
    }

    .Resultados img {
        max-width: 100%; /* Hacer que la imagen ocupe todo el ancho disponible */
        height: auto;
    }

    .Resultados .informacionResultados h3 {
        font-size: 1rem; /* Reducir tamaño del título */
    }

    .form-groupo {
        margin-bottom: 10px; /* Reducir el margen de los formularios */
    }

    .form-groupo select, .form-grupo input {
        padding: 8px; /* Asegurarse de que los inputs y select tengan un padding adecuado */
    }
}

@media (max-width: 480px) { /* Para dispositivos móviles */
    .Resultados {
        padding: 10px; /* Reducir el padding */
    }

    .Resultados img {
        max-width: 100%; /* Asegurar que la imagen se ajuste al tamaño disponible */
        height: auto;
        margin-bottom: 10px; /* Separar un poco la imagen del texto */
    }

    .Resultados .informacionResultados h3 {
        font-size: 0.9rem; /* Reducir aún más el tamaño del título */
    }

    .Resultados .informacionResultados p {
        font-size: 0.9rem; /* Reducir el tamaño de los párrafos */
    }

    .form-groupo {
        margin-bottom: 8px; /* Ajustar margen en móviles */
    }

    .form-groupo select, .form-grupo input {
        padding: 10px; /* Asegurarse de que los inputs y select se vean bien */
    }
}
</style>
</head>
<body>

	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>
		
		<div class="vuelos">
			<%
			List<LupaDto> resultados = (List<LupaDto>)request.getSession().getAttribute("resultadosLupa");
			Integer totalResultados = resultados.size();
			String busquedaIngresada = (String)request.getSession().getAttribute("busquedaIngresada");
			String opcionSeleccionadaPaquetesRutas = (String)request.getAttribute("opcionSeleccionadaPaquetesRutas");
			String opcionSeleccionadaDisplay = (String)request.getAttribute("opcionSeleccionadaDisplay");
			String opcionSeleccionadaMayus = (String)request.getAttribute("opcionSeleccionadaMayus");
			%>
			
			<div class="Resultados">
				<div class="informacionResultados">
					<h3><%=totalResultados %> resultados</h3>
					<%
					if (busquedaIngresada != null && !busquedaIngresada.isBlank()){
					%>
						<p>Busqueda ingresada: "<%=busquedaIngresada %>"</p>
					<%
					}else {
					%>
						<p>Busqueda ingresada: Vacía</p>
					<%
					}
					%>
					<form action="LupaServlet" method="get">
						<div class="form-grupo">
							<label for="opcionSeleccionadaPaquetesRutas">Resultados que desea mostrar:</label>
						</div>
						<div class="form-grupo">
							<select id="opcionSeleccionadaPaquetesRutas" name="opcionSeleccionadaPaquetesRutas">
								<%
								if (opcionSeleccionadaPaquetesRutas == null) {
								%>
								    <option value="todos" selected>Todos</option>
								    <option value="rutas">Rutas</option>
								    <option value="paquetes">Paquetes</option>
							    <%
								}else if (opcionSeleccionadaPaquetesRutas.equals("rutas")) {
							    %>
							    	<option value="todos">Todos</option>
								    <option value="rutas" selected>Rutas</option>
								    <option value="paquetes">Paquetes</option>
							    <%
								}else {
							    %>
							    	<option value="todos">Todos</option>
								    <option value="rutas">Rutas</option>
								    <option value="paquetes" selected>Paquetes</option>
							    <%
								}
							    %>
							</select>
						</div>
					
						<div class="form-grupo">
							<label for="opcionesDisplay">Seleccione un filtro para mostrar los datos:</label>
						</div>
						<div class="form-grupo">
							<select id="opcionesDisplay" name="opcionesDisplay">
								<%
								if (opcionSeleccionadaDisplay == null) {
								%>
								    <option value="defecto" selected>Por defecto</option>
								    <option value="alfabeticamente">Alfabéticamente (A-z a-z)</option>
							    <%
								}else {
							    %>
							    	<option value="defecto">Por defecto</option>
							    	<option value="alfabeticamente" selected>Alfabéticamente (A-z a-z)</option>
							    <%
								}
							    %>
							</select>
						</div>
						
						<div class="form-grupo">
							<label for="opcionesMayusculas">Seleccione si desea ignorar mayúsculas:</label>
						</div>
						<div class="form-grupo">
							<select id="opcionesMayusculas" name="opcionesMayusculas">
								<%
								if (opcionSeleccionadaMayus == null){
								%>
								    <option value="Si" selected>Si</option>
								    <option value="No">No</option>
							    <%
								}else {
							    %>
							    	<option value="Si">Si</option>
								    <option value="No" selected>No</option>
							    <%
								}
							    %>
							</select>
						</div>
						
						<input type="hidden" id="llamadoFiltro" name="llamadoFiltro" value="Si">
						
						<div class="form-grupo">
							<input type="submit" class="btn btn-primary" value="Aplicar filtros">
						</div>
					</form>
				</div>
			</div>
			
			<%
			for (LupaDto resultado : resultados){
				if (resultado.getTipo().equals("Ruta")) {
					RutaDeVueloDto ruta = (RutaDeVueloDto)resultado.getEntidad();
				%>
					<div class="vuelo">
						<img src="/ServidorWeb/Imagenes?nombreImagen=<%= ruta.getImagen() != null ? ruta.getImagen() : "noimg.jpg" %>" alt="Imagen de ruta">
						<div class="informacion">
							<h3><%=ruta.getNombre() %> (Ruta)</h3>
							<p><%=ruta.getResumen() %></p>
							<a href="consultaRutaServlet?rutaHome=<%=ruta.getNombre() %>&aerolineaHome=<%=ruta.getAerolinea().getNickName() %>&consultaHome=si">Leer más</a>
						</div>
					</div>
				<%
				}else {
					PaqueteDto paquete = (PaqueteDto)resultado.getEntidad();
					String clienteConsulta = null;
					String tipo = (String)request.getSession().getAttribute("tipo");
					if (tipo != "visitante") {
						String nicknameLogeado = (String)request.getSession().getAttribute("usuario");
						for (String nicknameComprador : paquete.getCompradores()) {
							if (nicknameComprador.equals(nicknameLogeado)){
								clienteConsulta = "clienteConsulta";
							}
						}
					}
				%>
					<div class="vuelo">
						<img src="/ServidorWeb/Imagenes?nombreImagen=<%= paquete.getImagen() != null ? paquete.getImagen() : "noimg.jpg" %>" alt="Imagen de paquete">
						<div class="informacion">
							<h3><%=paquete.getNombre() %> (Paquete)</h3>
							<p><%=paquete.getDescripcion() %></p>
							<% 
							if (clienteConsulta == null) { 
							%>
								<a href="consultaPaqueteServlet?nombre=<%=paquete.getNombre()%>">Leer más</a>
							<%
							}else{
							%>
								<a href="consultaPaqueteServlet?nombre=<%=paquete.getNombre()%>&clienteConsulta=clienteConsulta">Leer más</a>
							<%
							}
							%>
						</div>
					</div>
				<%
				}
			}
			%>
		</div>
	</div>

<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>