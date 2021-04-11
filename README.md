# Meet Together

### Project use
* Java 11
* Lombok
* Jackson
* JUnit5
* Mockito

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
    }]
}
```



example output
```javascript
[{"start":"11:30","end":"12:00"},{"start":"15:00","end":"15:30"},{"start":"15:05","end":"15:35"},{"start":"15:10","end":"15:40"},{"start":"15:15","end":"15:45"},{"start":"15:20","end":"15:50"},{"start":"15:25","end":"15:55"},{"start":"15:30","end":"16:00"},{"start":"18:00","end":"18:30"}] ```
