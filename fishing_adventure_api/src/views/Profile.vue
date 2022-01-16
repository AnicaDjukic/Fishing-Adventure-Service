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
export default {
  components: { UserInfo, StatisticsHomeOwner, StatisticsClient },
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
