# AndroidUISamples Kotlin Project
Rakuten MTSD Test

Receiving an URL to make a request and printing the response data
Code has been verified by the print logs and with the debug breakpoints
To see the results just simply run the project. The Library is already been added to the project.
I think unit test for parsing function isn't required since I am using the gson library to parse the resonse which is kinda already tested. So just forcefully sending null in case if the response is null or throws exception and the resonse callback receiver will receive it with the optional null for the null safety.
