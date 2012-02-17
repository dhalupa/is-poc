import org.grails.plugins.rest.JSONApiRegistry;
import org.grails.plugins.rest.JsonDateEditorRegistrar;
import org.grails.plugins.rest.JsonRestApiPropertyEditorRegistrar;

// Place your Spring DSL code here
beans = {
	jsonRestApiPropertyEditorRegistrar(JsonRestApiPropertyEditorRegistrar, ref("grailsApplication"))
	customPropertyEditorRegistrar(JsonDateEditorRegistrar)
	application.domainClasses.each { domainClass ->
		def resource = domainClass.getStaticPropertyValue('expose', String)
		if (resource) {
			JSONApiRegistry.registry[resource] = domainClass.fullName
		}
	}
	
}
