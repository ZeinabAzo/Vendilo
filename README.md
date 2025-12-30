# 🛒 Vendilo: Advanced Multi-Role E-Commerce Engine

## 🏗️ System Architecture

The system follows a **layered architecture pattern** to ensure separation of concerns:

- **UI Layer (CLI Interface)**  
  Handles user input and displays menus.

- **Controller Layer**  
  Orchestrates logic between the UI and Services.

- **Service Layer**  
  Contains core business logic and functional requirements.

- **Model Layer**  
  Defines core domain entities (Users, Products, etc.).

- **Storage Layer**  
  Handles data persistence using JSON storage.

---

## 🎯 Design Principles

### Strict Object-Oriented Design
- Heavy use of **Inheritance & Polymorphism** to manage complex product types.
- Clear separation of concerns between logic, data, and presentation.

### Modern Object Lifecycle
- **No static methods** or **Singleton patterns**.
- Instance-based object lifecycle management for better testability.

---

## 🧹 Clean Code & Quality Assurance

Vendilo enforces professional-grade Java standards to exceed academic requirements:

- **PMD**  
  Detects code smells and suboptimal programming patterns.

- **Checkstyle**  
  Enforces strict Java naming conventions and formatting.

- **Automated Test Suite**  
  Ensures system correctness and long-term maintainability.

- **High Testability**  
  By avoiding global state and static methods, the system achieves significantly higher testability and scalability.

---

## 👥 User Roles & Permissions

| Role     | Responsibilities |
|----------|------------------|
| Customer | Product search & filtering, wallet management, province-based shipping calculations |
| Seller   | Inventory management (Laptops, Mobiles, Books), revenue tracking (90% profit share) |
| Support  | Ticket resolution system, seller identity verification and onboarding |
| Manager  | Seller performance audits, user management, reward/penalty system |
| Admin    | Full system oversight, credential management, role assignment |

---

## 💎 Vendilo+ Loyalty Club

**Vendilo+ (باشگاه مشتریان)** is a subscription-based loyalty system available through monthly or yearly plans.

### Membership Benefits
- 5% flat discount on all purchases
- Free shipping for same-province orders
- 66% shipping discount for inter-province orders

---

## 🎫 Discount Engine

The system supports multiple dynamic discount strategies:

- **Percentage-based**  
  Discounts applied to the total cart value.

- **Threshold-based**  
  Fixed discounts triggered by high-value orders.

- **Global Announcements**  
  System-wide notifications for discounts issued by Managers.

---

## 📦 Product Hierarchy

The system uses deep inheritance to model products:

- **Product**  
  - ID  
  - Price  
  - Seller  

- **Book**  
  - ISBN  
  - Author  
  - Age Group  

- **DigitalProduct**  
  - RAM  
  - CPU  
  - Network Type  

- **Laptop**

- **Mobile**

---

## 📂 Project Structure

```plaintext
src/
├── controllers/    # Coordinates UI interactions and services
├── models/         # Core domain entities and data structures
├── services/       # Business logic and functional requirements
├── ui/             # CLI hierarchical menu system
└── util/           # Helper utilities (calendar, input helpers)
```

## 🖥️ User Interface

Vendilo features a robust CLI-based interactive menu system designed for professional use:

- **Hierarchical Menus**
    - Deeply nested navigation for complex tasks.

- **Role-Based Access**
    - Menus dynamically change based on the logged-in user role.

- **Input Validation**
    - Strict handling of user input to prevent system crashes.

- **Real-time Notifications**
    - Alerts for stock updates and support ticket status.

  
---

## 🛠️ How to Run
  - Prerequisites
  - Java JDK 17+

**Gradle**

- Run the Application
```plaintext
    ./gradlew run
```
- Run Tests & Quality Checks
```plaintext
    ./gradlew test
```
 
---


## 🎓 Academic Context

- This project was developed at K. N. Toosi University of Technology (KNTU).
- While created as part of the Advanced Programming curriculum, it was intentionally designed to reflect real-world software engineering practices and professional industry standards.

---

## 📄 License
This project is intended for educational and demonstration purposes.
