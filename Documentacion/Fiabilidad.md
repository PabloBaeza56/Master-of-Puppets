# Fiabilidad:
## Introducción:
“Si no podemos confiar en nuestros datos, ¿En quién lo haremos?”. Cuando se trata de la manipulación de datos debemos garantizar en todo momento que sean correctos y verdaderos, el detalle está en que, en sistemas basados en Web Scrapping, copiamos y pegamos datos de las páginas web por así decirlo. Lo que abre la posibilidad de que los datos (o los formatos de los datos) sean imprecisos, ante esto debemos garantizar mecanismos que permitan la veracidad de estos datos, dichos mecanismos los obtendremos a continuación.
## Contextualización:
En cierta plataforma famosa de obtención de empleo en línea, al crear tu usuario hay ciertas secciones las cuales son obligatorias, como, por ejemplo; la cabecera donde se encuentran tus datos básicos, sin embargo, hay otras secciones las cuales necesariamente no están presentes, en el caso de la mayoría de los estudiantes universitarios, muchos estudiantes no cuentan con el apartado de experiencia laboral, y no por eso el perfil es incorrecto.


También existen ciertos casos particulares, por ejemplo:


Un elemento de experiencia laboral contiene los siguientes campos:
- **Nombre empresa** (Obligatorio)
- **Nombre puesto** (Obligatorio)
- **Duración** (Obligatorio)
- **Ubicación** (Opcional)
- **Descripción** (Opcional)
- **Aptitudes** (Opcional)
- **Etc.** (Opcional)


Ahora bien, si nos encontráramos un elemento de experiencia laboral sin el campo de ubicación, realmente no habría problema alguno, en cambio sí existe la sección y existe un elemento, pero no encontramos el campo de duración; eso significa que estamos en graves problemas y que requerimos un mantenimiento correctivo inmediatamente.

## Desarrollo:
Antes de continuar se recomienda encarecidamente leer la sección anterior “Mantenimiento”, debido a que continuaremos sobre el ejemplo anterior.

Seguiremos con el ejemplo del minado, pero en esta sección nos centraremos en las siguientes clases donde se representa de la manera más clara, la fiabilidad.

- Mineable
- MandatoryElementException
- NotFoundFatalSectionException
- ScrapeableProduct
- Testeable


La clase Mineable contiene principalmente 2 tipos de métodos minarTextoObligatorio y minarTextoOpcional, los cuales como su nombre claramente indican realizan el minado según su importancia.
Supongamos que minarTextoOpcional no encuentra un campo (debido a que al ser un campo opcional es usuario no quiso ponerlo). Realmente no sucede nada y continua la ejecución normalmente. 
Supongamos que minarTextoObligatorio no encuentra un campo, en este caso fatídico se dispararía la clase de Excepción (por que hereda de java.Exception) MandatoryElementException, el cual como dice claramente su nombre se refiere a que un elemento obligatorio no ha sido encontrado. En este punto lo correcto sería parar la ejecución del programa para evitar que datos “corruptos” sean enviados a la base de datos.
Ahora bien, como mencionábamos al principio hay secciones las cuales son opcionales y otras como la cabecera son obligatorias por completo, ahora supongamos que por algún motivo no se encuentra la sección cabecera, en este caso fatídico se dispararía la clase de Excepción (por que hereda de java.Exception) NotFoundFatalSectionException, el cual como dice claramente su nombre se refiere a que una sección obligatoria no ha sido encontrado. En este punto lo correcto sería parar la ejecución del programa para evitar que datos “corruptos” sean enviados a la base de datos.

**Ahora bien, llega la pregunta lógica ¿Cómo verifico si existe la sección para empezar?**
Ese aspecto pertenece principalmente a la implementación, sin embargo, desde diseño podemos proponer una forma de manejar el problema en general. Aquí es cuando sale a relucir ScrapeableProduct.
ScrapeableProduct contiene 2 firmas de método y un método default.

-	T reclamarDatos() : El cuál es el método genérico que se encarga de devolver los métodos obtenido.
-	Boolean existeSeccion(): Es el método que verifica si existe la sección o no, y dispara la excepción NotFoundFatalSectionException en caso de ser necesario.


Es vital recordar que ScrapeableProduct es una interfaz, por lo que su implementación concreta se refleja en las clases de obtención de datos, las cuales implementan la interfaz.


Ahora bien, ¿Cómo garantizo que siempre se verifica si existe la sección, y en caso afirmativo se reclaman los datos?


La respuesta es trivial; usando funciones default en la interfaz creamos un método minarTemplate, el cual como su nombre claramente lo indica, hace uso del famoso patrón de diseño “Template”, con la pequeñísima de que, en vez de usar clases abstractas, usamos interfaces. De esta manera garantizamos que siempre se siga un cierto mecanismo de verificación de existencia.


**¿ Y Testable?**


Actualmente la clase Testeable contiene únicamente un método que está pensado para realizar pruebas rápidas para verificar el correcto funcionamiento del minado.
Pero la idea un poco más a futuro es la siguiente….
Nota: Lo que estoy a punto de escribir actualmente no tiene implementación, sin embargo, está pensado incluirse en el verano por empezar.
Como sabemos (y repetimos otra vez), el Web Scraping es extremadamente sensible para cambios externos por lo que debemos garantizar siempre que el sistema siga vigente, por lo tanto, se propone la siguiente modificación. Testeable se convertiría en una interfaz ahora con un método default, para entonces implementarse en todas las clases de minado de datos. De tal manera que el método de revisión de validez de minado de datos se podría incluir en el template “minadoTemplate”. (Una interfaz que implementa otra interfaz) De tal manera que cada vez que se ejecute un minado de datos podamos verificar si los métodos de minado siguen siendo válidos (usando un perfil prefabricado de tal manera que cumpla la mayoría de los casos). Igualmente, con eso podríamos tener un panorama global de los métodos disponibles y dañados, esto sería útil en el aspecto de mantenibilidad.


## Conclusión:
Aunque la fiabilidad en los sistemas basados en web Scrapping es más difícil alcanzar, actualmente (y a futuro), contamos con mecanismos que nos permiten verificar en todo momento la completitud y validez de todos los datos obtenidos.

