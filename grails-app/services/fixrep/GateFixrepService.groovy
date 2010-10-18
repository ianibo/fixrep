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
    def code = "Fixrep-GATE"


    void afterPropertiesSet() {
      // this.setting = grailsApplication.config.setting
      println "GateFixrepService initialisation completed"
      fixrepPluginManagerService.registerPlugin(this)
    }

    def analyise() {
      println "analyise"
    }

    def extract(text) {

      Long start_time = System.currentTimeMillis();

      def pluginResult = new com.k_int.fixrep.FixRepPluginResult(code:"Fixrep-GATE", status:"OK");

      try {

        // Return a list of extracted term information
        println("Calling GATE enrich");
        def gateclient = new RESTClient('http://fixrep.k-int.com/fixrepws/extract.xml')

        def response = gateclient.post(
          contentType : groovyx.net.http.ContentType.TEXT,
          requestContentType: groovyx.net.http.ContentType.XML,
          body: text)


        // Slurp xml response document - opencalais_response represents the root RDF:rdf element
        def gate_response = new XmlSlurper().parseText(response.data.text)

        gate_response.fixrep_result.features.each {
          def term = new com.k_int.fixrep.FixRepTerm( termSource: code, termString: it.feature_value.text(), termType: it.feature_type.text() )
          println "Adding gate term ${term}"
          pluginResult.terms.add(term);
        }
        println("gatews response \"${gate_response}\"")
      }
      finally {
        pluginResult.elapsed = System.currentTimeMillis() - start_time;
      }


      return pluginResult
    }

}
