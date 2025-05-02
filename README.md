# ArbitraryArithmetic

## Overview

ArbitraryArithmetic is a Java library and command-line tool designed to handle arithmetic operations on integers and floating-point numbers with unlimited precision. It overcomes the constraints of Java’s built-in numeric types by implementing string-based arithmetic methods.

---

## Features

- Infinite-precision support for:
  - *Integers* (AInteger.java)
  - *Floating-point numbers* (AFloat.java)
- Operations:
  - Addition (add)
  - Subtraction (sub)
  - Multiplication (mul)
  - Division (div)
- Java CLI runner: MyInfArith.java
- Apache Ant-based build automation
- Docker support
- Python script which compiles the code using Ant-based build automation and runs the code with taking input arguments

---


## Build & Run

### Option 1: Using the Python Script (run.py)

This script compiles source files, and optionally runs the program if arguments are passed.

#### Run with Arguments

bash
python3 run.py int mul 123456789 987654321


---

### Option 2: Using Docker
#### Build docker
sudo docker build -t javaproject .
#### Run Program

sudo docker run --rm java-inf-arith float div 244727.15202 75964.3891


---

## Example CLI Usage

### compiling the files with ant
ant compile
### removing precompiled files
ant clean

---
