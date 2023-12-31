package contracts.products

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name "shouldReturnProductListWithoutFilter"
            description "Deve retornar lista de produtos" //Opcional
            request{
                method GET()
                urlPath("/products")
                headers {
                    accept applicationJson()
                }
            }
            response {
                status 200
                body([
                        content: [
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
                        ],
                        size: 10,
                        totalElements: 3,
                        totalPages: 1,
                        number: 0
                ])
                bodyMatchers {
                    jsonPath('$.size', byEquality())
                    jsonPath('$.totalElements', byEquality())
                    jsonPath('$.totalPages', byEquality())
                    jsonPath('$.number', byEquality())
                    jsonPath('$.content.[0].id', byEquality())
                    jsonPath('$.content.[0].name', byEquality())
                    jsonPath('$.content.[0].price', byEquality())
                    jsonPath('$.content.[1].id', byEquality())
                    jsonPath('$.content.[1].name', byEquality())
                    jsonPath('$.content.[1].price', byEquality())
                    jsonPath('$.content.[2].id', byEquality())
                    jsonPath('$.content.[2].name', byEquality())
                    jsonPath('$.content.[2].price', byEquality())
                }
                headers {
                    contentType applicationJson()
                }
            }
        },
        Contract.make {
            name "shouldReturnProductListWithFilter"
            description "Deve retornar lista de produtos com filtro" //Opcional
            request{
                method GET() //Deve passar urlPath ou url, nunca ambos,
                // url pode ser apenas o path, mas o idela se for usar url que seja completa como http://localhost:8080/produtos
                urlPath("/products") {
                    queryParameters {
                        parameter('size', value(stub(2), test(2)))
                        parameter('number', value(stub(0), test(0)))
                    }
                }
                headers {
                    accept applicationJson()
                }
            }
            response {
                status 200
                body([
                        content: [
                                [
                                        id: 1,
                                        name: "Notebook Gamer RX76",
                                        price: "1999.9"
                                ],
                                [
                                        id: 2,
                                        name: "Monitor 22p",
                                        price: "1500.0"
                                ]
                        ],
                        size: fromRequest().query("size"),
                        totalElements: 3,
                        totalPages: 2,
                        number: fromRequest().query("number")
                ])
                bodyMatchers {
                    jsonPath('$.size', byEquality())
                    jsonPath('$.totalElements', byEquality())
                    jsonPath('$.totalPages', byEquality())
                    jsonPath('$.number', byEquality())
                    jsonPath('$.content.[0].id', byEquality())
                    jsonPath('$.content.[0].name', byEquality())
                    jsonPath('$.content.[0].price', byEquality())
                    jsonPath('$.content.[1].id', byEquality())
                    jsonPath('$.content.[1].name', byEquality())
                    jsonPath('$.content.[1].price', byEquality())
                }
                headers {
                    contentType applicationJson()
                }
            }
        }
]
