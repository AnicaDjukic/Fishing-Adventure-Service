<template>
  <div class="title">
    <h1>{{ entityType }} occupancy calendar</h1>
  </div>
  <VCalendar></VCalendar>
</template>

<script>
import VCalendar from "@/components/Utils/Calendar.vue";
import axios from "axios";
export default {
  components: { VCalendar },
  data: function () {
    return {
      entityType: undefined,
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
        if (res.data.includes("BOAT")) {
          this.entityType = "Boat";
        } else if (res.data.includes("HOME")) {
          this.entityType = "Cottage";
        } else if (res.data.includes("INSTRUCTOR")) {
          this.entityType = "Adventure";
        } else {
          window.location.href = "/";
        }
      });
  },
};
</script>

<style scoped>
.title {
  color: white;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin: 5%;
}
h1 {
  color: white !important;
}
</style>
