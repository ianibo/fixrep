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

class MultipartEdinaExtractService implements InitializingBean {

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

    def pluginResult = new com.k_int.fixrep.FixRepPluginResult(code:"EdinaExtract");

    // Return a list of extracted term information
    println("Calling EdinaExtract enrich");
    // def unlockclient = new RESTClient('http://unlock.edina.ac.uk/text/places') // ?format=json&gazetteer=geonames&type=xml')
    def unlockclient = new HTTPBuilder( 'http://unlock.edina.ac.uk/text/places' )


    // This appears to work as a multipart request

    unlockclient.request(POST) {request -> 
       requestContentType = 'multipart/form-data' 

       // Much help taken from http://evgenyg.wordpress.com/2010/05/01/uploading-files-multipart-post-apache/
       def multipart_entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

       multipart_entity.addPart( "format", new StringBody( "json", "text/plain", Charset.forName( "UTF-8" )));
       multipart_entity.addPart( "gazetteer", new StringBody( "geonames", "text/plain", Charset.forName( "UTF-8" )));
       multipart_entity.addPart( "type", new StringBody( "xml", "text/plain", Charset.forName( "UTF-8" )))
       multipart_entity.addPart( "document", new InputStreamBody( new ByteArrayInputStream(text.getBytes()), "document.xml") )

       request.addHeader("Content-length", "${multipart_entity.getContentLength()}")

       request.entity = multipart_entity;
    
       response.success = { resp ->
         println "response status: ${resp.statusLine}"
         assert resp.statusLine.statusCode == 200
       }
    }

    // def response = unlockclient.post(
    //   requestContentType: URLENC,
    //   body: [ 
    //     format: "json",
    //     type: "xml",
    //     gazetteer: "geonames",
    //     document: "${text}"
    //   ] )
       

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
