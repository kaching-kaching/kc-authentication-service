<h1 align="center">Welcome to Kaching authentication service 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-0.0.1-blue.svg?cacheSeconds=2592000" />
</p>

> Authentication service provides users managing endpoints for Kaching system

## Configuration
Example
```yaml
server:
  port: 8080
spring:
  application:
    name: kaching-authentication
  datasource:
    url: jdbc:postgresql://localhost:5432/kaching_authentication_db
    username: admin
    password: admin
    driverClassName: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: create
    generate-ddl: true
  data:
    rest:
      base-path: api
kaching:
  google:
    refreshToken:
      path: firebase/firebase-refresh-token.json
    webClient:
      signInHost: https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword
      apiKey: [YOUR_API_KEY]
```

## Author

👤 **Kaching Team**

* Website: https://github.com/kaching-kaching
* Github: [@kaching-kaching](https://github.com/kaching-kaching)

## Show your support

Give a ⭐️ if this project helped you!

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_