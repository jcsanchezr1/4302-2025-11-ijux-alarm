# IjuxAlarm

IjuxAlarm es la parte de la aplicación web de la plataforma IjuxAlarm, enfocada en la administración y gestión de la aplicación móvil. Esta app está orientada a la creación de alarmas para tratamientos médicos, control de sueño y reuniones. Desde la interfaz web, se manejan funcionalidades administrativas como la creación de usuarios, recuperación de contraseñas y la gestión de alarmas, permitiendo listar, habilitar y deshabilitar alarmas.

## Prerrequisitos

Antes de comenzar, asegúrate de tener instalados los siguientes requisitos:

- Intellij IDE configurado y actualizado

- Bootstrap para el diseño responsivo

- nvm (Node Version Manager) para gestionar las versiones de Node.js

- Angular CLI para el desarrollo en Angular

## Instalación

Sigue estos comandos para instalar y configurar el entorno:

### Configurar NVM

```
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
```
### Verificar la versión de NVM
```
nvm --version
```

### Instalar Node.js 18.17.1
```
nvm install 18.17.1
```

### Instalar la última versión de Node.js
```
nvm install node
```

### Usar la versión 18.17.1
```
nvm use 18.17.1
```

### Listar las versiones instaladas de Node.js
```
nvm list
```

### Instalar Node.js 20
```
nvm install 20
```

### Usar la versión 20
```
nvm use 20
```

### Instalar Angular CLI versión 22.5.1 de forma global
```
npm install -g @angular/cli@22.5.1
```

### Mostrar todas las versiones disponibles de Angular CLI
```
npm show @angular/cli versions --json
```
### Instalar la última versión de Angular CLI de forma global
```
npm install -g @angular/cli@latest
```

### Ejecutar la aplicación Angular
ng serve

**Nota:**  Accede a la aplicación en tu navegador en `http://localhost:4200/`

## Módulos y Pantallas

### Pantallas y Módulos realizados por `Ian Pablo Beltrán Moreno`

- **login:** Pantalla de inicio de sesión
- **recuperar-contrasena:** Pantalla para recuperación de contraseña donde solicita el email
- **registrar-usuario:** Pantalla para el registro de nuevos usuarios

### Pantallas y Módulos realizados por `Julio Sanchez`

- **habilitar-deshabilitar:** Funcionalidad para habilitar y deshabilitar alarmas
- **listar-alarmas:** Visualización y listado de alarmas existentes
- **recuperar-contrasena-nueva:** Pantalla para restablecer la contraseña donde solicita las nuevas contraseñas

#### Módulo Transversal

- **sidebar:** Barra lateral de navegación presente en las pantallas cuando esta autenticado el usuario
- **estadisticas:** Módulo deshabilitado, no forma parte de las seis pantallas principales
