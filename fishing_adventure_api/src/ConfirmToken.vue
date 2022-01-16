<template>
  <div class="d-block" style="background-color: rgb(33 37 41)">
    <img src="@/assets/logo_white.png" alt="" style="height: 12rem" />
  </div>
  <div
    style="
      width: 50%;
      margin: 6rem auto;
      background-color: rgb(33 37 41);
      padding: 2rem;
      border-radius: 20px;
    "
  >
    <h1 style="color: white">Account successesfuly activated!</h1>
    <h4 style="color: white; margin: 4rem 0">
      You have activated your account, now you can log in into Angler and start
      looking for your perfect vacation!
    </h4>
    <button class="btn btn-outline-primary" v-on:click="btnClick">
      Go to site!
    </button>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: "ConfirmToken",
  mounted() {
    axios
      .get("http://localhost:8080/auth/confirm-account?token=" + this.$route.query.token, {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then();
  },
  methods: {
    btnClick: function () {
      window.location.href = "/";
      
    },
  },
};
</script>

<style scoped>
.btn-outline-primary {
  color: white !important;
  border-color: white !important;
}

.btn-outline-primary:hover {
  color: black !important;
  border-color: black !important;
  background-color: white !important;
}

.btn-outline-primary:focus-visible,
.btn-outline-primary:focus {
  box-shadow: 0 0 0 0.25rem rgba(255, 255, 255, 0.575) !important;
}
</style>
