#!/bin/bash

# Docket Password Manager - Run Script
# This script compiles and runs the Docket application

echo "🔐 Docket Password & Notes Manager"
echo "===================================="
echo ""

# Navigate to project root
cd "$(dirname "$0")"

# Create bin directory if it doesn't exist
if [ ! -d "bin" ]; then
    mkdir bin
    echo "📁 Created bin directory"
fi

# Clean old compiled files
echo "🧹 Cleaning old compiled files..."
rm -rf bin/*

# Compile all Java files
echo "🔨 Compiling source files..."
javac -d bin src/main/java/com/docket/*.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
    echo ""
    echo "🚀 Launching Docket..."
    echo "===================================="
    echo ""

    # Run the application
    java -cp bin com.docket.Docket
else
    echo "❌ Compilation failed!"
    echo "Please check the error messages above."
    exit 1
fi

