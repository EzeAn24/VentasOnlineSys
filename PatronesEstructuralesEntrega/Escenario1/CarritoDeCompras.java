public class CarritoDeCompras {
    
    public static void main(String[] args) {
        
        // --- 1. Crear un Compuesto (Gabinete) ---
        Gabinete miPC = new Gabinete();
        miPC.agregarComponente(new CPU());
        miPC.agregarComponente(new DiscoSSD());
        
        // Precio base: Gabinete(120) + CPU(250) + DiscoSSD(100) = 470
        
        // --- 2. Aplicar Extras (Decorators) a la PC completa ---
        // Se envuelve el Compuesto (miPC)
        Componente pcConInstalacion = new ServicioInstalacion(miPC); 
        
        // Luego, se envuelve el resultado anterior con Garantia Extendida (combinación)
        Componente pcCompleta = new GarantiaExtendida(pcConInstalacion); 
        
        System.out.println("--- Ensamble Completo (Composite + Decorator) ---");
        System.out.println("Composición y Decoración:\n" + pcCompleta.getDescripcion());
        
        // Cálculo esperado:
        // 470 (base) + 50 (Instalación) = 520
        // 520 + 10% de 520 (52) = 572.0
        
        System.out.println("\nPrecio Total del Ensamblaje: $" + pcCompleta.getPrecio());
        
        // --- 3. Prueba de Decorator en una Hoja ---
        Componente ramSimple = new GarantiaExtendida(new MemoriaRAM()); // Usando una hoja (MemoriaRAM)
        System.out.println("\n--- Prueba de Componente Simple Decorado (Hoja) ---");
        System.out.println(ramSimple.getDescripcion() + " | Precio: $" + ramSimple.getPrecio());
    }
}

// **Nota:** Se agregó una clase faltante para que el código compile y sea más completo.
class MemoriaRAM implements Componente {
    private final float PRECIO = 80.0f; // $80 [cite: 28]
    @Override
    public float getPrecio() { return PRECIO; }
    @Override
    public String getDescripcion() { return "Memoria RAM (Hoja)"; }
}