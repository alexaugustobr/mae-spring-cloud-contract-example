package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST() //'POST'
        headers {
            contentType applicationJson()
        }
        urlPath("/products/1/reviews")
        body(
                [
                        grade: -1,
                        comment: ""
                ]
        )
    }
    response {
        status 400
        headers {
            contentType applicationJson()
        }
        body([
                status: 400,
                type: "invalid-data",
                title: "Dados inválidos",
                detail: "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
                userMessage: "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
                timestamp: anyIso8601WithOffset(),
                objects: [
                        [
                            name: "grade",
                            userMessage: "não deve ser nulo"
                        ],
                        [
                            name: "comment",
                            userMessage: "não deve estar em branco"
                        ]
                ]
        ])
        bodyMatchers {
            jsonPath('$.title', byType())
            jsonPath('$.detail', byType())
            jsonPath('$.userMessage', byType())
            jsonPath('$.objects', byType {
                maxOccurrence(2)
            })
            jsonPath('$.objects[0].name', byType())
            jsonPath('$.objects[1].name', byType())
            jsonPath('$.objects[0].userMessage', byType())
            jsonPath('$.objects[1].userMessage', byType())
        }
    }
}
