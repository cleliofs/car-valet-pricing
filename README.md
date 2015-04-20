# Lmn Speed Valet  - Unattended Programing Test

## Scenario

**LMN Speed Valet** is a renowned valeting service provider and is looking for developing online
**"Request a quote service"** for its customers.  The online application should quote customer total valet package price
based on the type of valet service selected by the customer and vehicle model.
LMN Speed Valet is celebrating 10 years in service and is offering *10%* discount of listed price to all its *TYPE C*
customers.

The prices are quoted based on the following criteria:

| Service Type | Duration	     | Type A	     | Type B	     | Type C        |
| ------------ | --------------- | ------------- | ------------- | ------------- |
| Gold	       | 2-3 hours	     | &pound;119.99 | &pound;123.99 | &pound;130.99 |
| Silver       | 1-2 hours	     | &pound;89.99	 | &pound;93.99	 | &pound;100.99 |
| Bronze       | Within 1 hour	 | &pound;79.99	 | &pound;83.99	 | &pound;90.99  |

Vehicle classification is handled via external service provider based on model of the car.
LMN Speed Valet services uses this external service to determine the type of the car based on the model.

It returns the following values:

| Values | Model Description | Example                          |
| ------ | ----------------- | -------------------------------- |
| TYPE_A | Small/Family/Mini | Audi A3/Ford Focus/Nissan Micra  |
| TYPE_B | Saloon/Executive  | BMW3/Ford Mondeo                 |
| TYPE_C | MPV/4X4           | BMW X5/Chrysler Voyager          |


UnknownType exception is thrown for invalid models and ServiceUnavailableException is thrown when the service is down.

## Instructions

 * Try spending no more than 2 hours on this problem.
 * Provide an implementation of the ValetingQuoteService interface which is a basic service to quote the price based on
the model of the car provided as input by the customer.
 * Feel free to refactor the interface and any supplied files in the package *com.lmn.speed.valetservice*, as you see
 suitable.
 * You can make use of a test harness or any appropriate means of input and output. Price quotes should be returned in
 &pound;x.xx format (rounded to 2 decimal places)

### Acceptance Criteria but not limited to:

| Model	| Service Type | Expected Output       |
| ----- | ------------ | --------------------- |
| Small	| Bronze 	   | &pound;79.99          |
| Mini 	| Silver	   | &pound;89.99          |
| 4X4	| Gold	       | &pound;117.90         |
| MVP	| Silver	   | &pound;90.99          |




