package fixrep


import org.springframework.beans.factory.InitializingBean
import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC

class GateFixrepService implements InitializingBean {

    static transactional = false
    static scope = "singleton"

    def grailsApplication
    def fixrepPluginManagerService
    def code = "Gate"


    void afterPropertiesSet() {
      // this.setting = grailsApplication.config.setting
      println "GateFixrepService initialisation completed"
      fixrepPluginManagerService.registerPlugin(this)
    }

    def analyise() {
      println "analyise"
    }

    def extract(text) {
      println "extract"
      // Return a list of extracted term information
      println("Calling GATE enrich");
      def gateclient = new RESTClient('http://fixrep.k-int.com/fixrepws/extract.xml')

      def response = gateclient.post(
        //contentType: 'application/xml',
        //contentType : groovyx.net.http.ContentType.TEXT,
        // contentType : groovyx.net.http.ContentType.TEXT,
        contentType : groovyx.net.http.ContentType.XML,
        requestContentType: groovyx.net.http.ContentType.XML,
        body: text)

      // println("opencalais response ${response.data.text}")

      // Slurp xml response document - opencalais_response represents the root RDF:rdf element
      def gate_response = new XmlSlurper().parseText(response.data.text)

      println("response ${gate_response}")

      def result = []
    }

}
