# User Directory App

This is an User Directory app that fetches user data from a public API, stores it in a local Room Database, and displays it in a clean, 
searchable UI. The app follows an offline-first architecture.

---

# App Overview

The app displays a list of users (name, email, and phone) from the JSONPlaceholder API.

- The list worksfully offline thanks to Room caching.  
- A built-in search bar filters users by name or email in real time, using only local data.

---

# Core Functionality

### 1. Fetch Users from API
- Retrofit is used to define the ApiService.
- A GET request retrieves user data, and Gson automatically parses the JSON into data classes.

### 2. Store Users in Local Database
- Room Database stores all user information.
- The User data class is marked with @Entity.
- @Embedded is used to flatten nested address and company objects into columns.

### 3. Display from Room 
- The UI observes a Kotlin Flow from the UserDao through the ViewModel
- The UI never uses API data directly, The room contains the only source of truth.

### 4. Offline-First Pattern
- On startup, the ViewModel attempts to refresh from the API.
- If the API call succeeds, Th Room is updated, and the UI auto-updates through Flow.
- If it fails The app falls back to the cached Room data with no error.

### 5. Search Functionality
- A SearchView sends the query to the ViewModel, Repository, Dao.
- Like SQL queries filter results locally.
- Searching works instantly and offline.

---

### 6. Project Structure
data/
├── api/ (API services using Retrofit)
├── db/ (Room Database, DAO, entities)
├── repository/ (UserRepository)
ui/
├── viewmodel/
├── adapter/
UserApplication.kt (Provides singleton instances for Repository & Database)

---

### 7. Screenshots

<img width="1080" height="2340" alt="Screenshot_20251114_012336" src="https://github.com/user-attachments/assets/205b70f8-fc11-4290-804a-7279462cd550" />

<img width="1080" height="2340" alt="Screenshot_20251114_012343" src="https://github.com/user-attachments/assets/6cfafccb-9d5b-4015-a17d-afca6715013a" />

<img width="1080" height="2340" alt="Screenshot_20251114_012356" src="https://github.com/user-attachments/assets/a26da409-ca07-4bfe-827d-3dab7f9ca849" />

<img width="1080" height="2340" alt="Screenshot_20251114_012412" src="https://github.com/user-attachments/assets/90e40545-9216-4dff-9ef5-3c2cbbf2e122" />

<img width="1080" height="2340" alt="Screenshot_20251114_012509" src="https://github.com/user-attachments/assets/72797d27-275f-48f4-8000-2024e97380d5" />

