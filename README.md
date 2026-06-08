# Sistema de Asignación de Asientos - AERO-USAC

## Integrantes

* Byron Alexander Crispin Guzmán - 202300951
* Heraldo David Escobar Rosales - 202211057

**Grupo:** 12

---

## Descripción

AERO-USAC es un sistema desarrollado en Java que permite administrar la asignación de asientos dentro de una aeronave comercial. La aplicación utiliza matrices bidimensionales para representar la cabina del avión y gestionar la disponibilidad de los asientos.

El sistema fue desarrollado como parte de la Práctica 1 del curso Introducción a la Programación y Computación 1 de la Universidad de San Carlos de Guatemala.

La cabina está formada por 20 filas y 6 columnas (A-F), separadas por un pasillo central, para un total de 120 asientos disponibles.

---

## Funcionalidades Implementadas

### Venta de Boleto Individual

Permite reservar un asiento indicando la fila y la columna deseada. El sistema valida que el asiento exista y que se encuentre disponible antes de realizar la reservación.

### Búsqueda de Boletos Contiguos

Busca y localiza automáticamente dos asientos consecutivos disponibles dentro de una misma fila, respetando el pasillo central de la aeronave.

### Asignación Automática

Asigna un asiento libre de forma automática considerando el balance de ocupación entre ambos lados de la cabina para mantener una distribución equilibrada de pasajeros.

### Zona VIP

Las filas 1 a 5 corresponden a Primera Clase. Cuando un asiento VIP es reservado, los asientos adyacentes pueden bloquearse automáticamente para proporcionar mayor comodidad y privacidad.

### Mapa de la Cabina

Permite visualizar en tiempo real el estado actual de todos los asientos de la aeronave.

### Reporte de Vuelo

Genera estadísticas relacionadas con:

* Asientos ocupados.
* Asientos libres.
* Asientos bloqueados.
* Ingresos recaudados.
* Porcentaje de ocupación.
* Ocupación por lado de la cabina.

---

## Distribución de la Cabina

```text
A B C || D E F
```

Donde:

* A, B y C corresponden al lado izquierdo.
* D, E y F corresponden al lado derecho.
* El símbolo `||` representa el pasillo central.

---

## Estructura de la Matriz

```java
char[][] cabina = new char[20][6];
```

Estados de los asientos:

| Estado | Descripción       |
| ------ | ----------------- |
| L      | Asiento Libre     |
| X      | Asiento Ocupado   |
| B      | Asiento Bloqueado |

---

## Tecnologías Utilizadas

* Java
* Apache NetBeans
* Git
* GitHub

---

## Historial de Funcionalidades

* Inicialización de la cabina.
* Visualización de la cabina.
* Reserva de asientos.
* Validación de filas y columnas.
* Clasificación VIP y Económica.
* Bloqueo automático de asientos VIP.
* Búsqueda de boletos contiguos.
* Asignación automática de asientos.
* Generación de reportes de vuelo.
* Documentación mediante README.md.

---

## Ejecución

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en Apache NetBeans.
3. Compilar el proyecto.
4. Ejecutar la clase principal.
5. Utilizar el menú principal del sistema.

---

## Capturas del Sistema

### Menú Principal

*(Agregar captura aquí)*

### Venta de Boletos

*(Agregar captura aquí)*

### Búsqueda de Boletos Contiguos

*(Agregar captura aquí)*

### Asignación Automática

*(Agregar captura aquí)*

### Reporte de Vuelo

*(Agregar captura aquí)*

---

## Control de Versiones

El proyecto fue desarrollado utilizando Git y GitHub para facilitar el control de versiones, el seguimiento de cambios y el trabajo colaborativo entre los integrantes del grupo.

---

## Repositorio

https://github.com/bcrispin317-cyber/AsignacionAsientosVuelo


