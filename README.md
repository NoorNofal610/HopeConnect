<div align="center">
    <img src="https://github.com/NoorNofal610/HopeConnect/blob/main/hopeConnect.png" alt="HopeConnect" width="500" height="400">
    <h1>Lighting the Path of Hope</h1>
    <a href="#" style="margin-right: 60px;">👾 View Demo</a>
    <a href="https://github.com/NoorNofal610/HopeConnect/issues/new?labels=bug" target="_blank">🐞 Report Bug</a>
</div>

# 🌟 About the Project

**HopeConnect** is a comprehensive backend API designed to support orphaned children in Gaza following the recent war.
The platform aims to bridge the gap between donors, sponsors, volunteers, and orphanages by providing a secure, transparent, and efficient system for managing donations, sponsorships, and volunteer services.
By leveraging modern API technologies and robust database management, HopeConnect ensures that resources reach the children who need them most while maintaining trust and accountability among all stakeholders.

  
# 📋 Table of Contents
• [ About the Project](#-about-the-project)  
• [ Built With](#-built-with)  
• [ Getting Started](#-getting-started)  
• [ Main Features](#-main-features)  
• [ Roles](#-roles)  
• [ Run on Postman](#-run-on-postman)  
• [ Contact](#-contact)
  
# 🔨 Built With

### SPRING BOOT

An open-source Java framework for creating stand-alone, production-grade applications.

### MYSQL

A reliable, open-source relational database management system commonly used for storing and managing data in web applications.

## 🧪 Run on Postman
A collaboration platform for designing, testing, and documenting APIs.

### GITHUB

A web-based platform for version control and collaboration using Git.

# 🚀 Getting Started

## ⚙️ Running the project

**To get started with the project:**

**1. Clone the repository:**
[https://github.com/NoorNofal610/HopeConnect](https://github.com/NoorNofal610/HopeConnect)

**2. Install Dependencies**

Make sure you have Maven installed. Run the following command to install the necessary dependencies:

```
  sh mvn clean install  
```

**3. Create The Database:**

* Make sure MySQL is installed and running on your local machine.

* Create a new database for the project:

  ```
  CREATE DATABASE demo;
  ```

* Update the application.properties or application.yml file in the src/main/resources directory with your MySQL database credentials (username and password).

  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/demo
  spring.datasource.username=your_mysql_username
  spring.datasource.password=your_mysql_password
  ```

**4. Run The Application:**

```
  sh mvn spring-boot:run  
```

# 💡 Main Features

## 📑 Orphan Profiles & Sponsorships

**Main Features:**

Orphan profiles with details (name, age, education status, health condition).
Sponsorship models (one-time, recurring, or targeted donations).
Real-time updates (photos, progress reports, medical updates) for sponsors.

**Overview:**

This feature ensures donors can track the well-being of sponsored children, fostering trust and long-term engagement.

## 💰 Donation Management System

**Main Features:**

Multiple donation categories:
General Fund (daily needs)
Education Support (tuition, books)
Medical Aid (healthcare costs)
Payment gateway integration (Stripe, PayPal, etc.).
Donation tracking with impact reports.

**Overview:**

Enables secure, flexible donations while providing transparency on fund utilization.

## 🛠️ Volunteer & Service Matching

**Main Features:**

Volunteer registration (skills, availability).
Orphanage requests (medical, educational, or mentorship needs).
Automated matching system to connect volunteers with opportunities.

**Overview:**

Optimizes resource allocation by linking volunteers with relevant tasks.

## 👍 Trust & Transparency

**Main Features:**

Donor dashboard with fund utilization reports.
Verified orphanages/NGOs to prevent fraud.
Review & rating system for organizations.

**Overview:**

Builds donor confidence through accountability and feedback mechanisms.

## 🚨 Emergency Support System

**Main Features:**

Urgent relief campaigns (food shortages, medical crises).
Email notifications for critical situations.

**Overview:**

Ensures rapid response to emergencies with real-time donor alerts.

## 🚚  Logistics & Resource Distribution

**Main Features:**

Real-time tracking of physical donations (food, clothes).
Pickup/delivery coordination for donors and orphanages.

**Overview:**

Enhances supply chain efficiency for humanitarian aid.

## 💸 Revenue Model & Sustainability

**Main Features:**

Small transaction fees to sustain operations.
Partnerships with NGOs and charities.

**Overview:**

Ensures long-term platform viability without relying solely on donations.

# 👥 Roles:
## 1. Admin
 
**Responsibilities:**

Manages user accounts (approval, suspension, deletion).
Verifies orphanages and NGOs to prevent fraud.
Monitors donations, sponsorships, and volunteer activities.
Generates reports (financial, impact analytics).
Handles emergency campaign setups.  

**Permissions:**

Full CRUD (Create, Read, Update, Delete) access to all data.
Can modify system settings.
Access to audit logs for security monitoring.  

## 2. Donor/Sponsor

**Responsibilities:**

Makes financial donations (one-time or recurring).
Sponsors specific orphan(s) with regular contributions.
Tracks donation impact (receives reports, updates).
Leaves reviews/ratings for orphanages/NGOs.  

**Permissions:**

View orphan profiles (non-sensitive data).
Access donation history & receipts.
Subscribe to emergency notifications.  

## 3. Volunteer

**Responsibilities:**

Registers skills, availability, and interests.
Applies for volunteer opportunities (teaching, medical aid, etc.).
Updates service logs (hours worked, tasks completed).  

**Permissions:**

Browse open volunteer requests.
Communicate with orphanage coordinators.
Log volunteer activities for transparency.  

## 4. Orphanage/NGO Representative

**Responsibilities:**

Registers orphan profiles (with consent).
Posts donation/volunteer requests (e.g., "Need winter clothes").
Submits progress reports for sponsored children.
Manages in-kind donation distributions.  

**Permissions:**

Add/edit orphan details (restricted to their facility).
Post urgent needs (food, medicine).
Respond to volunteer applications.   

## 🧪 Run on Postman
<a href="https://www.postman.com/blue-resonance-997013/workspace/advance-software/collection/43341860-7927cbaa-2b93-4e5d-9958-78755f8eeb29?action=share&creator=43341860" target="_blank">
  <button style="background-color: #ff6c37; color: white; padding: 10px 16px; border: none; border-radius: 4px; font-size: 14px; cursor: pointer;">
    Show All API on Postman
  </button>
</a>

# 📱 Contact

 - [Compose to Rema via Gmail](https://mail.google.com/mail/?view=cm&fs=1&to=remaabualnaser570@gmail.com)
- [Compose to Noor via Gmail](https://mail.google.com/mail/?view=cm&fs=1&to=noornofal610@gmail.com)
