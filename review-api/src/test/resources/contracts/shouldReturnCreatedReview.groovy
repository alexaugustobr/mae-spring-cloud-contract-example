package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST() //'POST'
        headers {
            contentType applicationJson() // 'application/json'
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
        body(
            [
                id: anInteger(),
                grade: 4,
                comment: "Superou as expectativas."
            ]
        )
    }
}
