package com.example.securityexample.user

import org.jetbrains.annotations.NotNull

data class UserRequest(@NotNull val email: String, @NotNull val password: String) {
}