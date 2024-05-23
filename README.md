# Instrucciones para la configuración de la base de datos

## Paso 1: Ejecutar el script en MySQL

Primero, ejecuta el siguiente script en MySQL:

```sql
drop database if exists `Sample`;
create database `Sample`;
use `Sample`;

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

create user 'acme-user'@'%' 
	identified by 'ACME-Us3r-P@ssw0rd';

create user 'acme-manager'@'%' 
	identified by 'ACME-M@n@ger-6874';
    
    grant select, insert, update, delete 
	on `Sample`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Sample`.* to 'acme-manager'@'%';
```
## Paso 2: Ejecutar el fichero java para realizar precarga de datos
Definir los roles: 
AcmeDancer\src\main\java\security\Authority.java

Ruta archivo populate:
AcmeDancer\src\main\resources\PopulateDatabase.xml

## Paso 3: Asegurarse que las querys en los archivos de repository son correctos
Probar querys ejecutando el fichero queryDatabase de la ruta: 
AcmeDancer\src\main\java\utilities\QueryDatabase.java

Ejemplo de introduccón: select c from Alumno c where c.userAccount.id = 38;
*Importante introducir al final de la query punto y coma ; 

## Paso 4: Vistas:

### 4.1: Definir las rutas a las que tienen acceso los roles en el archivo:
AcmeDancer\src\main\resources\spring\config\security.xml

### 4.2: Definir archivos de variables de tiles en el archivo: 
AcmeDancer\src\main\resources\spring\config\tiles.xml

### 4.3: Definir archivos de variables de traducción de messages en el archivo: 
AcmeDancer\src\main\resources\spring\config\i18n-l10n.xml

## Paso 5: Cada vista tiene que tener su controlador
Al crear un nuevo controlador para una vista, se tiene que reiniciar servidor.

