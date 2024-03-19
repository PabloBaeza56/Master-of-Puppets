# ADA 2.1. Atributos de Calidad en el Diseño del Software

## Usabilidad

## Modelo de Calidad Elegido
Antes de definir la usabilidad como atributo de calidad, es importante mencionar la elección del modelo de calidad que permitirá darle un mejor significado y contextualización a este concepto. Por este motivo explicaremos el propósito y características del **_modelo de calidad de McCall_**.

En ese entonces, era muy común que se concentrara en los requisitos funcionales del software, los cuales son fáciles de definir, medir y observar; sin embargo en cuanto a términos de calidad se inclinaba mucho a lo subjetivo. La calidad de un sistema era juzgado cuando entraba en funcionamiento. Es en este momento cuando el modelo de calidad de McCall surge por la necesidad de medir y calificar la calidad de un producto de software de manera cuantitativa y sin caer en la subjetividad.

El propósito general de este modelo es proveer una guía sobre cómo especificar y medir objetivamente el conjunto de atributos de calidad de un sistema, especificado en la fase de requerimientos, durante el proceso de diseño del producto de software.

Entre las principales características y ventajas del modelo de McCall están:
* Proveer un panorama general de la calidad del software.
* Proveer metodologías para validar métricas.
* Definir métricas que permitan una programación más independiente.
* Identificar métricas que puedan ser aplicadas en la fase de desarrollo del producto (análisis y diseño).
* Tratar de seer independiente y no tan ambiguo.
* Eliminar la subjetividad de la calidad del producto. 

## ¿En Qué Consiste la Usabilidad?
McCall define la usabilidad como el esfuerzo requerido para aprender, operar, ingresar entradas e interpretar la salida de un programa. Trata de responder a la pregunta *_"¿Puedo ejecutarlo?"_*. Asímismo, consta de varios aspectos:
* **_Operabilidad:_** atributos que determinan la facilidad de operación del software.
* **_Comunicación:_** atributos de software que proporcionan entradas y salidas fácilmente asimilables.
* **_Aprendizaje:_** atributos que facilitan la familiarización inicial del usuario con el software y la transición del modo actual de operación.

## Caso Real Donde se Encuentra la Usabilidad en el Diseño de Software
A continuación se hará un mención del proceso de diseño de un software matemático para el desarrollo de pensamiento matemático en un aula de clase y la metodología que siguieron para la usabilidad de este.
En pocas palabras, el software consiste en una aplicación que proponga ejercicios matemáticos a estudiantes utilizando los modelos de aprendizaje *_Problem Based Learning_* y orientado al logro.

### Actividades
De manera general, el grupo de trabajo realizó las siguientes **_actividades_**:
* Estudio de las herramientas actuales, es decir, sistemas similares al deseado, para generar una comparación y realizar una propuesta de mejora.
* Aplicación de un cuestionario o diagnóstico para determinar la importancia que tiene el nivel de dificultad de los problemas y descartar que el logro estuviera relacionado con la usabilidad de la herramienta.
* Aplicación de un test para medir la experiencia del usuario con la nueva herramienta.

### Métodos
Se emplearon dos **_métodos_** para evaluar la usabilidad del producto:
* Usabilidad por medio de parámetros de diseño o heurísticas como las propuestas por Nielsen.
* Usabilidad en base a la realización efectiva de ciertas metas o tareas propuestas por Wharton (Cognitive Walkthrough).

### Proceso
El proceso se dividió en 5 partes:
1. **_Aplicación de cuestionarios preeliminares:_**

Se creó un cuestionario, el cual fue aplicado a un grupo experimental, y siguió un modelo de aprendizaje distinto al planteado al inicio. Esto con el propósito de obtener datos para poder comparar en el futuro del proceso.

2. **_Creación de los problemas:_**

Comenzó con el estudio de las aplicaciones ya disponibles en el mercado que implementaran la metodología de aprendizaje propuesta. Luego se optó por utilizar un instrumento de  medición y calificación en ciencias y matemáticas, específicamente la prueba *_Trends in International Mathematics and Science Study_* (TIMSS). De esta manera, se usaron como base un set de problemas propuestas en la prueba y se procedió a implementar prototipos y testearlos en una muestra para afinar la usabilidad del producto final.

