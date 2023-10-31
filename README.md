# Equipo-Streams

Integrantes:
 
-> Juan Carlos Tovar Rosas
-> Ernesto Morales Ramos
-> Edgar Uriel Zaldivar Ramirez
 
### Proyecto de microservicios orientado a las consultas medicas de los pacientes de una clinica
 
## Herramientas:
 
Maven

Java 8

Springboot

## Antes de Despliegue:

1- Tener instalado en el equipo local Maven versión 3 o superior.

2- Tener instalado en el equipo el jdk 8 o superior.
 
## Despliegue:
 
1- Clonar el repositorio a su maquina local utilizando el comando "git clone" y la url del código fuente en la lína de comandos CMD donde se quiera clonar el proyecto, en cuyo caso quedaría - git clone https://github.com/SabadellMinsait/Equipo-Streams.git

    Ejemplo: C:\Projects Java>git clone https://github.com/SabadellMinsait/Equipo-Streams.git

2- Una vez teniendo el proyecto clonado, en la misma línea de comandos CMD, se dirigirá a la dirección del proyecto y a su vez a la dirección de cada microservicio.

    Ejemplo: C:\Projects Java\Equipo-Streams\consultas>

3- Se ejecutará el comando de maven "mvn clean package", el cuál creará un directorio llamado target en la carpeta raíz del microservicio.

    Ejemplo: C:\Projects Java\Equipo-Streams\consultas>mvn clean package

4- Se ubicará en la dirección de los microservicios faltantes de ejecutar el comando maven y se volverá a ejecutar "mvn clean package".

5- Una vez teniendo las carpetas target en cada microservicio, se abrirán 3 líneas de comando CMD y se ubicará en la raíz de cada uno de los proyectos hasta la nueva carpeta llamada target.

    Ejemplo: C:\Projects Java\Equipo-Streams\doctores\target>

6- En cada CMD se ejecutará la siguiente instrucción: 

    Ejemplo: C:\Projects Java\Equipo-Streams\doctores\target>java -jar pacientes-0.0.1-SNAPSHOT.jar
    Ejemplo: C:\Projects Java\Equipo-Streams\doctores\target>java -jar consultas-0.0.1-SNAPSHOT.jar 
    Ejemplo: C:\Projects Java\Equipo-Streams\doctores\target>java -jar Doctores-0.0.1-SNAPSHOT.jar 
 
7- Se levantará cada microservicio en cada CMD y para poder ver desplegadas en local los microservicios se verá las siguientes rutas:

### Microservicio consultas
http://localhost:8091/swagger-ui/index.html#/

### Microservicio doctores
http://localhost:8092/swagger-ui/index.html#/

### Microservicio pacientes
http://localhost:8080/swagger-ui/index.html#/


## Descripción de microservicios de proyecto Consulta Médica: 
  - Microservicio consulta: este microservicio consta de crear las consultas médicas teniendo conexión con los microservicios de Pacientes y Doctores, se validan que existan los ids correspondientes a meter en la consulta 
  para que una vez validados se puedan insertar, cuenta con 9 endpoints tanto para eliminar historial y consulta, así como también para posterar y obtener datos de consultas e historial. Cuentan con sus respectivas 
  validaciones para que no se inserte o eliminen elementos erroneos o que no existen.

  - Microservicio pacientes: En esta API se gestiona el control de los pacientes de sus datos generales y domicilio guardando la información en su tabla correspondiente manejando una relación de uno a uno, aquí se alojan la siguientes funcionalidades:
    
      •	Creación de un nuevo paciente y domicilio.
    
      •	Modificación de los datos de paciente y domicilio.
    
      •	Búsqueda total de pacientes.

      •	Búsqueda de paciente por nombre.
    
      •	Búsqueda de paciente por id.

      •	Búsqueda de consultas de paciente, en este endpoint podremos visualizar la información del paciente, así como las consultas que haya tenido y un resumen total de cuantas ha tenido.
    
      •	Eliminación de paciente por id, en este endpoint se implementó el control de eliminación de pacientes que ya tienen consultas generadas, generando eliminación de estas para no dejar inconsistencia de información.

  - Microservicio doctores: Crear, actualizar, eliminar,listar a cada uno de los doctores de la clinica y sus especialidades y poder ser asignados a una consulta.
