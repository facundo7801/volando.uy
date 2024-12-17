<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="uy.edu.fing.volandouy.webservices.EstadoRuta"%>
<%@ page import="uy.edu.fing.volandouy.webservices.RutaDeVueloDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.CategoriaDto"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>



<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<meta charset="UTF-8">
<title>Detalles de la Ruta de Vuelo</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
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

.btn-volver {
	display: block;
	margin: 20px auto;
}

.btn {
	margin-right: 10px;
}

.categorias {
	margin-top: 10px;
}

.contenedorToolTip {
	display: flex;
    justify-content: center;
    align-items: center;
}

.botones-grupo {
    display: flex;
    align-items: center;
    gap: 10px;
}

.tooltipFinalizarDiv {
    display: inline-flex;
    align-items: center;
    position: relative;
}

.tooltipFinalizar {
    visibility: hidden;
    background-color: #333;
    color: #fff;
    text-align: center;
    border-radius: 5px;
    padding: 5px 10px;
    position: absolute;
    bottom: 80%;
    left: 50%;
    transform: translateX(-50%);
    z-index: 1;
    opacity: 0;
    transition: opacity 0.3s;
    font-size: 0.85rem;
    white-space: normal;
    max-width: 300px;
    word-wrap: break-word;
    width: 250px;
}

.tooltipFinalizar::after {
    content: '';
    position: absolute;
    top: 100%;
    left: 50%;
    transform: translateX(-50%);
    border-width: 5px;
    border-style: solid;
    border-color: #333 transparent transparent transparent;
}
.tooltipFinalizarDiv:hover .tooltipFinalizar {
    visibility: visible;
    opacity: 1;
}

