# Kotlin Project
Rakuten MTSD Test

1: Receiving an URL to make a request and printing the response data.

2: Code has been verified by the print logs. To test the results just import the project in to the local AndroidStudio and run simply the project. The Library is already been added to the project. Once the compilation is done the results can be seen on Logcat. Kinldy Use "Rakuten" keyword to filter out the concerned debug logs only.

3: I think unit test for parsing function isn't required since I am using the gson library to parse the resonse which is kinda already tested. So just forcefully sending null in case if the response is null or throws exception and the resonse callback receiver will receive it with the optional null and check for the null safety.
