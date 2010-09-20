/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.k_int.fixrep

import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC

/**
 *
 * @author ibbo
 */
class OpenCalaisProcessor {
  
	  def save = {
      // println request.getFile("file").inputStream.text
      def metadata = request.getFile("file").inputStream.text

      def opencalais = new RESTClient('http://api.opencalais.com/tag/rs/enrich')
      opencalais.headers = [ 'x-calais-licenseID' : '***']

      def response = opencalais.post(
        //contentType: 'application/xml',
        //contentType : groovyx.net.http.ContentType.TEXT,
        contentType : groovyx.net.http.ContentType.TEXT,
        requestContentType: 'application/xml',
        body: metadata)

      println("opencalais response ${response.data.text}")
    }
}

