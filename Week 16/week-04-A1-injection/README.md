# A1 SQL injection

The plan for this week is to take a closer look at SQL injection,
how it is prevented,
and some advice for securing the rest of the DB in case of penetration.

## Learning Goals

After this week you are able to:

* Perform an attack on an unprotected database
* Prevent attacks on your database
* Explain the general principle behind injection attacks

## What to Read

* W3Schools has an overview of sql injection which is a good start
  https://www.w3schools.com/sql/sql_injection.asp
* The owasp A1 is about this
  https://www.owasp.org/images/7/72/OWASP_Top_10-2017_%28en%29.pdf.pdf
* An alternative source:
  [The Hitchhiker's Guide to SQL Injection   prevention](https://phpdelusions.net/sql_injection)
* SQL inject with assignment: https://github.com/SecurityDatFall2018/Week-4-SQLInject

Slides: [04-A1 SQL injection.pdf](https://github.com/securitydatspring2019/week-04-A1-injection/blob/master/04-A1%20SQL%20injection.pdf)

## Exercise

Implement the placeholders from `PlaceHolders.java`
in the `dk.cphbusiness.soft.sqlinject` package.


## Exam questions

* Give an example of a SQL inject which will give all users in a user table
* Explain how prepared statements prevent SQL injection
* Explain how to use placeholders in cases where prepared statements cannot do the job
* Explain how logging could be used to monitor injection attempts
