# API document  : https://developer.paypal.com/docs/api/overview/
          Manual/PreReq Steps :
          step 1: Create an account using https://developer.paypal.com/home/
          step 2 : Login into Developer dashboard using above created account credentials
          step 3 : Create a new app
          step 4 : Edit created app and retrive client_id and secret

# Postman Implementation 
1. PaypalGetAccessCode - Request to get access token 
                    Request end point - https://api.sandbox.paypal.com/v1/oauth2/token
                    Request Authorization - Basic -username = clientID and pasword = secret
                    Request Body - x-www-form-urlencoded - grant_type : client_credentials
                    Request method type - post
                    Expected Response fields :
                      "access_token": "<access token>",
                      "token_type": "Bearer",
                    Expected Response status code : 200
 2. CreateNewProduct - Creates new product and verifies that product is created
          Request end point - https://api.sandbox.paypal.com/v1/catalogs/products
          Request Authorization - Bearer - ABove retrived Access_token used 
          Request Body - json format - 
                  Sample request
                    {
                      "name": "Pranathi 60day API Service",
                      "description": "60Day API service provides practice problems 2 per week for 60days",
                      "type": "SERVICE",
                      "category": "SOFTWARE",
                      "image_url": "https://example.com/streaming.jpg",
                      "home_url": "https://example.com/home"
                    }
          Request method type - post
          Expected Response Mandatory fields :
                  Sample response :
                  {
                      "id": "prod_id",
                      "name": "prod_name",
                      "description": "prdo_desc",
                      "create_time": "2020-06-06T06:19:40Z",
                      "links": [
                          {
                              "href": "https://api.sandbox.paypal.com/v1/catalogs/products/prdo_id", 
                              "rel": "self",
                              "method": "GET"
                          },
                          {
                              "href": "https://api.sandbox.paypal.com/v1/catalogs/products/prod_id",
                              "rel": "edit",
                              "method": "PATCH"
                          }
                      ]
                  }
                
# a) Verify the status code for create product request
pm.test("Successful POST request", function () {
    pm.expect(pm.response.code).to.be.oneOf([201,202]);
});
# b) Verify the response contains category and type as expected
Since Response for request end point above with method type -post doesnt returns catogery and type fields but on requesting with method type get
          "href": "https://api.sandbox.paypal.com/v1/catalogs/products/prdo_id" in response gives catogery and type fields
          -pm.sendRequest  used to hit 
              - url:"https://api.sandbox.paypal.com/v1/catalogs/products/"+resp.id,
              - method: 'GET'
              - header: {
                      Authorization: "Bearer "+ pm.variables.get("paypalBearerAuth")
                      }
        
          - pm.test used to "Verify the response contains category as expected"
          - pm.test used to Verify the response contains type as expected"
          - pm.test used to "Successful POST request status code expected 200",
