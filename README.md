# IPC Quimik - Laboratory Management System

## Overview

IPC Quimik is a laboratory management system developed in Java and Java Swing. The application allows researchers and administrators to manage samples, patterns, and analysis results through matrix operations.

The project follows the Model-View-Controller (MVC) architecture and implements data persistence, CSV processing, HTML report generation, and concurrent execution using threads.

---

##  Features

- Researcher management
- Sample management
- Pattern management
- Matrix-based analysis processing
- CSV bulk import
- HTML report generation
- Data persistence through serialization
- Concurrent analysis simulation using threads
- Desktop graphical interface with Java Swing

---

##  Technologies Used

- Java
- Java Swing
- Object-Oriented Programming (OOP)
- Model-View-Controller (MVC)
- CSV File Processing
- Object Serialization
- HTML
- Git & GitHub

---

##  Project Architecture

### Model (`modelo`)
Contains the entities and business logic:

- Investigador
- Muestra
- Patrón
- Resultado
- SistemaLaboratorio

### View (`vista`)
Contains the graphical user interface developed with Java Swing:

- Login
- VentanaAdministrador
- VentanaInvestigador
- PanelInvestigadores
- PanelMuestras
- PanelPatrones
- PanelAnalisis
- PanelResultados
- PanelEstadisticas

### Controller (`controlador`)
Coordinates the interaction between the views and the model through:

- LaboratorioControlador

### Utilities (`util`)
Helper classes used throughout the application:

- Serializador
- CSV Reader

---

##  Algorithms Implemented

### Matrix Multiplication

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
            mt3[i][j] += mt1[i][k] * mt2[k][j];
        }
    }
}
```

Used to process sample matrices and compare them with selected patterns.

---

### Object Serialization

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

Allows the complete system state to be persisted between executions.

---

##  Main Functionalities

- User authentication.
- Researcher administration.
- Sample management.
- Pattern management.
- Matrix analysis.
- Result generation.
- Statistical reports.
- Data persistence.
- CSV import.

---

## Documentation

Detailed screenshots, user instructions, and examples of system usage can be found in the User Manual included in this repository.

- `Manual de Usuario.pdf`

---

##  Getting Started

1. Clone the repository:

```bash
git clone https://github.com/bcrispin317-cyber/ipc-quimik-laboratory-management.git
```

2. Open the project in Apache NetBeans.
3. Build the project.
4. Run the main application.

---

## Authors

- Byron Alexander Crispín Guzmán
- Heraldo David Escobar Rosales

---

## Collaboration

This project was developed collaboratively using Git and GitHub. Multiple commits were made throughout development to implement features, fix bugs, and improve the system.

---

## Academic Context

This project was originally developed as an academic project for the School of Computer Science and Systems Engineering at the Universidad de San Carlos de Guatemala and is maintained as part of our software development portfolio.

---

## License

This project is intended for educational and portfolio purposes.
