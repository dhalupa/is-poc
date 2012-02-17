package hr.zew

import grails.converters.*
import org.codehaus.groovy.grails.commons.GrailsClassUtils
import org.springframework.web.servlet.support.RequestContextUtils as RCU

class JsonRestApiController {

	def fetch () {
		def result=['response':[status:0]];
		def entity = grailsApplication.getClassForName(params.entity)
		result.response.data = entity.list()
		render text: result as JSON, contentType: 'application/json', status:  200
	}

	def add() {
		def result=[:];
		def entity = grailsApplication.getClassForName(params.entity);
		def obj = entity.newInstance()
		obj.properties = request.JSON.data
		obj.validate();
		if (obj.hasErrors()) {
			def locale = RCU.getLocale(request)
			def errors=[:]
			obj.errors.fieldErrors.collect { error ->
				errors[error.field]=[errorMessage:messageSource.getMessage(error, locale)];
			}
			result.status=-4;
			result.errors=errors;
		} else {
			obj.save(flush: true);
			result.data=obj;
		}

		render text:[response:result] as JSON, contentType: 'application/json', status: 200
	}

	def update() {
		def result=[:];
		def entity = grailsApplication.getClassForName(params.entity);
		def obj = entity.get(request.JSON.data.id);
		obj.properties = request.JSON.data;
		obj.validate();
		if (obj.hasErrors()) {
			def locale = RCU.getLocale(request);
			def errors=[:]
			obj.errors.fieldErrors.collect { error ->
				errors[error.field]=[errorMessage:messageSource.getMessage(error, locale)];
			}
			result.status=-4;
			result.errors=errors;
		} else {
			obj.save(flush: true)
			result.status=0;
			result.data=obj;
		}
		render text:[response: result] as JSON, contentType: 'application/json', status: 200
	}

	def remove() {
		def result=['response':[status:0]];
		def entity = grailsApplication.getClassForName(params.entity);
		def obj = entity.get(request.JSON.data.id)
		obj.delete(flush:true);
		render text: result as JSON, contentType: 'application/json', status: 200
	}



	def messageSource

	private extractErrors(model) {
		def locale = RCU.getLocale(request)
		model.errors.fieldErrors.collect { error ->
			messageSource.getMessage(error, locale)
		}
	}
}
