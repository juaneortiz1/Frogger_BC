# Frogger_BC

Welcome to the Frogger_BC project! This is a classic arcade-style game implemented using React for the frontend and Spring Boot with Java for the backend. The project follows the Model-View-Controller (MVC) design pattern and uses a Client-Server Architecture.

## Project Overview

The Frogger_BC is a browser-based game where players control frogs to cross a series of roads and rivers while avoiding obstacles such as cars and logs. The goal is to safely navigate the frog to one of the lily pads at the top of the screen.

## Architecture

### Client-Server Architecture

- **Client**: The client side is built using React, which handles the user interface and user interactions.
- **Server**: The server side is developed using Spring Boot with Java, which manages the game logic, state, and database interactions.

### Design Pattern

- **Model-View-Controller (MVC)**:
    - **Model**: Represents the game state and logic (e.g., `FroggerGame`, `Frog`, `Car`, `Log`, `LilyPad`).
    - **View**: The user interface components in React that render the game state and interact with the user (e.g., `GameCanvas`, `LoginPage`, `RegisterPage`).
    - **Controller**: The backend controllers that handle API requests and responses (e.g., `UserController`, `GameServiceController`).

## Technologies Used

- **Frontend**:
    - **React**: JavaScript library for building user interfaces.
    - **React Router DOM**: For handling routing and navigation in the application.
    - **HTML5 Canvas**: For rendering the game graphics.

- **Backend**:
    - **Spring Boot**: Framework for creating stand-alone, production-grade Spring-based applications.
    - **Java**: Programming language used for backend development.
    - **Spring Security**: For managing authentication and authorization.
    - **MongoDB**: NoSQL database used for user storage.

- **Build Tools**:
    - **Maven**: For managing the backend dependencies and build process.
    - **NPM/Yarn**: For managing frontend dependencies and build process.

## Setup Instructions

### Frontend Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/juaneortiz1/frontFrogger_BC.git
   ```

2. **Install Dependencies**:
   ```bash
   npm install
   # or
   yarn install
   ```

3. **Run the Frontend**:
   ```bash
   npm start
   # or
   yarn start
   ```

   The frontend will be available at `http://localhost:3000`.

### Backend Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/juaneortiz1/Frogger_BC.git
   cd frogger-game/backend
   ```

2. **Build the Backend**:
   ```bash
   mvn clean install
   ```

3. **Run the Backend**:
   ```bash
   mvn spring-boot:run
   ```

   The backend will be available at `http://localhost:8080`.

## API Endpoints

- **User Management**:
    - `POST /users/register`: Register a new user.
    - `POST /users/login`: Log in a user.

- **Game State**:
    - `GET /game/state`: Retrieve the current game state.
    - `POST /game/move`: Update the frog's position.

## Frontend Components

- **LoginPage**: Page for user login.
- **RegisterPage**: Page for user registration.
- **GameCanvas**: Component for rendering the game and handling user interactions.

## Backend Components

- **UserController**: Handles user registration and login.
- **GameServiceController**: Manages game state and operations.
- **UserService**: Business logic for user management.
- **FroggerGame**: Core game logic and state management.

## Running Tests

### Frontend Tests

1. **Run Unit Tests**:
   ```bash
   npm test
   # or
   yarn test
   ```

### Backend Tests

1. **Run Unit Tests**:
   ```bash
   mvn test
   ```

## Design Considerations

### MVC Design Pattern

- **Model**: Encapsulates the game's data and logic. Classes such as `FroggerGame`, `Frog`, `Car`, and `Log` represent the game's state and rules.
- **View**: React components such as `GameCanvas` and `LoginPage` are responsible for rendering the game's visual elements and handling user input.
- **Controller**: Spring Boot controllers handle HTTP requests and responses, managing interactions between the frontend and the backend.

### Client-Server Architecture

- **Client**: The React frontend interacts with the backend via RESTful API calls. It handles user input and displays the game state.
- **Server**: The Spring Boot backend processes game logic, manages state, and interacts with the database. It exposes RESTful APIs for the frontend to consume.


## Contact

For any questions or support, please contact:
- **GitHub**: [juaneortiz1](https://github.com/juaneortiz1)

