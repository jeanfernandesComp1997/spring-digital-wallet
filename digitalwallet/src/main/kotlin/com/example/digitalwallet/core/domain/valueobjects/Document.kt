package com.example.digitalwallet.core.domain.valueobjects

data class Document(val value: String) {

    init {
        require(value.isNotBlank()) { InvalidDocumentException("Document cannot be blank") }
        require(isValid(value)) { InvalidDocumentException("Invalid document format") }
    }

    private fun isValid(value: String?): Boolean {
        val formattedValue = value?.replace(".", "")?.replace("-", "") ?: return false
        if (formattedValue == "00000000000"
            || formattedValue == "11111111111"
            || formattedValue == "22222222222"
            || formattedValue == "33333333333"
            || formattedValue == "44444444444"
            || formattedValue == "55555555555"
            || formattedValue == "66666666666"
            || formattedValue == "77777777777"
            || formattedValue == "88888888888"
            || formattedValue == "99999999999"
            || (formattedValue.length != 11)
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
                num = (formattedValue[i].code - 48)
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
                num = (formattedValue[i].code - 48)
                sm += (num * peso)
                peso -= 1
                i++
            }
            r = 11 - (sm % 11)
            dig11 = if ((r == 10) || (r == 11)) '0'
            else (r + 48).toChar()
            return (dig10 == formattedValue[9]) && (dig11 == formattedValue[10])
        } catch (error: Exception) {
            return (false)
        }
    }

    class InvalidDocumentException(message: String) : IllegalArgumentException(message)
}