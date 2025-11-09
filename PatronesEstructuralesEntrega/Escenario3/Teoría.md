# 1. Análisis del Escenario y Problemas

El sistema de E‑Commerce debe integrar un nuevo proveedor de logística, `LogísticaVeloz`. El SDK externo (`ApiLogisticaVeloz.jar`) es incompatible con la interfaz interna que utiliza el sistema: `IServicioEnvio`.

## Problemas de incompatibilidad

### Interfaces diferentes
- Los nombres de los métodos no coinciden (ej.: `calcularCosto` vs `cotizarEnvio`).

### Tipos de datos diferentes
- La interfaz interna usa `String` para el código postal, mientras que la API externa espera un `int`.

### Métodos y objetos diferentes
- El método interno `despacharPedido` recibe parámetros simples, mientras que la API externa expone `enviarPaquete` que requiere un objeto `DatosEnvio`.

**Restricción importante:** no es posible modificar la librería externa `ApiLogisticaVeloz`.

## 2. Identificación del Patrón y Justificación

### Problema Identificado
- Incompatibilidad de Interfaces

### Patrón Elegido
- Adapter (Adaptador)

### Justificación Formal
La intención del patrón Adapter es convertir la interfaz de una clase en otra interfaz que el cliente espera. El adaptador permite que clases con interfaces incompatibles trabajen juntas. En este caso, crearemos un `AdaptadorLogisticaVeloz` que implementará nuestra interfaz `IServicioEnvio` (el Target) y "envolverá" (contendrá) una instancia de `ApiLogisticaVeloz` (el Adaptee), traduciendo las llamadas en el proceso.