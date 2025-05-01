🍔 Fast Food Ordering System (Java Swing)

A desktop-based fast food ordering system built in Java using MVC architecture. Designed to manage product selection, track orders, and compute totals with plans to support database persistence.

---
📁 Project Structure
src/ ├── controller/ ├── dao/ ├── models/ ├── views/ └── Main.java

- **models/** – Product classes (e.g. `Products`, `SpecialBurger`)
- **controller/** – Business logic (e.g. `OrderController`)
- **views/** – Swing GUI (`OrderingView`)
- **dao/** – Database access classes (planned)

---
🚀 Getting Started

✅ Prerequisites
- Java JDK 8 or higher
- NetBeans / IntelliJ / VS Code (Java enabled)
- Version control systems (GitHub and Git)

---
📜 Team Rules
- No direct pushes to main
- All work must be done in branches → reviewed via Pull Requests
- Use clear branch names: feature/..., bugfix/..., refactor/..., etc.

🔁 Pull Request Workflow
1. Push your branch
2. Open a Pull Request into main
3. A teammate must review and approve before merging.

💬 Commit Messages
Use clear prefixes:
- Refactor:  – Code cleanup
- Feature:  – New feature
- Bugfix:  – Fixing a bug
- Docs:  – Updated docs

⛔ Don'ts
- Don’t push unreviewed code to `main`.
- Don’t commit IDE settings or compiled `.class` files.
- Don’t leave console/debug prints in code.

🧼 Code Style
- Use meaningful method/class names
- Follow MVC separation
- One class = one responsibility

Let’s build something maintainable and professional together.

---
🔧 TODO
General:
Refactor to MVC (Done:peanutbutterchicken)

Database:
Add DAO(database access classes) and SQLite support (On-going:peanutbutterchicken)

Overall design:
Add products on unfinished categories

Features:
Dashboard (Plan: Track sales, orders, customers, stocks, etc.)
  -view
  -model
  -controller

