# Aplicación de Gestión de la Isla de Will

## 1. Descripción del proyecto

Este proyecto es una **aplicación de gestión de invitados y empleados de la Isla de Will**, desarrollada en **Java** con **JavaFX** y gestionada mediante **Maven**.

La aplicación permite:
- Visualizar la lista de invitados y empleados.
- Aplicar filtros sobre los datos.
- Mostrar estadísticas relevantes.
- Gestionar la información desde una interfaz gráfica.

El proyecto está organizado siguiendo una estructura clara que separa la lógica de negocio, la interfaz gráfica y la configuración del proyecto.

## 2. Explicación general del código

- **Modelo**: Clases que representan invitados y empleados, entre otros.
- **Controladores**: Gestionan la lógica y la interacción entre los datos y la interfaz.
- **Vistas (JavaFX)**: Interfaz gráfica de la aplicación.
- **Maven (`pom.xml`)**: Gestión de dependencias y configuración del proyecto, incluyendo JavaFX.

Esta estructura facilita el mantenimiento y la ampliación de la aplicación.

## 3. Tutorial de importación del proyecto (Maven + JavaFX)

### Requisitos

- **Java JDK 11 o superior**
- **JavaFX 13**
- **Eclipse IDE** (u otro IDE compatible con Maven)

> JavaFX 13 es compatible con Java 11 en adelante.

### Pasos para importar el proyecto en Eclipse

1. Abrir Eclipse.
2. Ir a **File → Import…**
3. Seleccionar **Maven → Existing Maven Projects**.
4. Pulsar **Next**.
5. Seleccionar la carpeta raíz del proyecto (donde está el `pom.xml`).
6. Pulsar **Finish**.
7. Esperar a que Maven descargue las dependencias.

Una vez completado el proceso, el proyecto quedará listo para ejecutarse.

---

## 4. Solución de problemas con Maven en Eclipse

Si al clonar o descargar el proyecto Eclipse muestra errores de Maven, normalmente se debe a que las dependencias no se han descargado correctamente.

### Forzar actualización de Maven

Si ocurre alguno de los siguientes casos:
- Dependencias no encontradas.
- El proyecto aparece con errores.
- El `pom.xml` no se resuelve correctamente.

Haz lo siguiente:

1. Clic derecho sobre el proyecto.
2. **Maven → Update Project…**
3. Marcar **Force Update of Snapshots/Releases**.
4. Pulsar **OK**.
5. Esperar a que se descarguen las dependencias.

### Nota adicional

Si el problema continúa:
- Ejecutar **Project → Clean…**
- Verificar que Eclipse usa un **JDK** y no un JRE.
- Comprobar que el `pom.xml` no tenga errores de sintaxis.
