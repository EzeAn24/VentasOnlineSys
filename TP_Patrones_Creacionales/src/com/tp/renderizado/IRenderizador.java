package com.tp.renderizado;  // <--- ARREGLADO

import com.tp.reporte.Reporte; // <--- ARREGLADO

// Basado en tu interfaz "Transporte"
public interface IRenderizador {
    // Le pasamos el reporte que debe renderizar
    void renderizar(Reporte reporte);
}