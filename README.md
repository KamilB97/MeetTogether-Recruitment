# Meet Together

### Project use
* Java 11
* Lombok
* Jackson
* JUnit5
* Mockito

src/main/java/com/kami/brzycki/CompareCalendarDemo.java class contains main method and is responsible for running program.
Data directory (./data) contains two directories: input, where two calendar.json files are stored and output directory where program writes output results (possible terms for a meeting).

Program takes as input two calendar files that are placed at ./data/input/calendar1.json and ./data/input/calendar2.json
It is required to preserve calendar1.json and calenda2.json file names, as they are used by program to load calendars data.

After program launch you will be asked to insert requested meeting duration time in hh:mm format.
Program output will appear at console as well as will be saved at ./data/output/result.json


Json calendar input format
<calendar1.json>

```javascript
{
   "working_hours": {
      "start": "08:00",
      "end": "16:00"
    },
    "planned_meeting": [
      {
        "start": "09:00",
        "end": "10:30"
      },
      {
        "start": "12:00",
        "end": "13:00"
      },
      {
        "start": "14:00",
        "end": "14:30"
      }
   ]
}
```

example output 
<result.json>

```javascript
[
    {
    "start":"11:30",
    "end":"12:00"
    },
    {
      "start":"15:00",
      "end":"15:30"
    },
    {
      "start":"15:05",
      "end":"15:35"
    },
    {
      "start":"15:10",
      "end":"15:40"
    },
    {
      "start":"15:15",
      "end":"15:45"
    },
    {
      "start":"15:20",
      "end":"15:50"
    },
    {
      "start":"15:25",
      "end":"15:55"
    },
    {
      "start":"15:30",
      "end":"16:00"
    },
    {
      "start":"18:00",
      "end":"18:30"
    }
] ```
