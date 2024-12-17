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

.btn-listar {
	margin-right: 10px;
}

.categorias {
	margin-top: 10px;
}

/* Estilo responsive */
@media (max-width: 1024px) { /* Tabletas y pantallas más pequeñas */
    .container {
        margin-top: 20px;
    }

    h1 {
        font-size: 1.5rem; /* Reducir el tamaño de la fuente en pantallas más pequeñas */
    }

    .card {
        padding: 15px; /* Reducir el padding para optimizar espacio */
    }

    .card img {
        height: 180px; /* Ajustar el tamaño de las imágenes */
    }

    .details-list li {
        flex-direction: column;
        padding: 8px 0; /* Reducir el padding */
    }

    .details-list li p {
        font-size: 0.9rem; /* Reducir tamaño de texto */
    }

    .details-list li strong {
        font-size: 0.9rem; /* Reducir tamaño de texto */
    }

    .btn-volver {
        margin: 15px auto;
    }
}

@media (max-width: 480px) { /* Móviles */
    .container {
        margin-top: 15px;
        padding: 0 15px; /* Agregar algo de padding en móviles */
    }

    h1 {
        font-size: 1.2rem; /* Tamaño más pequeño en móviles */
    }

    .card {
        padding: 10px; /* Reducir aún más el padding */
    }

    .card img {
        height: 150px; /* Hacer la imagen aún más pequeña */
    }

    .details-list li {
        padding: 6px 0;
    }

    .details-list li p {
        font-size: 0.8rem; /* Reducir aún más el tamaño de texto */
    }

    .details-list li strong {
        font-size: 0.8rem; /* Reducir aún más el tamaño de texto */
    }

    .btn-volver {
        margin: 10px auto;
    }

    .categorias {
        font-size: 0.9rem; /* Reducir el tamaño de las categorías */
    }
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
				<img src="/ServidorMobile/Imagenes?nombreImagen=<%= ruta.getImagen() != null ? ruta.getImagen() : "noimg.jpg" %>" alt="Imagen de ruta">

				<ul class="details-list">
					<li><p>Nombre:</p> <strong><%=ruta.getNombre()%></strong></li>
					<li><p>Descripción:</p> <strong><%=ruta.getDescripcion()%></strong></li>
					<li><p>Estado:</p> <strong><%=ruta.getEstado()%></strong></li>
					<li><p>Costo Turista:</p> <strong><%=ruta.getCostoTurista()%>$</strong></li>
					<li><p>Costo Ejecutivo:</p> <strong><%=ruta.getCostoEjecutivo()%>$</strong></li>
					<li><p>Costo Equipaje extra:</p> <strong><%=ruta.getCostoEquipajeExtra()%>$</strong></li>
					<li><p>Fecha de Alta:</p> <strong><%=dateFormat.format(ruta.getFechaAlta().toGregorianCalendar().getTime())%></strong></li>
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

				<div class="mt-3">

					<%
					if (ruta.getEstado() == EstadoRuta.CONFIRMADA) {
					%>
					<a href="listaVuelosServlet" class="btn btn-success btn-listar">Listar
						Vuelos</a>
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
    					<iframe src="<%=url + "?autoplay=1"%>" class="embed-responsive-item" allowfullscreen></iframe>
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
