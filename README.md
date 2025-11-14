User Directory App

This is an Android application for a mobile development class project. It's a "User Directory" app that fetches user data from a public API, stores it in a local Room Database, and displays it in a clean, searchable UI. The app is built with an offline-first architecture.

(Screenshot of the main screen with the list of users)

App Overview

The app displays a list of users (name, email, and phone) from the JSONPlaceholder API. This list is fully functional even when the device is offline, as the data is cached locally. The app also features a local search bar that allows users to filter the cached user list by name or email in real-time.

Core Functionality

Here is a brief, two-line description of how the core project requirements were implemented:

1. Fetch Users from API

We use Retrofit to define a simple ApiService interface. A GET request is made to the API, and Gson is used to parse the JSON response directly into our User data classes.

2. Store Users in Local Database

We use Room Database to store the user list. The User data class is defined as an @Entity, and we use @Embedded to seamlessly flatten the nested address and company objects into our table.

3. Display from Room (Single Source of Truth)

The UI (via the ViewModel) observes a Kotlin Flow directly from the UserDao. The UI never displays data from the API directly; it only reads from the Room database, which serves as the single source of truth.

4. Offline-First Pattern

When the app starts, the ViewModel immediately tries to refresh data from the API. If the API call succeeds, Room is updated (and the UI auto-updates via Flow); if it fails (no internet), the app simply shows the last-known cached data from Room without any error.

5. Search Functionality

A SearchView in the UI sends the query to the ViewModel, which passes it to the UserRepository. The UserDao performs a local, real-time search using a LIKE query in plain SQL, ensuring the search is fast and works offline.

Project Structure

data/: Contains all data-related classes (Models, DAO, Database, Repository, and API services).

ui/: Contains UI-related logic classes (ViewModel, Adapter, and ViewModelFactory).

UserApplication.kt: A custom Application class used to create singleton instances of the UserRepository and UserDatabase.

