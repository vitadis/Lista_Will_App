README — Solución a problemas de Maven en Eclipse
Si al clonar o descargar este proyecto desde GitHub Eclipse muestra errores relacionados con Maven, es muy probable que el IDE no haya descargado correctamente las dependencias o no haya actualizado el proyecto.

A continuación se explica cómo solucionarlo fácilmente.

🔧 Forzar actualización de Maven en Eclipse
Si Eclipse muestra errores como:

Dependencias que no se encuentran

El proyecto aparece con el icono de error

El pom.xml no parece resolverse correctamente

Entonces debes forzar una actualización del proyecto Maven.

Pasos para actualizar Maven en Eclipse
Haz clic derecho sobre el proyecto en el Package Explorer.

Selecciona:
Maven → Update Project…

En la ventana que aparece, marca:
Force Update of Snapshots/Releases

Pulsa OK.

Espera a que Eclipse descargue todas las dependencias y reconstruya el proyecto.

Esto debería resolver cualquier problema de configuración o dependencias pendientes.

Nota adicional
Si el problema persiste, también puedes probar:

Project → Clean… y reconstruir el proyecto.

Verificar que Eclipse tiene configurado un JDK y no solo un JRE.

Comprobar que el archivo pom.xml no contiene errores de sintaxis.
