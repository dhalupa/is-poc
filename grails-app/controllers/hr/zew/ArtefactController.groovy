package hr.zew

import grails.converters.JSON;

class ArtefactController {

	def index() {
		def model=[]
		for (cls in grailsApplication.domainClasses) {
			model<<[artefact:cls.shortName];
		}
		render text: model as JSON, contentType: 'application/json'
	}
}
