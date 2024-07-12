# Flag Demo

Simple Spring Boot app that colorize a flag given the color parameter.

Start the server on localhost, default to port 8080.

```
mvn spring-boot:run 
```

The GET endpoint `/image` accepts the parameter color, which is the RGB color in hexadecimal and without prefix.

Examples: 

* Default color: [http://localhost:8080/image](http://localhost:8080/image)
* Greenwashing: [http://localhost:8080/image?color=239731](http://localhost:8080/image?color=239731)
* Halloween: [http://localhost:8080/image?color=cd6e1d](http://localhost:8080/image?color=cd6e1d)
* Breast cancer: [http://localhost:8080/image?color=e41cdd](http://localhost:8080/image?color=e41cdd)
* Eggplant: [http://localhost:8080/image?color=63009d](http://localhost:8080/image?color=63009d)

