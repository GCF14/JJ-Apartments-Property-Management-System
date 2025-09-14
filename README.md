# JJ-Apartments-Property-Management-System
![image-alt](https://github.com/GCF14/JJ-Apartments-Property-Management-System/blob/8c1e7c37f5eed204fd75352ab500267741b0797e/%5B3%5D%20JJ%20Apartments%20Logo.png)
> **Note:** This is a fork of the original repository. This version extends and improves upon the original system with additional features and enhancements.
> THIS IS FOR A TEST COMMIT

## Description
This is a property management system designed for JJ Apartments, to help them streamline and digitize the way they manage their properties. The system aims to provide a comprehensive solution for tracking and managing various aspects of apartment rental and maintenance operations for their business.

## Prerequisites
Before running the application, make sure you have the following installed:

- **Java 21** - Required for running the backend Spring Boot application
- **Node.js** - Required for running the frontend Next.js application
- **MySQL** - Required for the database
- **MySQL Workbench** - For importing the database schema

## Database Setup

1. **Install MySQL** and **MySQL Workbench** if you haven't already
2. Create a new database schema in MySQL
3. Import the database schema:
   - Open MySQL Workbench
   - Connect to your MySQL server
   - Navigate to `backend/src/database/jj_apartments.sql`
   - Import/Execute this SQL file to create the required tables and sample data

## Installation & Setup

### Backend Setup (Spring Boot)

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Make sure you have Java 21 installed:
   ```bash
   java -version
   ```

3. Update the database configuration in `src/main/resources/application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. Run the backend application:
   - **Option 1:** Using your IDE (recommended)
     - Open the project in your preferred IDE (IntelliJ IDEA, Eclipse, VS Code)
     - Navigate to `src/main/java/com/jjapartments/backend/BackendApplication.java`
     - Right-click and select "Run Java" or use the run button
   
   - **Option 2:** Using command line
     ```bash
     ./mvnw spring-boot:run
     ```
     (On Windows, use `mvnw.cmd spring-boot:run`)

The backend will start on `http://localhost:8080`

### Frontend Setup (Next.js)

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Create a `.env` file in the frontend directory:
   ```bash
   # Create the .env file
   touch .env
   ```
   
   Add the following content to the `.env` file:
   ```properties
   NEXT_PUBLIC_API_URL=http://localhost:8080
   ```

4. Start the development server:
   ```bash
   npm run dev
   ```

The frontend will start on `http://localhost:3000`

## Running the Application

1. **Start the backend first** by running the `BackendApplication.java` file
2. **Then start the frontend** by running `npm run dev` in the frontend directory
3. Open your browser and navigate to `http://localhost:3000`

## Available Scripts

### Frontend
- `npm run dev` - Starts the development server
- `npm run build` - Builds the application for production
- `npm run start` - Starts the production server
- `npm run lint` - Runs the linter

### Backend
- `./mvnw spring-boot:run` - Runs the Spring Boot application
- `./mvnw clean install` - Builds the project

## API Endpoints
The backend API will be available at `http://localhost:8080/api/` with the following main endpoints:
- `/api/units` - Apartment management
- `/api/tenants` - Tenant management
- `/api/payments` - Payment tracking
- `/api/utilities` - Utility management
- `/api/expenses` - Expense tracking
- `/api/monthlyreports` - Monthly reports

## Troubleshooting

- **Database Connection Issues**: Ensure MySQL is running and the credentials in `application.properties` are correct
- **Port Conflicts**: Make sure ports 3000 (frontend) and 8080 (backend) are available
- **Java Version**: Ensure you're using Java 21 for the backend
- **Node.js Version**: Make sure you have a recent version of Node.js installed

## Technologies Used
- **Frontend**: Next.js, React, TypeScript, Tailwind CSS
- **Backend**: Spring Boot, Java 21
- **Database**: MySQL
- **Build Tools**: Maven (backend), npm (frontend)
