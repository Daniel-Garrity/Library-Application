package part01;

import java.util.Scanner;
/**
 *This class represents a menu which allows a user to interact with LibaryBook Objects
 *The constructor initialises a list of books with some predefined sample books
 * @author Daniel Garrity 
 */
public class QUBLibrary {
	private static Scanner input =new Scanner(System.in);//scanner for user input
	private static Library library= new Library();//instance of libary to manage books
	public static void main(String[] args) {
		display();//display method called to show menu
	}
	/**
	 * Displays the menu ofthe library system and handles user input to select various methods
	 */
	public static void display() {
		String menuOptions[] = {//array holding menu options
				"List all Books:",
				"List Books by Status:",
				"Add a Book:",
				"Remove a Book:",
				"Borrow a Book:",
				"Return a Book:",
				"Display Ranked List:",
				"Exit:"
		};
		while(true) {
			//Display menu using menuOptions Array
			System.out.println("****Book Menu****");
			for(int i=0;i<menuOptions.length;i++) {
				System.out.println((i+1)+". "+menuOptions[i]);
			}
			System.out.println("Enter your choice from 1-8");
			int userChoice=0;
			boolean validInput=false;
			while (!validInput) {
		        try {
		            userChoice = input.nextInt();
		            input.nextLine(); // consume the newline character

		            if (userChoice >= 1 && userChoice <= 8) {
		                validInput = true; // valid input, exit loop
		            } else {
		                System.out.println("Please enter a valid integer between 1-8");
		            }

		        } catch (Exception ex) {
		            System.out.println("Please enter a valid integer between 1-8");
		            input.nextLine(); 
		        }
		    }
			switch(userChoice) {
			case 1:
				listBooks();//list all books in the library
				break;
			case 2:
				listBooksbyStatus();// list books by their current status
				break;
			case 3:
				addBook();//add a new book to the library
				break;
			case 4:
				removeBook();//remove a book from the library
				break;
			case 5:
				borrowBook();//borrow a book from the library
				break;
			case 6:
				returnBook();// return a borrowed book to the library
				break;
			case 7:
				mostPopularBook();//list the most popular books in order of loan count
				break;
			case 8:
				System.out.println("Exiting Menu...");//exit the program
				input.close();
				return;
			}
		}
	}
	/**
	 * This method allows the user to see a list of all Books
	 */
	public static void listBooks() {
        // Call the list method from the Library class
        LibraryBook[] bookArray = library.list(); // get the array of books
        for(int i=0;i<bookArray.length;i++) {
        	LibraryBook book = bookArray[i];
        	 System.out.println(book);
        }
 
        }
	/**
	 * This method allows a user to borrow a book from the library by providing the book's ID
	 */
	private static void borrowBook() {
	    boolean validInput = false;
	    int bookId = 0;
	    
	    do {
	        System.out.println("Enter the Book ID to borrow:");
	        try {
	            bookId = input.nextInt();  // read the input as an integer
	            
	            // Check if the book exists in the library
	            LibraryBook bk = library.search(bookId);  
	            
	            if (bk != null) {  
	                // Check if the book is available for borrowing
	                if (bk.getStatus() == BookStatus.AVAILABLE) {
	                    // If available, borrow the book
	                    if (library.borrowBook(bookId)) {
	                        System.out.println("Book with ID " + bookId + " borrowed successfully.\n");
	                        validInput = true;  // exit the loop after successful borrow
	                    }
	                } else if (bk.getStatus() == BookStatus.ON_LOAN) {
	                    // book is on loan
	                    System.out.println("The book with ID " + bookId + " is currently on loan. You cannot borrow it right now.\n");
	                } else if (bk.getStatus() == BookStatus.WITHDRAWN) {
	                    // book has been withdrawn
	                    System.out.println("The book with ID " + bookId + " has been withdrawn from the library and cannot be borrowed.\n");
	                }
	            } else {
	                // If the book was not found
	                System.out.println("The book with ID " + bookId + " was not found in the library.\n");
	            }
	        } catch (Exception ex) {
	            // Handle invalid input 
	            System.out.println("Invalid input. Please enter a valid integer for the book ID.");
	            input.nextLine();  // clear the scannerr
	        }
	    } while (!validInput);  // continue asking until valid input is provided
	}

