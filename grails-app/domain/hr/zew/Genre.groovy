package hr.zew

class Genre {
	String name

	static expose='genre'

	static constraints = {  name(blank:false)  }
}
