<template>
<div>
    <div
      class="card mb-3 bg-dark mt-3 text-white"
      style="width: 80%; margin: auto"
    >
      <div class="row g-0">
        <div class="col-md-4 shadow-none navbar-container">
          <nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-info">
            <div class="container-fluid">
              <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbar-text"
                aria-controls="navbar-text"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbar-text">
                <ul class="navbar-nav mb-2 mb-lg-0 menu-ol" id="navbar-ul">
                  <li v-on:click="changeMenuDisplay" id="stats" class="active">
                    <p style="margin: 1rem 0">Stats</p>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        </div>
        <div class="col-md-8 shadow-none main main-stats" name="main-col" style="min-height: 320px">
          <Datepicker
              style="
                width: 60%;
                margin-right: 10px;
                border: 1px solid white;
                border-radius: 5px;
              "
              dark
              v-model="dateRange"
              @closed="dateRangeChanged"
              placeholder="Select dates.."
              range
              :enableTimePicker="true"
            ></Datepicker>

           <table class="table table-striped"  style="margin-top: 50px">
              <thead>
                <th>Advertiser</th>
                <th>Earnings</th>
                <th>Date of payment</th>
              </thead>
              <tbody>
                <tr v-for="earning in advertiserEarnings" :key="earning.id" v-bind:earning="earning">
                  <td> {{earning.advertiserEmail}} </td>
                  <td> ${{earning.amountEarned}} </td>
                  <td> {{earning.dateOfTransaction}} </td>
                </tr>
              </tbody>
            </table>
            <h5 v-if="advertiserEarnings.length == 0" style="margin-top:20px; margin-bottom:90px"> No transactions </h5>
            <h4 style="margin-top:15px"> Total earnings: {{ totalEarnings }}</h4>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  data: function () {
    return {
      dateRange: [],
      totalEarnings: 0,
      advertiserEarnings: [],
    };
  },
  mounted: function () {
  },
  methods: {
      dateRangeChanged: function () {
      if (this.dateRange[0] != undefined && this.dateRange[1] != undefined) {
        axios
            .get(
                "/advertiserEarnings/getEarningsForPeriod?start=" +
                moment(this.dateRange[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
                "&end=" +
                moment(this.dateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),
                {
                    headers: {
                    "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                    Authorization: "Bearer " + localStorage.refreshToken,
                    },
                }
                )
                .then((res) => {
                    this.totalEarnings = res.data;
                });
        axios
            .get(
                "/advertiserEarnings/getTransactionsForPeriod?start=" +
                moment(this.dateRange[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
                "&end=" +
                moment(this.dateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),
                {
                    headers: {
                    "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                    Authorization: "Bearer " + localStorage.refreshToken,
                    },
                }
                )
                .then((res) => {
                    this.advertiserEarnings = res.data;
                });
      }

    },
   
  },
};
</script>

<style scoped src="@/css/statistics.css"></style>

