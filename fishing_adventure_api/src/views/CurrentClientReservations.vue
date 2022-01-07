<template>
  <div>
    <div class="title">
      <h1>My current reservations</h1>
      <i
        class="fas fa-id-card fa-3x"
        style="font-family: 'Font Awesome 5 Pro'"
      ></i>
    </div>
    <h2 style="color:white;"> You can cancel your reservation up to 3 days before it is scheduled</h2>
    <div style="background-color: #212529; padding: 15px">
      <div class="container w-100" style="flex-direction: column">
        <div
          class="row row-cols-1 row-cols-sm-1 row-cols-md-4"
          style="justify-content: space-evenly; align-items: center"
        >
          <div class="col-md-3">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
          </div>
          <div class="col-md-4">
            <Datepicker
              style="
                width: 100%;
                margin-right: 10px;
                border: 1px solid white;
                border-radius: 5px;
              "
              dark
              id="picker"
              v-model="date"
              range
              placeholder="Select date"
              :enableTimePicker="false"
            ></Datepicker>
          </div>
          <div class="col-md-2">
            <select
              class="form-select form-select-dark form-select-sm text-white bg-dark"
              aria-label=".form-select-sm example"
            >
              <option selected>All reservations</option>
              <option value="1">Boats</option>
              <option value="2">Cottages</option>
              <option value="3">Adventures</option>
            </select>
          </div>
        </div>
      </div>
    </div>
    <div style="margin-top: 5%">
      <ClientReservationCard
        :review="false"
        :current="true"
        v-for="reservation in nonCancellable"
        :key="reservation.id"
        :reservation="reservation"
      ></ClientReservationCard>
      <ClientReservationCard
        :review="true"
        :current="true"
        v-for="reservation in reservations"
        :key="reservation.id"
        :reservation="reservation"
      ></ClientReservationCard>
    </div>
  </div>
</template>

<script>
import ClientReservationCard from "@/components/ClientReservationCard.vue";
import axios from "axios";
import moment from "moment";
export default {
  components: { ClientReservationCard },
  data: function () {
    return {
      numberOfPersons: "",
      date: "",
      nonCancellable:[],
      reservations:[],
      range: {
        start: new Date(),
        end: new Date(),
      },
    };
  },
  mounted() {
    axios
      .get("http://localhost:8080/reservation/client/current", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        console.log(res.data);
        var currentDate = new Date();
        currentDate.setDate(currentDate.getDate() + 3);
        for(let d of res.data) {
          if(moment(d.startDate).isBefore(moment(currentDate)))
            this.nonCancellable.push(d);
          else
            this.reservations.push(d);
        }
      });
  },
  methods: {},
};
</script>

<style scoped src="@/css/mycottages.css"></style>
