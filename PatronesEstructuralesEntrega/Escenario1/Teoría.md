# Informe: Patrones de Diseño Estructurales

## Escenario 1: Sistema de Ensamblaje de Computadoras

A continuación, se detalla la solución para el primer escenario, aplicando los patrones Composite y Decorator.

### Análisis del Escenario y Problemas

El escenario presenta dos problemas principales de diseño que deben resolverse:

#### 1. Uniformidad y Jerarquía (Componentes)

El cliente (el "Carrito de Compras") necesita tratar a todos los objetos de la misma manera para calcular el precio total. Esto es un desafío, ya que el sistema debe manejar:

- Componentes individuales (hojas) como un `DiscoSSD`
- Componentes compuestos (cajas) como un `Gabinete` que contiene otros componentes

El problema es cómo hacer que `getPrecio()` funcione de forma transparente en ambos casos.

#### 2. Flexibilidad de Responsabilidad (Extras)

El sistema requiere añadir "extras" (Garantía Extendida, Servicio de Instalación) a cualquier componente, ya sea simple o compuesto. Estos extras deben ser combinables (ej. Garantía + Instalación).

> Si intentáramos resolver esto con herencia, tendríamos una "explosión de clases":
> - DiscoConGarantia
> - GabineteConGarantia
> - GabineteConGarantiaEInstalacion
> 
> Lo cual es rígido e insostenible.

### Identificación y Justificación de Patrones

Para resolver los problemas identificados, se seleccionaron dos patrones estructurales:

#### Patrón Composite
- **Problema**: Uniformidad y Jerarquía
- **Justificación**: Permite componer objetos en estructuras de árbol para representar jerarquías de parte-todo. Los clientes (el carrito) pueden tratar objetos individuales (Hojas, como CPU) y composiciones de objetos (Compuestos, como Gabinete) de forma uniforme a través de una interfaz común (Componente).

#### Patrón Decorator
- **Problema**: Flexibilidad de Responsabilidad
- **Justificación**: Permite adjuntar responsabilidades adicionales a un objeto de forma dinámica y transparente, envolviéndolo. Esto permite añadir los costos de "extras" a cualquier Componente (sea Hoja o Compuesto) sin alterar su clase, y permite apilar (combinar) múltiples decoradores sobre el mismo objeto.