	/**
	 * This method allows a user to return a borrowed book to the library by providing the book's ID
	 */
	private static void returnBook() {
		boolean validInput=false;
		do{System.out.println("Enter the Book ID to return:");
		try {
		    int bookId = input.nextInt();  // accept the Book ID as input
		    input.nextLine();
		if(library.returnBook(bookId)) {
			System.out.println("Book with ID "+bookId+" returned sucessfully \n");
			validInput=true;
		}else {
			System.out.println("Book with ID: "+bookId+" could not be returned \n");
			validInput=true;
		}
		} catch (Exception ex) {
		    System.out.println("Invalid input. Please enter a valid integer for the book ID.");
		    input.nextLine();  // consume the invalid input
		}
		}while(!validInput);
	}
	/**
	 * This method prompts the user for details to add a new book to the library
	 */
	private static void addBook() {
		System.out.println("Enter Book title:");
		String title= input.nextLine();
		
		System.out.println("Enter Book author:");
		String author= input.nextLine();
		
		System.out.println("Enter Book isbn:");
		String isbn= input.nextLine();
		System.out.println("Select Book type: (Enter 1, 2 or 3)");
		BookType.displayBookType();
		int userChoice=0;
		boolean validInput=false;
		while (!validInput) {
	        try {
	            System.out.println("Enter the number corresponding to the Book type");
	            userChoice = input.nextInt();
	            input.nextLine();  // consume the newline character
	            // check if the input is within the valid range (1, 2, or 3)
	            if (userChoice >= 1 && userChoice <= 3) {
	                validInput = true;  // exit the loop if the input is valid
	            } else {
	                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
	            }
	        } catch (Exception ex) {
	            // catch invalid inputs and prompt the user again
	            System.out.println("Invalid input. Please enter a valid integer between 1 and 3.");
	            input.nextLine();  // consume the invalid input
	        }
	    }
		BookType selectedType =BookType.values()[userChoice-1];
		int edition =-1;
		while(edition<=0) {
		try {
			System.out.println("Enter Book edition");
			edition = input.nextInt();
			input.nextLine();// consume the newline character
			if(edition<1) {
				System.out.println("Edition must be a positive number");
			}
		} catch (Exception ex) {
			// catch invalid inputs and prompt the user again
			System.out.println("Invalid Input. Please enter a valid integer for the books edition");
			input.nextLine();// consume the invalid input
		}
		}
		System.out.println("Enter Book summary:");
		String summary= input.nextLine();
		
		double price =-1;
		while(price<=0)
			try {
	            System.out.println("Enter Book price:");
	            price = input.nextDouble();
	            input.nextLine(); // consume the newline character
	            if (price <= 0) {
	                System.out.println("Price must be a positive number.");
	            }
	        } catch (Exception ex) {
	            System.out.println("Invalid input. Please enter a valid positive number for the price.");
	            input.nextLine(); // consume the invalid input
	        }
		    
		
		System.out.println("Enter the Books cover image file name:");
		String coverImage = input.nextLine();
		try {
			LibraryBook newBook = new LibraryBook(title, author, isbn, selectedType, edition, summary, price, coverImage);//attempt to add the book to the library
			if(library.add(newBook)) {
				System.out.println("Book added sucessfully.");
			}else {
				System.out.println("Failed to add a new Book.");
			}
			
		}catch(Exception ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}
	/**
	 * This method removes a book from the library based on its ID
	 */
	private static void removeBook() {
		boolean validInput=false;
		do{
			System.out.println("Enter the Book ID of the Book you wish to remove:");
		
		try {
		    int bookId = input.nextInt();  // accept the Book ID as input
		    input.nextLine();
		    LibraryBook book = library.search(bookId);
		    if (book == null) {
                System.out.println("Book with ID " + bookId + " not found.");
                continue;
            }
		    System.out.println("Are you sure you want to remove this book with ID: "+bookId+" (yes/no)");
		    String userAns=input.nextLine().toLowerCase();
		    if(userAns.equals("no")) {
		    	System.out.println("Removal Cancelled, returning to Main Menu");
		    	validInput=true;
		    }else if (userAns.equals("yes")) {
                if (book.getStatus() == BookStatus.ON_LOAN) {
                    System.out.println("Unable to remove book as it is currently on loan.");
                    break;  // exit the loop if the book is on loan
                }

                if (book.getStatus() == BookStatus.WITHDRAWN) {
                    System.out.println("The book with ID " + bookId + " has already been removed.");
                    break;  // exit the loop if the book is withdrawn
                }

                //Remove the book if the status is okay
                if (library.remove(bookId)) {
                    System.out.println("Book with ID " + bookId + " has been removed.");
                    validInput = true;
                } else {
                    System.out.println("Failed to remove the book. Please try again.");
                }
            } else {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
		} catch (Exception ex) {
		    System.out.println("Invalid input.");
		    input.nextLine();  // consume the invalid input
		
		}
	}while(!validInput);
	}
	/**
	 * This method displays a list of books based on their current status (e.g available, on loan)
	 */
	private static void listBooksbyStatus() {
		System.out.println("Select Book Status: (Enter 1, 2 or 3)");
		BookStatus.displayBookStatus();
		int userChoice=0;
		boolean validInput=false;
		while (!validInput) {
	        try {
	            System.out.println("Enter the number corresponding to the Book Status");
	            userChoice = input.nextInt();
	            input.nextLine();  // consume the newline character
	            // check if the input is within the valid range (1, 2, or 3)
	            if (userChoice >= 1 && userChoice <= 3) {
	                validInput = true;  // exit the loop if the input is valid
	            } else {
	                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
	            }
	        } catch (Exception ex) {
	            // Catch invalid inputs (non-integer) and prompt the user again
	            System.out.println("Invalid input. Please enter a valid integer between 1 and 3.");
	            input.nextLine();  // consume the invalid input
	        }
	    }

	    //Retrieve the selected status and list books
	    BookStatus selectedStatus = BookStatus.values()[userChoice - 1];  // get the selected status
	    LibraryBook[] booksByStatus = library.list(selectedStatus);  

	    // Print the filtered books
	    if (booksByStatus.length > 0) {
	        System.out.println("Books with status " + selectedStatus + ":");
	        for (int i = 0; i < booksByStatus.length; i++) {
	            LibraryBook book = booksByStatus[i];
	            System.out.println(book);  
	        }
	    } else {
	        System.out.println("No books found with status " + selectedStatus + ".");
	    }
	}
	/**
	 * This method displays a ranked list of the most popular books, based on the number of times they have been loaned
	 */
	private static void mostPopularBook() {
		System.out.println("Books ranked in popularity order");
		LibraryBook[]popularBooks = library.mostPopular();

	    // display the ranked books
	    if (popularBooks.length > 0) {
	        for (LibraryBook book : popularBooks) {
	            System.out.println("ID: "+book.getId()+" Title: " + book.getTitle() + " Author: " + book.getAuthor() + " Borrowed: " + book.getLoanCount() + " times");
	        }
	    } else {
	        System.out.println("No books available.");
	    }
	}
}
