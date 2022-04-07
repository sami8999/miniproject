# miniproject1.0

To compile using javac:

1. cd into src folder
2. javac -cp jarFiles/*; *.java
3. java -cp jarFiles/*; Main

To run in intelliji

1. Download from intelliji-version branch and just run Main method 
2. Alternatively modify the javac-version as only path rooting changes:
  - In types/transferAgent change the root of database.txt to the commented version ("src/database.txt") in the read + writeToDatabase methods
  - In guis/frames/loginFrame change the filepath of image files to commented versions (src/guis/images/portfolio.png).
  - Run the Main class

Notes about the repo:

This repository consists of 4 different miniproject builds
1. Javac compilable (latest up to date) 
2. Intelliji compilable (latest up to date) 
3. Intelliji maven repository (old without refactoring)
4. Statless Intelliji maven repo  (simple build with no persistent storage and tightly coupled code) 

It is interesting to note how OOP design principles have influenced the latest builds in comparison to the simple stateless build. For example we have made use of "favour composition over inheritance" and "program to interfaces" principles which makes our code easier to test and less coupled. 
