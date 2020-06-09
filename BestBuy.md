# API document  : https://bestbuyapis.github.io/api-documentation/#user-guide
          apikey : qUh3qMK14GdwAs9bO59QRSCJ

# Postman Implementation 
1. ProductAvailabilityPostalCode-AreaFun - Request to get product available stores within given postalCode and radius(distance) using AreaFunction
  
                    Request end point - https://api.bestbuy.com/v1/stores(area({{postalCode}},{{radius}}))+products(search={{ProductForSearch}}&manufacturer={{ManufactureForSearch}})?format=json&show=storeId,name,address,distance&apiKey={{bestbuyapikey}}
                    Request Authorization - ApiKey  : qUh3qMK14GdwAs9bO59QRSCJ
                    Request method type - GET
                    Expected Response fields(sample) :
                                         "stores": [
                                                      {
                                                          "storeId": 441,
                                                          "name": "ATTLEBORO MA",
                                                          "address": "1337 S Washington St",
                                                          "distance": 4.49
                                                      },
                                                      {
                                                          "storeId": 1140,
                                                          "name": "MANSFIELD MA",
                                                          "address": "280 School St",
                                                          "distance": 10.89
                                                      }
                                                   ]
                    Expected Response status code : 200
 2. ProductAvailabilityPostalCode-Attribute - Request to get product available stores within given postalCode using attribute=PostalCode

                    Request end point - https://api.bestbuy.com/v1/stores(postalCode={{postalCode}})+products(search={{ProductForSearch}}&manufacturer={{ManufactureForSearch}})?format=json&show=storeId,name,address&apiKey={{bestbuyapikey}}
                    Request Authorization - ApiKey  : qUh3qMK14GdwAs9bO59QRSCJ
                    Request method type - GET
                    Expected Response fields(sample) :
                                         "stores": [
                                                      {
                                                          "storeId": 441,
                                                          "name": "ATTLEBORO MA",
                                                          "address": "1337 S Washington St",
                                                         
                                                      },
                                                      {
                                                          "storeId": 1140,
                                                          "name": "MANSFIELD MA",
                                                          "address": "280 School St",
                                                         
                                                      }
                                                   ]
                    Expected Response status code : 200      
3. CanonProductsPriceRange$1000-$1500 - - Request to return Canon Products whose range of price is from $1000 to $1500

                    Request end point - https://api.bestbuy.com/v1/products(manufacturer=canon&salePrice>1000&salePrice<1500)?show=sku,name,salePrice&format=json&apiKey={{bestbuyapikey}}
                    Request Authorization - ApiKey  : qUh3qMK14GdwAs9bO59QRSCJ
                    Request method type - GET
                    Expected Response fields(sample) :
                                          "products": [
                                                          {
                                                              "sku": 3479197,
                                                              "name": "Canon - EF 70–300mm f/4–5.6L IS USM Telephoto Zoom Lens - White",
                                                              "salePrice": 1349.99
                                                          },
                                                          {
                                                              "sku": 3936043,
                                                              "name": "Canon - EF 8-15mm f/4L Fisheye USM Ultra-Wide Zoom Lens - Black",
                                                              "salePrice": 1249.99
                                                          }
                                                   ]
                    Expected Response status code : 200  
4. ProductRegularAndSellingPRICE -  Request to return given product's selling Price and Regular Price

                    Request end point - https://api.bestbuy.com/v1/products(manufacturer=apple&search=iPhone 11 Pro 64GB)?format=json&show=name,regularPrice,salePrice&apiKey={{bestbuyapikey}}
                    Request Authorization - ApiKey  : qUh3qMK14GdwAs9bO59QRSCJ
                    Request method type - GET
                    Expected Response fields(sample) :
                                          "products": [
                                                          {
                                                              "sku": 3479197,
                                                              "name": "Canon - EF 70–300mm f/4–5.6L IS USM Telephoto Zoom Lens - White",
                                                              "salePrice": 1349.99
                                                          },
                                                          {
                                                              "sku": 3936043,
                                                              "name": "Canon - EF 8-15mm f/4L Fisheye USM Ultra-Wide Zoom Lens - Black",
                                                              "salePrice": 1249.99
                                                          }
                                                   ]
                    Expected Response status code : 200  
