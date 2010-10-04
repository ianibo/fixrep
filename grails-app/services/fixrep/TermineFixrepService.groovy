package fixrep

import org.springframework.beans.factory.InitializingBean

class TermineFixrepService implements InitializingBean {

    static transactional = false

    def grailsApplication
    def fixrepPluginManagerService
    def code = "Termine"


    void afterPropertiesSet() {
      println "TermineFixrepService initialisation completed"
      fixrepPluginManagerService.registerPlugin(this)
    }

    def analyise() {
      println "analyise"
    }

  def extract(text) {
    println "extract"

    def pluginResult = new com.k_int.fixrep.FixRepPluginResult(code:"Termine");

    // def termine_service = new RESTClient('http://api.opencalais.com/tag/rs/enrich')

    // def response = termine_service.post(
        //contentType: 'application/xml',
        //contentType : groovyx.net.http.ContentType.TEXT,
    //     contentType : groovyx.net.http.ContentType.TEXT,
    //     requestContentType: groovyx.net.http.ContentType.XML,
    //     body: text)

    // Slurp xml response document - opencalais_response represents the root RDF:rdf element
    // def opencalais_response = new XmlSlurper().parseText(response.data.text).declareNamespace(rdf: 'http://www.w3.org/1999/02/22-rdf-syntax-ns#', c: 'http://s.opencalais.com/1/pred/')

    return pluginResult
  }

}
