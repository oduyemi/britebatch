# BriteBatch API Documentation

## Introduction

### Overview

BriteBatch is an inventory management API designed to handle batches of products efficiently. It is built using Java Spring Boot and utilizes a MySQL database for data storage. This documentation provides detailed information on how to integrate and interact with the BriteBatch API.

#### Key Features

Batch creation and management

Product association with batches

Inventory tracking

User roles: Admin, Sales, Marketers

RESTful API for easy integration


#### Getting Started

To use the BriteBatch API, you need to obtain an API key. Follow the steps outlined in the Authentication section to get started.

#### Authentication

##### API Key

Authentication to the BriteBatch API is performed via API key. Each request must include an API key in the headers for successful authorization. To obtain an API key, please contact [your_company_contact_email].

Example:

http

`Copy code
GET /api/batches HTTP/1.1
Host: api.britebatch.com
Authorization: ApiKey YOUR_API_KEY`

#### Endpoints
##### 1. Batches

##### 1.1 Create Batch

`Endpoint: POST /api/batches`

###### Create a new batch of products.

Request Body:

json

`Copy code
{
  "name": "Batch 123",
  "description": "New products batch",
  "products": [1, 2, 3]
}
1.2 Get Batch Details
Endpoint: GET /api/batches/{batchId}`

###### Retrieve details of a specific batch.

##### 1.3 List Batches

`Endpoint: GET /api/batches`

###### List all batches.

##### 2. Products

##### 2.1 Get Products in Batch

`Endpoint: GET /api/batches/{batchId}/products`

###### Retrieve all products associated with a specific batch.

##### 2.2 Update Product (Sales)

`Endpoint: PUT /api/products/{productId}`

###### Update product details, typically used for recording sales.

##### 3. Users

##### 3.1 Assign Roles (Admin Only)

`Endpoint: PUT /api/users/{userId}/roles`

Assign roles to users. Only accessible by users with admin privileges.

Request Body:

json

`{
  "roles": ["admin", "sales"]
}`

##### 4. Error Handling

###### HTTP Status Codes

BriteBatch API uses standard HTTP status codes to indicate the success or failure of a request. Common status codes include:

`200 OK
201 Created
400 Bad Request
401 Unauthorized
404 Not Found
500 Internal Server Error
Error Response Format
Error responses include a JSON object providing details about the error.`

Example:

json

`{
  "error": "InvalidRequest",
  "message": "Invalid batch ID"
}`

##### 5. Examples

###### Creating a Batch

http

`POST /api/batches HTTP/1.1
Host: api.britebatch.com
Authorization: ApiKey YOUR_API_KEY
Content-Type: application/json
{
  "name": "New Batch",
  "description": "A batch of fresh products",
  "products": [4, 5, 6]
}`

###### Fetching Products in a Batch
http

`GET /api/batches/123/products HTTP/1.1
Host: api.britebatch.com
Authorization: ApiKey YOUR_API_KEY
Assigning Roles
http
Copy code
PUT /api/users/456/roles HTTP/1.1
Host: api.britebatch.com
Authorization: ApiKey YOUR_API_KEY
Content-Type: application/json
{
  "roles": ["admin", "sales"]
}`

#### Best Practices

##### Pagination

For endpoints that return a list of items, use pagination to manage large datasets. Use page and pageSize parameters.

Example:

http

`GET /api/batches?page=2&pageSize=10 HTTP/1.1`

#### Consistent Naming
Follow a consistent naming convention for API endpoints, request parameters, and response attributes to enhance clarity and maintainability.
