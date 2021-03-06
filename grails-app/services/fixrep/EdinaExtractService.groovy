package fixrep


import groovyx.net.http.*
import org.springframework.beans.factory.InitializingBean
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import groovyx.net.http.*
import org.apache.http.entity.mime.*
import org.apache.http.entity.mime.content.*
import java.nio.charset.Charset

class EdinaExtractService implements InitializingBean {

    static transactional = false
    static scope = "singleton"

    def grailsApplication
    def fixrepPluginManagerService
    def code = "EdinaExtract"


    void afterPropertiesSet() {
      // this.setting = grailsApplication.config.setting
      println "EdinaExtract initialisation completed"
      fixrepPluginManagerService.registerPlugin(this)
    }

    def analyise() {
      println "analyise"
    }

    def extract(text) {

    Long start_time = System.currentTimeMillis();

    def pluginResult = new com.k_int.fixrep.FixRepPluginResult(code:"EdinaExtract");

    try {
      // Return a list of extracted term information
      println("Calling EdinaExtract enrich");
      // def unlockclient = new RESTClient('http://unlock.edina.ac.uk/text/places') // ?format=json&gazetteer=geonames&type=xml')
      def unlockclient = new HTTPBuilder( 'http://unlock.edina.ac.uk/text/places' )

      def response = unlockclient.post(
        requestContentType: URLENC,
        body: [ 
          format: "xml",
          type: "text",
          gazetteer: "geonames",
          document: "${text}"
        ] )
      }
      catch ( Exception e ) {
        pluginResult.status = "Error"
        pluginResult.message = e.getMessage()
      }
      finally {
        pluginResult.elapsed = System.currentTimeMillis() - start_time;
      }
       

      // println("Edina extract response ${response.data.text}")

      // Slurp xml response document - opencalais_response represents the root RDF:rdf element
      // def gate_response = new XmlSlurper().parseText(response.data.text)

      // gate_response.fixrep_result.features.each {
      //   def term = new com.k_int.fixrep.FixRepTerm( termSource: code, termString: it.feature_value.text(), termType: it.feature_type.text() )
      //   println "Adding gate term ${term}"
      //   pluginResult.terms.add(term);
      // }


      // println("gatews response \"${gate_response}\"")
    return pluginResult
  }

}