5. StorePickupTrue - Request to return stores with pickupAvailibility for given product

                    Request end point - https://api.bestbuy.com/v1/products(manufacturer=apple&search=iPhone 11 Pro 64GB&inStorePickup=true)+stores(region=RI)?format=json&show=name,stores.storeId,stores.name&apiKey={{bestbuyapikey}}
                    Request Authorization - ApiKey  : qUh3qMK14GdwAs9bO59QRSCJ
                    Request method type - GET
                    Expected Response fields(sample) :
                                          "products": [
                                                            {
                                                                "name": "Apple - iPhone 11 Pro Clear Case",
                                                                "stores": [
                                                                    {
                                                                        "storeId": 569,
                                                                        "name": "WARWICK RI"
                                                                    }
                                                                ]
                                                            },
                                                            {
                                                                "name": "Apple - iPhone 11 Pro Max Clear Case",
                                                                "stores": [
                                                                    {
                                                                        "storeId": 569,
                                                                        "name": "WARWICK RI"
                                                                    }
                                                                ]
                                                            }
                                                         ]
                    Expected Response status code : 200  
  
  
 # Restassured Implementation
                     60DayApiRestAssured is maven project which is build by using 
                     RestAssured, java and testng
		src/main/java/week1/day2/bestbuy/ is the source folder for paypal implementation
1. src/main/java/week1/day2/bestbuy/StorePickUpTrue.java 
                    
                    - Testng annotations used 
                              @Test 
                                  Request end point (RestAssured.baseURI)  - https://api.bestbuy.com/v1
                                  Request Authorization - ApiKey provided in parameters  
                                      
                                  Request params : 
                                        Map<String, String> pMap = new HashMap<String, String>();
                                        pMap.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
                                        pMap.put("format", "json");
                                        pMap.put("show","name,stores.storeId,stores.name");
                                  Request method type - GET
                                  RequestPath for Method Type Get : products(manufacturer=apple&search=iPhone 11 Pro 64GB&inStorePickup=true)+stores(region=RI)
                                  Expected Response fields :
                                          "products": [
                                                            {
                                                                "name": "Apple - iPhone 11 Pro Clear Case",
                                                                "stores": [
                                                                    {
                                                                        "storeId": 569,
                                                                        "name": "WARWICK RI"
                                                                    }
                                                                ]
                                                            }
                                                       ]
                                  verify Expected status code : 200 
                                  assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());
                                      
2. src/main/java/week1/day2/bestbuy/ProductRegularAndSellingPrice.java 
                    
                    - Testng annotations used 
                              @Test 
                                  Request end point (RestAssured.baseURI)  - https://api.bestbuy.com/v1
                                  Request Authorization - ApiKey provided in parameters  
                                      
                                  Request params : 
                                        Map<String, String> pMap = new HashMap<String, String>();
                                        pMap.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
                                        pMap.put("format", "json");
                                        pMap.put("show","sku,name,salePrice,regularPrice");
                                  Request method type - GET
                                  RequestPath for Method Type Get : products(manufacturer=apple&search=iPhone 11 Pro 64GB)
                                  Expected Response fields :
                                          "products": [
                                                            {
                                                                "name": "Apple - iPhone 11 Pro Clear Case",
                                                                "stores": [
                                                                    {
                                                                        "storeId": 569,
                                                                        "name": "WARWICK RI"
                                                                    }
                                                                ]
                                                            }
                                                       ]
                                  verify Expected status code : 200 
                                  assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());
 
 3. src/main/java/week1/day2/bestbuy/ProductAvailabilityPostalCodeWithAreaFun.java 
                    
                    - Testng annotations used 
                              @Test 
                                  Request end point (RestAssured.baseURI)  - https://api.bestbuy.com/v1
                                  Request Authorization - ApiKey provided in parameters  
                                      
                                  Request params : 
                                        Map<String, String> pMap = new HashMap<String, String>();
                                        pMap.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
                                        pMap.put("format", "json");
                                        pMap.put("show","name,stores.storeId,stores.name");
                                  Request method type - GET
                                  RequestPath for Method Type Get : stores(area(02864,20))
                                  Expected Response fields :
                                           "stores": [
                                                        {
                                                            "storeId": 441,
                                                            "name": "ATTLEBORO MA",
                                                            "address": "1337 S Washington St",
                                                            "distance": 4.49
                                                        }
                                                       ]
                                  verify Expected status code : 200 
                                  assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());
4.src/main/java/week1/day2/bestbuy/CanonProductsInGivenPriceRange.java 
                    
                    - Testng annotations used 
                              @Test 
                                  Request end point (RestAssured.baseURI)  - https://api.bestbuy.com/v1
                                  Request Authorization - ApiKey provided in parameters  
                                      
                                  Request params : 
                                        Map<String, String> pMap = new HashMap<String, String>();
                                        pMap.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
                                        pMap.put("format", "json");
                                        pMap.put("show","sku,name,salePrice");
                                  Request method type - GET
                                  RequestPath for Method Type Get : products(manufacturer=canon&salePrice>1000&salePrice<1500)
                                  Expected Response fields :
                                          {
                                              "sku": 6183400,
                                              "name": "Canon - XA11 HD Flash Memory Premium Camcorder - Black",
                                              "salePrice": 1299.99
                                          }
                                  verify Expected status code : 200 
                                  assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());
