

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return found product by id"
    request {
        method GET()
        urlPath("/products/1")
    }
    response {
        status 200
        body([
                id: 1,
                name: "Notebook Gamer RX76",
                price: "1999.9",
                reviews: []
        ])
        bodyMatchers {
            jsonPath('$.id', byEquality())
            jsonPath('$.name', byEquality())
            jsonPath('$.price', byEquality())
        }
        headers {
            contentType("application/json")
        }
    }
}


