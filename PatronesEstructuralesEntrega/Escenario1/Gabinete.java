import java.util.ArrayList;
import java.util.List;

public class Gabinete implements Componente {
    private List<Componente> componentes = new ArrayList<>();
    private final float PRECIO_BASE = 120.0f; // $120

    public void agregarComponente(Componente componente) {
        componentes.add(componente);
    }

    // Al llamar a getPrecio, suma su propio precio base + el precio de todos sus hijos.
    @Override
    public float getPrecio() {
        float precioTotal = PRECIO_BASE;
        for (Componente comp : componentes) {
            precioTotal += comp.getPrecio();
        }
        return precioTotal;
    }

    @Override
    public String getDescripcion() {
        StringBuilder sb = new StringBuilder("Gabinete (Compuesto, Base: $" + PRECIO_BASE + ") que contiene:\n");
        for (Componente comp : componentes) {
            sb.append("  - ").append(comp.getDescripcion()).append("\n");
        }
        return sb.toString();
    }
}