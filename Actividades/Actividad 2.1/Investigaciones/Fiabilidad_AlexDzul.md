# ADA 2.1. Atributos de Calidad en el Diseño del Software

## Fiabilidad

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

## ¿En Qué Consiste la Fiabilidad?
McCall define la fiabilidad como la probabilidad que un sistema realice una funcionalidad de manera correcta en un entorno especificado en un periodo de tiempo dado. Trata de responder a la pregunta *_"¿Lo realiza de manera precisa las veces que lo requiero?"_*. Asímismo, consta de varios aspectos:
* Toleracia a errores: Provee continuidad de las operaciones bajo condiciones anormales. 
* Simplicidad: Provee implementación de funciones de la manera más entendible
* Consistencia: Provee un diseño uniforme y técnicas de implementación
* Precisión: provee la precisión requerida en los cálculos y salidas

## Caso Real Donde se Encuentra la Fiabilidad en el Diseño de Software

El modelo de Mc Call fue diseñado para proveer calidad a ciertos sistemas militares, por lo que podemos encontrar dentro del propio modelo la forma de como hacer el diseño de un software procurando la fiabilidad.

Basandonos en los criterios principales de la fiabilidad (tolerancia a fallos, simplicidad, consistencia y precisión) podemos encontrar las metricas correspondientes en el modelo de Mc Call de forma que podemos diseñar en base a estas metricas procurando cumplir con la mayor cantidad posible de estas.

Dentro del modelo podemos encontrar las siguientes metricas para la consistencia:

#### Medida de Consistencia de Procedimiento:

- Representación de diseño estandar: Uso de diagramas de flujo, gráficos HIPO, Lenguaje de Diseño de Programas. La medida se basa en el número de módulos cuya representación de diseño no cumple con los estándares.

- Convenciones de Secuencia de Llamada: Las interacciones entre los módulos deben estandarizarse. Los estándares se establecen durante el diseño y se siguen durante la implementación. La medida se basa en el número de módulos que no cumplen con las convenciones.

- Convenciones de Entrada/Salida: Se deben establecer y seguir convenciones sobre qué módulos realizarán E/S, cómo se realizará y los formatos de E/S. La medida se basa en qué módulos no cumplen con las convenciones.

- Convenciones de Manejo de Errores: Se requiere un método consistente para el manejo de errores. Las convenciones establecidas en el diseño se siguen en la implementación. La medida se basa en el número de módulos que no cumplen con las convenciones.

#### Medida de Cosistencia de Datos:

- Representación estándar de uso de datos: Se debe establecer y seguir una representación de diseño estándar para el uso de datos. Esta es una métrica de diseño solamente, identificando el número de módulos que violan los estándares.

- Convenciones de Nomenclatura: Se deben establecer y seguir convenciones de nomenclatura para variables y módulos.

- Consistencia de Unidades: Las unidades de las variables deben elegirse de manera consistente con todos los usos de las variables. La medida se basa en el número de módulos en los que no se utilizan unidades consistentes. Esto puede medirse tanto en el diseño como en la implementación.

- Definiciones Globales Consistentes: Los elementos de datos globales deben definirse de la misma manera por todos los módulos. La medida se basa en el número de módulos en los que los elementos de datos globales se definen de manera inconsistente tanto para el diseño como para la implementación.

- Consistencia de Tipo de Datos: Un elemento de datos definido como un tipo de datos particular debe usarse como ese tipo de datos en todas las ocurrencias. Una violación común de esta regla se encuentra en los arreglos donde se definen varios tipos de datos. La medida se basa en el número de módulos que utilizan tipos de datos de manera inconsistente.

Para la precisión se cuenta con la siguiente métrica:

#### Lista de Verificación de Precisión:

- Suficiencia de la Biblioteca Matemática: La precisión de las rutinas de las bibliotecas matemáticas utilizadas dentro del sistema debe verificarse para que sea consistente con los objetivos de precisión generales.

