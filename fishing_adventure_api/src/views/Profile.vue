<template>
  <div>
    <UserInfo></UserInfo>
    <HomeOwnerStatistics
      v-if="loggedInRole == 'ROLE_VACATION_HOME_OWNER'"
    ></HomeOwnerStatistics>
    <StatisticsClient v-if="loggedInRole == 'ROLE_CLIENT'"></StatisticsClient>
  </div>
</template>

<script>
import UserInfo from "@/components/UserInfo.vue";
import HomeOwnerStatistics from "@/components/HomeOwnerStatistics.vue";
import StatisticsClient from "@/components/StatisticsClient.vue";
import axios from "axios";
export default {
  components: { UserInfo, HomeOwnerStatistics, StatisticsClient },
  data: function () {
    return {
      loggedInRole: "",
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
        this.loggedInRole = res.data;
      });
  },
};
</script>

<style scoped></style>
