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
          Request method type - post
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
          Since Response for request end point(https://api.sandbox.paypal.com/v1/catalogs/products) with method type - post doesnt                    returns catogery and type fields
          Requesting below url from above response with method type get returns catogery and type fields as expected 
                    "href": "https://api.sandbox.paypal.com/v1/catalogs/products/prdo_id" 
                    -pm.sendRequest  used to hit 
                        - url:"https://api.sandbox.paypal.com/v1/catalogs/products/"+resp.id,
                        - method: 'GET'
                        - header: {
                                Authorization: "Bearer "+ pm.variables.get("paypalBearerAuth")
                                }

                    - pm.test used to "Verify the response contains category as expected"
                    - pm.test used to Verify the response contains type as expected"
                    - pm.test used to "Successful POST request status code expected 200"
      
3. ListProducts - Lists created products # Verify that the created products are listed
          
                    Request end point - https://api.sandbox.paypal.com/v1/catalogs/products
                    Request method type - GET
                    Request Authorization - Bearer - ABove retrived Access_token used 
                    Request Query params -
                              page : default value 1 --- returns page 1 of response
                                        example page : 2 --- returns 2nd page of response
                              page_size : default value 10 --- returns 10 records per page
                                        example page_size 50 --- returns 50 records per page
                     Expected sample response 
                     {
                      "total_items": 20,
                      "total_pages": 10,
                      "products": [
                        {
                          "id": "72255d4849af8ed6e0df1173",
                          "name": "Video Streaming Service",
                          "description": "Video streaming service",
                          "create_time": "2018-12-10T21:20:49Z",
                          "links": [
                            {
                              "href": "https://api.paypal.com/v1/catalogs/products/72255d4849af8ed6e0df1173",
                              "rel": "self",
                              "method": "GET"
                            }
                          ]
                        },
                        {
                          "id": "PROD-XYAB12ABSB7868434",
                          "name": "Video Streaming Service",
                          "description": "Audio streaming service",
                          "create_time": "2018-12-10T21:20:49Z",
                          "links": [
                            {
                              "href": "https://api.paypal.com/v1/catalogs/products/125d4849af8ed6e0df18",
                              "rel": "self",
                              "method": "GET"
                            }
                          ]
                        }
                      ],
                      "links": [
                        {
                          "href": "https://api.paypal.com/v1/catalogs/products?page_size=2&page=1",
                          "rel": "self",
                          "method": "GET"
                        },
                        {
                          "href": "https://api.paypal.com/v1/catalogs/products?page_size=2&page=2",
                          "rel": "next",
                          "method": "GET"
                        },
                        {
                          "href": "https://api.paypal.com/v1/catalogs/products?page_size=2&page=10",
                          "rel": "last",
                          "method": "GET"
                        }
                      ]
                    }

 # Restassured Implementation for scenario 2) Create multiple products [using dataprovider + RestAssured]
                     60DayApiRestAssured is maven project which is build by using 
                     RestAssured, java and testng
		src/main/java/week1/day1/paypal/ is the source folder for paypal implementation
1. src/main/java/week1/day1/paypal/PayPalBasics.java 
                    
                    public String paypalBearerAuth -- so that it can be used by any java class which extends PayPalBasics.java 
                    public ArrayList<String> productIds = new ArrayList<String>() -- productid of created app are stored here
                    - Testng annotations used 
                              @BeforeSuite 
                                  Request end point (RestAssured.baseURI)  - https://api.sandbox.paypal.com/v1/oauth2/token
                                  Request Authorization - Basic -username = clientID and pasword = secret  
                                         RestAssured
				.preemptive()
				.basic("username","pswd")
                                  Request params : paramsMap.put("grant_type", "client_credentials")
                                  Request method type - post
                                  Expected Response fields :
                                          "access_token": "<access token>", -- stored in variable "paypalBearerAuth" 
                                          "token_type": "Bearer",
                                      
                              @DataProvider(name="createpaypalapp")
                                   Using java.io.FilenameFilter, retrived  no.of "*.json" files from given projectworkspace
                                   File[] jsonFiles = projectWorkSpace.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".json");
				}
			     });
                                   This numOfJsonFiles is passed to build String[] size to return json file path

