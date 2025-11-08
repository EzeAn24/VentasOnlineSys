// --- Clases de Datos (Mocks) para la simulación ---
// (No son públicas para poder estar en el mismo archivo de prueba)
class DatosFiscales {
    public String cuit;
    public DatosFiscales(String c) { this.cuit = c; }
    public String toString() { return "Datos Fiscales de CUIT " + cuit; }
}

class MontosCalculados {
    public float montoTotal;
    public MontosCalculados(float m) { this.montoTotal = m; }
    public String toString() { return "Montos Calculados: $" + montoTotal; }
}

// --- 1. El Subsistema Complejo (Las clases que complican al cliente) ---

// Pasos 1 y 2
class ConectorDB {
    public void conectar() {
        System.out.println("1. Instanciando ConectorDB y conectando a la base de datos.");
    }
    public String obtenerCuitDelCliente(String idCliente) {
        System.out.println("2. Usando Lector De Datos para obtener CUIT del cliente: " + idCliente);
        return "20-99999999-9"; // CUIT simulado
    }
}

// Pasos 3 y 4
class ServicioWebAFIP {
    public void autenticarse() {
        System.out.println("3. Instanciando ServicioWebAFIP y autenticándose.");
    }
    public DatosFiscales obtenerDatosFiscales(String cuit) {
        System.out.println("4. Llamando a ServicioWebAFIP.obtenerDatosFiscales(" + cuit + ").");
        return new DatosFiscales(cuit); // Objeto de datos simulado
    }
}

// Paso 5
class ProcesadorDeImpuestos {
    public MontosCalculados calcularMontos(DatosFiscales datos) {
        System.out.println("5. Instanciando ProcesadorDeImpuestos para calcular montos con " + datos);
        return new MontosCalculados(15420.75f); // Montos simulados
    }
}

// Paso 6
class RenderizadorPDF {
    public void generarArchivo(MontosCalculados montos) {
        System.out.println("6. Instanciando RenderizadorPDF y generando el archivo con " + montos);
        System.out.println("   --> Archivo ReporteFiscal.pdf generado con éxito.");
    }
}


// --- 2. La Fachada (Facade) ---
// Esta es la clase pública principal que resuelve el problema.
public class ReporteFacade {
    
    // La fachada contiene las referencias a todas las clases del subsistema
    private ConectorDB conectorDB;
    private ServicioWebAFIP servicioAFIP;
    private ProcesadorDeImpuestos procesador;
    private RenderizadorPDF renderizador;

    public ReporteFacade() {
        // La fachada es responsable de instanciar el subsistema
        this.conectorDB = new ConectorDB();
        this.servicioAFIP = new ServicioWebAFIP();
        this.procesador = new ProcesadorDeImpuestos();
        this.renderizador = new RenderizadorPDF();
    }

    /**
     * Simplifica los 6 pasos complejos en una sola llamada para el cliente.
     * @param idCliente El ID del cliente para generar el reporte.
     */
    public void generarReporteFiscal(String idCliente) {
        System.out.println("\n--- Reporte Facade: Iniciando Generación de Reporte para ID Cliente: " + idCliente + " ---");
        
        // 1. Conectar y obtener CUIT
        conectorDB.conectar();
        String cuit = conectorDB.obtenerCuitDelCliente(idCliente);

        // 2. Autenticar y obtener Datos Fiscales
        servicioAFIP.autenticarse();
        DatosFiscales datos = servicioAFIP.obtenerDatosFiscales(cuit);

        // 3. Calcular montos
        MontosCalculados montos = procesador.calcularMontos(datos);

        // 4. Renderizar PDF
        renderizador.generarArchivo(montos);
        
        System.out.println("--- Proceso completado exitosamente (Simple para el cliente) ---\n");
    }
}

// --- 3. El Cliente (El módulo que necesita el reporte) ---
// Puedes poner este main() dentro de la clase ReporteFacade para probar,
// o crear una clase separada 'ModuloReportes'.
class ModuloReportes {
    public static void main(String[] args) {
        // El cliente solo interactúa con ReporteFacade
        ReporteFacade fachada = new ReporteFacade();
        
        // ¡Una sola línea resuelve el problema!
        fachada.generarReporteFiscal("CLIENTE_A001");
        
        // El cliente ya no sabe nada sobre ConectorDB, AFIP, PDF, etc.
    }
}