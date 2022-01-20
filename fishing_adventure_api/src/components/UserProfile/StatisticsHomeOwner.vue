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
                  <li v-on:click="changeMenuDisplay" id="terms">
                    <p style="margin: 1rem 0">Terms</p>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        </div>
        <div class="col-md-8 shadow-none main main-stats" name="main-col" style="height: 320px">
          <div class="points-container">
            <h4 style="margin-right: auto">{{ userCategory }} </h4>
            <h3 style="margin-right: 10px">{{ userPointsInfo.points }}</h3>
            <p>POINTS</p>
          </div>

           <table class="table table-striped"  style="margin-top: 50px">
              <thead>
                <th>Category</th>
                <th>Earnings</th>
                <th>Points required</th>
              </thead>
              <tbody>
                <tr>
                  <td> REGULAR </td>
                  <td> {{ 100 - loyaltyProgram.advertiserBaseCut }}% </td>
                  <td> 0 </td>
                </tr>
                <tr>
                  <td> SILVER </td>
                  <td> {{ 100 - loyaltyProgram.advertiserBaseCut + 2 }}% </td>
                  <td> {{loyaltyProgram.advertiserSilverPoints}} </td>
                </tr>
                <tr>
                  <td> GOLD </td>
                  <td> {{ 100 - loyaltyProgram.advertiserBaseCut + 4 }}% </td>
                  <td> {{loyaltyProgram.advertiserGoldPoints}} </td>
                </tr>
              </tbody>
            </table>
        </div>
        <div class="col-md-8 shadow-none main main-terms" name="main-col">
          <div>
            <h3>Policy</h3>
            <p>
              We want to bring you the best offers for creating new memories. For
              every reservation on our website you will be rewarded with points. Try to gain
              as many points as possible, the more points you gain the better offers you will get.
            </p>

            <h3>Penalties</h3>
            <p>
              Any advertiser may request the sanctioning of a user by filling out a
              complaint against them. Based on the complaint the
              administrator will decide whether the sanction will be applied. If a sanction
              is approved the user will receive a penalty.
            </p>
            <p>
              If the user receives 3 penalties, the creation of new reservations
              will be prohibited. Penalties are cleared every first of the
              month.
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  data: function () {
    return {
      userPointsInfo: [],
      loyaltyProgram: [],
      userCategory: [],
      penalties: 2,
      cottageReservations: 5,
      boatReservations: 1,
      adventureReservations: 2,
    };
  },
  mounted: function () {
    axios
      .get("/users/getUserPointsInfo", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.userPointsInfo = res.data;
        if (this.userPointsInfo.userCategory.includes("GOLD")) {
          this.userCategory = "GOLD";
        }
        else if (this.userPointsInfo.userCategory.includes("SILVER")) {
          this.userCategory = "SILVER";
        }
        else {
          this.userCategory = "REGULAR";
        }
      });

      axios
          .get("/userCategory/getLoyaltyProgram",
            {
              headers: {
                "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then((res) => {
            this.loyaltyProgram = res.data;
        });
  },
  methods: {
    changeMenuDisplay: function (event) {
      document.getElementById("stats").classList.remove("active");
      document.getElementById("terms").classList.remove("active");
      let elID = event.currentTarget.id;
      document.getElementById(elID).classList.add("active");

      if (elID == "stats") {
        document.querySelector(".main-stats").style.display = "block";
        document.querySelector(".main-terms").style.display = "none";
      } else if (elID == "terms") {
        document.querySelector(".main-stats").style.display = "none";
        document.querySelector(".main-terms").style.display = "block";
      } else {
        document.querySelector(".main-stats").style.display = "none";
        document.querySelector(".main-terms").style.display = "none";
      }
    },
  },
};
</script>

<style scoped src="@/css/statistics.css"></style>

