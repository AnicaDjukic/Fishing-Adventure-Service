<template>
  <div>
    <UserInfo></UserInfo>
    <HomeOwnerStatistics></HomeOwnerStatistics>
  </div>
</template>

<script>
import UserInfo from "@/components/UserInfo.vue";
import HomeOwnerStatistics from "@/components/HomeOwnerStatistics.vue";
import axios from "axios";
export default {
  components: { UserInfo, HomeOwnerStatistics },
  data: function () {
    return {
      isRequestedForDelete: undefined,
    };
  },
  mounted() {
    axios
      .get("http://localhost:8080/deleteRequest/isRequested", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.isRequestedForDelete = res.data;
      });
  },
};
</script>

<style scoped></style>
