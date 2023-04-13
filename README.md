# My Personal Project
**Leo Shang**
## Pomodoro Timer Application

I plan to develop my own pomodoro timer application. A pomodoro
timer is a specific timer where the user works for around *25-minutes* 
before taking *5-minute* increment breaks. After a certain number
of work timers are completed, the user is then allowed to receive a 
*15-minute* break. I have personally been using this study method throughout
both last term and this term. So far, it has been an effective method of studying,
so I hope to develop my own pomodoro timer application. 

**My application will include customization features including:**

- Adding and removing customizable tasks in a queue connected to timer
- Allow users to add tasks to a queue with timer type and task name
- Change timer times for breaks and working periods
- Change the number of work periods required in order to get a longer break
- Change background colors and/or image
- Change clock font

**My application will also include basic clock functions as well as queue functions including:**

- Adding or removing tasks to a queue connected to the timer
- When certain number of timers is done, connected task is removed from Queue
- Pausing and resuming timers
- Skipping timers
- Switching from break to work timer and vice versa
- Settings area for the customizations as featured above
- Include motivation text after completing a work timer (for fun...)

This application is mainly focussed and targeted towards being used by
students (like myself), especially towards high school and
university students.

## User stories

- As a user, I want to be able to add a task with timer type and task name to end of a queue 
(adding arbitrary number of tasks to a queue or adding x to y part)
- As a user, I want to be remove task at specific index in queue
- As a user, I want to be able to change the number of times a task is repeated in the queue
- As a user, I want to be able to see the number of times a certain task is being repeated in the queue
- As a user, I want to be able to save my queue to file by choice
- As a user, I want to be able to be able to load my queue from file by choice
- As a user, I want to be able to start a timer
- As a user, I want to be able to pause a timer 
- As a user, I want to be able to reset a timer
- As a user, I want to be able to change from break timer to work timer
- As a user, I want to be able to get a log of events by choice
- As a user, I want to be able to clear a log of events by choice


## Instructions for grader

- You can generate the first required action related to adding Xs to a Y by pressing the "plus" icon and type in the 
task that you would like to add. The task should be added to a table.
- You can generate the second required action related to adding Xs to a Y by pressing the "minus" icon and type in the 
task that you would like to remove and the number of repititions. MAKE SURE THE NAME MATCHES EXACTLY.
- You can locate my visual component by not only looking at the icons, but when the timer is done, a gif of a spinning
skeleton head will appear saying timer is done. To speed up the process of this, you can edit **TimerAppGui initTimer**
method and change _this.minutes = 25_ and _this.seconds = 0_ to _this.minutes = 0_ and _this.seconds = 5_
- You can save the state of my application by pressing the save button
- You can reload the state of my application by pressing the load data button
- 
## Phase 4: Task 2

**Example seen below**

- Wed Apr 12 16:21:25 PDT 2023  ->  Added task: Math
- Wed Apr 12 16:21:31 PDT 2023  ->  Added 4 repititions: Math
- Wed Apr 12 16:21:39 PDT 2023  ->  Removed 2 repititions: Math
- Wed Apr 12 16:21:45 PDT 2023  ->  Removed task: Math
- Wed Apr 12 16:21:49 PDT 2023  ->  Added task: Science
- Wed Apr 12 16:21:58 PDT 2023  ->  Added task: Youtube
- Wed Apr 12 16:22:05 PDT 2023  ->  Added 2 repititions: Science
- Wed Apr 12 16:22:11 PDT 2023  ->  Added 1 repititions: Youtube
- Wed Apr 12 16:22:15 PDT 2023  ->  Removed 2 repititions: Youtube
- Wed Apr 12 16:22:22 PDT 2023  ->  Added 21 repititions: Science
- Wed Apr 12 16:22:29 PDT 2023  ->  Removed 41 repititions: Science
- Wed Apr 12 16:22:34 PDT 2023  ->  Removed task: Science


## Phase 4: Task 3
- I definitely feel that I could have added more abstract classes and interfaces to increase cohesion. 
A good example can be adding an abstract class or interface among all the gui classes. 
A really repetitive code that I saw was the "initPanel" method and "initButton" method across all  the guis, which could have
been refactored into an interface before the guis implemented it.
- A possible pattern that I could have implemented was the singleton pattern within the
TaskQueue class. In phase 3 of the project when we were using guis, I had issues
making sure that the same TaskQueue object was being passed around the different windows/guis. 
To keep the same TaskQueue object, I passed TaskQueue as a parameter to the other classes, and these classes would
alter the TaskQueue. However, a far more easy solution could have been making TaskQueue a singleton pattern to ensure that the same
object was being passed around. 
- Another area where I could have implemented the singleton pattern was the TimerAppGui.
Whenever the AddTaskGui or RemoveTaskGui opened and the user inputted their values, the table in the TimerAppGui was unable to
update their values without an inefficient solution like adding a refresh button. Instead, I passed the TimerAppGui itself into
the AddTaskGui and RemoveTaskGui windows so that whenever the windows were done their adding or removing tasks,
TimerAppGui could be called to update the information. Instead, I could have used the singleton pattern so that I would not have
to pass TimerAppGui itself into AddTaskGui and RemoveTaskGui. 