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

*   **`ui.screens`:** Contains the UI components (Composables) of the app, including `EmployeeListScreen` and `EmployeeRow`.
*   **`data.local`:**  Placeholder for data models. It currently includes a simple `Employee` data class. In a real app, this might contain database-related classes (e.g., Room entities, DAOs).
*   **`ui.EmployeeViewModel`:** The ViewModel responsible for fetching data, managing the UI state (employees, loading, error), and handling user interactions (refresh).
*   **`MainActivity.kt`:** The main activity that hosts the Composable UI.

## Technologies Used

*   **Kotlin:** The primary programming language.
*   **Jetpack Compose:** Modern Android UI toolkit for building declarative UIs.
*   **LiveData:** For observing data changes in the ViewModel and updating the UI.
*   **Coroutines:** For asynchronous operations (e.g., network requests).
*   **`ConnectivityManager`:** To check network availability.
*   **(Potentially) Room:** For local data persistence (if you decide to implement offline caching).
*   **(Potentially) Hilt/Dagger:** For dependency injection (recommended for larger projects).
*   **(Potentially) Retrofit:** For making network requests (likely used in your `EmployeeViewModel`).
*   **(Potentially) Accompanist:** For additional Compose utilities (e.g., `SwipeRefresh` for pull-to-refresh).

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

