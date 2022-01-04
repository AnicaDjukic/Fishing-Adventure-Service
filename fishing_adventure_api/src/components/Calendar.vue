<template>
  <div class="card bg-dark text-white" style="margin: 5%">
    <div class="content">
      <FullCalendar :options="calendarOptions" class="calendar" />
      <div class="info">
        <h2>Availability</h2>
        <div style="margin: 2rem 0">
          <div class="element">
            <h4>Select {{ entityType }}</h4>
            <select id="selectEl">
              <option value=""></option>
              <option
                v-for="data of selectData"
                :value="data.label"
                :key="data.code"
              >
                {{ data.label }}
              </option>
            </select>
          </div>
          <div class="element">
            <h4>Available from:</h4>
            <Datepicker
              class="datePricker"
              dark
              id="startPicker"
              v-model="startDate"
              placeholder="Select date"
              :enableTimePicker="true"
              minutesIncrement="15"
              :minDate="new Date()"
            ></Datepicker>
          </div>
          <div class="element">
            <h4>Available to:</h4>
            <Datepicker
              class="datePricker"
              dark
              id="endPicker"
              v-model="endDate"
              placeholder="Select date"
              :enableTimePicker="true"
              minutesIncrement="15"
              :minDate="new Date()"
            ></Datepicker>
          </div>
          <div
            style="
              margin-top: 4rem;
              display: flex;
              justify-content: space-evenly;
            "
          >
            <button class="btn btn-outline-primary save" v-on:click="saveDate">
              Save
            </button>
            <button
              class="btn btn-outline-primary delete-btn"
              v-on:click="deleteDate"
            >
              Delete
            </button>
            <button class="btn btn-outline-primary" v-on:click="clearAll">
              Clear all
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import "@fullcalendar/core/vdom"; // solves problem with Vite
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import axios from "axios";

