# 🎴 UNO Game

A console-based **UNO card game developed in Java**.
The project simulates a complete UNO game for two to four players, with support for local multiplayer and computer-controlled opponents.

Players receive seven cards and take turns playing a card that matches the colour, number, or type of the card on the table. The first player with no cards remaining wins the game.

This project was developed to practise and demonstrate fundamental **object-oriented programming concepts in Java**, including classes, objects, inheritance, interfaces, arrays, methods, and game logic.

## 🎥 Demo

▶️ **Project demonstration:** [Watch the video on YouTube]([https://youtu.be/tVjJWD1qJdo?si=cFUV0U8g9HFrzZ1L])

The video can demonstrate creating a game, playing cards, drawing cards, using action cards, choosing wild-card colours, and playing against bots.

---

## 📌 Features

### 1. 🎮 Two Game Modes

The game provides two ways to play:

* **Play with friends** - all players take turns using the same console.
* **Play with computer** - the first player is human and the remaining players are bots.

The game supports **2, 3, or 4 players**.

---

### 2. 🃏 Standard UNO Deck

The application creates and shuffles a standard deck of **108 UNO cards**.

The deck includes:

* Four colours: red, green, blue, and yellow
* Number cards from 0 to 9
* Skip cards
* Reverse cards
* Draw Two cards
* Wild cards
* Wild Draw Four cards

Each player starts with seven cards.

---

### 3. ✋ Human Player Turns

During a turn, a player can:

* View the current top card.
* View the cards in their hand.
* Choose a card by its displayed index.
* Draw one card when needed.
* Choose a colour after playing a Wild or Wild Draw Four card.

A card is playable when it matches the top card's colour or number, or when it is a wild card.

---

### 4. 🤖 Computer Players

Bots automatically manage their turns.

They:

* Play the first playable card in their hand.
* Draw a card if no card can be played.
* Play a drawn card when it is playable.
* Choose the colour that appears most often in their hand after playing a wild card.

---

### 5. ⚡ Action Cards

The game handles the effects of UNO action cards:

| Card | Effect |
| --- | --- |
| Skip | Skips the next player's turn. |
| Reverse | Changes the direction of play. |
| Draw Two | The next player draws two cards and loses their turn. |
| Wild | Lets the current player choose the active colour. |
| Wild Draw Four | Lets the player choose a colour and makes the next player draw four cards. |

---

### 6. 🏆 Win Condition

After every turn, the game checks the number of cards remaining in the player's hand.

When a player has no cards left, the game announces that player as the winner and ends the session.

---

## 🧠 Concepts Demonstrated

This project focuses on core Java programming and object-oriented design.

### Java Programming

* Classes and objects
* Constructors
* Encapsulation using private attributes and getters/setters
* Arrays
* Methods and method parameters
* Conditional statements and loops
* User input with `Scanner`
* Randomisation with `Random`
* Modular program design

### Object-Oriented Programming

* **Inheritance** - `botPlayer` extends `Player`.
* **Method overloading** - players and bots use different `playCard` methods.
* **Interfaces** - `Game` implements `GameOperations`.
* **Encapsulation** - card, deck, player, and game data are organised in separate classes.

---

## 🏗️ Data Model

The project uses the following main classes.

### `Card`

Represents one UNO card and stores:

* Colour
* Value
* Type, such as `number`, `SKIP`, `REVERSE`, or `WILD`

It also checks whether the card can be played on the current top card.

### `Deck`

Represents the 108-card UNO deck. It is responsible for:

* Creating the complete deck
* Shuffling cards
* Dealing seven cards to each player
* Drawing cards during the game

### `Player`

Represents a human player. It stores a name and a hand of cards, and can add, play, count, and display cards.

### `botPlayer`

Extends `Player` to represent a computer-controlled opponent. It selects a playable card automatically and chooses a colour for wild cards.

### `Game`

Controls the game flow, including player turns, turn direction, the top card, and special-card effects.

### `GameOperations`

Defines the main game operations:

```java
Player start();
Card getTopCard();
Player playerTurn(Player player);
```

---

## 🔄 Game Workflow

The game follows this flow:

```text
Start game
    |
    v
Choose game mode and player count
    |
    v
Create and shuffle the UNO deck
    |
    v
Deal 7 cards to every player
    |
    v
Start a player's turn
    |
    v
Can the player play a card?
   /                  \
 Yes                  No
  |                    |
  v                    v
Play a valid card    Draw one card
  |                    |
  v                    v
Apply card effect   End turn or play drawn card
  |
  v
Does the player have 0 cards?
   /                  \
 Yes                  No
  |                    |
  v                    v
Announce winner     Move to the next player
```

---

## 🛠️ Technologies

* **Language:** Java
* **Libraries:** `java.util.Scanner`, `java.util.Random`
* **Application type:** Console application
* **Programming style:** Object-oriented programming

---

## 🚀 How to Run

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

### 2. Navigate to the project

```bash
cd YOUR_REPOSITORY
```

### 3. Compile the program

From PowerShell, run:

```powershell
javac -d out (Get-ChildItem "Code Source" -Filter *.java).FullName
```

### 4. Run the game

```powershell
java -cp out uno.Main
```

Follow the console prompts to select a game mode, enter the number of players, and provide player names.

---

## 📂 Project Structure

```text
UNO-Game/
|
|-- Code Source/
|   |-- Main.java              # Program entry point
|   |-- Game.java              # Game flow and turn handling
|   |-- GameOperations.java    # Game interface
|   |-- Card.java              # UNO card model
|   |-- Deck.java              # Deck creation, shuffling, and drawing
|   |-- Player.java            # Human player model
|   `-- botPlayer.java         # Computer player behaviour
|
|-- UML.pdf                    # UML class diagram
`-- README.md
```

---

## ⚙️ Current Limitations

This is an educational console project focused on Java and object-oriented programming.

Current limitations include:

* The game uses a console interface only.
* Cards are stored in memory and are not saved after the program closes.
* There is no graphical interface.
* Bot strategy is simple: a bot chooses the first playable card.
* The game does not include score tracking across multiple rounds.

---

## 🔮 Possible Improvements

Future versions could include:

* A graphical user interface using JavaFX or Swing
* Score tracking and multiple rounds
* Smarter bot strategies
* Saving and loading games from files
* Sound effects and card graphics
* Online multiplayer support
* Better input validation
* A game history or statistics screen

---

## 🎯 Purpose of the Project

The main goal of this project was to apply **Java programming and object-oriented programming concepts** to an interactive game.

Rather than using isolated class examples, the project combines cards, a deck, players, bots, and game rules into a complete console application where every class has a clear responsibility.

---

## 👩‍💻 Author

**[Your Name]**

Replace this text with your name and any details you would like to share.

---

⭐ If you find this project interesting, feel free to explore the source code and watch the demonstration video.

**[▶️ Watch the Project Demo on YouTube](YOUR_YOUTUBE_LINK_HERE)**
