# buyhereBackend
Buyhere is an e-commerce website consisting of both the background part for website administrators and the frontend part for normal customers.

## Basics

Built with **Spring Boot** and **Thymeleaf Template**

Database Layer: **Mysql**, **Amazon S3**, **Hibernate**

IDE: **IntelliJ IDEA**

## Features

### Buyhere Admin - Backend Administration 

#### Role-based authorization
**Spring Security** limits the authorization of different roles on the backend website, including:
 
  - Admin
  - Salesperson 
  - Editor 
  - Shipper
  - Assistant 
  
  <img src="https://www.linkpicture.com/q/backData.jpg" width="700px">
  <img src="https://www.linkpicture.com/q/backCreateuser.jpg" width="700px">
  
#### Mail Service
Buyhere builds the mail service with **Spring Mail**.

Everytime there is a new user registeration or a new order, the website default mail will send confirmation email for specific users.

Website administrator can modify mail address and mail template.

<img src="https://www.linkpicture.com/q/backSetting.jpg" width="700px">

<img src="https://www.linkpicture.com/q/backData.jpg" width="700px">

#### Sales Report

Website management team can easily check the sales situation with charts with **Google Chart API**

<img src="https://www.linkpicture.com/q/backReport.jpg" width="700px">
  
### Buyhere - Online Shopping Website
  
### User Authentication 

Buyhere enables user sign in with email/password and Google account through **Spring OAuth**.

<img src="https://www.linkpicture.com/q/frontLogin.jpg" width="700px">

### Products 

User can skan availble products in different categories, view product detail, user rateing, and add item to shopping cart.

<img src="https://www.linkpicture.com/q/frontLogin.jpg" width="700px">
<img src="https://www.linkpicture.com/q/frontLogin.jpg" width="700px">
<img src="https://www.linkpicture.com/q/frontLogin.jpg" width="700px">

### Check Out

Buyhere enabled payment integration with **PayPal Checkout API**.

<img src="https://www.linkpicture.com/q/frontCheckout.jpg" width="700px">


  

  
