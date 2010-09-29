import com.k_int.fixrep.*

class BootStrap {

    def springSecurityService

    def fixrepPluginManagerService
    def gateFixrepService
    def openCalaisFixrepService
    def termineFixrepService

    def init = { servletContext ->
        
        def user_role = Role.findByAuthority('user') ?: new Role(authority: 'user').save()
        def admin_role = Role.findByAuthority('admin') ?: new Role(authority: 'admin').save()

        println "User Role: ${user_role}"
        println "Admin Role: ${admin_role}"
        
        def admin_user = User.findByUsername('admin') ?: new User(username: 'admin', password: springSecurityService.encodePassword('admin'), enabled:true).save()
        def fixrep_user = User.findByUsername('fixrep') ?: new User(username: 'fixrep', password: springSecurityService.encodePassword('fixrep'), enabled:true).save()

        println "Admin user: ${admin_user}"
        println "Fixrep user: ${fixrep_user}"

        UserRole.create admin_user, admin_role
        UserRole.create admin_user, user_role
        UserRole.create fixrep_user, user_role

        def oai_dc_handler = DocumentHandler.findByRoot_schema_uri('http://www.openarchives.org/OAI/2.0/oai_dc/') ?: new DocumentHandler(
            root_schema_uri : 'http://www.openarchives.org/OAI/2.0/oai_dc/',
            handler_code : 'println "Handler"').save()

        def intute_oai_task = new OAITask(base_url:'http://someurl', owner: fixrep_user).save()
        def culturegrid_oai_task = new OAITask(base_url: 'http://culturegrid.org.uk', owner: fixrep_user).save()

        println "intute oai task: ${intute_oai_task}"
        println "cg oai task: ${culturegrid_oai_task}"

    }
    def destroy = {
    }
}
