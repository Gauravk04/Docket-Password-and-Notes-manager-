# 🔐 Docket - Password & Notes Manager

A modern, secure password and notes management application built with Java Swing, featuring Material Design UI and advanced encryption.

## ✨ Features

- 🔑 **Password Generator** - Generate strong, random passwords
- 🔒 **Text Encryption/Decryption** - Secure your sensitive text with AES encryption
- 💾 **Password Storage** - Store and manage account passwords securely
- 🔍 **Password Search** - Quick search for stored passwords
- 🗑️ **Password Management** - Delete unwanted password entries
- 📝 **Notes Management** - Add and retrieve personal notes
- 🎨 **Modern UI** - Beautiful Material Design inspired interface
- ⚡ **Fast & Lightweight** - Quick startup with animated splash screen

## 📁 Project Structure

```
Docket-Password-and-Notes-manager/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── docket/
│                   ├── Docket.java              # Main entry point
│                   ├── SplashScreen.java        # Loading screen
│                   ├── PasswordManagerUI.java   # Main UI
│                   ├── HashtablePassword.java   # Password storage
│                   ├── HashTableMap.java        # Storage interface
│                   ├── CryptoUtil.java          # Encryption utilities
│                   └── PasswordGenerator.java   # Password generation
├── bin/                                         # Compiled classes (auto-generated)
├── run.sh                                       # Run script
└── README.md                                    # This file
```

## 🚀 Quick Start

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Running the Application

**Method 1: Using run.sh (Recommended)**
```bash
# Make script executable (first time only)
chmod +x run.sh

# Run the application
./run.sh
```

The script automatically:
- Creates the `bin/` directory if needed
- Cleans old compiled files
- Compiles all Java source files
- Runs the application

**Method 2: Using IntelliJ IDEA**
1. Open the project in IntelliJ IDEA
2. Navigate to: `src/main/java/com/docket/Docket.java`
3. Right-click → **Run 'Docket.main()'**
4. Or click the green ▶️ play button

**Method 3: Manual Compilation**
```bash
# Compile
javac -d bin src/main/java/com/docket/*.java

# Run
java -cp bin com.docket.Docket
```

## 🔒 Security Features

- **PBE Encryption**: Password-Based Encryption using MD5 and DES
- **Hashtable Storage**: Custom hashtable implementation for efficient password storage
- **Secure Random**: Cryptographically strong random password generation
- **Linear Probing**: Collision resolution for hashtable operations

## 💻 Usage

1. **Generate Password**: Click "GENERATE PASSWORD" and specify desired length
2. **Encrypt Text**: Enter text and secret key to encrypt sensitive information
3. **Decrypt Text**: Provide encrypted text and correct secret key to decrypt
4. **Store Password**: Add account name and password to secure storage
5. **Search Password**: Enter account name to retrieve stored password
6. **Delete Password**: Remove account credentials from storage
7. **Add Note**: Create and save personal notes
8. **Get Note**: Retrieve saved notes

## 🛠️ Technical Details

### Why No Maven/Gradle?

This project uses a simple shell script instead of build tools like Maven or Gradle because:

- ✅ **No External Dependencies** - Project uses only Java standard libraries
- ✅ **Simplicity** - Easy to understand what's happening
- ✅ **Lightweight** - No additional tools to install
- ✅ **Transparency** - See exactly what commands are executed

**Note:** Professional projects with external libraries (JSON parsers, database drivers, etc.) typically use Maven/Gradle for automatic dependency management. Since this project has zero external dependencies, a simple shell script is sufficient and appropriate.

### Build Process

The `run.sh` script does three things:
```bash
1. rm -rf bin/*              # Clean old files
2. javac -d bin src/...      # Compile to bin/
3. java -cp bin com.docket.Docket  # Run
```

That's it! No complex configuration needed.

## 🐛 Troubleshooting

### `.class` files appear in root directory

If you see files like `SplashScreen.class` in the root, delete them:
```bash
rm *.class
```

These should only exist in `bin/`. They were likely created by accidentally running `javac` without the `-d bin` flag. The `.gitignore` file prevents them from being committed.

### Permission denied error

```bash
chmod +x run.sh
```

### "Command not found: javac"

Install JDK (not just JRE):
```bash
# macOS
brew install openjdk

# Check version
java -version
javac -version
```

## 🎨 UI Highlights

- **Material Design** - Clean, modern Google-inspired interface
- **Smooth Animations** - Hover effects, transitions, splash screen
- **Responsive Layout** - Intuitive organization
- **Custom Styling** - Styled buttons, text fields, gradients

## 📝 Development

### Architecture

- **Separation of Concerns** - Each class has single responsibility
- **Clean Build** - Compiled classes isolated in `bin/`
- **Version Control** - Build artifacts ignored by git
- **Organized Structure** - Standard Maven-style directory layout

### Adding Features

1. Create new `.java` file in `src/main/java/com/docket/`
2. Add package declaration: `package com.docket;`
3. Run `./run.sh` to compile and test

## 📄 License

Open source - available for educational purposes.

## 👤 Author

**Gaurav**

---

**Security Note**: This is an educational project. For production use, consider:
- AES-256 encryption instead of PBE with DES
- Secure key storage mechanisms
- Additional authentication layers

