Categories:

Validation Failure -> dead letter topic + fix publisher
Deserialization Falure -> Deat letter topic + schema check
Transient DB failure => bounded retry + dead letter topic
Poison, forever entry -> Forbidden pattern, never allow retry


Validation: UI does not verify user input to server 
Deserialization Failure: json from backend does not cleanly fit into UI objects
Transient DB faliuyre: docker connection fails for a second
Poison: repeated uncaught bad requests from the UI to the backend 


User experience: user does not get details from website due to failure