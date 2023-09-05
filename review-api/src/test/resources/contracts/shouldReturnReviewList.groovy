package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        url '/products/1/reviews'
        headers {
            accept applicationJson()
        }
    }
    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body([
                [
                        id: 1,
                        comment: "Adorei o produto",
                        createdAt: anyIso8601WithOffset(),
                        grade: 5,
                        productId: 1
                ],
                [
                        id: 2,
                        comment: "Bom custo-benefício",
                        createdAt: anyIso8601WithOffset(), //Usar esse, retorna o valor
                        grade: 4,
                        productId: 1
                ],
                [
                        id: 3,
                        comment: "Esperava mais do produto",
                        createdAt: iso8601WithOffset(), //Não usar esse, retorna o REGEX
                        grade: 3,
                        productId: 1
                ]
            ]
        )
        bodyMatchers {
            jsonPath('$.[0].id', byEquality())
            jsonPath('$.[0].grade', byEquality())
            jsonPath('$.[0].comment', byEquality())
            jsonPath('$.[0].createdAt', byRegex(iso8601WithOffset()))
            jsonPath('$.[0].productId', byEquality())

            jsonPath('$.[1].id', byEquality())
            jsonPath('$.[1].grade', byEquality())
            jsonPath('$.[1].comment', byEquality())
            jsonPath('$.[1].createdAt', byRegex(iso8601WithOffset()))
            jsonPath('$.[1].productId', byEquality())

            jsonPath('$.[2].id', byEquality())
            jsonPath('$.[2].grade', byEquality())
            jsonPath('$.[2].comment', byEquality())
            jsonPath('$.[2].createdAt', byRegex(iso8601WithOffset()))
            jsonPath('$.[2].productId', byEquality())
        }
    }
}
