<template>
  <div>
    <div class="title">
      <h1>My subscriptions</h1>
      <i
        class="fas fa-id-card fa-3x"
        style="font-family: 'Font Awesome 5 Pro'"
      ></i>
    </div>
    <div style="background-color: #212529; padding: 15px">
      <div class="container w-100" style="flex-direction: column">
        <div
          class="row row-cols-1 row-cols-sm-1 row-cols-md-4"
          style="justify-content: space-evenly; align-items: center"
        >
          <div class="col-md-3" style="margin-left: -23%">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
          </div>
          <div class="col-md-2" style="margin-right: -23%">
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
    <div>
      <SubscriptionCard
        v-for="subscription in subscriptions"
        :key="subscription.id"
        v-bind:subscription="subscription"
        v-on:refresh="refresh"
      ></SubscriptionCard>
    </div>
  </div>
</template>

<script>
import SubscriptionCard from "@/components/EntityCards/SubscriptionCard.vue";
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
import moment from "moment";
export default {
  components: { SubscriptionCard },
  data: function () {
    return {
      numberOfPersons: 0,
      subscriptions: [],
    };
  },
  mounted: function () {
    axios
      .get("/client/subscriptions", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.subscriptions = res.data;
      });
  },
  methods: {
    refresh: function (unsubscribed) {
      const index = this.subscriptions.indexOf(unsubscribed);
        if (index > -1) {
          this.subscriptions.splice(index, 1);
        }
    },
    search: function () {
      if (
        this.date[0] != undefined &&
        this.date[1] != undefined &&
        this.numberOfPersons != 0
      ) {
        this.searchByDateAndPersons();
      }
    },
    searchByDateAndPersons: function () {
      axios
        .get(
          "/vacationHome/search?start=" +
            moment(this.date[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&end=" +
            moment(this.date[1]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&persons=" +
            this.numberOfPersons,
          {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            },
          }
        )
        .then((res) => {
          this.homeEntities = res.data;
          for (let e of this.homeEntities) {
            e.rating = Number(e.rating).toFixed(2);
          }
        });
    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
