public class ServicioInstalacion extends ComponenteDecorator {
    private static final float COSTO_INSTALACION = 50.0f; // Costo fijo de $50

    public ServicioInstalacion(Componente componente) {
        super(componente);
    }

    @Override
    public float getPrecio() {
        // Añade un costo fijo de $50 al precio del componente envuelto
        return super.getPrecio() + COSTO_INSTALACION;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Servicio de Instalación ($" + COSTO_INSTALACION + ")";
    }
}