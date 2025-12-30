Vendilo: Advanced Multi-Role E-Commerce Engine (Java)
📌 Project Overview
Vendilo (وندیلو) is a sophisticated, full-scale e-commerce simulation developed at K. N. Toosi University of Technology (KNTU). Moving far beyond a simple classroom assignment, this project implements a complex ecosystem with five distinct user roles, dynamic financial logic, and a subscription-based loyalty program.

The project was developed in two major phases (V2 and V3), evolving from a basic marketplace into a managed platform with administrative oversight and automated notifications.

🏗️ Architecture & Clean Code Standards
This project was built with a heavy focus on Scalable Architecture and Clean Code principles.

Strict OOP: Advanced use of Inheritance and Polymorphism to manage diverse products (Digital vs. Books) and user roles.

No Statics Policy: Per the strict requirements, the project avoids static methods and the Singleton pattern in favor of instance-based logic and proper object lifecycle management.

Automated Quality Assurance: The project includes a dedicated test suite featuring:

PMD Tests: Automated scans for "Code Smells" and suboptimal programming patterns.

Checkstyle: Strict adherence to industry-standard Java naming conventions and formatting.

JSON Data Persistence: Custom data-loading layer (loaddb) to manage persistent storage for Users, Products, and Transactions.

🚀 Key Features
👥 User Roles & Permissions
Customer: Advanced product searching/filtering, Province-based shipping calculation, and Wallet management.

Seller: Inventory management for specific product types (Laptops, Mobiles, Books) and revenue tracking (90% profit share).

Support: Ticket resolution system and Seller identity verification.

Manager: Global user management, performance auditing for sellers, and reward/penalty systems.

Admin: Full system oversight and credential management.

💎 The Vendilo+ System
A comprehensive Loyalty Club (باشگاه مشتریان) where users can purchase monthly/yearly subscriptions to unlock:

A flat 5% discount across the entire catalog.

Subsidized Shipping: Free shipping for local province orders and 66% discounts for inter-province orders.

🎫 Dynamic Discount Engine
Percentage-based codes: Reductions on the total cart value.

Threshold-based codes: Fixed discounts for high-value orders.

Global Announcements: Manager-issued discount codes that trigger system-wide notifications.

📦 Product Hierarchy
Sophisticated modeling of products using deep inheritance:

Digital Products: Specific logic for RAM, CPU models, and Network types (Mobiles/Laptops).

Books: Management of ISBNs, Authors, and Age Group categories.

📂 Project Structure
As seen in the source code:

controllers/: Orchestrates logic between the UI and Data layers.

models/: Defines the core entities (User, Product, Order, etc.).

services/: Core business logic (Authentication, Search, Management).

ui/: A robust hierarchical menu system for a CLI-based interactive experience.

util/: Helper utilities including a custom Calendar for time-sensitive logic simulation.

🛠️ How to Run
Ensure you have JDK 17+ and Gradle installed.

Clone the repository.

Run the application:

Bash

./gradlew run
To run the Clean Code quality checks:

Bash

./gradlew test
