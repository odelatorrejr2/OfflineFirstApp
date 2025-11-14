# User Directory App

This is an Android application for a mobile development class project. It is a **User Directory** app that fetches user data from a public API, stores it in a local Room Database, and displays it in a clean, searchable UI. The app follows an **offline-first architecture**.

---

## 📱 App Overview

The app displays a list of users (name, email, and phone) from the **JSONPlaceholder API**.

- The list works **fully offline** thanks to Room caching.  
- A built-in search bar filters users by **name or email in real time**, using only local data.

---

## ⚙️ Core Functionality

Below is a brief overview showing how each major requirement was implemented.

### 1. Fetch Users from API
- Retrofit is used to define the `ApiService`.
- A GET request retrieves user data, and Gson automatically parses the JSON into data classes.

### 2. Store Users in Local Database
- Room Database stores all user information.
- The `User` data class is marked with `@Entity`.
- `@Embedded` is used to flatten nested address and company objects into columns.

### 3. Display from Room (Single Source of Truth)
- The UI observes a **Kotlin Flow** from the `UserDao` through the ViewModel.
- The UI **never** uses API data directly — Room contains the only source of truth.

### 4. Offline-First Pattern
- On startup, the ViewModel attempts to refresh from the API.
- If the API call succeeds → Room is updated, and the UI auto-updates through Flow.
- If it fails → The app falls back to the **cached Room data** with no error.

### 5. Search Functionality
- A SearchView sends the query to the ViewModel → Repository → Dao.
- `LIKE` SQL queries filter results locally.
- Searching works instantly and **offline**.

---

## 🗂️ Project Structure

