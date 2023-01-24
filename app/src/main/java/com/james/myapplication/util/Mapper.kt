package com.james.myapplication.util

import com.james.myapplication.data.source.remote.response.UserResponse
import com.james.myapplication.model.User

fun UserResponse.toUser() = User(
    id, email, firstName, lastName, avatar
)