package com.k_int.fixrep

import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC

class AnalyseController {

    def index = { }

    def save = {
      // println request.getFile("file").inputStream.text
      def metadata = request.getFile("file").inputStream.text

      def opencalais = new RESTClient('http://api.opencalais.com/tag/rs/enrich')
      opencalais.headers = [ 'x-calais-licenseID' : '***']

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
      redirect(action: 'index')
    }
}
