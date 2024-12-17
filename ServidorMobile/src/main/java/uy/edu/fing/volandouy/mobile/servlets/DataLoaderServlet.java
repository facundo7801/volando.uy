package uy.edu.fing.volandouy.mobile.servlets;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.DataLoaderService;

@WebServlet("/DataLoaderServlet")
public class DataLoaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DataLoaderServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		// Obtener el path absoluto de la carpeta resources
		String basePath = context.getRealPath("/WEB-INF/classes");

		try {
			DataLoaderService serviceImplementacion = WebServiceAdapter.getService(DataLoaderService.class, "DataLoaderService");
			serviceImplementacion.cargarDatos(basePath);
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

		} catch (IOException e) {
			response.getWriter().write("Error al cargar los datos: " + e.getMessage());
		} catch (Exception e) {
			response.getWriter().write("Error al procesar los datos: " + e.getMessage());
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

}
