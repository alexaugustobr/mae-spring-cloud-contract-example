package contracts.products

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'should return updated product'
    request{
        method PUT()
        headers {
            contentType applicationJson()
        }
        urlPath("/products/1") {
            body([
                    id: 1,
                    name: "Notebook X11",
                    price: "899.9"
            ])
        }
    }
    response {
        status 200
        body([
                id: 1,
                name: "Notebook X11",
                price: "899.9",
                reviews: []
        ])
        bodyMatchers {
            jsonPath('$.id', byEquality())
            jsonPath('$.name', byEquality())
            jsonPath('$.price', byEquality())
        }
        headers {
            contentType applicationJson()
        }
    }
}
