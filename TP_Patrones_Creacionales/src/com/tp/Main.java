package com.tp;

// Importamos las clases de todos nuestros paquetes
import com.tp.config.GestorConfiguracion;
import com.tp.reporte.Orientacion;
import com.tp.reporte.Reporte;
import com.tp.renderizado.IRenderizador;
import com.tp.renderizado.RenderizadorFactory;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Demostración del TP de Patrones Creacionales ---");

        // --- 1. Demostración de SINGLETON (Req 3) ---
        System.out.println("\n[Paso 1: Usando Singleton]");
        GestorConfiguracion config = GestorConfiguracion.getInstancia();
        System.out.println("El directorio de reportes es: " + config.getPathReportes());

        // Demostramos que es la misma instancia
        GestorConfiguracion config2 = GestorConfiguracion.getInstancia();
        System.out.println("¿config y config2 son la misma instancia? " + (config == config2));


        // --- 2. Demostración de BUILDER (Req 2) ---
        System.out.println("\n[Paso 2: Usando Builder]");
        Reporte reporteFinanzas = new Reporte.ReporteBuilder(
                    "Reporte Anual de Finanzas",    // Título (obligatorio)
                    "Contenido principal del reporte..." // Cuerpo (obligatorio)
                )
                .conAutor("Departamento de Contabilidad") // Opcional
                .conFecha(LocalDate.now())                // Opcional
                .conPieDePagina("Confidencial")           // Opcional
                .build();
        
        System.out.println("Reporte creado exitosamente: " + reporteFinanzas);


        // --- 3. Demostración de FACTORY (Req 1) ---
        System.out.println("\n[Paso 3: Usando Factory]");
        
        // El cliente (Main) no sabe de "new RenderizadorPDF()".
        // Solo pide lo que necesita por un String [cite: 20]
        String formatoElegido = "PDF";
        IRenderizador motor = RenderizadorFactory.crearRenderizador(formatoElegido);

        // "Enlazamos" todo: le pasamos el Reporte (del Builder) al Motor (del Factory)
        motor.renderizar(reporteFinanzas);

        // Probamos con otro formato
        formatoElegido = "EXCEL";
        motor = RenderizadorFactory.crearRenderizador(formatoElegido);
        motor.renderizar(reporteFinanzas);
    }
}