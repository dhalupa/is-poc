package hr.zew

class Book {
	//Change added to aother branch
	String title
	String author
	Genre genre

	static expose='book'

	static mapping={ genre lazy:false }

	static constraints = {
		title(blank:false)
		author(blank:false)
	}

	def String getGenreName(){
		return genre.name;
	}
}
