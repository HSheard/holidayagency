The following programming exercise is designed to help us assess the skills of a candidate, including:

* Code quality
* Test skills
* Design skills
* Communication and project management skills

## Time and prioritization
- We don't expect you to spend more than a few hours on this however you're welcome to spend as long as you wish.
- We recommend using the stack you are most comfortable with - we would rather see something high quality than something you struggled with due to unfamiliarity
- We love to see agile principles applied in the way you tackle this task

## What to submit:
Please include the following:

* The source code
* Build scripts
* Diagrams (if any)
* README with steps to run the exercise
* Evidence to show that your solution works correctly against the supplied test data

We need to be able to run your result, verify that it works, and have a good look at your code to assess code quality and testing ability.

## Usage of aids:
Please feel free to use any tools, assistance and libraries you find helpful but be prepared to discuss their use.
You will be asked about the project at interview stage.

## Getting help:
If you have any question or think something is unclear or not right, don't hesitate to contact the development team by emailing us at [development@panintelligence.com](mailto:development@panintelligence.com).
We'd love to help and hear feedback or queries!

## Exercise:
A holiday agency would like to suggest the lowest travel cost for holiday journeys to their customers.
A return journey consists of the following parts:

1. Journey to the airport:

    * **Taxi**: £0.40/mile - (allows up to 4 people per taxi - e.g. require 2 taxis if 5 people travel together)
    * **Car**: £0.20/mile - (allows up to 4 people per car), additional £3 parking fee

2. An outbound and an inbound flight journey:

    * **Flight**: £0.10/passenger/mile

### Your task:

* Write a small app that suggests the cheapest quote for a given journey
* Focus on all the important parts of software development rather than adding more functionality
* The task will scale to the time you have available, limited time to spend on the task is understandable
* We've provided some sample data for the exercise (which helps you to write tests), and use your program to fill row `8`, `9`, `10` (see **Sample Output: JOURNEY SUGGESTIONS**)

## Explanation of input and output

### Input: FLIGHTS

e.g. `AB800` , `BC900` , `CD400`, where

* 1st character represents a departure airport
* 2nd character represents a destination airport
* Number represents the distance in miles

e.g. `AB800` means: from `A` to `B`, the distance is 800 miles

### Input: JOURNEYS

e.g. `2, A20, D` , `1, B30, D`, which consists of

* number of passengers
* departure airport, and the distance to that airport
* destination airport

e.g. `2, A20, D` means:

* 2 passengers
* travel 20 miles to `A`
* take the flight from `A` to `D`
  * (could be indirect flights, if routes are available and cheaper e.g. from `A` to `B`, then `B` to `D`)

### Output: JOURNEY SUGGESTIONS
For each journey, the program should provide the following as the result:

* The type of vehicle to be used to get to the airport
* The return cost of vehicle travelling (Note: a return journey doesn't double the parking fees)
-	The **lowest cost** outbound flight route with all the connect flights displayed
    * there could be multiple routes from `B` to `D`
    * `AB800--BC900--CD400` and `AB800--BF400--FD200` but only the lowest cost one would be displayed
* The cost of the **lowest cost** outbound flight
* The **lowest cost** inbound flight route with all the connect flights displayed
* The cost of the **lowest cost** inbound flight
* If no outbound / inbound flight routes for the journey, display 'No outbound flight' / 'No inbound flight'
* The total cost of the journey (If no outbound or inbound flight, the total cost of the journey would be 0)

## Sample data for input and output

### Sample Input: FLIGHTS
```
['AB800', 'BC900', 'CD400', 'DE400',
 'BF400', 'CE300', 'DE300', 'EB600',
 'CE200', 'DC700', 'EB500', 'FD200']
 ```


### Sample Input: JOURNEYS

| #  | passengers | home to airport | destination |
|----|------------|-----------------|-------------|
| 1  | 2          | 'B20'           | 'D'         |
| 2  | 1          | 'B30'           | 'D'         |
| 3  | 2          | 'A20'           | 'D'         |
| 4  | 2          | 'C30'           | 'A'         |
| 5  | 2          | 'B10'           | 'C'         |
| 6  | 5          | 'B10'           | 'C'         |
| 7  | 1          | 'D25'           | 'B'         |
| 8  | 4          | 'D40'           | 'A'         |
| 9  | 2          | 'B5'            | 'D'         |
| 10 | 9          | 'B30'           | 'D'         |

### Sample Output: JOURNEY SUGGESTIONS
|#  | vehicle | vehicle return cost | outbound route         | outbound cost | inbound route        | inbound cost | total cost |
|---|---------|---------------------|------------------------|---------------|----------------------|--------------|------------|
|1  | 'Car'   | 11.00               | 'BF400--FD200'         | 120.00        | 'DE300--EB500'       | 160.00       | 291.00     |
|2  | 'Car'   | 15.00               | 'BF400--FD200'         | 60.00         | 'DE300--EB500'       | 80.00        | 155.00     |
|3  | 'Car'   | 11.00               | 'AB800--BC900--CD400'  | 420.00        | 'No inbound flight'  | 0            | 0          |
|4  | 'Car'   | 15.00               | 'No outbound flight'   | 0             | 'AB800--BC900'       | 340.00       | 0          |
|5  | 'Taxi   | 8.00                | 'BC900'                | 180.00        | 'CE200--EB500'       | 140.00       | 328.00     |
|6  | 'Taxi   | 16.00               | 'BC900'                | 450.00        | 'CE200--EB500'       | 350.00       | 816.00     |
|7  | 'Car'   | 13.00               | 'DE300--EB500'         | 80.00         | 'BF400--FD200'       | 60.00        | 153.00     |
|8  | ?       | ?                   | ?                      | ?             | ?                    | ?            | ?          |
|9  | ?       | ?                   | ?                      | ?             | ?                    | ?            | ?          |
|10 | ?       | ?                   | ?                      | ?             | ?                    | ?            | ?          |
