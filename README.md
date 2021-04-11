# Meet Together

### Project use
* Java 11
* Lombok
* Jackson
* JUnit5
* Mockito

Json calendar input format
<calendar1.json>
#### {</br>
"working_hours": {</br>
    "start": "08:00",</br>
    "end": "16:00"</br>
  },</br>
  "planned_meeting": [</br>
    {</br>
      "start": "09:00",</br>
      "end": "10:30"</br>
    },</br>
    {</br>
      "start": "12:00",</br>
      "end": "13:00"</br>
    },</br>
    {</br>
      "start": "14:00",</br>
      "end": "14:30"</br>
    }</br>
  ]</br>
}</br>

example output
[{"start":"11:30","end":"12:00"},{"start":"15:00","end":"15:30"},{"start":"15:05","end":"15:35"},{"start":"15:10","end":"15:40"},{"start":"15:15","end":"15:45"},{"start":"15:20","end":"15:50"},{"start":"15:25","end":"15:55"},{"start":"15:30","end":"16:00"},{"start":"18:00","end":"18:30"}]
