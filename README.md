# CLI Management System

## Overview
This project is a Command-Line Interface (CLI) Library Management System built in Java with
functionality around the book and members models (Library System) to show my knowledge about OOP in Java. It provides an intuitive way to manage 
a library's collection of books, register members, and track book borrowing and returns. The system ensures efficient data management and data persistence while
using repositories and follows an object-oriented design approach.

### Key Objectives:
- Provide a simple yet robust system for library management.
- Maintain structured and scalable code using the repository pattern.
- Allow easy modification and expansion for future enhancements.
- Provide persistence of data for the cli application

## Features
- Add, remove, and update books
- Register and manage library members
- Borrow and return books with a ledger system
- Repository pattern for managing data
- Input sanitization and proper error handling of data.

## Project Structure
```
cli-managent-system/
├── src/
│   ├── Main.java
│   ├── models/
│   │   ├── Book.java
│   │   ├── Member.java
│   │   ├── Ledger.java
│   │   ├── Entity.java
│   │   ├── BookMember.java
│   ├── repositories/
│   │   ├── BookRepository.java
│   │   ├── MemberRepository.java
│   ├── service/
│   │   ├── LibraryManager.java
│   │   ├── Menu.java
│   │   ├── PersistData.java
```

## Code Explanation
### Main Entry Point
The application starts in [`Main.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/Main.java), which initializes the system and provides the command-line interface for user interaction.


### Models
- [`Book.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/models/Book.java) – Represents a book entity.
- [`Member.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/models/Member.java) – Represents a library member.
- [`Ledger.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/models/Ledger.java) – Manages book borrowing.
- [`BookMember.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/models/BookMember.java) - Represents a record linking the library member who borrrowed book to the book borrowed.
- [`Entity.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/models/Entity.java) - Represents an interface which models that need persistence implements the interface to allow smooth conversion of string data to entity data from persistenc.

### Repositories
- [`BookRepository.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/repositories/BookRepository.java) – Handles book data storage and retrieval.
- [`MemberRepository.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/repositories/MemberRepository.java) – Manages library members.

### Business Logic
- [`LibraryManager.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/service/LibraryManager.java) – Contains the core logic for borrowing and returning books.
- Members can only borrow book that are available

### Data Persistence
To ensure that the system retains book and member records even after the application is closed, data is stored using a persistence mechanism in [`PersistData.java`](https://github.com/sarahelhnbaly/cli-managent-system/blob/master/src/service/PersistData.java). This class manages the saving and loading of data, ensuring:
- Books and members and history are stored in a Comma Seperated Value Files (csv).
- Data is retrieved at startup to restore the system state.
- Changes (additions, deletions, updates) are committed to persistent storage on program ending.


## Setup & Usage
1. Clone the repository:
   ```sh
   git clone https://github.com/sarahelhnbaly/cli-managent-system.git
   ```
2. Compile and run the project:
   ```sh
   javac src/Main.java
   java src/Main
   
   ```
##Testing Video:
To see the project in action, please watch the testing video here: [https://drive.google.com/file/d/1yDnSRFCXrfbw443GgrvAgZxYOO2lDtR5/view?usp=sharing].


