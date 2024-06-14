# MultiEnvironmentConfigurationforEcommerceSite

## Introduction
This Ecomerce Management System allows user manage products through various of operations that interact with the database

## Features
- Add a single products or multiple products using name, description, seller, categories and price  with validation to make sure inputs are correct
- Retrieve products:
    - Retrieve all products
    - Retrieve products by Name
    - Retrieve products by Sellers
    - Retrieve products by Price
    - Retrieve products by ID
    - Retrieve products in sorted orders 
- Update existing products details with validation
- Delete products from the database
- Switch between spring profiles

## Setup Instruction
1. Clone the repository: `git clone https://github.com/hyah01/MultiEnvironmentConfigurationforEcommerceSite`
2. Navigate to the project directory `cd MultiEnvironmentConfigurationforEcommerceSite`
3. Open `application.properties` file located in `src/main/resources`
4. Modify the configuration based on your database
```
   spring.datasource.url=jdbc:mysql://localhost:3306/customermanagementapi
   spring.datasource.username=username
   spring.datasource.password=password
```
5. Build the project `mvn clean install`
6. Run the application `mvn spring-boot:run`
7. Access the application through  `http://localhost:9090` or 7070 or 8080 based on what profile you are on
8. Use Postman to utilize HTTP Requests

## Usage
### Add 1 Products
Use `POST` method on `/products`
On Body `[{
"name": "Pen",
"description": "Pen",
"price": 15.99,
"seller": "Yy",
"categories" : ["Supply"]
},]`

### Add Multiple products
Use `POST` method on `/customers`
On Body `[{
"name": "Pen",
"description": "Pen",
"price": 15.99,
"seller": "Yy",
"categories" : ["Supply"]
},
{
"name": "Laptop",
"description": "Laptop",
"price": 499.99,
"seller": "Best Buy",
"categories" : ["Tech","Personal"]
}]`

### Add Multiple products n amount of time
Use `POST` method on `/customers/multiple?num={num}`
On Body `[{
"name": "Pen",
"description": "Pen",
"price": 15.99,
"seller": "Yy",
"categories" : ["Supply"]
},
{
"name": "Laptop",
"description": "Laptop",
"price": 499.99,
"seller": "Best Buy",
"categories" : ["Tech","Personal"]
}]`

### Update 1 Product
Use `PUT` method on `/products`
On Body `[
{
"id": 1,
"name": "Product A",
"description": "Updated Description",
"price": 19.99,
"seller": "Updated Seller",
"categories": ["Updated Category"]
}
]`

### Update Multiple Products
Use `PUT` method on `/products`
On Body `[
{
"id": 1,
"name": "Product1 A",
"description": "Updated Description1",
"price": 19.99,
"seller": "Updated Seller1",
"categories": ["Updated Category1"]
},
{
"id": 2,
"name": "Product2 A",
"description": "Updated Description2",
"price": 29.99,
"seller": "Updated Seller2",
"categories": ["Updated Category2"]
}
]`

### Delete a Product
Use `DELETE` method on `/products/{id}` Where id is the ID of the product

### Get All Products
Use `GET` method on `/products`

### Get Product By ID
Use `GET` method on `/products/{id}` Where id is the ID of the product

### Get Product By Name
Use `GET` method on `/products/name?name={ProductName}` Where ProductName is the name of the product

### Get Product By Seller
Use `GET` method on `/products/seller?seller={ProductSeller}` Where ProductSeller is the name of the seller

### Get Product By Price
Use `GET` method on `/products/price?price={ProductPrice}` Where ProductPrice is the price of the product

### Get Product By Category
Use `GET` method on `/products/category?category={ProductCategory}` Where ProductPrice is the category of the product

### Get Product Sorted By Name
Use `GET` method on `/products/name/sorted`

### Get Product Sorted By Seller
Use `GET` method on `/products/seller/sorted`





