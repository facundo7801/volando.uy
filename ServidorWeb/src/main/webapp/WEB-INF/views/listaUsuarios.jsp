<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="uy.edu.fing.volandouy.webservices.UsuarioDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.ClienteDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.AerolineaDto"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Usuarios</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="./media/listarUsuarios.css" />
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>
		<div class="container">
			<%
			String seguidosL = (String)request.getAttribute("seguidosL");
			String seguidoresL = (String)request.getAttribute("seguidoresL");
			if (seguidosL != null) {
			%>
				<h1>Lista de Seguidos</h1>
			<%
			}else if (seguidoresL != null) {
			%>
				<h1>Lista de Seguidores</h1>
			<%
			}else {
			%>
				<h1>Lista de Usuarios Registrados</h1>
			<%
			}
			%>
			
			<!-- Version como las rutas -->
			<div class="row">
				<%
				List<UsuarioDto> usuarios = (List<UsuarioDto>) request.getSession().getAttribute("usuarios");
				if (usuarios != null && !usuarios.isEmpty()) {
					for (UsuarioDto usuario : usuarios) {
						String tipoDeUsuario = (usuario instanceof ClienteDto) ? "(Cliente)" : "(Aerolinea)";
					%>
						<div class="col-md-6 col-lg-4 mb-4">
							<div class="card shadow-sm h-100">
									<img src="/ServidorWeb/Imagenes?nombreImagen=<%= usuario.getImagen() != null ? usuario.getImagen() : "noimg.jpg" %>" alt="Imagen de usuario" 
                                 class="card-img-top imagen-ruta">
									
								<div class="card-body d-flex flex-column">
									<h5 class="card-title"><%=usuario.getNickName() + " " + tipoDeUsuario%></h5>
									<p class="card-text"><%=usuario.getEmail()%></p>
									<a href="detallesUsuarioServlet?nickname=<%=usuario.getNickName()%>"
										class="btn btn-info mt-auto ver-detalles">Ver Detalles</a>
								</div>
							</div>
						</div>
					<%
					}
				} else {
				%>
				<div class="col-12">
					<div class="alert alert-warning text-center">
						<%
						if (seguidosL != null) {
						%>
							No sigue a ningún usuario
						<%
						}else if (seguidoresL != null) {
						%>
							No posee seguidores
						<%
						}else {
						%>
							No hay usuarios registrados
						<%
						}
						%>
					</div>
				</div>
				<%
				}
				%>
			</div>
			<!-- Version como las rutas -->
			
			
			<!--<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Nickname</th>
						<th>Nombre</th>
						<th>Email</th>
						<th>Más detalles</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="usuario" items="${usuarios}">
						<tr>
							<td>${usuario.nickName}</td>
							<td>${usuario.nombre}</td>
							<td>${usuario.email}</td>
							<td><a
								href="<c:url value='detallesUsuarioServlet?nickname=${usuario.nickName}'/>"
								class="btn btn-info">Ver Detalles</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>-->

			<!-- Paginación
			<nav>
				<ul class="pagination">
					<c:forEach var="i" begin="1" end="${totalPages}">
						<li class="page-item ${i == currentPage ? 'active' : ''}"><a
							class="page-link" href="ListaUsuariosServlet?page=${i}">${i}</a>
						</li>
					</c:forEach>
				</ul>
			</nav>
			-->

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
