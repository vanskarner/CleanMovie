<div align="right" size="1px">
<a href='https://github.com/vanskarner/CleanMovie'>English</a>
</div>

![CleanMovieCover](https://github.com/vanskarner/CleanMovie/assets/39975255/5984299f-24c4-436f-ac1e-086a2aecc399)

# Clean Architecture | Android
Aplicación de las directrices y principios del libro **Clean Architecture: A Craftsman’s Guide to Software Structure and Design** del Tío Bob en un sencillo proyecto Android.

|Robert C. Martin. Clean Architecture: A Craftsman's Guide to Software Structure and Design. Prentice Hall, 2017|
|:---:|
|![book](https://m.media-amazon.com/images/I/41-sN-mzwKL._SX218_BO1,204,203,200_QL40_FMwebp_.jpg)|
<p align="center">
  <img src="https://github.com/vanskarner/CleanMovie/assets/39975255/7d7c53a6-7c85-4456-a725-99814d3b1eb5" alt="CleanArchitectureCover_2017" style="display: block; margin: auto;">
</p>

## Introducción
Para garantizar la comprensión del proyecto, hemos optado por utilizar los elementos más básicos, evitando la sobrecarga de detalles que suele darse en el desarrollo de Android, manteniendo la simplicidad y haciéndolo más comprensible para aquellos que se están familiarizando con el tema.

Si bien el proyecto está desarrollado en Java, es solo para destacar la [importancia del contexto](https://github.com/vanskarner/CleanMovie/wiki/El-Contexto-Importa) en estas guías, ya que en general se recomienda Kotlin como lenguaje principal para el desarrollo de aplicaciones Android, ya que ofrece una sintaxis moderna y concisa, oficialmente avalada por Google.

![ExampleCleanMovie](https://user-images.githubusercontent.com/39975255/234139272-fc119831-0b79-4ca6-aaf5-6898d4624408.gif)

## Division del Proyecto
El repositorio tiene 4 ramas que representan el mismo sistema pero desacoplado de diferente forma:

| package_by_component_Main | package_by_component_Secondary |
| --- | --- |
| ![ByComponent- Main](https://github.com/vanskarner/CleanMovie/assets/39975255/6d542b6d-1042-4447-be61-e38b3778539b) | ![ByComponente - Secondary](https://github.com/vanskarner/CleanMovie/assets/39975255/6d542b6d-1042-4447-be61-e38b3778539b) |

| package_by_feature | package_by_layer |
| --- | --- |
| ![ByFeature](https://github.com/vanskarner/CleanMovie/assets/39975255/2558784a-876b-4834-9079-35e2f02a75b6) | ![ByLayer](https://github.com/vanskarner/CleanMovie/assets/39975255/2f2d9001-032b-49a2-bbe2-09b05072be92) |

## Capacidad de Prueba
Cada módulo del proyecto en todas las ramas tiene asociado sus propias pruebas.

| Ejecución de pruebas de extremo a extremo |
| :---: |
| ![EndToEndTestRunning](https://user-images.githubusercontent.com/39975255/234135918-05f205b7-6296-43a5-b0f5-b2d5ed23d5d8.gif) |

| Reportes de pruebas |
| :---: |
| ![gif-pruebas](https://github.com/vanskarner/CleanMovie/assets/39975255/834208cb-0731-4b4e-9ec0-0e64aab55616) |

## Métricas
Cada rama tiene su propio análisis para conocer sus métricas asociadas. Los resultados de este análisis se puede encontrar en la sección de [Resumen de Proyecto](https://github.com/vanskarner/CleanMovie/wiki/Resumen-del-Proyecto).

<p align="center">
  <img src="https://github.com/vanskarner/CleanMovie/assets/39975255/bf241b81-da29-4a86-beb9-fe9d33714c4d" alt="Metrics of the main branch" style="display: block; margin: auto;">
</p>

## Wiki
Obtenga una comprensión más amplia sobre la **Arquitectura Limpia** siguiendo las guias proporcionadas [AQUÍ](https://github.com/vanskarner/CleanMovie/wiki/Inicio)

## Discusiones
Consulte la sección de discusiones [AQUÍ](https://github.com/vanskarner/CleanMovie/discussions).

## Consideraciones
- Si quieres utilizar la aplicación, primero tienes que generar tu clave api de desarrollador en [Themoviedb API](https://www.themoviedb.org/settings/api). Luego, una vez generado, debe poner esa clave en el archivo [data.properties](https://github.com/vanskarner/CleanMovie/blob/package_by_component_Main/data.properties):
```properties
#Project properties
themoviedbApiKey="HERE_YOUR_KEY"
```
- Para ejecutar cualquier tipo de prueba, no es necesaria una clave API.

## Otro ejemplo usando Kotlin

Una simple y pequeña aplicacion que sigue los conceptos de descritos en esta la wiki pero usando Kotlin, Corrutinas, ViewModel, LiveData y Hilt: https://github.com/vanskarner/CleanExampleKT
