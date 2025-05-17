# Inditex Prices API

## 🛠 Instrucciones de Uso

Ejecutar el siguiente comando para compilar el proyecto:

   ```bash
   mvn clean install
   ```
## Iniciar la aplicación
Para lanzar la aplicación desde tu IDE (Botón run) o desde la terminal ejecuta:

   ```bash
    mvn spring-boot:run
   ``` 

## Realizar consultas

Acceder a la interfaz Swagger UI está disponible en: http://localhost:8082/swagger-ui.html

### Obten información de una promoción por fecha
Se puede acceder al endpoint de consulta de precios por rango de fechas: `/api/v1/get-prices-info`, ahí introduce los datos de consulta: fecha, id de marca e id del producto en los formatos sugeridos en la interfaz

### Crear promociones personalizadas
Se ha habilitado un endpoint adicional para registrar nuevas promociones, lo que permite añadir marcas, rangos de fechas y precios específicos: `/api/v1/create-prices`

##  Base de Datos H2
Durante la inicialización de la aplicación:

Se crea automáticamente la tabla PRICES y se insertan los siguientes datos indicados en el enunciado del proyecto.

````
| ID    | BRAND_ID | START_DATE            | END_DATE              | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|-------|----------|-----------------------|-----------------------|------------|------------|----------|-------|------|
|   0   | 1        | 2020-06-14-00.00.00   | 2020-12-31-23.59.59   | 1          | 35455      | 0        | 35.50 | EUR  |
|   1   | 1        | 2020-06-14-15.00.00   | 2020-06-14-18.30.00   | 2          | 35455      | 1        | 25.45 | EUR  |
|   2   | 1        | 2020-06-15-00.00.00   | 2020-06-15-11.00.00   | 3          | 35455      | 1        | 30.50 | EUR  |
|   3   | 1        | 2020-06-15-16.00.00   | 2020-12-31-23.59.59   | 4          | 35455      | 1        | 38.95 | EUR  |
````
## Acceso a la consola H2
Abre un navegador para consultar la base de datos y accede a:
http://localhost:8082/h2-console

Para acceder asegurarse de introducir estos datos en el formulario de conexión:

`JDBC URL: jdbc:h2:mem:inditex-app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`

`User: user`

``Password: password``






