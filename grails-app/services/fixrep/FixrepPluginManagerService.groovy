package fixrep

import org.springframework.beans.factory.InitializingBean

class FixrepPluginManagerService implements InitializingBean {

    static transactional = false

    def grailsApplication
    def registered_plugins = []

    void afterPropertiesSet() {
      // this.setting = grailsApplication.config.setting
      println "FixrepPluginManagerService initialisation completed"
    }

    def registerPlugin(plugin) {
      println "Registering plugin ${plugin}"
      registered_plugins.add(plugin)
    }

    def reportPlugins() {
      println "Plugins : ${registered_plugins}"
    }

    def extract() {
      // Return a list of extracted term information
      def result = []
    }
}
