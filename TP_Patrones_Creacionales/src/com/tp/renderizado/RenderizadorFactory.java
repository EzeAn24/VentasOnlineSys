package com.tp.renderizado;

// Esta es la fábrica simple.
// Es la clase que el cliente usará para pedir los motores.
public class RenderizadorFactory {

    // Un método estático que crea el objeto correcto según el string [cite: 20]
    public static IRenderizador crearRenderizador(String tipo) {
        
        if (tipo == null || tipo.isEmpty()) {
            return null;
        }
        
        if ("PDF".equalsIgnoreCase(tipo)) {
            return new RenderizadorPDF();
        
        } else if ("EXCEL".equalsIgnoreCase(tipo)) {
            return new RenderizadorExcel();
        
        } else if ("CSV".equalsIgnoreCase(tipo)) {
            return new RenderizadorCSV();
        }
        
        // Si en el futuro se pide "XML", solo se agrega un "else if" aquí [cite: 21]
        
        throw new IllegalArgumentException("Tipo de reporte no soportado: " + tipo);
    }
}