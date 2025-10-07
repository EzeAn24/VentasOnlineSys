📝 Descripción del Diagrama de Clases del Sistema de Venta Online

Este diagrama de clases UML (Lenguaje Unificado de Modelado) modela la estructura y las relaciones clave de un Sistema de Venta Online (e-commerce). Define las entidades principales (clases), sus atributos (datos que almacenan) y sus operaciones (métodos o funcionalidades) y cómo interactúan entre sí.

👥 Clases de Usuario y Roles
Usuario → Clase base para cualquier persona que interactúa con el sistema, conteniendo información personal básica.

Persona → Clase abstracta o de tipo, relacionada con la Logística y los pedidos.

Cliente → Hereda de Usuario y tiene la capacidad de administrar su perfil (actualizarPerfil()). Un cliente tiene un CarritoDeCompras.

Administrador → Un rol especial dentro del sistema, aunque sus operaciones específicas no están detalladas aquí, se relaciona con el Cliente.

🛒 Gestión de Compras
CarritoDeCompras → Representa la cesta de compra de un Cliente. Mantiene el estado (ACTIVO, PENDIENTE, CERRADO) y la fechaCreacion. Ofrece métodos para agregar, eliminar, y actualizar la cantidad de productos, y para finalizar la compra.

ItemCarrito → Un elemento de línea dentro del carrito, que contiene la cantidad de un producto específico, su precioUnitario y el método para calcular el subtotal (calcularSubtotal()). El CarritoDeCompras contiene múltiples ItemCarrito.

Producto → La mercancía que se vende. Incluye atributos como nombre, precio, stock y categoria. Ofrece métodos clave como reducir y aumentar el stock, y obtener el precio con descuento.

📦 Proceso de Pedido y Logística
Pedido → Una orden de compra que se realiza a partir de un CarritoDeCompras finalizado. Contiene el estado del pedido (PENDIENTE_ENVIO, EN_CAMINO, etc.), fechas clave, y el total. Métodos principales: generar y confirmar el pedido y el envío.

Envio → Un elemento de línea del pedido que contiene detalles logísticos como la direccionEnvio, costoEnvio y el numeroRastreo. Permite rastrear el envío (rastrearEnvio()). Un Pedido tiene uno o más Envio.

Logistica → Encargada de la gestión de inventario (stock) y la capacidad de almacén. Tiene métodos para gestionar el inventario y asignar envíos (asignarEnvio(Pedido)). La Logística gestiona los Pedido.

💳 Transacciones
MetodoPago → Representa la forma en que se liquida un Pedido (tarjeta, PayPal, etc.). Contiene el tipo y detalles de pago, y el método esencial para procesar el pago (procesarPago()). Un Pedido se paga con un MetodoPago.

⚙️ Gestión de Proveedores
CompraAProveedores → Un registro interno para reponer el inventario. Se compra a un Producto y contiene información sobre el proveedor y la fechaCompra. Permite realizar la compra y actualizar el stock.