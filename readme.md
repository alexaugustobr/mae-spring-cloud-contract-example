# Executando stub local

## Compilar e colocar artefato na m2
```bash
mvn clean install
```

## Apenas gerar
```bash
mvn org.springframework.cloud:spring-cloud-contract-maven-plugin:convert
```


## Download do runner jar
```bash
wget https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-contract-stub-runner-boot/4.0.4/spring-cloud-contract-stub-runner-boot-4.0.4.jar
```

## Executando o runner jar junto ao stub do app

```bash
java -jar spring-cloud-contract-stub-runner-boot-4.0.4.jar --stubrunner.ids=com.algaworks.example:product-api:0.0.1-SNAPSHOT:8081 --stubrunner.stubs-mode=LOCAL
```

```bash
java -jar spring-cloud-contract-stub-runner-boot-4.0.4.jar --stubrunner.ids=com.algaworks.example:review-api:0.0.1-SNAPSHOT:8082 --stubrunner.stubs-mode=LOCAL
```