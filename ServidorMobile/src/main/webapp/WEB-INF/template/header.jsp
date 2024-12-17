<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="nav-bar">

	<div class="izq">
		<!-- Icono de menú con margen ajustado sin afectar la barra -->
		<a href="#" class="btn btn-primary btn-sm boton-menu" type="button"
			data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample"
			aria-controls="offcanvasExample"> <i class="fa fa-bars"></i>
		</a>

		<div class="offcanvas offcanvas-start menu-container contenedor-menu"
			tabindex="-1" id="offcanvasExample"
			aria-labelledby="offcanvasExampleLabel">
			<div class="offcanvas-header canva-boton-cerrar">
				<button class="btn-close" type="button" aria-label="Close"
					data-bs-dismiss="offcanvas"></button>
			</div>

			<ol class="canva-lista">
				<li><a
					<%if ("home".equals(request.getSession().getAttribute("pagina"))) {%>
					class="activo" <%}%> href="homeServlet"> <i class="fa fa-home"></i>
						Home
				</a></li>
				
					<c:if test="${tipo == 'cliente'}">
						<li>
							<div class="boton-canva-lista">
								<form action="loginServlet" method="get">
									<button class="btn btn-danger elementos cerrar-sesion">Cerrar sesión</button>
								</form>
							</div>
						</li>
					</c:if>
		
					<!--<c:if test="${tipo == 'visitante' || tipo == null}">
						<li>
							<div class="boton-canva-lista">
									<form action="loginServlet?id=cerrar_sesion" method="get">
										<button class="btn boton-azul"><i class="fa fa-user me-sm-1 icono-iniciar-sesion"></i>Iniciar Sesión</button>
									</form>
							</div>
						</li>
					</c:if> -->

			</ol>
			
		</div>

		<a class="volandouy" href="homeServlet">Volando.uy</a>
	</div>

	<div class="der">
		<span class="input-group-text text-body p-0 m-0 barra-lupa"> <a href=""
			class="fa fa-search p-2 lupa" id="buscarLink" aria-hidden="true" onclick="actualizarHref()"></a> <input
			type="search" class="form-control" id="searchInput" placeholder="Ruta, descripción" onkeydown="teclaPresionada(event)">
		</span>
	</div>

</nav>

<script>
    function actualizarHref() {
        var textoBusqueda = document.getElementById('searchInput').value;

        var url = "LupaServlet?busqueda=" + textoBusqueda;
        
        document.getElementById('buscarLink').href = url;
    }
    
    function teclaPresionada(event) {
    	if (event.key === "Enter"){
    		actualizarHref();
    		var lupa = document.getElementById('buscarLink');
    		lupa.click();
    	}
    }
</script>
