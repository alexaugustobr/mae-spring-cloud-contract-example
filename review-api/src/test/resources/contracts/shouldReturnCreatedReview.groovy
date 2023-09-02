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
                grade: 4,
                comment: "Superou as expectativas."
            ]
        )
    }
    response {
        status 201
        headers {
            contentType applicationJson()
        }
        body(
            [
                id: 1, //1 Será apresentado no stub, podemos usar anyPositiveInt
                grade: 4,
                comment: "Superou as expectativas.",
                createdAt: iso8601WithOffset(),
                productId: 1
            ]
        )
        bodyMatchers {
            jsonPath('$.id', byType())
////            jsonPath('$.grade', byEquality())
////            jsonPath('$.comment', byEquality())
//            jsonPath('$.createdAt', byRegex(iso8601WithOffset()))
////            jsonPath('$.productId', byEquality())
        }
    }
}
