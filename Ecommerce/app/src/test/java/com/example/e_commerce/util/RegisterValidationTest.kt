package com.example.e_commerce.util

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegisterValidationTest{

   private lateinit var validator:RegisterValidation
    @Before
    fun setup(){
        validator = RegisterValidation()

    }

    @Test
    fun emailIsValidWithInvalidEmailThenReturnFalseValue(){
        val validation = validator.emailIsValid("test-dd.com")
        assertEquals(false,validation)
    }
    @Test
    fun emailIsValidWithInvalidEmailThenReturnTrueValue(){
        val validation = validator.emailIsValid("test_dd@gmail.com")
        assertEquals(true,validation)

    }
    @Test
    fun passwordIsInvalidPasswordThenReturnFalseValue(){
        val validation = validator.passwordValid("1")
        assertEquals(false,validation)
    }
    @Test
    fun passwordIsvalidPasswordThenReturnTrueValue(){
        val validation = validator.passwordValid("123456%aA")
        assertEquals(true,validation)
    }
}