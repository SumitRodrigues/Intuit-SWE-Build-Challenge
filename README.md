# Intuit Build Challenge

![Java](https://img.shields.io/badge/Java-21-orange)
![Maven](https://img.shields.io/badge/Build-Maven-blue)
![JUnit](https://img.shields.io/badge/Tests-JUnit5-green)
![License](https://img.shields.io/badge/License-MIT-lightgrey)
![Status](https://img.shields.io/badge/Build-Passing-brightgreen)

This repository contains both assignments required for the **Intuit Build Challenge**.  
Each assignment is implemented in **Java**, follows clean **OOP principles**, uses **Maven**, and includes full **JUnit 5** test coverage.

---

# Table of Contents
1. [Assignment 1 – Producer–Consumer (pc001)](#assignment-1--producerconsumer-pc001)  
2. [Assignment 2 – Sales Analytics (sa001)](#assignment-2--sales-analytics-sa001)  
3. [Tech Stack](#tech-stack)  
4. [Author](#author)

---

# Assignment 1 – Producer–Consumer (pc001)

## Overview

This assignment implements a classic producer–consumer pattern, demonstrating thread synchronization and communication.

The program simulates concurrent data transfer between:
- a producer thread that reads from a source container and places items into a shared queue, and
- a consumer thread that reads from the queue and stores items in a destination container. 

To meet the required testing objectives, the system includes:
- Thread synchronization
- Concurrent programming
- Blocking queue behavior (via a custom bounded buffer)
- Wait/Notify mechanism (classic implementation included)

---

## Key Features

### **Bounded Blocking Buffer**
Shared `Queue<Integer>` with a fixed capacity ensures producers cannot overflow the buffer.

### **Thread Synchronization**
- Producers **wait** when the buffer is full  
- Consumers **wait** when the buffer is empty  
- Uses `synchronized` blocks for mutual exclusion  
- Uses `notifyAll()` to awaken waiting threads safely  

### **Concurrent Programming**
The system runs producer and consumer threads concurrently, demonstrating proper thread lifecycle management and inter-thread communication.
- Implemented using ExecutorService (modern)  
- Classic version uses raw Java threads

### **Blocking Queue Behavior**
A custom bounded blocking buffer ensures proper backpressure:
- Producer blocks when the buffer reaches capacity
- Consumer blocks when buffer is empty
- Ensures controlled, deadlock-free data flow

### **Wait/Notify Mechanism**
A separate ProducerConsumerWaitNotify implementation demonstrates:
- synchronized blocks
- wait() to suspend threads
- notifyAll() to resume thread execution

### **Traceable Output**
Console logs clearly show production and consumption workflow.

### **JUnit Tests**
Tests validate:
- Buffer never exceeds capacity  
- Producer/consumer wait behavior  
- Concurrency correctness  
- No deadlocks in repeated execution  

---

## Run the Program

```bash
cd Intuit-assignment1-pc001-producer-consumer
mvn clean install
mvn exec:java -Dexec.mainClass="com.intuit.pc001.ProducerConsumerWaitNotify"
```
## Output:
<img width="1384" height="644" alt="Screenshot 2025-11-16 at 8 13 17 PM" src="https://github.com/user-attachments/assets/6d7ffe7e-fe1e-4d7a-a455-5463789afe7c" />


## Run Tests

```bash
mvn test
```

# Assignment 2 – Sales Analytics (sa001)

## Overview

This module reads sales data from a CSV file and performs multiple analytical queries using **functional programming paradigms**, **Stream operations**, **lambda expressions**, and **data aggregation techniques**, exactly as required in the problem statement.

The implementation demonstrates proficiency with the Java Streams API by executing various **aggregation and grouping operations** on structured sales data.

## Features & Highlights

### **Strongly-Typed POJO Model**
`SalesRecord` represents each row of the CSV dataset with fields for:
- Order ID  
- Customer  
- Region  
- Product  
- Quantity  
- Unit Price  
- Date  

The model is immutable, ensuring safe and predictable functional transformations during analysis.

### **SalesAnalyzer Service**

Implements all analytical operations using **functional programming**, **Stream pipelines**, and **lambda expressions**, including:
- total revenue calculation  
- revenue grouped by product  
- order count grouped by region  
- highest-revenue product lookup  
- average order value computation  

All analytics use **aggregation and grouping** as described in the assignment’s detailed instructions.

### **Robust CSV Parsing**

Handles:
- Numeric parsing errors  
- Missing or malformed fields  
- Irregular CSV formatting  
- Safe fallback behavior with descriptive error messages  

The parsed data is converted into immutable POJOs before being processed by Stream operations.

### **Full JUnit Test Coverage**

Unit tests validate:
- Correctness of aggregation logic  
- Stream-based grouping operations  
- Behavior with empty or invalid datasets  
- Functional programming flows  
- Lambda-driven data transformations  

These tests ensure the application meets all four testing objectives:  
**functional programming**, **Stream operations**, **data aggregation**, and **lambda expressions.**

## Example Input (sales.csv)
```bash
OrderId,Customer,Region,Product,Quantity,UnitPrice,Date
1001,Mark West,West,Keyboard,2,30.00,2024-01-14
1002,Ana Singh,East,Mouse,1,20.00,2024-01-20
```

## Run the Program
```bash
cd Intuit-assignment2-sa001-sales-analytics
mvn clean install
mvn exec:java -Dexec.mainClass="com.intuit.sa001.main"
```

## Output:
<img width="1315" height="574" alt="Screenshot 2025-11-16 at 8 02 21 PM" src="https://github.com/user-attachments/assets/2a981e79-c0ed-4f90-ac42-0633748f17ba" />


## Run Tests
```bash
mvn test
```

## Tech Stack

- Java 21
- Maven (modular multi-project setup)
- JUnit 5
- Java Concurrency (synchronized, wait(), notifyAll())
- Java I/O & NIO
- Java Streams API
- OOP / Immutability / Clean Code
- Error Handling & Unit Testing Best Practices

## Author

**Sumit Rodrigues**

**GitHub: https://github.com/SumitRodrigues**
**Email: sumitrod11@gmail.com**