- Suficiencia de métodos numéricos: Los métodos numéricos utilizados dentro del sistema deben ser consistentes con los objetivos de precisión.

Para la tolerancia a errores contamos con las siguientes métricas:

#### Lista de Verificación de Control de Tolerancia a Errores:

- Procesamiento concurrente controlado de manera centralizada: Las funciones que pueden ser utilizadas de manera concurrente deben ser controladas centralmente para proporcionar verificación de concurrencia, bloqueos de lectura/escritura, etc. Ejemplos incluyen un gestor de bases de datos, manejo de E/S, manejo de errores, etc. El control central debe ser considerado en el diseño y luego implementado.

- Errores corregibles y procesamiento continuado: Cuando se detecta un error, debe estar disponible la capacidad para corregirlo en línea y luego continuar el procesamiento. Un ejemplo es un mensaje del operador indicando que se montó la cinta incorrecta y el procesamiento continuará cuando se monte la cinta correcta. Esto puede medirse en el diseño e implementación.

- Cuando se detecta una condición de error, la condición debe ser pasada a la rutina de llamada: La decisión sobre qué hacer respecto a un error debe tomarse en un nivel donde un módulo afectado esté controlado. Este concepto se incorpora en el diseño y luego se implementa.

#### Lista de Verificación de Recuperación de Datos de Entrada Incorrectos:

- Rango de valores (razonabilidad) para los elementos especificados y verificados: Los atributos de cada elemento de entrada deben verificarse en cuanto a su razonabilidad. Ejemplos incluyen verificar si los elementos deben ser numéricos, alfabéticos, positivos o negativos, de cierta longitud, diferentes de cero, etc. Estas comprobaciones deben especificarse en el diseño y existir en el código en la implementación.

- Solicitudes conflictivas y combinaciones ilegales identificadas y verificadas: Las comprobaciones para ver si los datos de entrada redundantes están de acuerdo, si las combinaciones de parámetros son razonables y si las solicitudes son conflictivas deben documentarse en el diseño y existir en el código en la implementación.

- Todos los datos de entrada se verifican antes de que comience el procesamiento: La verificación de la entrada no debe detenerse en el primer error encontrado, sino continuar a través de toda la entrada y luego informar todos los errores. El procesamiento no debe comenzar hasta que se informen los errores y se realicen correcciones o se dé un comando para continuar el procesamiento.

- Determinación de que todos los datos están disponibles antes del procesamiento: Para evitar pasar por varios pasos de procesamiento antes de descubrir datos de entrada incompletos, se deben realizar comprobaciones de suficiencia de datos de entrada antes de que comience el procesamiento.

#### Lista de Verificación de Recuperación de Fallas Computacionales:

- Prueba de rango de parámetros de índice de bucle y transferencia múltiple antes de su uso: Las pruebas de rango para índices de bucle y transferencias múltiples deben especificarse en el diseño y existir en el código en la implementación.

- Verificación de subíndices: Las comprobaciones de valores de subíndice legales deben especificarse en el diseño y codificarse durante la implementación.

- Comprobación de la razonabilidad de los parámetros de salida críticos durante el procesamiento: Se deben realizar ciertas comprobaciones de rango de valores durante el procesamiento para garantizar la razonabilidad de las salidas finales. Esto se hace generalmente solo para parámetros críticos. Estos deben ser identificados durante el diseño y codificados durante la implementación.

#### Lista de Verificación de Recuperación de Fallas de Hardware:

- Recuperación de Fallas de Hardware: La especificación de diseño y el código para proporcionar la recuperación de las fallas de hardware identificadas en los requisitos deben existir en las fases de diseño e implementación, respectivamente.

#### Lista de Verificación de Recuperación de Errores de Dispositivos:

- Recuperación de Errores de Dispositivos: La especificación de diseño y el código para proporcionar el manejo requerido de errores de dispositivos deben existir en las fases de diseño e implementación, respectivamente.

