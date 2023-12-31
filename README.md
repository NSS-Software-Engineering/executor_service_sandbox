# Executor Service Demo Sandbox

This Java program demonstrates how the choice of ExecutorService strategies (whether 'cached newCachedThreadPool' or 'fixed newFixedThreadPool'), customizing task sleep and delay in task submission, along with the total number of tasks, can influence the utilization and reuse of threads.

## Configuration
```
Flag name                   Description
("t", "type")               Executor type (cached or fixed)
("fs", "fixed-pool-size")   Fixed pool size
("sd", "sleep-delay")       Sleep delay in milliseconds
("sbd", "submission-delay") Submission delay in milliseconds
("n", "num-tasks")          Number of tasks
```
## Example outputs

### Run 

```
gradle run  --args="--type fixed --fixed-pool-size 1 --sleep-delay 2000 --submission-delay 1000 --num-tasks 5"
```
### Output
```
Using newFixedThreadPool of size 1
Task 1 is being executed by Thread: pool-1-thread-1
Task 1 has completed.
Task 2 is being executed by Thread: pool-1-thread-1
Task 2 has completed.
Task 3 is being executed by Thread: pool-1-thread-1
Task 3 has completed.
Task 4 is being executed by Thread: pool-1-thread-1
Task 4 has completed.
Task 5 is being executed by Thread: pool-1-thread-1
Task 5 has completed.
Thread pool-1-thread-1 ran tasks: [Task 1, Task 2, Task 3, Task 4, Task 5]
```

### Run  
```
gradle run  --args="--type cached --sleep-delay 200 --submission-delay 10 --num-tasks 15"
```

### Output

```
Using newCachedThreadPool.
Task 1 is being executed by Thread: pool-1-thread-1
Task 2 is being executed by Thread: pool-1-thread-2
Task 3 is being executed by Thread: pool-1-thread-3
Task 4 is being executed by Thread: pool-1-thread-4
Task 5 is being executed by Thread: pool-1-thread-5
Task 6 is being executed by Thread: pool-1-thread-6
Task 7 is being executed by Thread: pool-1-thread-7
Task 8 is being executed by Thread: pool-1-thread-8
Task 9 is being executed by Thread: pool-1-thread-9
Task 10 is being executed by Thread: pool-1-thread-10
Task 11 is being executed by Thread: pool-1-thread-11
Task 12 is being executed by Thread: pool-1-thread-12
Task 13 is being executed by Thread: pool-1-thread-13
Task 14 is being executed by Thread: pool-1-thread-14
Task 15 is being executed by Thread: pool-1-thread-15
Task 1 has completed.
Task 2 has completed.
Task 3 has completed.
Task 4 has completed.
Task 5 has completed.
Task 6 has completed.
Task 7 has completed.
Task 8 has completed.
Task 9 has completed.
Task 10 has completed.
Task 11 has completed.
Task 12 has completed.
Task 13 has completed.
Task 14 has completed.
Task 15 has completed.
Thread pool-1-thread-7 ran tasks: [Task 7]
Thread pool-1-thread-6 ran tasks: [Task 6]
Thread pool-1-thread-9 ran tasks: [Task 9]
Thread pool-1-thread-8 ran tasks: [Task 8]
Thread pool-1-thread-1 ran tasks: [Task 1]
Thread pool-1-thread-3 ran tasks: [Task 3]
Thread pool-1-thread-2 ran tasks: [Task 2]
Thread pool-1-thread-15 ran tasks: [Task 15]
Thread pool-1-thread-5 ran tasks: [Task 5]
Thread pool-1-thread-14 ran tasks: [Task 14]
Thread pool-1-thread-4 ran tasks: [Task 4]
Thread pool-1-thread-13 ran tasks: [Task 13]
Thread pool-1-thread-12 ran tasks: [Task 12]
Thread pool-1-thread-11 ran tasks: [Task 11]
Thread pool-1-thread-10 ran tasks: [Task 10]
```
