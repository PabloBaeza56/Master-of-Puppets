# Eficiencia:

## Definicion:
Según McCall, la eficiencia se define como la capacidad del software para ofrecer el rendimiento adecuado en términos de recursos utilizados y tiempo de ejecución. Los aspectos clave de la eficiencia incluyen:

- **Tiempo de respuesta**: La capacidad del software para completar sus funciones en un tiempo aceptable, proporcionando resultados rápidamente a los usuarios.
- **Uso de recursos**: La capacidad del software para utilizar la menor cantidad posible de recursos (CPU, memoria, almacenamiento) durante su funcionamiento.
- **Economía**: La capacidad del software para minimizar los costos asociados al uso de recursos y tiempo de ejecución.
- **Velocidad de procesamiento**: La capacidad del software para realizar operaciones complejas en el menor tiempo posible, optimizando el rendimiento general.

Cada uno de estos atributos contribuye a la percepción general de la eficiencia del software y su capacidad para realizar tareas de manera rápida y con un uso óptimo de los recursos disponibles.

## Introducción:
La eficiencia es otro elemento vital en todos sistemas informático, nos permite sacarles todo el poder a nuestros equipos de cómputo y a su vez proporcionar un rendimiento sobresaliente.

Los sistemas basados en Web Scrapping debe llevar el rendimiento un poco más allá, esto debido a que para realizar los procesos correspondientes el programa requiere del uso de un driver, un driver es una instancia de una pestaña de un navegador web, en dicha ventana realizaremos todas nuestras operaciones de búsqueda, cabe destacar que es bien sabido que los navegadores web consumen una gran cantidad de recursos informáticos, el uso de un driver para estos casos es más que obligatorio, por lo tanto nos vemos en la necesidad de optimizar al máximo nuestra solución informática, para así garantizar un buen rendimiento y un completo aprovechamiento de los recursos informáticos.

Actualmente ya hemos abusado un poco en el ejemplo de minado de datos, así que le daremos la oportunidad de que brille a otra parte del sistema informático que se considera fundamental “El Controlador Maestro”.


## Contextualización:

El controlador maestro es la clase encargada del inicio de sesión, así como del manejo de cookies.

![Clase](Documentacion/Images/ControladorMaestroClase.jpg)

Como podremos observar lo único público de esta clase es el constructor y la función inyectarCookies. No explicaremos a detalle lo que realiza cada clase debido a que en teoría no contamos con implementación, ahora bien, te preguntaras ¿Dónde está la eficiencia?
Retrocedamos un poco, recordemos que, para poder acceder a una página web, por lo general requerimos iniciar sesión, hasta el momento no hay ningún problema, iniciamos sesión normalmente, el detalle este que el driver de selenium (la ventana del navegador web), no tiene memoria, un guarda nada. Una vez “cerrado” se pierde todo dato. (Inclusive las cookies de inicio de sesión).

## Desarrollo:

Se podría pensar, bueno no hay problema cada vez que inicialice el driver (ósea que realice cualquier operación en el programa), inicio sesión y fin de la historia; el detalle se encuentra en que las páginas web tienen mecanismos de detección para verificar que un robot no este tratando de ingresar, ¿Cómo lo logran? Después de x número de inicios de sesión, para finalizar el inicio de sesión se te solicitara una verificación humana (un desafío o puzle), el detalle esta que el usuario no podrá acceder al driver (debido a que se ejecuta en segundo plano), por lo tanto el inicio de sesión fallara lo que provocara que ninguna función se pueda realizar, lo que dejara el programa inutilizable durante varios días, hasta que la pagina te permita volver a ingresar sin la necesidad de una verificación humana, este sería un ciclo sin fin. Como comentario adicional un inicio de sesión toma bastante tiempo por lo que sería una pérdida de recursos realizar esta acción indistintamente.

Por supuesto esto el hecho de que el usuario no pueda usar el programa informático durante varios días es inadmisible y se debe de evitar a toda costa. Ante esta situación podríamos pensar, ¿Y si guardamos las cookies de inicio de sesión en un archivo?, esta sería una solución bastante lógica, no tendríamos el problema de inicio de sesión, además de que las operaciones podrían realizarse de manera más rápida, ya que una inyección de cookies es menos tardada que un inicio de sesión completo.
Pero esto nos abre otro problema, como sabemos las cookies no son eternas, después de x cantidad de días caducan, volviéndolas inútiles. Requerimos un mecanismo que nos permita verificar si siguen siendo válidas, si no en caso contrario que las actualice.

Además, tenemos otro problema, aunque la lectura de cookies desde un archivo de texto tiene una velocidad de lectura aceptable, definitivamente no es la mejor, requerimos de alguna manera guardar las cookies en memoria local para así simplemente inyectarlas en el driver y poder ahorrar mucho más tiempo.

La solución es sencilla y se encuentra en el constructor del Controlador Maestro, a continuación, vamos a exponer un poco de “Pseudocodigo”, el cual 
permite resolver los problemas anteriormente mencionados sin mucho esfuerzo.

![Constructor](Documentacion/Images/ControladorMaestroConstructor.jpg)

## Conclusión:
Dados los mecanismos propuestos anteriormente, dichos nos permitirán ahorrar tiempo en inicios de sesión innecesarios, así como en la lectura de cookies a través de un cache permitiendo un ahorro significativo de los recursos informáticos, así como una fluidez en general mucho más significativa.
