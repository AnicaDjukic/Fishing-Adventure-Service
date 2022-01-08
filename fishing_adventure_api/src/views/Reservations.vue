<template>
  <div>
    <div class="title">
      <h1>Reservations</h1>
      <img style="height: 10rem" src="@/assets/reserved.png" />
    </div>
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
              <option selected>All statuses</option>
              <option value="1">Upcoming</option>
              <option value="2">Current</option>
              <option value="3">Finished</option>
            </select>
          </div>
        </div>
      </div>
    </div>
    <div style="margin-top: 5%">
      <AdvertiserReservationCard
        v-for="entity in searchResults"
        :key="entity.id"
        v-bind:entity="entity"
        @createReservation="createReservation"
      ></AdvertiserReservationCard>
    </div>
  </div>
  <AdvertiserReservationModal 
    id="AdvertiserReservationModal"
    :clientEmail="clientEmail"
    :clientName="clientName"
    :clientSurname="clientSurname"
  ></AdvertiserReservationModal>
</template>

<script>
import AdvertiserReservationCard from "@/components/AdvertiserReservationCard.vue";
import AdvertiserReservationModal from "@/components/AdvertiserReservationModal.vue";
import { Modal } from 'bootstrap';
import axios from "axios";
export default {
  components: { AdvertiserReservationCard, AdvertiserReservationModal },
  data: function () {
    return {
      numberOfPersons: "",
      range: {
        start: new Date(2020, 9, 12),
        end: new Date(2020, 9, 16),
      },
      date: "",
      searchText: "",
      searchResults: [],
      entities: [],
      clientEmail: "",
      clientNane: "",
      clientSurname: "",
    };
  },
  mounted: function () {
    axios
      .get(
        "http://localhost:8080/reservation/allByAdvertiser", {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        }
      )
      .then((res) => {
        this.searchResults = res.data;
        this.entities = res.data;
      });
  },
  methods: {
    createReservation(email, clientName, clientSurname) {
      this.clientEmail = email;
      this.clientName = clientName;
      this.clientSurname = clientSurname;
      var myModal = document.getElementById('AdvertiserReservationModal');
      var modal = Modal.getOrCreateInstance(myModal)
      modal.show()
    }
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
