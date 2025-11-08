public class DiscoSSD implements Componente {
    private final float PRECIO = 100.0f; // $100

    @Override
    public float getPrecio() {
        return PRECIO;
    }

    @Override
    public String getDescripcion() {
        return "Disco SSD (Hoja)";
    }
}