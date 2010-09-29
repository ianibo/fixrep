package fixrep


import org.springframework.beans.factory.InitializingBean

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
      def result = []
    }

}
