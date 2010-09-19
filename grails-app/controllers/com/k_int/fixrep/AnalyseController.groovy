package com.k_int.fixrep

class AnalyseController {

    def index = { }

    def save = {
      println request.getFile("file").inputStream.text
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
