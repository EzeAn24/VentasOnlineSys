public abstract class ComponenteDecorator implements Componente {
    protected Componente componenteDecorado;

    public ComponenteDecorator(Componente componente) {
        this.componenteDecorado = componente;
    }

    // Por defecto, los decoradores delegan la descripción y el precio al objeto envuelto.
    @Override
    public float getPrecio() {
        return componenteDecorado.getPrecio();
    }

    @Override
    public String getDescripcion() {
        return componenteDecorado.getDescripcion();
    }
}