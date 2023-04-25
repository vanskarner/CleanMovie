# Clean Movie - Android
Review of the most relevant concepts of the book in a simple android project

![book](https://m.media-amazon.com/images/I/41-sN-mzwKL._SX218_BO1,204,203,200_QL40_FMwebp_.jpg)

## Goal
The project has been built using the most basic elements to ensure simplicity in understanding, without getting lost in the multitude of details that exist in current Android development. However, the main reason for using JAVA in development is to explain the significance of context, which becomes highly relevant towards the end of these guides. Outside of that reason, Kotlin is generally recommended for Android application development, as it offers a concise, modern syntax, and is officially endorsed by Google.

![ExampleCleanMovie](https://user-images.githubusercontent.com/39975255/234139272-fc119831-0b79-4ca6-aaf5-6898d4624408.gif)


## Project division
The repository has 2 branches that represent the same project. These branches represent the design and organization of the code adapted for these series of guides

| Package per Component | Package per Layer |
| ------------- | ------------- | 
| ![PerComponent](https://user-images.githubusercontent.com/39975255/234137273-c7938232-6b12-4d74-9b03-8d12019e2a9e.png) | ![PerLayer](https://user-images.githubusercontent.com/39975255/234137255-72c9cf1c-e119-4f08-af89-f6cff3f37523.png)|

## Testing capability
Each module of the project has its associated unit and integration tests according to the branch of the project.

End-to-end and integration testing:
![TestResult_CleanMovie](https://user-images.githubusercontent.com/39975255/234136214-c9aa2faa-cde7-41e8-be7d-a1633991d3f0.jpg)
![TestResult_CleanMovie](https://user-images.githubusercontent.com/39975255/234135918-05f205b7-6296-43a5-b0f5-b2d5ed23d5d8.gif)

## Considerations
- If you want to use the app, you need to first generate your developer api key at [Themoviedb API](https://www.themoviedb.org/settings/api). Then once generated, you must put in the file [data.properties](https://github.com/vanskarner/CleanMovie/blob/package_per_component/data.properties):
```properties
#Project properties
themoviedbApiKey="HERE_YOUR_KEY"
```
- To run any type of test, an API key is not necessary.

## Wiki
The important thing is not what the system does, but how we integrate the main concepts from a practical point of view.
Know in detail the explanation of clean architecture following the guides that I provide [HERE](https://github.com/vanskarner/CleanMovie/wiki)
