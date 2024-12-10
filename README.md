# AGENTES Y MISIONES

## Descripción

Esta API REST consiste en una serie de Agentes a los que se les asignan misiones. Los agentes tendrán que completar 
las misiones a contra-reloj para poder cobrar la recompensa.

## Justificación

Esta API REST puede ser usada tanto por organizaciones que velan por la paz como los Vengadores o los X-men como una empresa que quiere 
asignar tareas críticas a sus empleados. Por ejemplo cada empleado tiene una tarea que realizar y si consigue completarla en el tiempo indicado
consigue una recompensa.
También puede ser usada con fines educativos, recibiendo los estudiantes tareas o proyectos que tienen que completar para obtener recompensas
académicas.


## Entidad Relación 
![Entidad Relacion](./imgs/entidadRelacion.png)

## Paso a tablas
![Paso a Tablas](./imgs/pasoATablas.png)

## Tablas

Agente: El agente tiene un nombre, nombre en clave ( ID ), rol, password y bounty ( bounty seria el dinero total de las misiones realizadas ).

Misiones: La misión tiene un ID, nombre de misión, lugar, tipo de misión ( Enumerado que define el tipo de mision ya sea rescate, espionaje o desmantelar algo ), descripcion de la misión y la recompensa.

Asignacion-mision-agente: La asignacion tiene un agente, una mision, tiempo para completarla y un estado ( Enumerado que define el estado de la misión, completada, en proceso, mision fallida ).


### Agente
    nombre: String
    nombreEnClave: String ( id )
    password: String
    rol: String
    bounty: double

### Misiones
    id: Long
    nombre: String
    lugar: String
    tipo: Enum (?)
    descripcion: String
    recompensa: double

### Asignacion
    agente: Agente
    mision: Mision
    tiempoParaCompletarla: (por determinar)
    estado: Enum (?)

## Lógica de negocio y Restricciones semánticas

Un agente puede participar en varias misiones, si tiene una mision asignada no puede hacer otra.

A una mision solo se le asigna un agente, si el agente está en una mision no puede ser asignado.

Si un agente consigue completar la mision, se queda con la recompensa.

Si pasa el tiempo de la mision y no ha sido completada, pasa automaticamente a mision fallida.


# Endpoints

## Endpoints para agentes

* POST /agentes/login: Permite acceder al agente al sistema.
  - Ruta pública, todas las peticiones se permiten.
  - Entrada: JSON con nombre de agente y password.
  - Salida: Token de ajente si accede correctamente.
  
* POST /agentes/register: 
