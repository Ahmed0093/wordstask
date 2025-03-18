# Multi-Module Domain Data Presentation Word Task Project

This project is a multi-module Android application built using **Jetpack Compose** for the UI, **Room** for the database, **Coroutines** for asynchronous programming, **OkHttp** for network requests, and **Jsoup** for parsing HTML responses. The project is structured as a multi-module project to promote separation of concerns, reusability, and scalability.

## App Description

The app fetches an HTML response from a given URL, parses the response, and displays a list of words along with how many times each word repeated. A word is defined as any sequence of characters separated by spaces excluding espical characters. The app also supports offline functionality, error handling, and additional features like searching and sorting.

### Key Features:
1. **Fetch and Parse HTML Response**:
   - The app fetches an HTML response using **OkHttp**.
   - The response is parsed using **Jsoup** to extract and count words.
   - A word is defined as any sequence of characters separated by spaces.

2. **Display Word List**:
   - The parsed words are displayed in a list, showing each word and its frequency.

3. **Offline Support**:
   - When offline, the app displays the last parsed words stored in the local **Room database**.

4. **Error and Empty States**:
   - The app handles error states.

5. **Search Functionality**:
   - A search button allows users to search the loaded list for matching words.

6. **Sorting**:
   - A sort button toggles between ascending and descending order for the word list.

7. **Unit Testing**:
   - The `:presentation` layer includes **unit tests** for the **ViewModel** to ensure business logic and state management are working as expected.

## Project Structure

The project is divided into the following modules:

1. **`:app`**: The main application module responsible for managing **dependency injection** using **Hilt**. It ties all the modules together and initializes the application.
2. **`:data`**: Handles data operations, including network requests, data parsing, and interaction with the `:database` module.
3. **`:database`**: A separate module dedicated to **Room database** operations. It contains entities, DAOs, and the database setup.
4. **`:domain`**: Contains the business logic, use cases, and repository interfaces.
5. **`:presentation`**: Responsible for the **UI screens** and state management using **Jetpack Compose**. It communicates with the `:domain` module to fetch and display data. This module also includes **unit tests** for the **ViewModel**.

## Technologies Used

- **Jetpack Compose**: Modern Android UI toolkit for building native UI.
- **Room**: SQLite object mapping library for local data storage.
- **Coroutines**: Kotlin's library for asynchronous programming.
- **OkHttp**: HTTP client for making network requests.
- **Jsoup**: Java library for working with real-world HTML, providing a convenient API for extracting and manipulating data.
- **Hilt**: Dependency injection framework for Android, built on top of Dagger, to simplify DI across modules.
- **JUnit**: Framework for writing and running unit tests.
- **MockK**: Mocking library for Kotlin, used in unit tests to mock dependencies.

## Features

- **Multi-module Architecture**: The project is divided into separate modules for better separation of concerns.
- **Dependency Injection**: **Hilt** is used to manage dependencies and communicate between modules.
- **Data Persistence**: **Room** is used to store data locally in a SQLite database, encapsulated in the `:database` module.
- **Network Operations**: **OkHttp** is used to perform network requests, and **Jsoup** is used to parse HTML responses.
- **Asynchronous Programming**: **Coroutines** are used to handle asynchronous tasks, making the code more readable and maintainable.
- **Modern UI**: **Jetpack Compose** is used to build a modern, responsive UI in the `:presentation` module.
- **Offline Support**: The app displays the last parsed words when offline.
- **Error Handling**: Gracefully handles network errors and empty states.
- **Search and Sort**: Allows users to search the word list and toggle between ascending and descending order.
- **Unit Testing**: The `:presentation` layer includes **unit tests** for the **ViewModel** to ensure business logic and state management are working as expected.

## TODO
- Add code Coverage
