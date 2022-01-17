<template>
  <div>
    <div class="title">
      <h1>Advertiser complaints</h1>
      <i
        class="fas fa-exclamation-circle fa-4x"
        style="font-family: 'Font Awesome 5 Pro'"
      ></i>
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
        style="justify-content: space-evenly; align-items: center; height: 38px; color: #dddddd; font-size: 20px"
      >
        Sanctioning a client will add a penalty to their account.
      </div>
    </div>
    <div>
        <table class="table-users">
            <thead>
                <tr>
                    <th>Service</th>
                    <th >
                        Advertiser
                    </th>
                    <th>Client </th>
                    <th>Report </th>
                    <th colspan="2">Sanction?</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="report in reports" :key="report.id" v-bind:report="report">
                    <td>{{report.serviceName}}</td>
                    <td>{{report.advertiserFullName}}</td>
                    <td>{{report.clientFullName}}</td>
                    <td>{{report.content}} </td>
                    <td>
                        <button class="green-btn" v-on:click="sanctionClient(report)"
                        > Yes</button>
                    </td>
                                        <td>
                        <button class="black-btn" v-on:click="dontSanctionClient(report)"
                        > No</button>
                    </td>

                </tr>
            </tbody>
        </table>
      
    </div>
  </div>
 
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  data: function () {
    return {
      searchText: "",
      searchResults: [],
      reports: []
    };
  },
  mounted: function () {
    axios
      .get("/reservationReport/getReportsAwaitingReview", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.reports = res.data;
      });
  },
  methods: {
    sanctionClient: function (report) {
        axios
          .put("/reservationReport/sanctionClient/", report, {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(() => {
            this.reports.splice(this.reports.indexOf(report));
            this.$toast.show(
            "User has been sanctioned.",
            {
              duration: 3000,
            }
          );
          });
    },
    dontSanctionClient: function (report) {
        axios
          .put("/reservationReport/rejectPenalty/", report, {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(this.reports.splice(this.reports.indexOf(report)));
    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
<style scoped src="@/css/admin.css"></style>