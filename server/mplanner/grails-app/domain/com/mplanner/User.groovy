package com.mplanner

class User {

    String name
    String username
    String email

    static constraints = {
        name(nullable: false)
        username(nullable: false, unique: true)
        email(nullable: false, email: true, unique: true)
    }

    boolean isInvited(Event e) {
        return email in e.invited
    }
}
