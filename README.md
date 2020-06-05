# 60DayApiPractice
#Day1
Implemented Postman response for the following in this git check-in
EndPoint : https://covid-19.dataflowkit.com/v1
Documentation: https://documenter.getpostman.com/view/11203393/SzfAz776?version=latest
Authorization -> No Auth

1. Find the top 5 Country with Highest New Cases in recent day :
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
2. Find the top 5 Country with lowest New Deaths Cases for recent day :
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
3. Find the Status of your Country - done :
https://covid-19.dataflowkit.com/v1/india
This is straight forward request.
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
