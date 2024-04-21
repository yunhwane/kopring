package com.example.securityexample.user.service

import org.jetbrains.annotations.NotNull

data class UserRequest(@NotNull val email: String, @NotNull val password: String) {
}