export default {
  components: {
    FullCalendar, // make the <FullCalendar> tag available
  },
  setup() {
    const startDate = ref();
    const endDate = ref();

    return {
      startDate,
      endDate,
    };
  },
  data() {
    return {
      currentEvent: "",
      selectData: [],
      entityType: "",
      calendarOptions: {
        plugins: [interactionPlugin, dayGridPlugin, timeGridPlugin],
        initialDate: new Date(),
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay",
        },
        selectable: true,
        eventClick: this.event,
        selectMirror: true,
        dayMaxEvents: true,
        initialView: "dayGridMonth",
        events: [],
      },
    };
  },
  mounted() {
    axios
      .get("http://localhost:8080/users/getRole", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        let loggedInRole = res.data;

        if (loggedInRole == "ROLE_VACATION_HOME_OWNER") {
          this.entityType = "cottage";
          axios
            .get("http://localhost:8080/vacationHome/getNamesByUser", {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            })
            .then((res) => {
              this.selectData = res.data;
              for (let data of this.selectData) {
                axios
                  .get(
                    "http://localhost:8080/availabilityDate/getByServiceProfile/" +
                      data.code,
                    {
                      headers: {
                        "Access-Control-Allow-Origin": "http://localhost:8080",
                        Authorization: "Bearer " + localStorage.refreshToken,
                      },
                    }
                  )
                  .then((res) => {
                    for (let newData of res.data) {
                      newData.title = data.label;
                      newData.url = "neki";
                      newData.startDate = new Date(newData.start);
                      newData.endDate = new Date(newData.end);
                      this.calendarOptions.events.push(newData);
                    }
                  });
              }
            });
        } else if (loggedInRole == "ROLE_BOAT_OWNER") {
          this.entityType = "boat";
          axios
            .get("http://localhost:8080/boat/getNamesByUser", {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            })
            .then((res) => {
              this.selectData = res.data;
              for (let data of this.selectData) {
                axios
                  .get(
                    "http://localhost:8080/availabilityDate/getByServiceProfile/" +
                      data.code,
                    {
                      headers: {
                        "Access-Control-Allow-Origin": "http://localhost:8080",
                        Authorization: "Bearer " + localStorage.refreshToken,
                      },
                    }
                  )
                  .then((res) => {
                    for (let newData of res.data) {
                      newData.title = data.label;
                      newData.url = "neki";
                      newData.startDate = new Date(newData.start);
                      newData.endDate = new Date(newData.end);
                      this.calendarOptions.events.push(newData);
                    }
                  });
              }
            });
        } else {
          window.location.href = "/";
        }
        // else if(loggedInRole == "ROLE_FISHING_INSTRUCTOR"){
        // this.entityType = 'adventure';

        // }
      });
  },
  methods: {
    clearAll: function () {
      document.getElementsByTagName("select")[0].value = "";
      this.startDate = "";
      this.endDate = "";

      for (let ev of this.calendarOptions.events) {
        if (ev.id == this.currentEvent.id) {
          ev.color = "";
          break;
        }
      }

      this.currentEvent = "";
    },
    event: function (info) {
      info.jsEvent.preventDefault(); // don't let the browser navigate
      document.getElementsByTagName("select")[0].value = info.event.title;
      this.startDate = info.event.start;
      this.endDate = info.event.end;
      this.currentEvent = info.event;

      for (let ev of this.calendarOptions.events) {
        if (ev.id == this.currentEvent.id) {
          ev.color = "#434c54";
        } else {
          ev.color = "";
        }
      }
    },
    saveDate: function () {
      let newAvailabilityDate;
      if (this.currentEvent) {
        newAvailabilityDate = {
          id: this.currentEvent.id,
          start: this.startDate,
          end: this.endDate,
          title: this.currentEvent.title,
        };

        axios
          .put(
            "http://localhost:8080/availabilityDate/update/" +
              this.currentEvent.id,
            newAvailabilityDate,
            {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then(() => {
            for (let ev of this.calendarOptions.events) {
              if (ev.id == this.currentEvent.id) {
                ev.start = this.startDate;
                ev.end = this.endDate;
                break;
              }
            }
          });
      } else {
        newAvailabilityDate = {
          start: this.startDate,
          end: this.endDate,
          title: this.currentEvent.title,
        };

        let profileLabel = document.getElementsByTagName("select")[0].value;
        let profileId;
        for (let data of this.selectData) {
          if (data.label == profileLabel) {
            profileId = data.code;
          }
        }
        axios
          .post(
            "http://localhost:8080/availabilityDate/save/" + profileId,
            newAvailabilityDate,
            {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then((res) => {
            res.data.title = profileLabel;
            this.calendarOptions.events.push(res.data);
          });
      }
    },
    deleteDate: function () {
      axios
        .delete(
          "http://localhost:8080/availabilityDate/" + this.currentEvent.id,
          {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          }
        )
        .then(() => {
          for (let ev of this.calendarOptions.events) {
            if (ev.id == this.currentEvent.id) {
              this.calendarOptions.events.pop(ev);
              break;
            }
          }
        });
    },
  },
};
</script>
<style scoped>
.element {
  margin-bottom: 2rem;
}
.save {
  background-color: #1a252f;
}
.save:hover {
  background-color: #2c3e50 !important;
  color: white !important;
  border: 1px solid white !important;
}

.delete-btn {
  background-color: #2f1a1a !important;
}

.delete-btn:hover {
  background-color: #502c2c !important;
  color: white !important;
  border: 1px solid white !important;
}
.content {
  display: flex;
  justify-content: space-between;
}
.calendar {
  width: 70%;
}

.info {
  background: #212529;
  width: 28%;
  border-radius: 5px;
  border: 1px solid white;
  padding: 1rem;
}

select {
  background: transparent;
  color: white;
  border: 0;
  border-bottom: 1px solid white;
  width: 100%;
}

option {
  background: #212529;
}

.datePricker {
  margin-top: 2%;
  border: 1px solid white;
  border-radius: 5px;
  width: 100%;
  box-shadow: none !important;
}
</style>
