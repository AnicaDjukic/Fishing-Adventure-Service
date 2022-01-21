<template>
  <div>
    <UserInfo></UserInfo>
    <StatisticsAdvertiser
      v-if="loggedInRole != 'ROLE_CLIENT' && loggedInRole != 'ROLE_ADMIN'"
    ></StatisticsAdvertiser>
    <StatisticsClient v-if="loggedInRole == 'ROLE_CLIENT'"></StatisticsClient>
    <StatisticsAdmin v-if="loggedInRole == 'ROLE_ADMIN'"></StatisticsAdmin>

  </div>
</template>

<script>
import UserInfo from "@/components/UserProfile/UserInfo.vue";
import StatisticsAdvertiser from "@/components/UserProfile/StatisticsAdvertiser.vue";
import StatisticsClient from "@/components/UserProfile/StatisticsClient.vue";
import StatisticsAdmin from "@/components/UserProfile/StatisticsAdmin.vue";

import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  components: { UserInfo, StatisticsAdvertiser, StatisticsClient, StatisticsAdmin },
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
