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
