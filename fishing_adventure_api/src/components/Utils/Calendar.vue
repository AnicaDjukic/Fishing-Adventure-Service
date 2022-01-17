<template>
  <div class="card bg-dark text-white" style="margin: 5%">
    <div class="content">
      <FullCalendar :options="calendarOptions" class="calendar" />
      <div class="info">
        <h2>Availability</h2>
        <div style="margin: 2rem 0">
          <div class="element">
            <h4>Select {{ entityType }}</h4>
            <select id="selectEl" :disabled="selectDisabled">
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
            <Datepicker
              class="datePricker"
              dark
              id="disabledStartPicker"
              v-model="startDate"
              placeholder="Select date"
              :enableTimePicker="true"
              minutesIncrement="15"
              :minDate="new Date()"
              v-if="disabledPickers"
              disabled
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
            <Datepicker
              class="datePricker"
              dark
              id="disabledEndPicker"
              v-model="endDate"
              placeholder="Select date"
              :enableTimePicker="true"
              minutesIncrement="15"
              :minDate="new Date()"
              disabled
              v-if="disabledPickers"
            ></Datepicker>
          </div>
          <div
            style="
              margin-top: 4rem;
              display: flex;
              justify-content: space-evenly;
            "
          >
            <button
              class="btn btn-outline-primary save"
              v-on:click="saveDate"
              id="saveBtn"
            >
              Save
            </button>
            <button
              class="btn btn-outline-primary delete-btn"
              v-on:click="deleteDate"
              id="deleteBtn"
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
axios.defaults.baseURL = process.env.VUE_APP_URL;

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
      disabledPickers: false,
      selectDisabled: false,
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
      .get("/users/getRole", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        let loggedInRole = res.data;

        if (loggedInRole == "ROLE_VACATION_HOME_OWNER") {
          this.entityType = "cottage";
          axios
            .get("/vacationHome/getNamesByUser", {
              headers: {
                "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            })
            .then((res) => {
              this.selectData = res.data;
              for (let data of this.selectData) {
                axios
                  .get(
                    "/availabilityDate/getByServiceProfile/" +
                      data.code,
                    {
                      headers: {
                        "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                        Authorization: "Bearer " + localStorage.refreshToken,
                      },
                    }
                  )
                  .then((res) => {
                    for (let newData of res.data) {
                      newData.title = data.label;
                      newData.url = "dateRange";
                      newData.defId = newData.id;
                      newData.startDate = new Date(newData.start);
                      newData.endDate = new Date(newData.end);
                      newData.serviceId = data.code;
                      this.calendarOptions.events.push(newData);
                    }
                  });
              }
            });
        } else if (loggedInRole == "ROLE_BOAT_OWNER") {
          this.entityType = "boat";
          axios
            .get("/boat/getNamesByUser", {
              headers: {
                "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            })
            .then((res) => {
              this.selectData = res.data;
              for (let data of this.selectData) {
                axios
                  .get(
                    "/availabilityDate/getByServiceProfile/" +
                      data.code,
                    {
                      headers: {
                        "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                        Authorization: "Bearer " + localStorage.refreshToken,
                      },
                    }
                  )
                  .then((res) => {
                    for (let newData of res.data) {
                      newData.title = data.label;
                      newData.url = "dateRange";
                      newData.defId = newData.id;
                      newData.serviceId = data.code;
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
      this.selectDisabled = false;
      this.disabledPickers = false;
      this.startDate = "";
      this.endDate = "";
      document.getElementById("saveBtn").style.display = "block";
      document.getElementById("deleteBtn").style.display = "block";
      document.getElementById("startPicker").style.display = "block";
      document.getElementById("endPicker").style.display = "block";
      document.getElementsByTagName("select")[0].value = "";

      for (let ev of this.calendarOptions.events) {
        if (ev.id == this.currentEvent.id) {
          ev.color = "";
          break;
        }
      }

      this.currentEvent = "";
    },
    event: function (info) {
      this.selectDisabled = true;
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

      if (this.endDate < new Date()) {
        document.getElementById("saveBtn").style.display = "none";
        document.getElementById("deleteBtn").style.display = "none";
        document.getElementById("startPicker").style.display = "none";
        document.getElementById("endPicker").style.display = "none";
        this.disabledPickers = true;
      } else {
        document.getElementById("saveBtn").style.display = "block";
        document.getElementById("deleteBtn").style.display = "block";
        document.getElementById("startPicker").style.display = "block";
        document.getElementById("endPicker").style.display = "block";
        this.disabledPickers = false;
      }
    },
    saveDate: function () {
      let newAvailabilityDate;
      if (this.currentEvent) {
        newAvailabilityDate = {
          start: this.startDate,
          end: this.endDate,
          title: this.currentEvent.title,
        };

        let profileId;
        let profileLabel = document.getElementsByTagName("select")[0].value;
        for (let data of this.selectData) {
          if (data.label == profileLabel) {
            profileId = data.code;
          }
        }
        axios
          .put(
            "/availabilityDate/update/" +
              this.currentEvent.id +
              "/" +
              profileId,
            newAvailabilityDate,
            {
              headers: {
                "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then((res) => {
            this.copyOldAndSaveNewData(res, profileId, profileLabel);
            this.clearAll();
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
            "/availabilityDate/save/" + profileId,
            newAvailabilityDate,
            {
              headers: {
                "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then((res) => {
            this.pushOneData(res.data, profileId, profileLabel);
            this.clearAll();
          });
      }
    },
    deleteDate: function () {
      let profileLabel = document.getElementsByTagName("select")[0].value;
      let profileId;
      for (let data of this.selectData) {
        if (data.label == profileLabel) {
          profileId = data.code;
        }
      }
      axios
        .delete(
          "/availabilityDate/" +
            this.currentEvent.extendedProps.defId +
            "/" +
            profileId,
          {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          }
        )
        .then((res) => {
          this.copyOldAndSaveNewData(res, profileId, profileLabel);
          this.clearAll();

          this.$toast.show(
            "Availability dates that include reserved appointments cannot be deleted.",
            {
              duration: 4000,
            }
          );
        });
    },
    pushOneData: function (newData, profileId, profileLabel) {
      newData.title = profileLabel;
      newData.url = "dateRange";
      newData.defId = newData.id;
      newData.serviceId = profileId;
      newData.startDate = new Date(newData.start);
      newData.endDate = new Date(newData.end);
    },
    copyOldAndSaveNewData: function (res, profileId, profileLabel) {
      let copyEvents = [];
      for (let ev of this.calendarOptions.events) {
        copyEvents.push(ev);
      }
      this.calendarOptions.events.length = 0;
      for (let ev of copyEvents) {
        if (ev.serviceId != profileId) {
          this.calendarOptions.events.push(ev);
        }
      }
      for (let newData of res.data) {
        this.pushOneData(newData, profileId, profileLabel);
        this.calendarOptions.events.push(newData);
      }
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
