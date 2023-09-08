package contracts.products

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return not found product"
    request {
        method GET()
        urlPath("/products/9999")
    }
    response {
        status 404
        body([
                status: 404,
                timestamp: anyIso8601WithOffset(), //"2023-08-24T09:22:28.7844133-03:00"
                title: "Recurso não encontrado",
                type: "resource-not-found"
        ])
    }
}
