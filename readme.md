
# Drones Task

## description

- In this task you are to design a drone that can fly in the air, carry some medication
  and deliver those medications to a patient.
- each drone can carry multiple medications during a trip
- the trip has the following fields
    - loadedAt: the time the drone was loaded with medications
    - deliveredAt: the time the drone was delivered with medications
- the user can ask a drone to load medications by sending a post request to `/drones/{drone_id}/load`
- with the following schema

```json
 {
  "med": [
    2,
    3
  ],
  "trip": {
    "timeToDeliver": 5,
    "loadedAt": "2022-08-30T20:05:11.209378",
    "deliveredAt": "2022-09-01T20:05:11.209402"
  }
}
```
- after doing the validation and making sure that the perocess is possible, the drone state is chnage to `LOADING`
- once the drone is dispatched, an Async job will start to load and then change the state to `LOADED`
- then change is again to deliverying abd thats is for the async function
## Crone job
a cron job is running and checking the drones state every 1 seconds,
and it is udating the drones by following the following rules

### Drone statuses checks

- Case 1
    - Idle do nothing and charge the drone
- Case 2
    - drone is delivering
      if loaded_at + current time < delivery_time
        - drone is delivering
        - reduce the battery by 0.1
          else if loaded_at + current time >= delivery_time
        - drone is delivered
        - reduce the battery by 0.1
        - change status to returning
- Case 3
    - drone is returning
      if loaded_at + current time  < delivery_time * 2
        - drone is returning
        - reduce the battery by 0.1
          else if loaded_at + current time >= delivery_time * 2
        - reduce the battery by 0.1
        - change status to idle

------
