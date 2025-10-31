package com.tp.reporte;

import java.time.LocalDate;

// Basado en tu archivo Builder.java (clase Pizza)
public class Reporte {

    // Atributos del TP [cite: 25-32]
    private final String titulo;         // Obligatorio
    private final String cuerpoPrincipal; // Obligatorio
    private final String encabezado;     // Opcional
    private final String pieDePagina;    // Opcional
    private final LocalDate fecha;       // Opcional
    private final String autor;          // Opcional
    private final Orientacion orientacion; // Opcional

    // Constructor privado, solo el Builder lo usa
    private Reporte(ReporteBuilder builder) {
        this.titulo = builder.titulo;
        this.cuerpoPrincipal = builder.cuerpoPrincipal;
        this.encabezado = builder.encabezado;
        this.pieDePagina = builder.pieDePagina;
        this.fecha = builder.fecha;
        this.autor = builder.autor;
        this.orientacion = builder.orientacion;
    }

    // Getters para usar el reporte
    public String getTitulo() { return titulo; }
    // ... puedes agregar más getters si los necesitas

    @Override
    public String toString() {
        return "Reporte [titulo=" + titulo + ", autor=" + autor + ", fecha=" + fecha + "]";
    }

    // El Builder como clase estática anidada
    // Basado en tu PizzaBuilder
    public static class ReporteBuilder {
        
        // Parámetros obligatorios [cite: 25, 26]
        private final String titulo;
        private final String cuerpoPrincipal;

        // Parámetros opcionales [cite: 27-32]
        private String encabezado;
        private String pieDePagina;
        private LocalDate fecha;
        private String autor;
        private Orientacion orientacion = Orientacion.VERTICAL; // Valor por defecto

        // Constructor del Builder con datos obligatorios
        public ReporteBuilder(String titulo, String cuerpoPrincipal) {
            this.titulo = titulo;
            this.cuerpoPrincipal = cuerpoPrincipal;
        }

        // Métodos "setter" fluidos para los opcionales
        public ReporteBuilder conEncabezado(String encabezado) {
            this.encabezado = encabezado;
            return this; // Permite encadenar llamadas
        }

        public ReporteBuilder conPieDePagina(String pieDePagina) {
            this.pieDePagina = pieDePagina;
            return this;
        }

        public ReporteBuilder conFecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public ReporteBuilder conAutor(String autor) {
            this.autor = autor;
            return this;
        }

        public ReporteBuilder conOrientacion(Orientacion orientacion) {
            this.orientacion = orientacion;
            return this;
        }

        // Método final que construye el objeto Reporte
        public Reporte build() {
            return new Reporte(this);
        }
    }
}