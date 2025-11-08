public class CPU implements Componente {
    private final float PRECIO = 250.0f; // $250

    @Override
    public float getPrecio() {
        return PRECIO;
    }

    @Override
    public String getDescripcion() {
        return "CPU (Hoja)";
    }
}