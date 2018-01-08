# Event Driven Architecture Example with Rabbit MQ

This example demonstrate the Event driven architecture with Rabbit MQ

## Prerequisite
1. Rabbit MQ Running Instance

## About the example

**1. UIService** :- This microservice send request to Rabbit MQ (We call it as PurchaseRequest, purchase Queue is used to this purpose). This microservice also listen on the topic which paymentService is sending. In this microservice we are using server side events which is pushed to angular js every 2 sec.

**2.PaymentService** :- This microservice listen on the purchase queue, it will do some processing and it will send confirmation message to a Topic (Rabbit MQ will automatically create Queue as per the consumers, we don't need to give any name)

**3.StockService** :- This microservice listen on the topic, which paymentService is sending. (Its not doing any business login, its just an example)



