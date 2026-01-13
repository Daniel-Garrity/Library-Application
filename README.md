# QUB Library Management System (Java)
A Java-based library management system developed as part of my university coursework.  
This project was built to practice **object-oriented programming, data handling, and user interface design** in Java.  
It includes two versions:
1. **QUBLibrary** – Standard console-based version.  
2. **QUBLibraryUpdated** – Enhanced console version with colors, fonts, and images to enchance user expierence.

## Why I bult this
This project was part of a university assignment where we had to create a library management application.
My goal was to simulate a real-world library system while practicing Java programming concepts.
During the project I mainly focused on ensuring robust input validation, a good book tracking system and enhancing the console interface with **fonts, colors, and book cover images** in the updated version 
This project helped me strengthen my skills in **object-oriented programming, arrays, enums, loops, exception handling, and basic GUI concepts**.

## Features
- **Add, Remove, Borrow, Return Books** with input validation  
- **List Books** by all or filtered by status (Available, On Loan, Withdrawn)  
- **Track Book Popularity** via loan count  
- **Enhanced Console UI** (Updated version):
  - Colored headings and text
  - Custom fonts for headings and normal text
  - Book cover images displayed in the console
  - QUB logo displayed on menu  
- Robust handling of invalid inputs  
- Exit safely with confirmation

## How to run
  **Requirements:** Java 8+
### Standard Version (Part 1)
  1. Open `part01.QUBLibrary` in any Java IDE (VS Code, Eclipse, IntelliJ).  
  2. Compile and run:
 
### Enhanced Version (Part 2)
  part02.QUBLibraryUpdated is included to show an enhanced console interface.
  Note: This version requires a custom Console library for Java. It is included for demonstration purposes but may not run without the required library.
  You can still explore the code to see how the interface was enhanced with fonts, colors, and images.

## How to Use it?
-Choose options from the menu by entering numbers 1–8.
-Adding a Book: Enter title, author, ISBN, edition, type, summary, price, and cover image filename.
-Removing a Book: Requires confirmation; books on loan cannot be removed.
-Borrowing/Returning a Book: Enter the Book ID as prompted; status is checked automatically.
-Listing Books: Option to view all books, filter by status, or view most popular books.
## License
This project is licensed under the [MIT License](LICENSE).
## Author
Daniel Garrity
University Project- Queen’s University Belfast
