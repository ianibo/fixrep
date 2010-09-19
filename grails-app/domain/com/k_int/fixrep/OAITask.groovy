package com.k_int.fixrep

class OAITask {

    static constraints = {
    }

    String base_url
    static belongsTo = [owner: User]
}
