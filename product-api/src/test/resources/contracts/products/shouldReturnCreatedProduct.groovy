package contracts.products

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'should return created product'
    request{
        method POST()
        headers {
            header(contentType(), applicationJson())
            header(authorization(), value(
                    stub(anyNonBlankString()),
                    test(execute('generateToken()'))
            ))
        }
        urlPath("/products") {
            body(file("json/create-product-request.json"))
        }
    }
    response {
        status 201
        body(file("json/create-product-response.json"))
        headers {
            contentType applicationJson()
        }
    }
}
