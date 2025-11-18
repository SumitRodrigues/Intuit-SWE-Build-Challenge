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

This module implements the classic **Producer–Consumer concurrency pattern** using:
- Java threads  
- Low-level synchronization (`wait()` / `notifyAll()`)  
- A bounded shared queue  
- Deadlock-free coordination logic  
- Full JUnit test coverage  

This assignment demonstrates thread-safe operations, proper signaling between producers and consumers, and controlled access to a shared resource.

---

## Key Features

### **Bounded Blocking Buffer**
Shared `Queue<Integer>` with a fixed capacity ensures producers cannot overflow the buffer.

### **Thread Synchronization**
- Producers **wait** when the buffer is full  
- Consumers **wait** when the buffer is empty  
- Uses `synchronized` blocks for mutual exclusion  
- Uses `notifyAll()` to awaken waiting threads safely  

### **Concurrency Reliability**
- Prevents race conditions  
- Avoids deadlocks  
- Ensures consistent state transitions  

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

This module reads a sales.csv file and computes analytics using:
- Java File I/O
- Java Streams API
- Immutable POJO modeling
- Aggregation pipelines
- Clean exception handling
- JUnit test validation

It demonstrates structured data parsing, stream processing, and safe data transformations.

## Features & Highlights
### **Strongly-Typed POJO Model**
### SalesRecord represents each row with fields:
- Order ID
- Customer
- Region
- Product
- Quantity
- Unit Price
- Date

Immutable fields ensure safe and predictable operations throughout processing.

### **SalesAnalyzer Service**
### Provides:
- calculateTotalRevenue()
- calculateTotalQuantity()
- revenueByProduct()
- sortByRevenue()
- filterByRegion()

All implemented with modern Java Stream operations.

### **Robust CSV Parsing**
### Handles:
- Invalid numeric values
- Missing fields
- CSV formatting errors
- Graceful failures with helpful error messages.

### **Full JUnit Tests**
### Covers:
- Revenue correctness
- Quantity aggregation
- Empty dataset behavior
- Invalid input scenarios
- Product/region analytics

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
