# Sistema de Asignación de Asientos - AERO-USAC

## Integrantes

- Byron Alexander Crispin Guzman
- Heraldo David Escobar Rosales

## Descripción

Este proyecto consiste en desarrollar un sistema de asignación de asientos para una aerolínea utilizando matrices bidimensionales en Java.

La cabina está formada por 20 filas y 6 columnas (A-F), separadas por un pasillo central.

## Funcionalidades Implementadas

### Venta de Boleto Individual
Permite reservar un asiento indicando la fila y la columna.

### Búsqueda de Boletos Contiguos
Busca y asigna automáticamente dos asientos consecutivos disponibles en una misma fila.

### Asignación Automática
Asigna un asiento libre de forma automática considerando el balance de ocupación entre ambos lados de la cabina.

### Mapa de la Cabina
Muestra el estado actual de todos los asientos del avión.

### Reporte de Vuelo
Muestra:

- Asientos ocupados
- Asientos libres
- Asientos bloqueados
- Ingresos recaudados
- Porcentaje de ocupación
- Ocupación por lado de la cabina

## Estructura de la Matriz

```java
char[][] cabina = new char[20][6];
```

Estados de los asientos:

- L = Libre
- X = Ocupado
- B = Bloqueado

## Tecnologías Utilizadas

- Java
- Apache NetBeans
- Git
- GitHub

## Historial de Funcionalidades

- Inicialización de la cabina
- Visualización de la cabina
- Reserva de asientos
- Validación de filas y columnas
- Clasificación VIP y Económica
- Bloqueo de asientos VIP
- Búsqueda de boletos contiguos
- Asignación automática
- Reporte de vuelo

## Ejecución

1. Abrir el proyecto en NetBeans.
2. Ejecutar la clase principal.
3. Utilizar el menú principal del sistema.

## Repositorio

https://github.com/bcrispin317-cyber/AsignacionAsientosVuelo