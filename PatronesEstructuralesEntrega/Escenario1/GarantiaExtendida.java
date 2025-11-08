public class GarantiaExtendida extends ComponenteDecorator {
    
    public GarantiaExtendida(Componente componente) {
        super(componente);
    }

    @Override
    public float getPrecio() {
        // Añade un 10% al costo total del componente al que se aplica.
        float precioBase = super.getPrecio();
        float costoExtra = precioBase * 0.10f;
        return precioBase + costoExtra;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Garantía Extendida (10% extra)";
    }
}