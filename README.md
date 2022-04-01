# Virtual Instrument



- The application is a ***virtual instrument*** that will look like a piano, which you can play using your keyboard 
(the keyboard you use to type). This application will use the second row of keys to play notes ("a" to "l"). You will 
be able to play multiple notes at once and create harmony.

- This application can be used by anyone, all you would need is a computer with a keyboard.

- I've always been interested in musical applications, and I'd like to use this opportunity to work on creating an 
interesting application that incorporates music into it.



##User Stories

- As a user, I want to be able to remember all the notes I've played.
- As a user, I want to hear the notes I play.
- As a user, I want to view the notes I've played.
- As a user, I want to hear the notes I've played at once.


- As a user, I want to save my list of notes list to file.
- As a user, I want to be able to load my list of notes list from file.


Phase 4:
Representative sample
Added note a to List of Notes


Fri Apr 01 13:21:35 PDT 2022
Added note d to List of Notes


Fri Apr 01 13:21:38 PDT 2022
Added note g to List of Notes


Fri Apr 01 13:21:40 PDT 2022
Added note j to List of Notes


Fri Apr 01 13:21:41 PDT 2022
Added note l to List of Notes

Phase 4: Task 3 
One thing I would've changed if I had more time would be the way I implement Event Logs.
There's an extra interface in the UI section (LogPrinter) that does not do every much. I'd change this and implement it
in InstrumentApp. There's also a class (FilePrinter) in my model package, which performs very little on its own, 
so I would implement all of its functions in my InstrumentApp.
Also, my InstrumentApp and Synth are very difficult to understand, so I would write or extract methods to make the code 
more readable. 


Note:
I've used template and some code from the following repositories:-
-TellerApp
-B1 practice problems
https://github.students.cs.ubc.ca/CPSC210/B1-Practice-Problems-Starters.git