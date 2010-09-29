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
      // Return a list of extracted term information
      def result = []
    }

}
