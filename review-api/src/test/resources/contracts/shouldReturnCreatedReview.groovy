package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST() //'POST'
        headers {
            contentType applicationJson()
        }
        urlPath value(
                    stub(regex("^\\/products\\/[1-9]\\d*\\/reviews\$")), //Qualquer positivo
                    test("/products/1/reviews")
        )
        body(
            [
                grade: value(  // value ou $
                        stub(regex('[1-5]')), //Valor que vai para regex de request do stub
                        test("2") //Valor fixo que vai para o teste de contrato
                ),
                comment: $(
                        stub(nonBlank()),
                        test("Superou as expectativas.")
                )
            ]
        )
//        body(
//            [
//                    grade: 2,
//                    comment: "Superou as expectativas."
//            ]
//        )
//        bodyMatchers {
//            jsonPath('$.grade', byRegex('[1-5]'))
//            jsonPath('$.comment', byRegex(nonBlank()))
//        }
    }
    response {
        status 201
        headers {
            contentType applicationJson()
        }
        body(
            [
                id: 1, //1 Será apresentado no stub, podemos usar anyPositiveInt
                grade: fromRequest().body('$.grade'),
                comment: fromRequest().body('$.comment'),
                createdAt: anyIso8601WithOffset()
            ]
        )
        bodyMatchers {
            jsonPath('$.id', byRegex(positiveInt()))
////            jsonPath('$.grade', byEquality())
////            jsonPath('$.comment', byEquality())
//            jsonPath('$.createdAt', byRegex(iso8601WithOffset()))
////            jsonPath('$.productId', byEquality())
        }
    }
}
