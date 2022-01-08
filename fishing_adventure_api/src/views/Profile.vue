<template>
  <div>
    <UserInfo></UserInfo>
    <HomeOwnerStatistics></HomeOwnerStatistics>
  </div>
</template>

<script>
import UserInfo from "@/components/UserInfo.vue";
import HomeOwnerStatistics from "@/components/HomeOwnerStatistics.vue";
export default {
  components: { UserInfo, HomeOwnerStatistics },
};
</script>

<style scoped></style>
