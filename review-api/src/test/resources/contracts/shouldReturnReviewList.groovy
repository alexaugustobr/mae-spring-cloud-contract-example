package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET() //'GET'
        url '/products/1/reviews'
        headers {
            contentType(applicationJson()) // ou 'application/json'
        }
    }
    response {
        status OK() //200
//        body([
//                [
//                        id: anInteger(),
//                        comment: anyNonBlankString(),
//                        created_at: iso8601WithOffset(),
//                        grade: anInteger(),
//                        product_id: anInteger()
//                ]
//        ])
    }
}
