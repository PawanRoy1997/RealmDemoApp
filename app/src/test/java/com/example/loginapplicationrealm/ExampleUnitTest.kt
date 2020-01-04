package com.example.loginapplicationrealm

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun correctNameTest(){
        when{
            ("This is not intended to pass this test").isValidUsername() -> assert(false)
            ("shor").isValidUsername() -> assert(false)
            ("Iamthecool").isValidUsername() -> assert(true)
        }
    }

    @Test
    fun passwordValidatorTest(){
        when {
            ("123456").isValidPassword() -> assert(false)
            ("1234567891011121314").isValidPassword() -> assert(false)
            ("123456789101112").isValidPassword() -> assert(true)
        }
    }

    private fun String.isValidPassword(): Boolean {
        return this.length in 8..15
    }

    private fun String.isValidUsername(): Boolean {
        return this.length in 5..25
    }
}