2. src/main/java/week1/day1/paypal/CreateProductwithbody.java 
          
          extends PayPalBasics -- for Bearer token "String paypalBearerAuth 
          Testng annotations used  
	  		@Test
          Request end point -  https://api.sandbox.paypal.com/v1/catalogs/products
       	  Request Authorization - bearer
          Request Method type - POST
          Request Headers - headerList.add(new Header("Authorization","Bearer "+paypalBearerAuth)) 
          Request Body - sample request : 
                    {
                      "name": "body within body",
                      "description": "request body given in body()",
                      "type": "SERVICE",
                      "category": "SOFTWARE",
                      "image_url": "https://example.com/streaming.jpg",
                      "home_url": "https://example.com/home"
                    }
          Request Content type - ContentType.JSON
          Response :
                    verify Expected status code : 201 
                    assertTrue(resp.getStatusCode() == 201, "Expected status code : 201 and actual :" + resp.getStatusCode());
 
                    verify Expected response time < 600 and actual response time
                    assertTrue(resp.getTime()<  600,"Expected response time < 600 and actual :" + resp.getTime());
		
                    verify Expected content type = JSON and actual response content type
                    assertTrue(resp.contentType().toLowerCase().contains("json"),"Expected content type = JSON and actual :" + resp.contentType());
          
          
3.src/main/java/week1/day1/paypal/CreateMultipleProducts.java 
          
          extends PayPalBasics -- for Bearer token "String paypalBearerAuth " 
	  Testng annotations used  
	  		@Test
	  Testng attributes used 
	  		@Test(dataProvider = "createpaypalapp") -- dataprovider created in PayPalBasics.java
          Request end point - https://api.sandbox.paypal.com/v1/catalogs/products
          Request Authorization - bearer
          Request Method type - POST
          Request Headers - headerList.add(new Header("Authorization","Bearer "+paypalBearerAuth)) 
          Request Content type - ContentType.JSON
	  Request Body - Json file fetched from dataprovider
          Response : JsonPath jsonresp = resp.jsonPath()
	  productIds.add(jsonresp.get("id").toString())  -- arrayList created in PayPalBasic.java to store productids created
                    verify Expected status code : 201 
                    assertTrue(resp.getStatusCode() == 201, "Expected status code : 201 and actual :" + resp.getStatusCode());
 
                    verify Expected response time < 600 and actual response time
                    assertTrue(resp.getTime()<  600,"Expected response time < 600 and actual :" + resp.getTime());
		
                    verify Expected content type = JSON and actual response content type
                    assertTrue(resp.contentType().toLowerCase().contains("json"),"Expected content type = JSON and actual :" + resp.contentType());

4.src/main/java/week1/day1/paypal/ListCreatedProducts.java 
          
          extends PayPalBasics -- for Bearer token "String paypalBearerAuth " 
	  Testng annotations used  
	  		@Test
	  Request end point - https://api.sandbox.paypal.com/v1/catalogs/products
          Request Authorization - bearer
          Request Method type - POST
	  Request Params - "page_size": "100" -- since response is returning default 1 page with 10 product records created, this param is used to return 100 records per 1 page
          Request Headers - headerList.add(new Header("Authorization","Bearer "+paypalBearerAuth)) 
          Request Content type - ContentType.JSON
	 
	 
          Response : JsonPath jsonresp = resp.jsonPath()
	 	    List<String> prodIDList = jsonresp.getList("products.id")  -- arrayList to get all product ids from json response
		    
		    verify if created product is listed in response by iterating through productIds(list stored product id's created in list)
		    assertTrue(prodIDList.contains(productIds.get(index)), "Product is not listed");
                    
		    verify Expected status code : 200
                    assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());
 
                    verify Expected response time < 600 and actual response time
                    assertTrue(resp.getTime()<  600,"Expected response time < 600 and actual :" + resp.getTime());
		
                    verify Expected content type = JSON and actual response content type
                    assertTrue(resp.contentType().toLowerCase().contains("json"),"Expected content type = JSON and actual :" + resp.contentType());
          
