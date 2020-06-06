# POSTMAN IMPLEMENTATION FOR COVID-19 PROBLEM STATEMENT - 60dayAPIProgram.postman_collection.json

Implemented Postman response for the following in this git check-in
EndPoint : https://covid-19.dataflowkit.com/v1
Documentation: https://documenter.getpostman.com/view/11203393/SzfAz776?version=latest
Authorization -> No Auth

# 1. Find the top 5 Country with Highest New Cases in recent day :
 end point  - https://covid-19.dataflowkit.com/v1
 used "Last Update" field from response to get countries with new cases updated in recent day and converted into milliseconds - lastUpdatedMS
 1.if lastUpdatedMS is greater than expectedLastUpdated - last24hrs in milliseconds,
 2.retrieved "New Cases_text" field value along with "Country_text" field value and added them into 2d array - innerarray
 3.this innerarray is further added-pushed into countrieswithNewCasesArray
 4.sorted countrieswithNewCasesArray using javascript sort function - function sortByColumn(a, colIndex){

    a.sort(sortFunction);

    function sortFunction(a, b) {
        if (a[colIndex] === b[colIndex]) {
            return 0;
        }
        else {
            return (a[colIndex] > b[colIndex]) ? -1 : 1;
        }
    }

    return a;
}
5.iterated through sortedarray and printed top 5 countries with highest new cases
var top5 = 0;
for(var outerindex = 0; outerindex < sortedArray.length; outerindex++){
    if(sortedArray[outerindex][0] != "World" && top5 < 5){
         console.log(sortedArray[outerindex][0]+" : "+sortedArray[outerindex][1]); 
         top5++;
    }
  
};
# 2. Find the top 5 Country with lowest New Deaths Cases for recent day :
https://covid-19.dataflowkit.com/v1
used "Last Update" field from response to get countries with new cases updated in recent day and converted into milliseconds - lastUpdatedMS
 1.if lastUpdatedMS is greater than expectedLastUpdated - last24hrs in milliseconds,
 2.retrieved "New Deaths_text" field value along with "Country_text" field value and added them into 2d array - innerarray
 3.this innerarray is further added-pushed into countrieswithNewDeathCasesArray
 4.sorted countrieswithNewDeathCasesArray using javascript sort function - function sortByColumn(a, colIndex){

    a.sort(sortFunction);

    function sortFunction(a, b) {
        if (a[colIndex] === b[colIndex]) {
            return 0;
        }
        else {
            return (a[colIndex] > b[colIndex]) ? -1 : 1;
        }
    }

    return a;
}
5.iterated through sortedarray and printed top 5 countries with highest new cases
var top5 = 0;
for(var outerindex = 0; outerindex < sortedArray.length; outerindex++){
    if(sortedArray[outerindex][0] != "World" && top5 < 5){
         console.log(sortedArray[outerindex][0]+" : "+sortedArray[outerindex][1]); 
         top5++;
    }
  
};
# 3. Find the Status of your Country - done :
end point  - https://covid-19.dataflowkit.com/v1/india
This is straight forward request which rerturns expected statistics of country - india
In all requests, 
Verified the response HTTP status code = 200, 201
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});
Verified the Response Time < 600 ms
pm.test("Response time is less than 600ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(600);
});
verified the Content Type = json
pm.test("verify content type of response as json", function () {
    
     pm.response.to.be.json;

});


# Rest Assured IMPLEMENTATION FOR COVID-19 PROBLEM STATEMENT - 60DayApiRestAssured is maven project which is build by using 
# RestAssured/java/testng
# src/main/java/week1/day1/covid/ is the source folder for Covid-19 implementation

# Scenario :1 Find the Status of your Country - done
# src/main/java/week1/day1/covid/CountryStatsForIndia.java 
request end point  - https://covid-19.dataflowkit.com/v1/india
This is straight forward request which rerturns expected statistics of country - india
**request type method  - get
*TestNg - @Test is used to execute test scenario
*expected response code - 200
*expected response time < 600
*expected content type - json

# 2. Find the top 5 Country with Highest New Cases in recent day :
request end point  - https://covid-19.dataflowkit.com/v1
**request type method  - get
*TestNg - @Test is used to execute test scenario
*expected response code - 200
*expected response time < 600
*expected content type - json
*Java concepts used 
Collections : 

     - List<String> countryList -- contains jsonresp.getList("Country_text")
     - List<String> lastUpdatedList -- contains jsonresp.getList("'Last Update
     - List<String> newCasesList --contains jsonresp.getList("'New Cases_text'");
				 - Map<Integer, String> countryMap -- This map is build upon verifying if each element(Last Update value) in lastUpdatedList is > last24hrs(recent day calculation) and if newCasesList element is not empty then that perticular countryName and NewCasesNumber is added to countryMap
     - TreeMap - orders the added elements in ascending order by default
     - Collections.reverseOrder() - reverse the order of given collection, here TreeMap is given in code

# 3. Find the top 5 Country with lowest New Deaths Cases for recent day :
https://covid-19.dataflowkit.com/v1
request end point  - https://covid-19.dataflowkit.com/v1
**request type method  - get
*TestNg - @Test is used to execute test scenario
*expected response code - 200
*expected response time < 600
*expected content type - json
*Java concepts used 
Collections : 

     - List<String> countryList -- contains jsonresp.getList("Country_text")
     - List<String> lastUpdatedList -- contains jsonresp.getList("'Last Update'")
     - List<String> newDeathCasesList --contains jsonresp.getList("'New Deaths_text'")
				 - Map<Integer, String> countryMap -- This map is build upon verifying if each element(Last Update value) in lastUpdatedList is > last24hrs(recent day calculation) and if newCasesList element is not empty then that perticular countryName and NewCasesNumber is added to countryMap
     - TreeMap - orders the added elements in ascending order by default
     
