package contracts.products

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'should return created product'
    request{
        method POST()
        headers {
            contentType("application/json")
        }
        urlPath("/products") {
            body([
                    name: "Notebook Gamer RX76",
                    price: "1999.9"
            ])
        }
    }
    response {
        status 201
        body([ //validacao de forma implicita
                id: 1,
                name: "Notebook Gamer RX76",
                price: "1999.9",
                reviews: []
        ])
//        body([ //validacao explicita
//                id: anyNumber(),
//                name: anyOf("Notebook Gamer RX76"),
//                price: anyOf("1999.9"),
//                reviews: []
//        ])
//        bodyMatchers { // geracao de stub dinamico
//            jsonPath('$.id', byRegex("^[0-9]*\$"))
//            jsonPath('$.name', anyIso8601WithOffset())
//            jsonPath('$.price',byEquality())
//            jsonPath('$.reviews',byType({
//                        minOccurrence(0)
//                    })) //byNull
//        }
        headers {
            contentType("application/json")
        }
    }
}
