import org.grails.plugins.rest.JSONApiRegistry;

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/api/$domain" (controller: 'jsonRestApi') {
			entity = { JSONApiRegistry.registry[params.domain] }
			action = { request.JSON.operationType }
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
