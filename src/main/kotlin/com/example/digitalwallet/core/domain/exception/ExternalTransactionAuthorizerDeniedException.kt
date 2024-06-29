package com.example.digitalwallet.core.domain.exception

class ExternalTransactionAuthorizerDeniedException :
    RuntimeException("External authorizer service denied the transaction.")