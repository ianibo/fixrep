import com.k_int.fixrep.*

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        
        def user_role = Role.findByAuthority('user') ?: new Role(authority: 'user').save()
        def admin_role = Role.findByAuthority('admin') ?: new Role(authority: 'admin').save()

        println "User Role: ${user_role}"
        println "Admin Role: ${admin_role}"
        
        def admin_user = User.findByUsername('admin') ?: new User(username: 'admin', password: springSecurityService.encodePassword('admin'), enabled:true).save()

        println "Admin user: ${admin_user}"

        UserRole.create admin_user, admin_role
        UserRole.create admin_user, user_role

        def oai_dc_handler = DocumentHandler.findByRoot_schema_uri('http://www.openarchives.org/OAI/2.0/oai_dc/') ?: new DocumentHandler(
            root_schema_uri : 'http://www.openarchives.org/OAI/2.0/oai_dc/',
            handler_code : 'println "Handler"').save()
    }
    def destroy = {
    }
}
