# IjuxAlarm

IjuxAlarm es la parte de la aplicación mobile de la plataforma IjuxAlarm, enfocada en la
administración y gestión de alarmas. Esta app está orientada a la creación de alarmas para
tratamientos médicos, control de sueño y reuniones. Desde la app móvil, se manejan funcionalidades
como la creación de usuarios, listar alarmas, y crear los tres diferentes tipos de alarmas.

## Prerrequisitos Generales

Antes de comenzar, asegúrate de tener instalados los siguientes requisitos:

- Java 8
- Gradle 8.7
- Android Studio (Koala Feature Drop | 2024.1.2) o una versión más reciente

## Opción 1: Construcción y ejecución de la aplicación de forma local

1. Clonar [repositorio](https://github.com/jcsanchezr1/4302-2025-11-ijux-alarm)
2. Abrir el proyecto con Android Studio
3. Sincronizar el archivo `build.gradle` para establecer las versiones adecuadas de los sistemas en
   su máquina y configurar el entorno para construir la aplicación.
4. Ejecute la aplicación mediante alguna de las siguientes opciones:

- **En el emulador de Android Studio:** Ejecutar la aplicación en un emulador de Android desde
  Android Studio. Crear y seleccionar el emulador, y luego hacer clic en el botón `Run App`.

- **En un dispositivo Android:** También puede utilizar un dispositivo Android real como emulador
  conectándolo a su máquina. Una vez conectado, seleccione el dispositivo y haga clic en el botón `
  Run App` en Android Studio para iniciar la aplicación.

**Nota:** Para la ejecución en un dispositivo Android se debe habilitar la opción de modo
desarrollador.

## Opción 2: Ejecución de la aplicación (APK)

1. **Descargar el APK:** Obtener el archivo APK de la aplicación, ubicado en el directorio raíz del
   repositorio [APK](https://github.com/jcsanchezr1/4302-2025-11-ijux-alarm/blob/main/mobile/app-ijux-alarm.apk).
2. **Transferir el APK al dispositivo:** Conectar el dispositivo Android a la computadora mediante
   un cable USB y copiar el archivo APK al almacenamiento del dispositivo. Alternativamente, enviar
   el APK al dispositivo a través de correo electrónico o un servicio de almacenamiento en la nube.
3. **Habilitar la instalación de aplicaciones desconocidas:** En el dispositivo, acceder a
   Configuración > Seguridad (o Aplicaciones, según el modelo) y activar la opción para permitir
   la instalación de aplicaciones de fuentes desconocidas.
4. **Instalar la aplicación:** Localizar el archivo APK en el dispositivo, abrirlo y seguir las
   instrucciones para instalarlo.
5. **Ejecutar la aplicación:** Una vez instalada, abrir la aplicación desde el menú de aplicaciones
   del dispositivo para comenzar a usarla.

## Módulos y Pantallas

### Pantallas y Módulos realizados por `Julio Cesar Sanchez Rodriguez`

- **Login:** Pantalla de inicio de sesión
- **Registrar usuario:** Pantalla para el registro de nuevos usuarios
- **Listar alarmas:** Visualización y listado de alarmas existentes

### Pantallas y Módulos realizados por `Ian Pablo Beltrán Moreno`

- **Crear alarma de tratamiento:** Formulario para creación de alarma de tratamiento 
- **Crear alarma de reunión:** Formulario para creación de alarma de reunión 
- **Crear alarma de sueño:** Formulario para creación de alarma de tratamiento 
