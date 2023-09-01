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
                id: 1,
                grade: 4,
                comment: "Superou as expectativas."
            ]
        )
        bodyMatchers {
            jsonPath('$.id', byType())
//            jsonPath('$.grade', byEquality())
//            jsonPath('$.comment', byEquality())
        }
    }
}
