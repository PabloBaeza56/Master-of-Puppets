# Extensibilidad

## Introducción
Las casas de un piso están preparadas (no todas) para extenderse, tienen la capacidad estructural y arquitectónica para que sea introducido un segundo piso (inclusive un tercero). Por otro lado, nuestros programas, al igual que dichas casas, deben ser capaces de crecer sobre la misma base siempre con la seguridad de que no se van a desmoronar.

## Definición
McCall define la extensibilidad como a facilidad de hacer cambios necesarios según lo solicitado. Trata de responder a la pregunta *_"¿Puedo modificarlo?"_*. Asímismo, consta, principalmente, de dos aspectos:

* Modularidad
* Capacidad de expansión

## Contextualización en el Proyecto
En el proyecto *STEAM RADAR* hablaremos de extensibilidad en el apartado de minado de datos del sistema de Web Scrapping.

> **NOTA**: Es importante haber leído antes el apartado de [Manteniblidad](Mantenibilidad.md) para una correcta comprensión de la situación del problema.

## Desarrollo
Recapitulemos el diagrama anterior. Más información en [Mantenibilidad](Mantenibilidad.md):

![Diagrama de Clases](images/MinadoDatos.jpg)

Recordemos que los círculos más grandes son las clases y los círculos verdes son llamadas de métodos a la clase `Mineable` (dentro del método `reclamarDatos`), esto con la finalidad de que para cada sección se pueda extraer un campo concreto usando su respectivo método `Mineable`.

Para poder observar la extensibilidad en *STEAM RADAR* consideremos los siguientes casos:

### Caso 1: Se agrega un nuevo campo a *Obtener educación*
La solución en este caso sería trivial, únicamente tendríamos que agregar una llamada adicional a `Mineable`, dentro del método `reclamarDatos`.

![Caso1](images/DiagramaExt_Caso1.jpeg)

### Caso 2: Se agrega una nueva sección *Pasatiempos*
Para este caso, tendríamos que agregar una nueva clase que debería extender de `Mineable` e implementar `ScrapablePoduct`, así como agregar `IteradorElementoTablaWeb`. No obstante, **¿qué sucedería con `Usuario`?**

Ahí está el detalle, `Usuario` además de ser una clase de datos también contiene internamente una clase estática, con la finalidad de implementar el reconocido patrón de diseño **Builder**, de esta manera podemos ensamblar al complejo objeto usuario sin preocuparnos de cómo vamos a manejar la adición de nuevos datos.

Cabe aclarar que para fines del programa el patrón **Builder** es más adecuado, pero supongamos el caso en que se le permita al usuario seleccionar los campos que desea minar. En este caso, el patrón **Builder** sería completamente inútil, ya que tendríamos que contemplar una infinidad de casos de llamadas a **Builder**. Por lo tanto, en esta situación hipotética lo más adecuado sería implementar el patrón de diseño **Decorador** para permitirle al usuario hacer las *n* combinaciones que dicho requiera, permitiendo evitar el código duplicado y permitiendo al programa ser más conciso y flexible.

![Caso1](images/DiagramaExt_Caso2.jpeg)

### Caso 3: Se desea agregar un objeto usuario, pero de una plataforma *Y*
Esta situación podría darse en algún punto. Nuestra solución se denomina STEAM-RADAR (y no está fijada a una plataforma), debido a que en un futuro se tiene pensado implementar un minado en una plataforma igualmente útil. 

Si se fijaron en el diagrama, existe una pequeña interfaz denominada `asService`, la cual contiene un único método denominado `build`. Esto con la intención de “tratar de obligar” a la clase que lo implemente usar un patrón de diseño **Builder**.

![Caso1](images/DiagramaExt_Caso3.jpeg)

Con esto logramos una clara jerarquía, gracias al polimorfismo. Lo anterior nos permitiría crear un perfil de perfiles, lo que sería útil para analizar los datos de un mismo usuario en diferentes plataformas. Esto nos podría arrojar estadísticas de uso por cada plataforma, así como otros múltiples datos relevantes.

## Conclusión
Como claramente se mencionó, nuestro programa está preparado para extenderse hasta donde sea necesario, cuenta con los elementos estructurales para poder lograr una gran extensibilidad con el menor dolor de cabeza posible.

Asimismo, se cumple con la definición establecida en el modelo de calidad de McCall, pues gracias a la modularidad hecha en el sistema ([Diagrama de Clase](images/Diagrama_Clases.jpg)) podemos observar la capacidad que tiene el software para poder expandirse o modificarse en varios casos sin muchas complicaciones.