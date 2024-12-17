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

				<li><a
					<%if ("consultaUsuarios".equals(request.getSession().getAttribute("pagina"))) {%>
					class="activo" <%}%> href="ListaUsuariosServlet"> <i
						class="fa fa-users"></i> Consulta Usuarios
				</a></li>

				<li><a
					<%if ("consultaPaquetes".equals(request.getSession().getAttribute("pagina"))) {%>
					class="activo" <%}%> href="listaPaqueteServlet"> <i
						class="fas fa-box"></i> Consulta Paquetes
				</a></li>

				<c:if test="${tipo == 'cliente'}">
					<li><a <%if ("comprarPaquete".equals(request.getSession().getAttribute("pagina"))) {%>
					class="activo" <%}%> href="CompraPaqueteServlet"> <i
						class="fa-solid fa-box-open"></i> Comprar Paquete
				</a></li>
				</c:if>

				<c:if test="${tipo == 'aerolinea'}">
					<li><a <%if ("altaRuta".equals(request.getSession().getAttribute("pagina"))) {%>
					class="activo" <%}%> href="AltaRutaServlet"> <i class="fa-solid fa-plane"></i>
						Alta Ruta
				</a></li>
				</c:if>
			</ol>
		</div>

		<a class="volandouy" href="homeServlet">Volando.uy</a>
	</div>

	<div class="der">
		<span class="input-group-text text-body p-0 m-0 barra-lupa"> <a href=""
			class="fa fa-search p-2 lupa" id="buscarLink" aria-hidden="true" onclick="actualizarHref()"></a> <input
			type="search" class="form-control" id="searchInput" placeholder="Ruta, Paquete, descripción" onkeydown="teclaPresionada(event)">
		</span>

		<c:if test="${tipo == 'cliente' || tipo == 'aerolinea'}">
			<span class="txtUser"> <i class="fa fa-user me-sm-1 icono-iniciar-sesion"></i> <a
				class="elementos"
				href="modificarUsuarioServlet?nickname=${fn:escapeXml(sessionScope.usuario)}">
					<span>${fn:escapeXml(sessionScope.usuario)}</span>
			</a>
			</span>

			<form action="loginServlet" method="get">
				<button class="btn btn-danger elementos cerrar-sesion">Cerrar sesión</button>
			</form>
		</c:if>

		<c:if test="${tipo == 'visitante' || tipo == null}">
			<span class="txtUser"> <i class="fa fa-user me-sm-1 icono-iniciar-sesion"></i> <a
				class="elementos" href="loginServlet?id=cerrar_sesion"><p>Iniciar
						Sesión</p></a>
			</span>
		</c:if>
	</div>

</nav>

<script>
	var textoValido = false;

    function actualizarHref() {
        var textoBusqueda = document.getElementById('searchInput').value;
        
        if (!validarBusqueda(textoBusqueda)){
        	textoValido = false;
        	event.preventDefault();
        }else{
	        var url = "LupaServlet?busqueda=" + textoBusqueda;
	        document.getElementById('buscarLink').href = url;
	        textoValido = true;
    	}
    }
    
    function teclaPresionada(event) {
    	if (event.key === "Enter"){
    		actualizarHref();
    		if (textoValido){
	    		var lupa = document.getElementById('buscarLink');
	    		lupa.click();
    		}
    	}
    }
    
	// Lista de caracteres no permitidos según RFC 7230 y RFC 3986
	const caracteresInvalidos = /[{}[\]|\\^`]/;

	function validarBusqueda(textoBusqueda) {
	    if (caracteresInvalidos.test(textoBusqueda)) {
	        alert("La búsqueda contiene caracteres no permitidos.");
	        return false;
	    }
	    return true;
	}
</script>
