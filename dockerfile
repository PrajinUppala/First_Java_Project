FROM python:3.10-slim

WORKDIR /app

COPY . /app

# Install Java and Ant for building the Java class
RUN apt-get update && apt-get install -y default-jdk ant && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Compile the Java code during build time if needed (optional)
# RUN ant clean compile

# Default run command – lets you pass args at runtime
ENTRYPOINT ["python3", "MyInfArith.py"]
