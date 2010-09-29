package fixrep

import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC
import org.springframework.beans.factory.InitializingBean

class OpenCalaisFixrepService implements InitializingBean {

    static transactional = false

    def grailsApplication
    def fixrepPluginManagerService
    def code = "OpenCalais"

    void afterPropertiesSet() {
      // this.setting = grailsApplication.config.setting
      println "OpenCalaisFixrepService initialisation completed"
      fixrepPluginManagerService.registerPlugin(this)
    }

    def analyise() {
      println "analyise"
    }

    def extract(text) {
      println "extract"
      // Return a list of extracted term information

      println("Calling opencalais enrich using license ${grailsApplication.config.fixrep.plugins.opencalais.license}")
      def opencalais = new RESTClient('http://api.opencalais.com/tag/rs/enrich')
      opencalais.headers = [ 'x-calais-licenseID' : grailsApplication.config.fixrep.plugins.opencalais.license]

      def response = opencalais.post(
        //contentType: 'application/xml',
        //contentType : groovyx.net.http.ContentType.TEXT,
        contentType : groovyx.net.http.ContentType.TEXT,
        requestContentType: groovyx.net.http.ContentType.XML,
        body: text)

      // println("opencalais response ${response.data.text}")

      // Slurp xml response document - opencalais_response represents the root RDF:rdf element
      def opencalais_response = new XmlSlurper().parseText(response.data.text).declareNamespace(rdf: 'http://www.w3.org/1999/02/22-rdf-syntax-ns#', c: 'http://s.opencalais.com/1/pred/')

      println("Finding description elements, root element name is ${opencalais_response.name()}")

      opencalais_response.'rdf:Description'.each { 
        println("Processing description node with type ${it.'rdf:type'}")
      }

      println("Done")
      def result = ['test':'value']
    }

}
