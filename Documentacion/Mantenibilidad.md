# Mantenibilidad

## Introducción

Cuando se trata de Web Scrapping, los sistemas que usan dicha técnica son bastante sensibles al cambio, debido a que usan como base el código HTML presente en la página web objetivo. Esto implica que si la página web cambia (por mínimo que se pueda apreciar visualmente), podría provocar la inutilización general del sistema, de ahí la vital importancia de que el sistema sea fácil de reparar.

Debido al extensivo tamaño del sistema, nos limitaremos a mostrar la mantenibilidad en el apartado que, a nuestro criterio, debe (y es) más mantenible: el proceso de extracción de datos.

## Contextualización

Una página en HTML no es precisamente un monolito (pensemos en un HTML común y corriente), dicha página está generalmente dividida por “secciones”, las cuales pueden o no ser visibles por el usuario. Pongamos un ejemplo simple:

![Ejemplo de perfil](images/perfil_linkedin.jpg)

En cierta plataforma de búsqueda de empleo en línea, en el perfil de cada respectivo usuario contamos con varias secciones. En el elemento proporcionado podemos apreciar las secciones “Cabecera” (Nombre informal) y “Acerca de”. Por supuesto, existen muchas más secciones (que no necesariamente poseen todos los usuarios), por cuestiones prácticas acotaremos la contextualización a estas 2 secciones de ejemplo.

Como podemos observar, cada sección contiene sus respectivos campos:

### Cabecera:
- Nombre de usuario
- Leyenda
- Ubicación
- Seguidores

### Acerca de:
- Descripción

Un detalle importante a considerar es que existen métodos para acceder directamente al bloque de sección deseado (una dirección), a este elemento se le denomina Xpath. Al acceder al bloque también puedo acceder a sus “atributos”, los cuales serían sus respectivos campos mencionados anteriormente.

## Desarrollo

Ahora procederemos a mostrar una versión parcial del diagrama de clases, centrado principalmente en el proceso de minado, por lo que para este ejemplo consideraremos despreciables los demás aspectos del proyecto.

![Diagrama de Clases](images/Diagrama_Clases.jpg)

Para apreciar mejor el diagrama es recomendable observarlo en la sección de anexo.

Tenemos como centro de todo al usuario (así debería ser siempre), el cual según el diagrama de campos contiene a los objetos experiencia laboral, educación e información personal. Dichos objetos son los que contienen la información específica de cada sección.

Igualmente, para cada clase de información le corresponde una y solo una clase de extracción de datos:
- Experiencia → ObtenerExperiencia
- Educación → ObtenerEducacion
- Datos básicos → ObtenerDatosCabecera 

Una vez llegamos a las clases de obtención de información observaremos algo bastante curioso, cada clase de minado de datos:
- Extiende de `Mineable`
- Tiene agregación con `IteradorElementoTablaWeb`

Nota: También las clases anteriormente mencionadas se relacionan con otras clases, eso sin contar las clases `Testeable`, `MandatoryElementException`, `ScrapeableProduct` y `NotFoundFatalSectionException`, las cuales analizaremos posteriormente en el atributo de calidad “Fiabilidad”.

Ahora bien, la pregunta que nos interesa responder es: ¿Dónde esta la mantenibilidad en todo esto?

![Diagrama de Clases](images/MinadoDatos.jpg)

Como se puede apreciar en este diagrama simplificado, cada “sección” es independiente a la otra, por lo que si hubiese una falla (debido a que cambió la página, y por lo tanto su Xpath), no sería un fallo catastrófico que rompiera todo el sistema. En este punto es válido cuestionarse, ¿Qué son estos puntos verdes que se encuentran dentro de las clases?. Simplemente son llamadas a métodos que van a minar campos concretos de cada sección, aquí es cuando entra `Mineable`.

### ¿Qué es Mineable y por qué las clases de obtención de datos extienden de ella?

Para empezar, tenemos que decir que `Mineable` es una clase libre de contexto. Lo que significa que independientemente de la página web en que se use, siempre va a funcionar para obtener datos (siempre y cuando se proporcionen los argumentos correspondientes).

La razón de que extiendan de esta clase es que la relación a utilizar es demasiado fuerte (además de que es la única clase del programa autorizada para hacer Web Scrapping).

Nota: Analizaremos el minado Obligatorio y Opcional en “Fiabilidad”.

Ahora bien, como podemos observar, todas las clases contienen un método llamado `reclamarDatos` (debido a que todas las clases extienden de `ScrapeableProduct`). La razón de eso es que, dentro de ese método, en teoría, realices las llamadas de los métodos de `Mineable`, para que en cada método mines, única y exclusivamente un campo. Esto bajo la idea de que, si llega a fallar el minado de un campo, únicamente tengas que cambiar el `CSSelector` del método o en el peor de los casos el elemento base (El Xpath de la sección).

Bajo esta estructura garantizamos que cada elemento es autónomo y no debe depender de otro elemento más que el `elementoBase` (El Xpath de la sección).

### ¿Qué es IteradorElementoTablaWeb y por qué posee agregación con las clases de obtención de datos?

Como podremos observar, existen secciones que son básicamente una tabla, por ejemplo, “educación” y “experiencia”. Por lo tanto, requerimos iterar sobre ellas. Es aquí cuando empleamos nuestro confiable patrón de diseño Iterador (`IteradorElementosListaWeb`).

Ahora bien, la razón por la que empleamos la agregación es debido a que la forma de iterar una tabla puede cambiar y, por lo tanto, su implementación. Al usar agregación, nos proporciona un bajo acoplamiento que puede ser aprovechado en dichos casos. Simplemente tendríamos que intercambiar la clase, recordando implementar la interfaz correspondiente.

## Conclusión

Con los mecanismos anteriormente mencionados garantizamos que, ante cualquier falla causada por el eventual “aging” del sistema o la existencia de algún cambio en el HTML de la página objetivo, el mantenimiento correctivo sea lo más transparente y sencillo posible. Debido a la independencia de obtención de datos de cada campo perteneciente a una sección específica.
