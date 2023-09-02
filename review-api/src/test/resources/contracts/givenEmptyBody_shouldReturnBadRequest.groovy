package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST() //'POST'
        headers {
            contentType textPlain()
        }
        urlPath("/products/1/reviews")
        body("")
    }
    response {
        status 415
        headers {
            contentType applicationJson()
        }
//        body([
//                status: 415,
//                type: "https://api.alganews.com.br/incomprehensible-message",
//                "title": "Mensagem incompreensível",
//                detail: "O corpo da requisição está inválido. Verifique erro de sintaxe.",
//                userMessage: "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.",
//                timestamp: anyIso8601WithOffset()
//        ])
    }
}
