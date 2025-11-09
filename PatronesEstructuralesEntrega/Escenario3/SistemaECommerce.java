// --- Clases de Datos (Mocks) de la librería externa "LogisticaVeloz" ---
// (No son públicas para poder estar en el mismo archivo de prueba)
class Cotizacion {
    public float costo;
    public int dias;
    public Cotizacion(float c, int d) { this.costo = c; this.dias = d; }
}

class DatosEnvio {
    public String direccion;
    public int cpDestino;
    public String idPedido;
    public DatosEnvio(String dir, int cp, String id) {
        this.direccion = dir;
        this.cpDestino = cp;
        this.idPedido = id;
    }
}

// --- 1. El Target (La interfaz que tu sistema espera) ---
interface IServicioEnvio {
    float calcularCosto (String codigoPostal);
    String obtenerTiempoEstimado(String codigoPostal);
    String despacharPedido (String direccion, String codigoPostal, String idPedido);
}


// --- 2. El Adaptee (La clase incompatible que NO SE PUEDE MODIFICAR) ---
// (Simulamos la clase externa)
class ApiLogisticaVeloz {
    public Cotizacion cotizarEnvio (int cpDestino) {
        System.out.println("  [ApiLogisticaVeloz] Cotizando envío a CP: " + cpDestino);
        return new Cotizacion (150.50f, 2); // Simulación de respuesta
    }
    
    public String enviarPaquete(DatosEnvio datos) {
        //... lógica interna compleja para procesar el envío
        System.out.println("  [ApiLogisticaVeloz] Enviando paquete a " + datos.direccion + " (ID: " + datos.idPedido + ")");
        return "LV-987654321"; // Simulación de tracking
    }
}


// --- 3. El Adapter (El traductor) ---
// Esta clase implementa el Target (IServicioEnvio) y envuelve al Adaptee (ApiLogisticaVeloz)
class AdaptadorLogisticaVeloz implements IServicioEnvio {
    
    // Contiene la instancia del objeto que queremos adaptar
    private ApiLogisticaVeloz logisticaVeloz;

    public AdaptadorLogisticaVeloz(ApiLogisticaVeloz logistica) {
        this.logisticaVeloz = logistica;
    }

    // Método 1: Traduce calcularCosto(String)
    @Override
    public float calcularCosto(String codigoPostal) {
        System.out.println("[Adaptador] Traduciendo calcularCosto(String)...");
        // **Conversión de tipos (String a int)** [cite: 80]
        int cpDestino = Integer.parseInt(codigoPostal); 
        
        // **Llamada al método incompatible** [cite: 79]
        Cotizacion cotizacion = logisticaVeloz.cotizarEnvio(cpDestino);
        return cotizacion.costo;
    }

    // Método 2: Traduce obtenerTiempoEstimado(String)
    @Override
    public String obtenerTiempoEstimado(String codigoPostal) {
        System.out.println("[Adaptador] Traduciendo obtenerTiempoEstimado(String)...");
        int cpDestino = Integer.parseInt(codigoPostal);
        Cotizacion cotizacion = logisticaVeloz.cotizarEnvio(cpDestino);
        return cotizacion.dias + " días hábiles.";
    }

    // Método 3: Traduce despacharPedido(String, String, String)
    @Override
    public String despacharPedido(String direccion, String codigoPostal, String idPedido) {
        System.out.println("[Adaptador] Traduciendo despacharPedido(params...) a enviarPaquete(DatosEnvio)...");
        // **Conversión de tipos y objetos** [cite: 81]
        int cpDestino = Integer.parseInt(codigoPostal);
        DatosEnvio datos = new DatosEnvio(direccion, cpDestino, idPedido);
        
        return logisticaVeloz.enviarPaquete(datos);
    }
}


// --- 4. El Cliente (El sistema de E-Commerce) ---
public class SistemaECommerce {
    public static void main(String[] args) {
        
        // La clase externa que necesitamos integrar
        ApiLogisticaVeloz apiVeloz = new ApiLogisticaVeloz();
        
        // Creamos el adaptador para que sea compatible con nuestro sistema (Target)
        IServicioEnvio servicioEnvio = new AdaptadorLogisticaVeloz(apiVeloz);
        
        // Nuestro sistema (el cliente) usa el Adaptador como si fuera un servicio nativo
        // El cliente no sabe que por detrás se está llamando a "cotizarEnvio" o "enviarPaquete"
        
        System.out.println("--- PRUEBA DE COSTO ---");
        float costo = servicioEnvio.calcularCosto("1414");
        System.out.println("Costo final (para el cliente): $" + costo);
        
        System.out.println("\n--- PRUEBA DE DESPACHO ---");
        String tracking = servicioEnvio.despacharPedido("Calle Falsa 123", "1414", "PED-555");
        System.out.println("Tracking generado (para el cliente): " + tracking);
    }
}