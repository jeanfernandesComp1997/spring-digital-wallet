package com.example.digitalwallet.core.domain.valueobjects

data class Document(val value: String) {

    init {
        require(value.isNotBlank()) { InvalidDocumentException("Document cannot be blank") }
        require(isValid(value)) { InvalidDocumentException("Invalid document format") }
    }

    private fun isValid(value: String?): Boolean {
        if (value == null) return false
        if (value == "00000000000"
            || value == "11111111111"
            || value == "22222222222"
            || value == "33333333333"
            || value == "44444444444"
            || value == "55555555555"
            || value == "66666666666"
            || value == "77777777777"
            || value == "88888888888"
            || value == "99999999999"
            || (value.length != 11)
        ) return (false)
        val dig10: Char
        val dig11: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int
        try {
            sm = 0
            peso = 10
            i = 0
            while (i < 9) {
                num = (value[i].code - 48)
                sm += (num * peso)
                peso -= 1
                i++
            }
            r = 11 - (sm % 11)
            dig10 = if ((r == 10) || (r == 11)) '0'
            else (r + 48).toChar()
            sm = 0
            peso = 11
            i = 0
            while (i < 10) {
                num = (value[i].code - 48)
                sm += (num * peso)
                peso -= 1
                i++
            }
            r = 11 - (sm % 11)
            dig11 = if ((r == 10) || (r == 11)) '0'
            else (r + 48).toChar()
            return (dig10 == value[9]) && (dig11 == value[10])
        } catch (error: Exception) {
            return (false)
        }
    }

    class InvalidDocumentException(message: String) : IllegalArgumentException(message)
}