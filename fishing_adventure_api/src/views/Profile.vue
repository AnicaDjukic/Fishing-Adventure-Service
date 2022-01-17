<template>
  <div>
    <UserInfo></UserInfo>
    <StatisticsHomeOwner
      v-if="loggedInRole == 'ROLE_VACATION_HOME_OWNER'"
    ></StatisticsHomeOwner>
    <StatisticsClient v-if="loggedInRole == 'ROLE_CLIENT'"></StatisticsClient>
  </div>
</template>

<script>
import UserInfo from "@/components/UserProfile/UserInfo.vue";
import StatisticsHomeOwner from "@/components/UserProfile/StatisticsHomeOwner.vue";
import StatisticsClient from "@/components/UserProfile/StatisticsClient.vue";
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  components: { UserInfo, StatisticsHomeOwner, StatisticsClient },
  data: function () {
    return {
      loggedInRole: "",
    };
  },
  mounted() {
    axios
      .get("/users/getRole", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
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
