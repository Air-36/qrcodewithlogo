# QR Code with Logo Extension for MIT app Inventor
  This Code may not be that efficient and any suggestions or changes to the code are welcome.

## Table of Contents
- [Setup  and installation](#setup-and-installation)   
- [Generating aix method-1](#generating-aix-method-1)   
- [Generating aix method-2](#generating-aix-method-2-efficient)   
- [Contact](#contact)   

## Setup and Installation
Follow the steps to buildup environment for creating *.aix from the above java code:
1. Download [App Inventor extension Template](https://github.com/mit-cml/extension-template) 
   and add [ZXing JAR](https://repo1.maven.org/maven2/com/google/zxing/core/3.5.1/core-3.5.1.jar) to
   ```pgsql
   /extension-template-master/libs/
   ```
2. Download and install [Java SE developer kit](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
3. Open System Properties → Environment Variables
4. Add a new system variable:
    ```cs
    Variable name: JAVA_HOME   
    Value: C:\Program Files\Java\jdk1.8.0_361 (or your actual JDK path)
    ```
   (<span style="color:red">Note: </span>don't just copy path as version may vary, check for your version)

5. Find the Path variable → Edit → Add:
     ```
      %JAVA_HOME%\bin
     ```
6. You can verify if it works by opening command prompt and run:
     ``` bash
      java -version
      javac -version
      echo %JAVA_HOME%
     ```
    Expected output
    ``` nginx
      javac 1.8.0_361
     ```
7. Download and install [Apache Ant](https://ant.apache.org/bindownload.cgi)(Link for Windows)   
   For Linux(Ubuntu):
   ```bash
   sudo apt update
   sudo apt install ant openjdk-11-jdk
   ```
   For macOS:
   ```bash
   brew install ant
   ```
   If you don’t have Homebrew:
   ```cs
    /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```
8. Open System Properties → Environment Variables
9. Add a new system variable:
    ```cs
    Variable name: ANT_HOME
    Value: C:\Ant\apache-ant-1.10.14 (or your path)
    ```
   (Note: don't just copy path as version may vary, check for your version)

10. Find the Path variable → Edit → Add:
     ```
      %ANT_HOME%\bin
     ```
11. You can verify if it works by opening command prompt and run:
     ``` nginx
      ant -version
     ```
    Expected output
    ``` scss
      Apache Ant(TM) version 1.10.14 compiled on ...
     ```

## Generating aix method 1
 1.clone repo using command prompt or dowloading [zip](https://github.com/Air-36/qrcodewithlogo)
   ```bash
   git clone https://github.com/Air-36/qrcodewithlogo
   ```
 2. Copy paste QRCodeWithLogo.java to 
   ```swift
   \extension-template-master\src\com\example\qrcodewithlogo\
   ```
   if com doesn't exist in src, create one.
 3. Open terminal in the root of the template and run:
   ```nginx
   ant extensions
   ```
 4. The .aix will be created inside build/ folder.

## Generating aix method 2 (efficient)
 1. Build App Inventor’s Full Build Environment by pasting below command: 
   ```bash
   git clone https://github.com/mit-cml/appinventor-sources
   cd appinventor-sources
   ```
 2. Copy paste QRCodeWithLogo.java to 
   ```pgsql
   \appinventor-sources\appinventor\components\src\com\example\qrcodewithlogo\
   ``` 
   if com doesn't exist in src, create one.
3. open command prompt at 
   ```swift
   \appinventor\components\
   ```
   <span style="color:red">Note: </span>the **Path is incorrect** (for any above steps)
   ```
   \appinventor-sources\appinventor\build\components\
   ```
4. Run the command 
   ```nginx
   ant clean
   ant extensions
   ``` 
   to remove any existing file and rebuild new files
5. If build was successful
   find *.aix files at:
   ```swift
   C:\Users\shrin\Downloads\appinventor-sources\appinventor\components\build\extensions\
   ```

## Contact 
  __Shrinivas Basanagouda Malipatil__   
  Email    : shrinivasmalipatil@gmail.com   
  LinkedIn : [Shrinivas Malipatil](https://in.linkedin.com/in/shrinivas-malipatil-2745492b8)
