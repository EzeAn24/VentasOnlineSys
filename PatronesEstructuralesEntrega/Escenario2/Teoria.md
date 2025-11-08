# Escenario 2: Módulo de Generación de Reportes
## 1. Análisis del Escenario y Problemas

### El Problema
El cliente (la interfaz de usuario) debe realizar una secuencia de 6 pasos complejos que involucran instanciar y coordinar 4 clases diferentes del subsistema (`ConectorDB`, `ServicioWebAFIP`, `ProcesadorDelmpuestos`, `RenderizadorPDF`) solo para generar un reporte.

### Problemas Graves Identificados

1. **Alta Complejidad**
    - El cliente se ve forzado a conocer y gestionar la lógica de orquestación de todo el subsistema.

2. **Fuerte Acoplamiento**
    - El cliente está fuertemente acoplado a cuatro clases diferentes.
    - Si cualquiera de esas clases del subsistema cambia (por ejemplo, `ServicioWebAFIP` añade un nuevo paso de autenticación), el cliente debe ser modificado.

## 2. Identificación del Patrón y Justificación

### Problema Identificado
- Complejidad y Acoplamiento

### Patrón Elegido
- Facade (Fachada)

### Justificación Formal
La intención del patrón Facade es proporcionar una interfaz unificada y simplificada a un conjunto de interfaces en un subsistema complejo. Al crear una clase ReporteFacade, esta se encargará de realizar los 6 pasos internamente. El cliente solo necesitará llamar a un único método (ej. generarReporteFiscal()), reduciendo drásticamente la complejidad y desacoplando al cliente del subsistema.