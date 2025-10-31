package com.tp.config;

// Basado en tu archivo Singleton/ConfiguracionApp.java
public class GestorConfiguracion {

    // 1. La única instancia
    // Usamos "eager" (ansiosa) como en tu ejemplo de DatabaseConnection
    private static final GestorConfiguracion instancia = new GestorConfiguracion();

    // Atributos de configuración del TP
    private String urlBd;
    private String userBd;
    private String pathReportes;

    // 2. Constructor privado
    private GestorConfiguracion() {
        // Cargamos la configuración (simulado)
        this.urlBd = "jdbc:mysql://servidor/db_principal";
        this.userBd = "admin_user";
        this.pathReportes = "/opt/app/reportes/";
        System.out.println("Gestor de Configuración inicializado.");
    }

    // 3. Método estático público para obtener la instancia
    public static GestorConfiguracion getInstancia() {
        return instancia;
    }

    // Getters para los datos del TP
    public String getUrlBd() {
        return urlBd;
    }

    public String getUserBd() {
        return userBd;
    }

    public String getPathReportes() {
        return pathReportes;
    }
}