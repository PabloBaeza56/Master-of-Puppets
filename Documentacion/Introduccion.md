# Documentación del proyecto del STEAM-RADAR

## Introducción

Existe la red social (por cuestiones legales no podemos mencionar su nombre explícitamente por lo que la denominaremos como Y), la cual permite conectar con otros profesionistas de tu campo tanto globales como locales, así como postularte para ofertas de empleo. En Y tienes la capacidad de crear un perfil en el cual puedes destacar tu experiencia laboral, tu educación, tus certificados y diplomados, entre otros aspectos destacables relacionados con el profesionista. Podríamos decir que es la versión moderna de un CV.

Cierta persona relacionada a recursos humanos, de cierta empresa transnacional centrada en software médico, menciona que al pagar la suscripción Premium es capaz de acceder a ciertos datos privilegiados, como por ejemplo: Buscar candidatos para ciertos criterios específicos. Sin embargo, la funcionalidad que provee la plataforma es limitada, lo que implica que no es posible realizar búsquedas complejas, las cuales pudiesen ser más significativas al momento de tomar decisiones de negocio.

## Contextualización del problema

Requerimos de alguna forma poder acceder a la página de Y, poder ingresar al perfil de ese usuario; “tomar”, “recuperar”, “minar”, “obtener” la información que resulte más relevante y posteriormente “guardarla” en algún lado para su posterior análisis.

## Objetivos

Recuperar la información de los perfiles presentes en la plataforma Y, para posteriormente realizar análisis estadístico que nos proporcionen conclusiones cuantificables, en el menor tiempo posible.

## Usuarios Objetivo

Como es apreciable cada perfil de usuario contiene cierta cantidad de información valiosa, la cual podría ser aprovechada tanto por personal universitario relacionado a la vinculación laboral y empresarial, así como por personal de recursos humanos; Para obtener estadísticas valiosas que permitan tomar decisiones empresariales basadas en datos (Business Intelligence).

## Tecnologías utilizadas

En la versión anterior de este proyecto (DEPRECATED), se utilizó el lenguaje de programación Python, sin embargo, al no ser fuertemente tipado, no tener un correcto manejo de errores, además de no estar fuertemente orientado a objetos, ocasiona múltiples problemas en el aspecto no funcional (poca mantenibilidad, nula recuperabilidad, poca o ninguna extensibilidad). Bajo los criterios anteriormente mencionados, el equipo llegó a la conclusión de que el proyecto bajo este modelo iba a ser insostenible, por lo tanto, se procedió a buscar un lenguaje de programación que cumpliera con las características antes mencionadas, por lo que concluimos que el lenguaje de programación en Java sería lo ideal. Seleccionamos la última versión, no porque nos fueran útiles las nuevas funcionalidades introducidas, sino más bien para poder gozar de una compatibilidad de versiones anteriores a largo plazo.

Para poder recuperar la información deseada de la página web de Y, requerimos de una técnica informática denominada Web Scrapping, la cual consiste en ir “raspando” o “minando” secciones individuales del código HTML, con el objetivo de lograr obtener el texto asociado a ciertas etiquetas.

En Java formalmente no cuenta con librerías orientadas específicamente a Web Scrapping, sin embargo, podríamos tener una aproximación a estas usando la librería denominada Selenium, la cual aunque originalmente estaba pensada para automatización de pruebas en entornos Web, cuenta con ciertas funcionalidades orientadas a obtener texto de un HTML, las cuales vamos a aprovechar para nuestros fines.

## Librerías Empleadas

De manera no muy extensiva enumeramos y describimos las librerías empleadas en este proyecto, así como el rol que cumplen dentro de nuestro sistema.

### *Selenium-java - versión 4.20.0*
Librería principal centrada en el manejo del controlador del navegador web, así como en la obtención de los datos en el respectivo HTML.

![selenium-java](images/selenium-java.jpg)

### *Mongodb-driver-sync - versión 5.1.0*
Librería centrada en el manejo y distribución de las funcionalidades relacionadas con la base de datos.

![mongodb-driver-sync](images/mongodb-driver-sync.png)

### *Gson - versión 2.10.1*
Librería que permite un mapeo automático de un objeto permitiendo enviar un objeto creado en Java directamente a la base de datos sin la necesidad de una conversión o adaptación. Podríamos decir de manera informal que se trata de un ORM.

![gson](images/gson.png)

### *Lombok - versión 1.18.32*
Librería que permite la generación interna de setters, getters y constructores.

![lombok](images/lombok.png)
