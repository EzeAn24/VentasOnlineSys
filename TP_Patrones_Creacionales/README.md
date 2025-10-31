# Trabajo Práctico: Patrones Creacionales - POO II

Este proyecto implementa una solución en Java para el escenario del "Sistema de Configuración y Renderizado de Reportes", aplicando los tres patrones de diseño creacionales solicitados.

## 1. Documento de Justificación

A continuación, se detallan las decisiones de diseño tomadas para cada requerimiento.

---

### 🚀 Requerimiento 1: El Motor de Renderizado

* **¿Qué patrón de diseño creacional eligieron?**
    * Se utilizó el patrón **Factory** (específicamente, una implementación de *Simple Factory*).

* **¿Por qué este patrón es la solución adecuada para este problema?**
    * Este patrón es ideal porque el cliente (la clase `Main` o cualquier módulo) no necesita saber *cómo* se construye un objeto renderizador (`RenderizadorPDF`, `RenderizadorExcel`, etc.).
    * [cite_start]El cliente puede simplemente solicitar el motor que necesita a través de un método estático (`RenderizadorFactory.crearRenderizador("PDF")`), delegando por completo la lógica de instanciación a la fábrica[cite: 20].

* **¿Qué problema(s) evita?**
    * **Evita el Acoplamiento:** El código cliente no se acopla a las clases concretas (ej. `new RenderizadorPDF()`). Solo conoce la interfaz `IRenderizador`.
    * **Cumple el Principio Abierto/Cerrado (OCP):** El sistema está "abierto a la extensión" pero "cerrado a la modificación". [cite_start]Si en el futuro se necesita agregar un `RenderizadorXML`[cite: 21], **no es necesario modificar el código cliente**. Simplemente se crea la nueva clase `RenderizadorXML` y se añade un `else if` en la `RenderizadorFactory`.

---

### 🧱 Requerimiento 2: La Construcción de los Reportes

* **¿Qué patrón de diseño creacional eligieron?**
    * Se utilizó el patrón **Builder**.

* **¿Por qué este patrón es la solución adecuada?**
    * [cite_start]El objeto `Reporte` es una entidad compleja con una combinación de datos obligatorios (título, cuerpo) [cite: 24-26] [cite_start]y múltiples datos opcionales (encabezado, fecha, autor, etc.) [cite: 27-32].
    * El Builder permite construir el objeto paso a paso, de forma legible y fluida (ej. `new Reporte.ReporteBuilder(...).conAutor(...).conFecha(...).build()`).

* **¿Qué problemas específicos del "constructor" resuelve?**
    * **Resuelve el "Constructor Telescópico":** Evita la necesidad de crear múltiples constructores (`Reporte(titulo)`, `Reporte(titulo, autor)`, `Reporte(titulo, autor, fecha)`), lo cual es difícil de mantener.
    * [cite_start]**Evita pasar `null`:** Resuelve el problema de tener un único constructor gigante que obliga al cliente a pasar `null` para todos los parámetros opcionales que no desea usar (ej. `new Reporte("Titulo", "Cuerpo", null, null, null, "Autor", null)`), lo cual es propenso a errores y muy poco legible[cite: 34].

---

### ⚙️ Requerimiento 3: El Gestor de Configuración Global

* **¿Qué patrón de diseño creacional eligieron?**
    * Se utilizó el patrón **Singleton**.

* **¿Por qué este patrón es la solución adecuada para este requerimiento?**
    * [cite_start]El requerimiento es la definición de libro de texto del patrón Singleton: "garantizar que solo exista **una y solo una instancia** del objeto `GestorConfiguracion`" [cite: 41] [cite_start]y que exista un "**único punto de acceso** global"[cite: 40].
    * [cite_start]Esto es crucial para un gestor de configuración, ya que evita la sobrecarga de crear múltiples objetos de configuración y previene inconsistencias en los datos[cite: 42].

* **¿Cómo garantizaron la unicidad de la instancia?**
    * La unicidad se garantiza mediante la combinación de dos técnicas:
        1.  **Constructor Privado:** El constructor de la clase (`private GestorConfiguracion()`) es privado, lo que impide que cualquier otra clase pueda crear una instancia usando `new GestorConfiguracion()`.
        2.  **Método Estático de Acceso:** Se provee un método público y estático (`public static GestorConfiguracion getInstancia()`) que devuelve la **única instancia**, la cual se almacena en un atributo estático y privado (`private static final GestorConfiguracion instancia`).

## 2. Código Fuente

El código fuente está estructurado en paquetes de Java dentro de la carpeta `src/`, separando las responsabilidades de cada patrón:
* `src/com/tp/config`: Contiene el Singleton.
* `src/com/tp/reporte`: Contiene el Builder.
* `src/com/tp/renderizado`: Contiene el Factory.
* `src/com/tp/Main.java`: Demuestra el funcionamiento de los tres patrones.
