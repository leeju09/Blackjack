# Blackjack Game

A Java-based implementation of the classic **Blackjack** game. This project demonstrates the application of the Single Responsibility Principle (SRP) and clean object-oriented design (composition over inheritance).

Built for **Unit 8: Classes - A deeper look (Enums, Records, and Associations)**

Assignment at North Hennepin Community College

Course: CSCI 2001-52 Object Oriented Programming

Fall Semester 2026


## Learning Objectives

This project was built to demonstrate and master several core concepts from the Unit 8 curriculum:

*   **Enumerated Types (Enums):** Successfully created custom `Suit` and `Rank` enums with parameterized constructors, fields, and methods to guarantee only valid cards can exist.
*   **Collections (`ArrayList`):** Used `ArrayList<Card>` to dynamically store the deck and the hands, allowing cards to be dealt and removed at runtime.
*   **Algorithmic Problem Solving:** Implemented the **Fisher-Yates shuffle** algorithm to ensure an unbiased randomization of the deck, and correctly handled the flexible value of the Ace.
*   **Object-Oriented Design (SRP):** Strictly separated data (`Card`, `Hand`), logic (`Deck`), and execution (`Game`, `TestBlackjack`) so no class took on too many responsibilities.
*   **Composition over Inheritance:** Used aggregation to pass the deck and hands into the Game class, keeping the architecture modular and avoiding tangled inheritance trees.
*   **Method Decomposition:** Broke complex actions (like the player's turn and the dealer's turn) into small, readable, single-purpose methods.

## Overview

Blackjack is a comparing card game between one or more players and a dealer. The goal is to get a hand total as close to 21 as possible without going over. This project implements a fully interactive console-based version of the game.

### Implemented Classes
1.  **Card (`Card.java`)**: Represents a single immutable playing card. It knows its suit and rank, but knows nothing about the game rules.
2.  **Deck (`Deck.java`)**: Creates all 52 unique cards, shuffles them using Fisher-Yates, and deals from the top of the deck.
3.  **Hand (`Hand.java`)**: Manages the cards held by the player or dealer. It calculates the total score, including the complex rule of the Ace changing from 11 to 1.
4.  **Game (`Game.java`)**: The brain of the application. It coordinates the deck and hands, handles player input, and evaluates the win, loss, or push conditions.
5.  **Main (`TestBlackjack.java`)**: The entry point. It launches the game and holds no state or logic.

## Screenshots

### Console Output
*(Insert your console output screenshot here)*

## Extra Features (Beyond the Rubric)

*   **Formatted Console Output**: The console output is cleanly formatted to hide the dealer's hole card until the reveal, and prints the hand totals dynamically.
*   **Explicit SRP Documentation**: Every class is documented with a `Why:` header to clearly define why certain decisions were made and its single responsibility.
*   **Pattern Matching Logic**: Originally designed using advanced switch expressions, refactored into classic `if/else` statements for maximum portability across all Java environments.

## Project Architecture

The project strictly follows the **Single Responsibility Principle** and avoids inheritance in the game logic by using **Aggregation**.

| File | Responsibility |
| :--- | :--- |
| `Card.java` | **Data:** Manages the immutable suit and rank of a card. |
| `Suit.java` | **Data:** Enforces the four valid suits. |
| `Rank.java` | **Data:** Enforces the valid card ranks and their point values. |
| `Deck.java` | **Data/Rules:** Creates the 52 cards and handles Fisher-Yates shuffling. |
| `Hand.java` | **Data/Logic:** Calculates the total score and handles Ace logic. |
| `Game.java` | **Driver/Logic:** Coordinates the flow, handles input, and determines the winner. |
| `TestBlackjack.java` | **Driver:** Executes the game. |
