# IPC Quimik - Sistema de Gestión de Laboratorio

## Datos Generales

**Universidad de San Carlos de Guatemala**
**Facultad de Ingeniería**
**Escuela de Ciencias y Sistemas**
**Curso:** Introducción a la Programación y Computación 1
**Laboratorio:** Introducción a la Programación y Computación 1
**Proyecto:** Proyecto 2
**Grupo:** 10

### Integrantes

* Byron Alexander Crispín Guzmán - 202300951
* Heraldo David Escobar Rosales - 202211057

---

## Descripción del Proyecto

IPC Quimik es un sistema de gestión de laboratorio desarrollado en Java y Java Swing. El sistema permite administrar investigadores, muestras, patrones y resultados de análisis mediante el uso de operaciones con matrices.

Además, implementa:

* Interfaz gráfica con Java Swing.
* Persistencia de datos mediante serialización de objetos.
* Carga masiva de información mediante archivos CSV.
* Generación de reportes HTML.
* Concurrencia utilizando hilos para la simulación del análisis.

---

## Explicación del Patrón MVC

El proyecto se desarrolló siguiendo el patrón Modelo-Vista-Controlador (MVC) para mantener una adecuada organización del código.

### Modelo (`modelo`)

Contiene las clases que representan la información del sistema, tales como:

* Investigador
* Muestra
* Patrón
* Resultado
* SistemaLaboratorio

Estas clases almacenan los datos y atributos necesarios para el funcionamiento de la aplicación.

### Vista (`vista`)

Contiene todas las ventanas y paneles de la interfaz gráfica desarrollados con Java Swing, permitiendo la interacción del usuario con el sistema.

Entre ellas se encuentran:

* Login
* VentanaAdministrador
* VentanaInvestigador
* PanelInvestigadores
* PanelMuestras
* PanelPatrones
* PanelAnalisis
* PanelResultados
* PanelEstadisticas

### Controlador (`controlador`)

La clase `LaboratorioControlador` funciona como intermediario entre la vista y el modelo, gestionando la obtención y almacenamiento de la información del sistema.

### Utilidades (`util`)

Contiene clases de apoyo para el proyecto, entre ellas:

* Serializador
* Lectura de archivos CSV.

Esta estructura permitió mantener una mejor organización, reutilización y mantenimiento del código.

---

# Fragmentos de Código Relevantes

## Algoritmo de Procesamiento de Matrices

```java
for (int i = 0; i < n; i++) {

    for (int j = 0; j < n; j++) {

        for (int k = 0; k < n; k++) {

            mt3[i][j] +=
                    mt1[i][k] * mt2[k][j];
        }
    }
}
```

Este algoritmo realiza la multiplicación de matrices necesaria para el análisis de las muestras del laboratorio. Posteriormente se genera la matriz resultante y se compara con el patrón seleccionado para determinar si existe coincidencia.

---

## Persistencia de Datos mediante Serialización

```java
public static void guardar(SistemaLaboratorio sistema) {

    try {

        ObjectOutputStream salida =
                new ObjectOutputStream(
                        new FileOutputStream(ARCHIVO));

        salida.writeObject(sistema);
        salida.close();

    } catch (Exception e) {

        System.out.println("Error al guardar");
    }
}
```

Este método permite almacenar el estado completo del sistema en un archivo binario (`laboratorio.dat`). Gracias a la serialización de objetos, toda la información de investigadores, muestras, patrones y resultados se conserva entre ejecuciones del programa.

---

## Tecnologías Utilizadas

* Java
* Java Swing
* Programación Orientada a Objetos
* Serialización de Objetos
* Archivos CSV
* HTML
* Git
* GitHub

---

## Repositorio

Repositorio oficial del proyecto:

https://github.com/bcrispin317-cyber/IPC1V_Practica1_G10.git