</style>
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>

		<div class="container">
			<h1>Detalles de la Ruta de Vuelo</h1>
			<%
			RutaDeVueloDto ruta = (RutaDeVueloDto) request.getAttribute("findRuta");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (ruta != null) {
			%>
			<div class="card">
				<img src="/ServidorWeb/Imagenes?nombreImagen=<%= ruta.getImagen() != null ? ruta.getImagen() : "noimg.jpg" %>" alt="Imagen de ruta">

				<ul class="details-list">
					<li><p>Nombre:</p> <strong><%=ruta.getNombre()%></strong></li>
					<li><p>Descripción:</p> <strong><%=ruta.getDescripcion()%></strong></li>
					<li><p>Estado:</p> <strong><%=ruta.getEstado()%></strong></li>
					<li><p>Costo Turista:</p> <strong><%=ruta.getCostoTurista()%>$</strong></li>
					<li><p>Costo Ejecutivo:</p> <strong><%=ruta.getCostoEjecutivo()%>$</strong></li>
					<li><p>Costo Equipaje extra:</p> <strong><%=ruta.getCostoEquipajeExtra()%>$</strong></li>
					<li><p>Fecha de Alta:</p> <strong><%=dateFormat.format(ruta.getFechaAlta().toGregorianCalendar().getTime())%></strong></li>
					<%
					if (ruta.getEstado() == EstadoRuta.FINALIZADA) {
					%>
						<li><p>Fecha de Finalización:</p> <strong><%=dateFormat.format(ruta.getFechaFinalizada().toGregorianCalendar().getTime())%></strong></li>
					<%
					}
					%>
					<li><p>Aerolínea:</p> <strong><%=ruta.getAerolinea().getNombre()%></strong></li>
					<li><p>Hora:</p> <strong><%=ruta.getHoraString()%></strong></li>
					<li><p>Ciudad Origen:</p> <strong><%=ruta.getCiudadOrigen().getNombre()%></strong></li>
					<li><p>Ciudad Destino:</p> <strong><%=ruta.getCiudadDestino().getNombre()%></strong></li>
					<li>
						<p>Categorías:</p>
						<strong>
							<%
							List<CategoriaDto> categorias = ruta.getCategorias();
							if (categorias != null && !categorias.isEmpty()) {
								for (int i = 0; i < categorias.size(); i++) {
									out.print(categorias.get(i).getNombre());
									if (categorias.size() > 0) {
								if (i < categorias.size() - 1) {
									out.print(", ");
								}
									}
								}
							} else {
								out.print("No hay categorías disponibles");
							}
							%>
						</strong>
					</li>
				</ul>

				<!--<div class="categorias">
					<h5>Categorías:</h5>
					<p>
						<%
						/*List<CategoriaDto> categorias = ruta.getCategorias();
						if (categorias != null && !categorias.isEmpty()) {
							for (int i = 0; i < categorias.size(); i++) {
								out.print(categorias.get(i).getNombre());
								if (categorias.size() > 0) {
							if (i < categorias.size() - 1) {
								out.print(", ");
							}
								}
							}
						} else {
							out.print("No hay categorías disponibles");
						}*/
						%>
					</p>
				</div>-->

				<div class="mt-3 botones-grupo">

					<%
					String usuarioLogeado = (String) request.getSession().getAttribute("usuario");
					if (ruta.getEstado() == EstadoRuta.CONFIRMADA) {
					%>
						<a href="listaVuelosServlet" class="btn btn-success">Listar
							Vuelos</a>
						<%
						if (usuarioLogeado != null && ruta.getAerolinea().getNickName().equals(usuarioLogeado)) {
						%>
							<a href="altaVueloServlet" class="btn btn-success">Agregar un
								Vuelo</a>
							<% 
							String sePuedeFinalizar = (String)request.getAttribute("sePuedeFinalizar");
							if (sePuedeFinalizar != null) { 
							%>
								<a href="finalizarRutaServlet" class="btn btn-danger">Finalizar Ruta</a>
							<%
							}else {
							%>
								<div>
								<div class=tooltipFinalizarDiv>
									<span class="tooltipFinalizar">La ruta pertenece a un paquete o posee vuelos que no se realizaron</span>
									<a 
										href="#" 
										class="btn btn-primary btn-volver disabled" 
										tabindex="-1" 
										aria-disabled="true" 
										style="background-color: #cccccc; color: #666666; cursor: not-allowed;">
										Finalizar Ruta
									</a>
								</div>
							</div>			
							<% 
							}
							%>
						<%
						}
					%>
					<%
					}else if (ruta.getEstado() == EstadoRuta.FINALIZADA && usuarioLogeado != null && ruta.getAerolinea().getNickName().equals(usuarioLogeado)) {
					%>
						<a href="listaVuelosServlet" class="btn btn-success">Listar
							Vuelos</a>
							
							<div>
								<div class=tooltipFinalizarDiv>
									<span class="tooltipFinalizar">La ruta ya se encuentra finalizada</span>
									<a 
										href="#" 
										class="btn btn-primary btn-volver disabled" 
										tabindex="-1" 
										aria-disabled="true" 
										style="background-color: #cccccc; color: #666666; cursor: not-allowed;">
										Finalizar Ruta
									</a>
								</div>
							</div>
					<%
					}
					%>
				</div>
				
				<%
				if (ruta.getVideo() != null){
					String url = ruta.getVideo();
					String ERROR_URL = "https://www.youtube.com/embed/invalid_video?autoplay=1";
			    	String youtubePattern = "(https?://(?:www\\.)?youtube\\.com/watch\\?v=([a-zA-Z0-9_-]+))|(?:https?://(?:www\\.)?youtu\\.be/([a-zA-Z0-9_-]+))|(?:https?://(?:www\\.)?youtube\\.com/embed/([a-zA-Z0-9_-]+))";
			        
			        Pattern pattern = Pattern.compile(youtubePattern);
			        Matcher matcher = pattern.matcher(url);
			        
			        if (matcher.find()) {
			            String videoId = matcher.group(2) != null ? matcher.group(2) : matcher.group(3);
			            if (videoId != null && videoId.length() == 11 && !url.equals(ERROR_URL)) {
			            	url = "https://www.youtube.com/embed/" + videoId;
			            }else if (!url.matches("^(https://(www\\.)?(youtube\\.com/embed/|player\\.vimeo\\.com/video/|google\\.com/maps/embed\\?)([a-zA-Z0-9_-]+))")) {
			            	url = null;
			            }
			        }else {
			        	url = null;
			        }
				%>
					<div class="embed-responsive embed-responsive-16by9 mx-auto mt-4">
    					<iframe src="<%=url%>" class="embed-responsive-item" allowfullscreen></iframe>
					</div>
				<%
				}
				%>
				
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
