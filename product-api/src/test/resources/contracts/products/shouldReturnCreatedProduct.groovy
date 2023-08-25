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
        body([
                id: 1,
                name: "Notebook Gamer RX76",
                price: "1999.9",
                reviews: []
        ])
        bodyMatchers {
            jsonPath('$.id', byRegex("^[0-9]*\$"))
            jsonPath('$.name', byEquality())
            jsonPath('$.price',byEquality())
            jsonPath('$.reviews',byType({
                        minOccurrence(0)
                    })) //byNull
        }
        headers {
            contentType("application/json")
        }
    }
}
