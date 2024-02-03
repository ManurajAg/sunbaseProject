# Sunbase Internship Project

## Requirements
  - Ensure Java installation and configuration for spring boot projects
  - JS enabled Browser
  - Internet Connection
  - MySql 8 with database named as sunbase
  - Ensure backend server runs on localhost:8080 port
## Features
  - CRUD operation for customer via rest api
  - Syncing through api
## How to run
  - Start backend server
  - Start frontend Server
  - Access loginPage
  - Enter credentials username :- admin, password :- admin

## Images
  - Login Page
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/dc45c686-fdc3-40f2-afd9-a3b90cc127d7)

  - Login Request, kindly note that it sets jwt token received in local storage so that it can be used for setting as authentication token in request's header
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/c75466a6-8afb-458e-80c0-bda65f70bb22)

  - JWT Token recieved from server
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/be965040-79bf-4ec7-8eb1-334c4059688c)
    
  - Customer List Page,customers are sorted on basis of email
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/d9812000-3f2e-4f70-b9d1-ec52516f41b1)
  
  - Demonstrating Paging Functionality, Page 2 of customer List as Configured for 5 customers per page
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/1358fd2c-fca9-4b61-aaaf-861caf96983f)

  - Search Functionality
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/632e522c-0aec-4a6a-a057-6e8b13ee9c6e)
    
  - Add Customer Form
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/bed16f44-93b2-4ce1-b87b-664a752b83b8)

  - Update Customer From with prefilled information
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/995b8bb3-e90e-4db2-943c-997d92503a1a)
    
  - Delete Customer :- Kindly note that Test2 Contact has been deleted and also note changes in paging
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/72e03c95-16b8-44b2-b312-d3dfb32e3ccf)

  - Sync call to get token, below image shows access_token in local storage
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/41daa289-74a1-45f1-820d-443f61196c77)
   
  - Code for call to get token from api
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/ca6b9a5c-5d58-47bf-8fa8-dd755cdaa3b2)
    
  - Call for contacts from api
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/c3209881-c9ee-40f2-9d21-b807077c06bc)
    
  - It calls saveContactsOfApiToDB function which is as :- Note the token sent in header for jwt
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/de93ae8a-0421-45a3-852d-a40d3ee1cc52)
    
  - Backend Request Handler to update/save contacts from api
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/cb0e2c31-763f-44a0-8dcc-bc5fd02feaf6)
  
  - JWT token and Access token stored in api
    ![image](https://github.com/ManurajAg/sunbaseProject/assets/93424763/8f552da7-7bcb-478d-9422-97c469359cf2)

    
    
