package com.tp.renderizado;

import com.tp.reporte.Reporte;

// Basado en tu clase "Camion"
public class RenderizadorPDF implements IRenderizador {
    @Override
    public void renderizar(Reporte reporte) {
        System.out.println("Renderizando reporte '" + reporte.getTitulo() + "' en formato PDF...");
        // (Aquí iría la lógica real para crear un PDF)
    }
}