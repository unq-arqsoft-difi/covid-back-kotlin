package difi.covid.exceptions

import difi.covid.User

class ExistsUserException(user: User) : Throwable("Cannot add User with ${user.email} because already exists.")
