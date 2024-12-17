package uy.edu.fing.volandouy.web;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.ImagenesService;


@SuppressWarnings("serial")
@WebServlet("/Imagenes")
public class Imagenes extends HttpServlet {
       
    public Imagenes() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreImagen = (String) request.getParameter("nombreImagen");
        
        byte[] img = null;

         ImagenesService port = WebServiceAdapter.getService(ImagenesService.class, "ImagenesService");

        try {

            img = port.getFile(nombreImagen);
            response.setContentType("image/jpg");
            response.setContentLength((int) img.length);
            OutputStream out = response.getOutputStream();
            //ImageIO.write(img, "png", out);
            out.write(img);
            out.close();
            
        } catch (Exception ex) {            

        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