Las métricas para la simplicidad son las siguientes:

#### Medida de Estructura de Diseño:

- Diseño organizado de manera descendente: Un gráfico jerárquico de módulos del sistema generalmente está disponible o es fácil de construir a partir de la documentación de diseño. Debe reflejar la noción aceptada de diseño descendente. El sistema está organizado en una estructura de árbol jerárquico, donde cada nivel del árbol representa descripciones de detalles de procesamiento de niveles inferiores.

- No hay funciones duplicadas: Las descripciones de las funciones que debe realizar cada módulo en el diseño y la función real realizada por el módulo codificado deben ser evaluadas para asegurarse de que no estén duplicadas por otros módulos.

- Independencia de módulos: El procesamiento realizado dentro de un módulo no debe depender de la fuente de entrada o del destino de la salida. Esta regla se puede aplicar a la descripción del módulo durante el diseño y al módulo codificado durante la implementación. La medida para este elemento se basa en el número de módulos que no cumplen con esta regla.

- El procesamiento de los módulos no depende del procesamiento previo: El procesamiento realizado dentro de un módulo no debe depender del conocimiento o de los resultados del procesamiento previo, por ejemplo, la primera vez que se pasa por el módulo, la enésima vez, etc. Esta regla se aplica como se describe anteriormente, tanto en el diseño como en la implementación.

- Cada descripción de módulo incluye entrada, salida, procesamiento y limitaciones: Se debe desarrollar documentación que describa la entrada, salida, procesamiento y limitaciones para cada módulo durante el diseño y estar disponible durante la implementación. La medida para este elemento se basa en el número de módulos que no tienen esta información documentada.

- Cada módulo tiene una entrada única y una salida única: La determinación del número de módulos que violan esta regla en el diseño y la implementación se puede realizar y es la base para la métrica.

- No hay datos globales: Esta es una medida binaria que identifica la complejidad añadida a un sistema por el uso de datos globales. Si no existen datos globales, esta medida es 1; si existen datos globales, es 0.

#### Medida de la Complejidad del Flujo de Datos y Control:

- Esta métrica puede medirse a partir de la representación de diseño (por ejemplo, diagramas de flujo) y automáticamente desde el código. Se utiliza un análisis de flujo de ruta e información de conjunto/utilización de variables a lo largo de cada camino. Una variable se considera "activa" en un nodo si puede ser utilizada nuevamente a lo largo de ese camino en el programa. La medida de complejidad se basa en sumar la "actividad" de todas las variables a lo largo de todos los caminos en el programa.

## Métricas a usar en el proyecto

Para definir algunas métricas para la usabilidad de STEAM-RADAR utilizaremos los atributos del cual consiste este concepto según McCall:

| Criterio | Métrica |
|--------------|--------------|
| Precisión   | Se verificó que las funciones de las bibliotecas a usar dan los resultados esperados    |
| Precisión   | Número de pérfiles scrappeados que no coincida su información scrappeada con la visible en su perfil   |
| Tolerancia a errores   | El programa sigue en funcionamiento tras un error    |
| Tolerancia a errores   | Existe un método para verificar los datos de entrada antes del procesamiento    |
| Consistencia/Tolerancia a errores   | Número de modulos que no siguen las conveciones de métodos para el manejo de errores    |
| Consistencia   | Número de modulos que no siguen las convenciones de entrada  y salida    |
| Consistencia   | Número de modulos que no utilizan la representación estandar de los datos    |
| Simplicidad   | Número de modulos que dependen de los resultados de otros modulos    |
| Simplicidad   | Número de modulos que no tienen una descripción completa (descripcion, entrada, salida, limitaciones) en la documentación   |
| Simplicidad    | No existen funciones que tengan la misma funcionalidad    |

## Referencia
* https://apps.dtic.mil/sti/pdfs/ADA049014.pdf