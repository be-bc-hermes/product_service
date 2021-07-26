- Run RabbitMQ with docker :
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
  
- Update Product Price Endpoint
  PUT
  {
  "id":3,
  "priceType":"MOBILE",
  "oldPrice":135,
  "newPrice":150

}