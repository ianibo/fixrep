package fixrep

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
      def result = []
    }

}
