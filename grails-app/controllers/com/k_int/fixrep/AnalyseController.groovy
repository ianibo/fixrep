package com.k_int.fixrep

import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC

class AnalyseController {

    def fixrepPluginManagerService

    def index = { }

    def save = {

      def metadata = request.getFile("file").inputStream.text

      fixrepPluginManagerService.reportPlugins()

      // A map of extacted metadata
      def extracted_metadata = [:]

      fixrepPluginManagerService.registered_plugins.each { plugin, m=extracted_metadata ->
        println "Processing using plugin ${plugin.code}"
        m.put("${plugin.code}", plugin.extract(metadata))
      }
      
      println("After all plugins, extracted metadata is : ${extracted_metadata}")

      /*
      // println request.getFile("file").inputStream.text

      println("Calling opencalais enrich using license ${grailsApplication.config.fixrep.plugins.opencalais.license}")
      def opencalais = new RESTClient('http://api.opencalais.com/tag/rs/enrich')
      opencalais.headers = [ 'x-calais-licenseID' : grailsApplication.config.fixrep.plugins.opencalais.license]

      def response = opencalais.post(
        //contentType: 'application/xml',
        //contentType : groovyx.net.http.ContentType.TEXT,
        contentType : groovyx.net.http.ContentType.TEXT,
        requestContentType: groovyx.net.http.ContentType.XML,
        body: metadata)

      println("opencalais response ${response.data.text}")

      // twitter.auth.basic userName, passwd
      // def downloadedfile = request.getFile('file');
      // downloadedfile.transferTo(new File('c:/somefolder/filename.jpeg'))
      //if(!f.empty) {
      //f.transferTo( new File('someotherloc') )
      //response.sendError(200,'Done');
      //}
      //else {
      // flash.message = 'file cannot be empty'
      // redirect(action:'uploadForm')
      //}
      */

      redirect(action: 'index')
    }
}