3. **_Desarrollo de prototipos:_**
 
Se terminó de definir los problemas que calzaran como contenido para los usuarios finales. Luego se necesitó organizar y estructurar las actividades de la apliación para poder realizar maquetas alineadas con la usabilidad y experiencia del usuario. Para evaluar los diseños preeliminares, se emplearon las heurísticas anteriormente mencionadas.

4. **_Aplicación de pruebas:_**

Se aplicó un cuestionario de 8 preguntas, similares a los de la aplicación, en la que los alumnos debían resolverlos y explicar la estrategia que utilizaron para llegar a la respuesta, esto con el propósito de determinar si la experiencia del usuario representó un sesgo en el aprendizaje del alumno.

5. **_Testeo en conjunto:_**

Empezó con la creación de la aplicación tomando en cuenta los resultados de test anteriores y prototipos. Luego se puso a prueba la aplicación en un grupo de una escuela a modo de testeo piloto del software. Para medir y evaluar resultados se utilizó una rúbrica (lo que permitió evaluar la eficiencia, eficacia y satisfacción del software). Posteriormente, se realizaron las correcciones necesarias, según la rúbrica y resultados obtenidos.


## Proceso Que Se Seguirá en el Proyecto
Ya hemos explicado los atributos de los que la usabilidad consta, así como un ejemplo de éste en el proceso de diseño del software. Ahora, abordaremos la usabilidad en el contexto de STEAM-RADAR y explicaremos su proceso en el diseño del software.

### Métodos
* **_Heurísticas de JaKob Nielsen_**

Serie de patrones que el diseño de un sistema debe cumplir. A partir de principios y guías ayuda a evaluar y medir la usabilidad de la interfaz de un sistema.

* **_Cognitive Walkthrough de Wharton_**

Identifica problemas de usabilidad por medio de la evaluación de uso que dan al sistema los nuevos usuarios. Para esto, mide la usabilidad de la interfaz por medio de la facilidad de uso que ésta tiene. Es un modelo basado en posibles tareas que desarrolla el usuario.

El uso de ambos métodos se complementan el uno al otro, pues las heurísticas de Nielse no toman en cuenta la interfaz como influencia para resolver problemas o realizar procedimientos. Asimismo, el método de Wharton no provee detalles de qué es lo 
que hace a una acción más visible a los usuarios y qué tipos de acciones consideran más los usuarios al momento de realizar la tarea. De esta manera, combinar estos métodos permiten medir la eficiencia, eficacia y satisfacción de un software.

### Métricas
Para definir algunas métricas para la usabilidad de STEAM-RADAR utilizaremos los atributos del cual consiste este concepto según McCall:

| Criterio | Métrica |
|--------------|--------------|
| Operabilidad/Desempeño   | Tiempo para completar una tarea    |
| Operabilidad/Desempeño   | Número y porcentaje de tareas completadas sin asistencia    |
| Operabilidad/Desempeño   | Número y porcentaje de tareas completadas incorrectamente    |
| Operabilidad/Desempeño   | Número de selecciones incorrectas    |
| Operabilidad/Desempeño   | Tiempo necesario para recuperarse de errores    |
| Aprendizaje   | Tasa de errores en usuarios nuevos    |
| Aprendizaje    | Tiempo que le toma al usuario en aprender el sistema en un nivel considerable (50% de las operaciones del sistema)    |

### Cuestionarios de Satisfacción
Otra manera de medir la usabilidad es a través de cuestionarios de satisfacción que permitan ver la manera en la que los usuarios interactuaron y experimentaron con el sistema y de esta manera determinar la facilidad de uso del sistema.

### Pruebas de Usabilidad
A través de una muestra significativa de nuestros usuarios, ofrecerles el uso del software para poder analizar el comportamiento de los usuarios con el sistema, permitiendo identificar problemas de usabilidad y mejorar la experiencia del usuario.
La intención sería realizar pruebas presenciales donde el equipo esté en contacto directo con el usuario.