# Android Employee List App

This Android application demonstrates a basic employee list management app built using Jetpack Compose, Kotlin, and follows the MVVM (Model-View-ViewModel) architecture. It fetches employee data from a remote API, displays it in a list, and handles online/offline states.

## Features

*   **Employee List Display:** Displays a list of employees with their names and positions.
*   **Remote Data Fetching:** Fetches employee data from a remote API (currently using a mock server running on `localhost` for development).
*   **Loading Indicator:** Shows a loading indicator while fetching data.
*   **Online/Offline Status:** Displays the app's current online/offline status.
*   **Refresh Functionality:** Allows users to refresh the employee list using a button.
*   **Error Handling:** Basic error handling to display error messages if data fetching fails. (Note: You need to add this to the `EmployeeViewModel` as discussed in our conversation.)
*   **Network Security Configuration:** Configured to allow cleartext traffic to `10.0.2.2` during development to connect to a local server.

## Project Structure

*   **`data`:**
    *   **`local`:**
        *   `Employee.kt`: Data class representing the Employee entity for the Room database.
        *   `EmployeeDao.kt`: Data Access Object (DAO) interface for Room, defining database interactions.
        *   `EmployeeDatabase.kt`: Room database class that provides access to the `EmployeeDao`.
    *   **`remote`:**
        *   `ApiService.kt`: Interface defining the API endpoints using Retrofit annotations.
        *   `RetrofitInstance.kt`: Provides a configured instance of Retrofit for making network requests.
    *   **`repository`:**
        *   `EmployeeRepository.kt`: Repository class that acts as a single source of truth for employee data. It interacts with both the `ApiService` (for remote data) and the `EmployeeDao` (for local data).

*   **`ui`:**
    *   **`screens`:** Contains the UI components (Composables) of the app. (You should add your `EmployeeListScreen.kt` and `EmployeeRow.kt` files here).
    *   **`theme`:** Contains files related to the app's theme (colors, typography, shapes).
    *   `EmployeeViewModel.kt`: ViewModel responsible for fetching and managing data from the `EmployeeRepository` and exposing it to the UI.
    *   `EmployeeViewModelFactory.kt`: Factory class for creating instances of `EmployeeViewModel`.

*   **`utils`:** Contains utility functions or classes used throughout the app. (You should add your `isNetworkAvailable` function and any other helper functions here).

*   `MainActivity.kt`: The main activity that hosts the Composable UI.

## Technologies Used

*   **Kotlin:** The primary programming language.
*   **Jetpack Compose:** Modern Android UI toolkit for building declarative UIs.
*   **LiveData:** For observing data changes in the ViewModel and updating the UI.
*   **Coroutines:** For asynchronous operations (e.g., network requests).
*   **Retrofit:** For making network requests.

## Getting Started

### Prerequisites

*   Android Studio (latest stable version recommended).
*   Node.js and npm (to run the mock server).
*   An Android Emulator or a physical Android device for testing.

### Setup

1. **Clone the repository:**

    ```bash
    git clone <repository_url>
    ```

2. **Open the project in Android Studio.**

3. **Run the mock server:**
    *   Navigate to the `server` directory (or wherever your `server.js` file is located).
    *   Install dependencies: `npm install`
    *   Start the server: `node server.js`

4. **Configure the Network Security:**
    *   Ensure that `network_security_config.xml` is set up to allow cleartext traffic to `10.0.2.2` (for development).

5. **Build and run the app** on the emulator or a connected device.

