# Holidayagency
This project is a small rest API written using spring boot to be submitted as part of the panintelligence tech test.
## Dependencies:
    - Java 21

## To run the project run the following commands
    - gradlew
    - gradlew bootRun

## Example CURL
    curl --request POST \
    --url http://localhost:9999/find-quickest \
    --header 'Content-Type: application/json' \
    --header 'User-Agent: insomnia/10.3.1' \
    --data '{
    "journey": {
    "passengers": 2,
    "homeToAirport": "B20",
    "destination": "D"
    }
    }'
 

## Things to improve given more time
 - More thorough unit testing.
 - More thorough component test - negative scenarios, more edge cases.
 - Some logging rather than printlns.
 - Much more error handling.

## Things I would add
 - controller advice
 - use of real database
 - a UI to consume this service