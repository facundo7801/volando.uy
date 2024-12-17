package uy.edu.fing.volandouy.web;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.*;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/TarjetaDeEmbarqueServlet")
public class TarjetaDeEmbarqueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String tipo = (String)request.getSession().getAttribute("tipo");
		if (tipo != null && ("cliente".equals(tipo))) {
	    	String nickReservaCliente = request.getParameter("nickNameClienteCheckIn");
	        String vueloCheckIn = request.getParameter("vueloNombreCheckIn");
	
	        DetallesReservaService serviceReservaImpl = WebServiceAdapter.getService(DetallesReservaService.class, "detallesReservaService");
	
	        ReservaDto reserva = serviceReservaImpl.reservaCliente(nickReservaCliente, vueloCheckIn);
	
	        UsuarioControllerService serviceUserImpl = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
	
	        ClienteDto userEmbarque = (ClienteDto)serviceUserImpl.obtenerUsuarioPorNickName(nickReservaCliente);
	        RutaDeVueloDto ruta = serviceUserImpl.obtenerRutaDeVueloPorNombre(reserva.getRuta());
	
	        if (reserva == null || ruta == null) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos de la reserva o ruta no disponibles");
	            return;
	        }
	
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=tarjeta_embarque.pdf");
	
	        Document document = new Document();
	        try (OutputStream out = response.getOutputStream()) {
	            PdfWriter.getInstance(document, out);
	            document.open();
	
	            // Configuración de fuentes
	            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.WHITE);
	            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
	            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
	
	            // Encabezado con fondo azul y margen inferior
	            PdfPTable headerTable = new PdfPTable(1);
	            headerTable.setWidthPercentage(100);
	            PdfPCell headerCell = new PdfPCell(new Phrase("TARJETA DE EMBARQUE", fontTitle));
	            headerCell.setBackgroundColor(new BaseColor(0, 121, 255));
	            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            headerCell.setPadding(10);
	            headerCell.setBorder(Rectangle.NO_BORDER);
	            headerTable.addCell(headerCell);
	            document.add(headerTable);
	            document.add(Chunk.NEWLINE);
	
	            // Tabla principal con bordes para detalles de embarque
	            PdfPTable mainTable = new PdfPTable(2);
	            mainTable.setWidthPercentage(100);
	            mainTable.setSpacingBefore(10f);
	            mainTable.setSpacingAfter(10f);
	            mainTable.setWidths(new int[]{1, 2});
	
	            // Detalles del cliente y vuelo
	            mainTable.addCell(createStyledCell("Nombre del cliente:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(userEmbarque.getNombre(), fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	            
	            mainTable.addCell(createStyledCell("Apellido del cliente:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(userEmbarque.getApellido(), fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	
	            
	
	            mainTable.addCell(createStyledCell("Fecha de Check-In:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(new SimpleDateFormat("yyyy-MM-dd").format(reserva.getFechaCheckIn().toGregorianCalendar().getTime()), fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	
	            mainTable.addCell(createStyledCell("Ruta:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(reserva.getRuta(), fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	            
	            mainTable.addCell(createStyledCell("Origen:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(ruta.getCiudadOrigen().getNombre(), fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	            
	            mainTable.addCell(createStyledCell("Aeropuerto Origen:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(ruta.getCiudadOrigen().getNombreAeropuerto(), fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	            
	            mainTable.addCell(createStyledCell("Destino:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(ruta.getCiudadDestino().getNombre(), fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	            
	            mainTable.addCell(createStyledCell("Aeropuerto Destino:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(ruta.getCiudadDestino().getNombreAeropuerto(), fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	
	            mainTable.addCell(createStyledCell("Hora de Embarque:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(reserva.getEmbarqueHoraString(), fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	
	            mainTable.addCell(createStyledCell("Vuelo:", fontBold, Element.ALIGN_LEFT, BaseColor.LIGHT_GRAY, true));
	            mainTable.addCell(createStyledCell(vueloCheckIn, fontBody, Element.ALIGN_LEFT, BaseColor.WHITE, false));
	
	            document.add(mainTable);
	
	            // Detalles de pasajeros
	            Paragraph passengersTitle = new Paragraph("Pasajeros:", fontBold);
	            passengersTitle.setSpacingBefore(10f);
	            passengersTitle.setSpacingAfter(5f);
	            document.add(passengersTitle);
	
	            PdfPTable passengersTable = new PdfPTable(1);
	            passengersTable.setWidthPercentage(100);
	            List<PasajeDto> pasajeros = reserva.getPasajeros();
	            for (PasajeDto pasajero : pasajeros) {
	                PdfPCell passengerCell = new PdfPCell(new Phrase(pasajero.getNombre() + " " + pasajero.getApellido() + ", Asiento: " + pasajero.getNumeroAsiento(), fontBody));
	                passengerCell.setPadding(5);
	                passengerCell.setBorder(Rectangle.NO_BORDER);
	                passengersTable.addCell(passengerCell);
	            }
	            document.add(passengersTable);
	
	
	            document.close();
	        } catch (DocumentException e) {
	            throw new IOException("Error al generar el PDF: " + e.getMessage(), e);
	        }
		}else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
    }

    private PdfPCell createStyledCell(String content, Font font, int alignment, BaseColor bgColor, boolean boldBorder) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(alignment);
        cell.setBackgroundColor(bgColor);
        cell.setPadding(5);
        if (boldBorder) {
            cell.setBorderWidthBottom(1.5f);
        } else {
            cell.setBorder(Rectangle.NO_BORDER);
        }
        return cell;
    }
}
