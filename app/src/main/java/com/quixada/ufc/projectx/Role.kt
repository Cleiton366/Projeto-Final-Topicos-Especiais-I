package com.quixada.ufc.projectx

import java.io.Serializable

var roles = mutableListOf<Role>()

class Role (
    var roleName : String,
    var roleDescription : String
    ) : Serializable
