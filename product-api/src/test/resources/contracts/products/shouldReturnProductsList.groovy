package contracts.products

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Deve retornar lista de produtos" //Opcional
    request{
        method GET() //Deve passar urlPath ou url, nunca ambos,
        // url pode ser apenas o path, mas o idela se for usar url que seja completa como http://localhost:8080/produtos
        urlPath("/products") {
        }
    }
    response {
        status 200
        body([
                [
                        id: 1,
                        name: "Notebook Gamer RX76",
                        price: "1999.9"
                ],
                [
                        id: 2,
                        name: "Monitor 22p",
                        price: "1500.0"
                ],
                [
                        id: 3,
                        name: "Microfone FT342",
                        price: "300.0"
                ]
        ])
        bodyMatchers {
            jsonPath('$.[0].id', byEquality())
            jsonPath('$.[0].name', byEquality())
            jsonPath('$.[0].price', byEquality())
            jsonPath('$.[1].id', byEquality())
            jsonPath('$.[1].name', byEquality())
            jsonPath('$.[1].price', byEquality())
            jsonPath('$.[2].id', byEquality())
            jsonPath('$.[2].name', byEquality())
            jsonPath('$.[2].price', byEquality())
        }
        headers {
            contentType("application/json")
        }
    }
}
