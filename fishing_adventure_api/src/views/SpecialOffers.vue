<template>
  <div>
    <div class="title">
      <h1>My special offers</h1>
      <img style="height: 10rem" src="@/assets/price-tag.png" />
    </div>
    <div
      style="
        background-color: #212529;
        padding: 15px;
        text-align: -webkit-center;
      "
    >
      <div
        class="container w-100 row row-cols-1 row-cols-sm-1 row-cols-md-4"
        style="justify-content: space-evenly; align-items: center"
      >
        <div class="col-md-2">
          <button
            type="button"
            class="btn btn-outline-primary text-nowrap me-2"
            data-bs-toggle="modal"
            data-bs-target="#OfferModal"
          >
            New offer
          </button>
        </div>
        <div class="col-md-5">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
          />
        </div>
        <div class="col-md-5">
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
            placeholder="Select date"
            range
            :enableTimePicker="false"
          ></Datepicker>
        </div>
      </div>
    </div>

    <div style="margin-top: 5%">
      <SpecialOffersCard
        v-for="offer of offers"
        :key="offer.offerId"
        :offer="offer"
      ></SpecialOffersCard>
    </div>
  </div>
  <OfferModal id="OfferModal"></OfferModal>
</template>

<script>
import SpecialOffersCard from "@/components/SpecialOffersCard.vue";
import OfferModal from "@/components/OfferModal.vue";
import axios from "axios";
export default {
  components: { SpecialOffersCard, OfferModal },
  data: function () {
    return {
      numberOfPersons: "",
      date: "",
      offers: "",
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
            .get("http://localhost:8080/vacationHome/getServiceOffersByUser", {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            })
            .then((res) => {
              this.offers = res.data;
            });
        } else if (loggedInRole == "ROLE_BOAT_OWNER") {
          this.entityType = "boat";
          axios
            .get("http://localhost:8080/boat/getServiceOffersByUser", {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            })
            .then((res) => {
              this.offers = res.data;
            });
        } else {
          window.location.href = "/";
        }
        // else if(loggedInRole == "ROLE_FISHING_INSTRUCTOR"){
        // }
      });
  },
  methods: {},
};
</script>

<style scoped src="@/css/mycottages.css"></style>
