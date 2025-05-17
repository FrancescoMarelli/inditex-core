# Inditex Prices API

## 🛠 Instrucciones de Uso

**Compilar la aplicación**  
Ejecutart el siguiente comando para compilar el proyecto:

   ```bash
   mvn clean install
   ```
## Iniciar la aplicación
Para lanzar la aplicación desde tu IDE (Botón run) o desde la terminal ejecuta:

   ```bash
    mvn spring-boot:run
   ``` 

## Realizar consultas
Se puede acceder al endpoint de consulta de precios por rango de fechas: `/api/v1/get-prices-info`, ahí introduce los datos de consulta: fecha, id de marca e id del producto

## Crear promociones personalizadas
Se ha habilitado un endpoint adicional para registrar nuevas promociones, lo que permite añadir marcas, rangos de fechas y precios específicos.

##  Base de Datos H2
Durante la inicialización de la aplicación:

Se crea automáticamente la tabla PRICES.

Se insertan los datos indicados en el enunciado del proyecto.

## Acceso a la consola H2
Abre un navegador y accede a:
http://localhost:8082/h2-console

Completa el formulario de conexión:

`JDBC URL: jdbc:h2:mem:inditex-app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`

`User: user`

``Password: password``

## Documentación OpenAPI
Interfaz Swagger UI está disponible en:
http://localhost:8082/swagger-ui.html




