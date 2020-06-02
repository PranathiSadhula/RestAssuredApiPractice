# 60DayApiPractice
#Day1
Implemented Postman response for the following in this git check-in
EndPoint : https://covid-19.dataflowkit.com/v1
Documentation: https://documenter.getpostman.com/view/11203393/SzfAz776?version=latest
Authorization -> No Auth

1. Find the top 5 Country with Highest New Cases :
 https://corona-virus-stats.herokuapp.com/api/v1/cases/countries-search?order=new_cases&how=dsc&limit=6&page=1
2. Find the top 5 Country with lowest New Deaths Cases for recent day :
https://corona-virus-stats.herokuapp.com/api/v1/cases/countries-search?order=new_deaths&how=asc&limit=5&page=1
3. Find the Status of your Country - done :
https://covid-19.dataflowkit.com/v1/india
4. Verified the response HTTP status code = 200
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});
5. Verified the Response Time < 600 ms
pm.test("Response time is less than 600ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(600);
});
6. verified the Content Type = json
pm.test("verify content type of response as json", function () {
    
     pm.response.to.be.json;

});
