# Clean Architecture - Android
Application of the concepts and principles from Uncle Bob's Clean Architecture book in a simple Android project.

|Martin, Robert C. Clean Architecture: A Craftsman's Guide to Software Structure and Design. Prentice Hall, 2017|
|:---:|
|![book](https://m.media-amazon.com/images/I/41-sN-mzwKL._SX218_BO1,204,203,200_QL40_FMwebp_.jpg)|
<p align="center">
  <img src="https://github.com/vanskarner/CleanMovie/assets/39975255/b1e694e8-42bc-4d2e-ae50-324b9f883be9" alt="CleanArchitectureCover_2017" style="display: block; margin: auto;">
</p>

## Presentation
To ensure project comprehension, we have chosen to utilize the most basic elements, avoiding the overload of details that is often encountered in Android development. The focus is on maintaining simplicity, making it accessible to those who are getting familiar with the subject. It is also important to mention that, while the project is based on Java to highlight [the significance of context](https://github.com/vanskarner/CleanMovie/wiki/The-Context-Matters) in these guides, Kotlin is generally recommended as the primary language for Android application development, as it offers a modern and concise syntax, officially endorsed by Google.

![ExampleCleanMovie](https://user-images.githubusercontent.com/39975255/234139272-fc119831-0b79-4ca6-aaf5-6898d4624408.gif)

## Project division
The repository has 2 branches that represent the same project. These branches represent the approaches to decoupling the code.

| Package by Component | Package by Layer |
| ------------- | ------------- | 
| ![ByComponent](https://github.com/vanskarner/CleanMovie/assets/39975255/c0662f98-0edb-4fb3-aa14-7d3fc8268b2b) | ![ByLayer](https://user-images.githubusercontent.com/39975255/234137255-72c9cf1c-e119-4f08-af89-f6cff3f37523.png)|

## Testing capability
Each module of the project has its associated unit and integration tests according to the branch of the project.

|End-to-end test running|End-to-end test report|
|-|-|
|![EndToEndTestRunning](https://user-images.githubusercontent.com/39975255/234135918-05f205b7-6296-43a5-b0f5-b2d5ed23d5d8.gif)|![EndToEndTestReport](https://user-images.githubusercontent.com/39975255/234136214-c9aa2faa-cde7-41e8-be7d-a1633991d3f0.jpg)|

## Wiki
Get a broader understanding of clean architecture by following the guidelines provided [HERE](https://github.com/vanskarner/CleanMovie/wiki)

## Discussions
Refer to the issues section [HERE](https://github.com/vanskarner/CleanMovie/discussions)

## Considerations
- If you want to use the app, you need to first generate your developer api key at [Themoviedb API](https://www.themoviedb.org/settings/api). Then once generated, you must put in the file [data.properties](https://github.com/vanskarner/CleanMovie/blob/package_per_component/data.properties):
```properties
#Project properties
themoviedbApiKey="HERE_YOUR_KEY"
```
- To run any type of test, an API key is not necessary.
