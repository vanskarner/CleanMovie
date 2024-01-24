<div align="right" size="1px">
<a href='https://github.com/vanskarner/CleanMovie/blob/package_by_component_Main/README_SPA.md'>Spanish</a>
</div>

![CleanMovieCover](https://github.com/vanskarner/CleanMovie/assets/39975255/5984299f-24c4-436f-ac1e-086a2aecc399)

# Clean Architecture | Android
Application of the guidelines and principles from the book Clean Architecture: A Craftsman's Guide to Software Structure and Design by Uncle Bob in a simple Android project.

|Martin, Robert C. Clean Architecture: A Craftsman's Guide to Software Structure and Design. Prentice Hall, 2017|
|:---:|
|![book](https://m.media-amazon.com/images/I/41-sN-mzwKL._SX218_BO1,204,203,200_QL40_FMwebp_.jpg)|
<p align="center">
  <img src="https://github.com/vanskarner/CleanMovie/assets/39975255/7d7c53a6-7c85-4456-a725-99814d3b1eb5" alt="CleanArchitectureCover_2017" style="display: block; margin: auto;">
</p>

## Introduction
To ensure the understanding of the project, we have chosen to use the most basic elements, avoiding the overload of details that usually occurs in Android development, keeping it simple and making it more understandable for those who are just getting familiar with the subject.

While the project is developed in Java, it is only to highlight [the importance of context](https://github.com/vanskarner/CleanMovie/wiki/The-Context-Matters) in these guides, as Kotlin is generally recommended as the main language for Android application development, as it offers a modern and concise syntax, officially endorsed by Google.

![ExampleCleanMovie](https://user-images.githubusercontent.com/39975255/234139272-fc119831-0b79-4ca6-aaf5-6898d4624408.gif)

## Project division
The repository has 4 branches that represent the same system but decoupled in different ways:

| package_by_component_Main | package_by_component_Secondary |
| --- | --- |
| ![ByComponent- Main](https://github.com/vanskarner/CleanMovie/assets/39975255/6d542b6d-1042-4447-be61-e38b3778539b) | ![ByComponente - Secondary](https://github.com/vanskarner/CleanMovie/assets/39975255/6d542b6d-1042-4447-be61-e38b3778539b) |

| package_by_feature | package_by_layer |
| --- | --- |
| ![ByFeature](https://github.com/vanskarner/CleanMovie/assets/39975255/2558784a-876b-4834-9079-35e2f02a75b6) | ![ByLayer](https://github.com/vanskarner/CleanMovie/assets/39975255/2f2d9001-032b-49a2-bbe2-09b05072be92) |

## Testing capability
Each project module in all branches has its own associated tests.

| Execution of end-to-end tests |
| :---: |
| ![EndToEndTestRunning](https://user-images.githubusercontent.com/39975255/234135918-05f205b7-6296-43a5-b0f5-b2d5ed23d5d8.gif) |

| Test reports |
| :---: |
| ![gif-pruebas](https://github.com/vanskarner/CleanMovie/assets/39975255/834208cb-0731-4b4e-9ec0-0e64aab55616) |

## Metrics
Each branch has its own analysis to know its associated metrics. The results of this analysis can be found in the [Project Summary](https://github.com/vanskarner/CleanMovie/wiki/Project-Summary) section.

<p align="center">
  <img src="https://github.com/vanskarner/CleanMovie/assets/39975255/bf241b81-da29-4a86-beb9-fe9d33714c4d" alt="Metrics of the main branch" style="display: block; margin: auto;">
</p>

## Wiki
Get a broader understanding of Clean Architecture by following the guidelines provided [HERE](https://github.com/vanskarner/CleanMovie/wiki).

## Discussions
Refer to the discussions section [HERE](https://github.com/vanskarner/CleanMovie/discussions)

## Considerations

- If you want to use the app, you need to first generate your developer api key at [Themoviedb API](https://www.themoviedb.org/settings/api). Then, once generated, you must put that key in the [data.properties](https://github.com/vanskarner/CleanMovie/blob/package_by_component_Main/data.properties) file:
```properties
#Project properties
themoviedbApiKey="HERE_YOUR_KEY"
```
- To run any type of test, an API key is not necessary.

## Another Example in Kotlin

A simple and small application that follows the concepts described in this wiki but using Kotlin, Coroutines, ViewModel, LiveData and Hilt: https://github.com/vanskarner/CleanExampleKT
