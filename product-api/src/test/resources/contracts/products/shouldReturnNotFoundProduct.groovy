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
//        bodyMatchers {
//            jsonPath('$.status', byEquality())
//            jsonPath('$.timestamp', byEquality())
//            jsonPath('$.timestamp', byRegex("^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24\\:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?\$"))
//            jsonPath('$.title', byEquality())
//            jsonPath('$.type', byEquality())
//        }
    }
}
