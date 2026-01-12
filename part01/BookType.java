package part01;
/**
 * Represents the different book types
 */
public enum BookType {
	FICTION,//represents fictional books
	NON_FICTION,//represents non-fictional books
	REFERENCE;//represents reference books
	/**
	 * Displays the book types to the console
	 */
	public static void displayBookType() {
		for(int i=0;i<BookType.values().length;i++) {
			BookType type=BookType.values()[i];
			System.out.printf("%d. %s \n", i+1,type);
	}
}
}
