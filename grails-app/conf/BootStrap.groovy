import org.grails.plugins.rest.JSONDomainMarshaller;

import grails.converters.JSON;
import hr.zew.*;
class BootStrap {

	def grailsApplication;

	def init = { servletContext ->
		if(!Genre.count()){
			new Genre(name:'Science Fiction').save(failOnError:true);
			new Genre(name:'Romance').save(failOnError:true);
			new Genre(name:'Poetry').save(failOnError:true);
		}
		if(!Book.count()){
			new Book(author: "Stephen King", title: "The Shining",genre:Genre.findByName('Science Fiction')).save(failOnError: true)
			new Book(author: "James Patterson", title: "Along Came a Spider",genre:Genre.findByName('Science Fiction')).save(failOnError: true)
		}
		JSON.registerObjectMarshaller(new JSONDomainMarshaller(grailsApplication))
	}
	def destroy = {
	}
}
