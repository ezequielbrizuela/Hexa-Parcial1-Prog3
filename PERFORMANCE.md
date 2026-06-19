# Anexo Tecnico de Rendimiento - EcoRide Pro

Decisiones de rendimiento aplicadas en la version Pro del sistema EcoRide. El objetivo, de acuerdo a los requerimientos del Parcial 2, fue mejorar el acceso, limpieza y ordenamiento de datos usando estructuras nativas de Java, sin base de datos, sin concurrencia, sin lambdas y sin API Stream.

## 1. Busqueda Instantanea de Vehiculos por Patente

En la version anterior de Ecoride, la estacion guardaba los vehiculos en una lista y la busqueda por patente recorria cada elemento hasta encontrar una coincidencia. Ese enfoque tiene complejidad O^n, porque en el peor caso el sistema debe revisar todos los vehiculos registrados, lo cual causaba una enorme perdida de rendimiento y mal uso de recursos.

Para mejorar este punto, `EstacionAnclaje` usa ahora un `HashMap<String, Vehiculo>`, donde la clave es la patente normalizada con `trim().toUpperCase()`. De esta forma, buscar un vehiculo por patente se resuelve con `get`, cuyo tiempo promedio es O^1.

Los vehiculos ya no se eliminan de la estacion cuando inician un viaje. Su disponibilidad se controla mediante el patron State, dejando el objeto siempre accesible por patente para poder finalizar el viaje o consultar su fase actual.

## 2. Deduplicacion de Alertas GPS

El problema de las alertas GPS duplicadas se resolvió con una sola pasada sobre la lista recibida. El servicio `GpsService` usa un `LinkedHashSet<CoordenadaGps>` para almacenar solo coordenadas unicas.

La eleccion de `LinkedHashSet` permite dos cosas evitar duplicados usando `equals` y `hashCode` y por otra parte conservar el orden de primera aparicion de cada coordenada.

La complejidad promedio del algoritmo es O(n), porque cada reporte se analiza una sola vez y la insercion en el set es O(1) promedio. Esto evita el enfoque ineficiente de bucles anidados, donde cada coordenada se compara contra todas las demas y el costo crece a O(n^2).

validaciones:
si la lista es `null`, se devuelve una lista vacia.
si un reporte individual es `null`, se ignora.
si una coordenada esta fuera de rango, se lanza `SolicitudInvalidaException`.
las latitudes validas ahora deben ser entre -90 y 90. Mientras que las longitudes validas ahora deben ser entre -180 y 180.

## 3. Ordenamiento Natural y Ordenamiento Alternativo

El ordenamiento de flota se resolvió con 2 mecanismos distintos para no mezclar responsabilidades.

### Orden Natural por Bateria

`Vehiculo` implementa `Comparable<Vehiculo>`. El criterio natural es la bateria de menor a mayor, porque esa es la prioridad  del equipo de mantenimiento: cargar primero los vehiculos con menos energia. Esto permite que si dos vehiculos tienen el mismo nivel de bateria, se usa la patente como "desempate". Esto hace que el resultado sea estable y predecible.

La complejidad de `Collections.sort` ordena la copia de la lista en O^n log n.

### Orden Alternativo por Tarifa Base

El orden comercial por tarifa se resolvio con `ComparadorTarifaBaseDescendente`, un `Comparator<Vehiculo>` externo. Este criterio ordena de mayor a menor tarifa base, sin modificar el orden natural definido dentro de `Vehiculo`.

Esta separacion permite usar estos criterios en memoria de forma independiente:
Para prioridad de carga: `Collections.sort(vehiculos)`. Para tarifa descendente: `Collections.sort(vehiculos, new ComparadorTarifaBaseDescendente())`.

Estos reportes trabajan sobre copias traidas desde `EstacionAnclaje.obtenerTodosLosVehiculos()`. Asi se evita alterar por accidente la estructura interna principal donde se administra la flota.