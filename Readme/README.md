# Reto JDBC - Registro de Participantes a Webinar

## 👩‍💻 Integrante

- Jessica Urrego

## 📌 Descripción del proyecto

Este proyecto consiste en una aplicación de consola desarrollada en Java para gestionar las inscripciones de participantes a un webinar corporativo.

La aplicación utiliza **JDBC** para conectarse a una base de datos **MySQL** y permite registrar, consultar, buscar, contar y eliminar participantes.

El proyecto está desarrollado utilizando el patrón de arquitectura **MVC (Modelo - Vista - Controlador)** y **Maven** para la gestión de dependencias.

---

## 🛠️ Tecnologías utilizadas

- Java
- MySQL
- MySQL Workbench
- JDBC
- Maven
- NetBeans
- Git
- GitHub

---

## 📂 Estructura del proyecto

```text
src/
└── main/
    └── java/
        ├── Modelo/
        │   ├── Clases/
        │   │   └── Participante.java
        │   │
        │   └── Persistencia/
        │       ├── ConexionBD.java
        │       └── Operaciones.java
        │
        ├── Controlador/
        │   └── ControladorParticipante.java
        │
        └── Vista/
            └── VistaParticipante.java