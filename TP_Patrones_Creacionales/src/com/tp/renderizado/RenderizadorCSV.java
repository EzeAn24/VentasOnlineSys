package com.tp.renderizado;

import com.tp.reporte.Reporte;

// Basado en tu clase "Barco"
public class RenderizadorCSV implements IRenderizador {
    @Override
    public void renderizar(Reporte reporte) {
        System.out.println("Renderizando reporte '" + reporte.getTitulo() + "' en formato CSV...");
        // (Aquí iría la lógica real para crear un CSV)
    }
}