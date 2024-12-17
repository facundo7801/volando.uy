<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        html, body {
            height: 100%; /* Asegura que la página ocupe todo el alto del viewport */
            margin: 0;
            padding: 0;
        }

        body {
            display: flex;
            flex-direction: column;
            justify-content: space-between; /* Espacia los elementos para que el footer quede al final */
            min-height: 100vh; /* Asegura que el body ocupe el viewport completo */
        }

        main {
            flex: 1; /* El contenido principal ocupa todo el espacio restante */
        }

        footer {
            text-align: center; /* Centra el texto horizontalmente */
            font-weight: bold; /* Aplica negrita */
            font-size: 0.85rem; /* Tamaño de letra más pequeño */
            padding: 1rem; /* Espaciado alrededor del footer */
            background-color: #f8f9fa; /* Color de fondo claro para distinguir el footer */
            color: #333; /* Color del texto para que sea fácil de leer */
            position: relative; /* Relativo para posicionar adecuadamente en la página */
            width: 100%; /* Asegura que el footer ocupe todo el ancho de la pantalla */
        }
    </style>
</head>
<body>
    <main>
    </main>

    <footer>
        <p>&copy; 2024® volando.uy. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
