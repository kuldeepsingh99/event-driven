# Event Driven Architecture Example with Rabbit MQ

This example demonstrate the Event driven architecture with Rabbit MQ

## Prerequisite
1. Rabbit MQ Running Instance

## About the example
![alt text](https://github.com/kuldeepsingh99/event-driven/blob/d2dd19b83eff0773c02fe182d96101ecf6bd424c/Image.png)

**1. UIService** :- This microservice send request to Rabbit MQ (We call it as PurchaseRequest). This microservice also listen for the Payment Processed Event on the topic which paymentService is sending. In this microservice we are using server side events which is pushed to browser every 2 sec.

**2.PaymentService** :- This microservice listen on the purchase queue, it will do some processing and it will send confirmation message to a Topic (Rabbit MQ will automatically create Queue as per the consumers, we don't need to give any name)

**3.StockService** :- This microservice listen on the topic, which paymentService is sending. (Its not doing any business login, its just an example)

**4.serverEventUI**:- This a angular JS Code which is used for send Purchase Event request and consume Event Stream.

## Steps

1. Import the Java Maven Project and run all the project

2. Run the UI Project (npm install and ng serve command) 

3. Click on Purchase Button, on every Button cick we can see the Inventory getting decreased.




