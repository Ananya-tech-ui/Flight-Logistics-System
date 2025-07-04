# 🛫 Flight Route Optimizer System

**A Java-based flight pathfinder that calculates the cheapest routes between Indian cities**

![Java](https://img.shields.io/badge/Java-11%2B-blue)
[![Open in Colab](https://colab.research.google.com/assets/colab-badge.svg)](https://colab.research.google.com/github/yourusername/repo/blob/main/FlightSystem.ipynb)

## ✨ Features

- **25+ preloaded flight routes** across 10+ Indian cities
- **6 major airlines** supported (Indigo, Vistara, Air India, etc.)
- **Dijkstra's algorithm** for optimal pathfinding
- **Interactive CLI** with city listing capability
- **Colab-compatible** with zero setup required

## 🛠️ Tech Stack

- **Core**: Java 11
- **Algorithm**: Priority Queue-based Dijkstra's
- **Data**: In-memory graph structure

## 📦 Installation

### Google Colab
```python
!apt-get install openjdk-11-jdk-headless -qq
!javac FlightSystem.java
!java FlightSystem
Local Execution
bash
javac FlightSystem.java
java FlightSystem
🧭 Usage
text
🏨 Flight Route Finder 🏨
1. Find cheapest flight
2. List all cities
3. Exit
Choose option: 1
Enter departure city: Delhi
Enter arrival city: Chennai

✈️ Cheapest Route Found ✈️
──────────────────────────
Total Cost: ₹8500
Total Time: 220 minutes
Flight Path:
──────────────────────────
• Delhi → Mumbai
  Indigo (6E-201) | ₹4500 | 120 mins
• Mumbai → Chennai
  Air India (AI-702) | ₹4000 | 100 mins
🌍 Covered Cities
Delhi
Mumbai
Bangalore
Chennai
Hyderabad
Kolkata
Goa
Pune
Kochi
Guwahati

🛩️ Supported Airlines
Airline	Code Prefix
Indigo	6E
Vistara	UK
Air India	AI
SpiceJet	SG
Go First	G8
Air Asia	I5


📂 Project Structure
text
FlightSystem/
├── FlightSystem.java         # Main application
├── Flight.java               # Flight data model
└── Node.java                 # Pathfinding node
🤝 Contributing
Fork the repository
Add new flight routes in initializeFlights()
Submit a pull request
