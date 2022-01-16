<template>
    <div class="review-fa continer">
        <div class="review-tp">
            <img class="user-rev-img" src="@/assets/user-comment.png" alt="" />
            <div class="rev-info">
              <h5>{{review.clientFullName}}</h5>
              <h6>{{review.datePosted}}</h6>
        </div>
        <div style="
                margin-left: auto;
                display: inline-flex;
                justify-content: flex-end;
                align-content: center;">
            <i v-for="star in review.rating" class="fas fa-star rev-star" v-bind:key="star"></i>
        </div>
        </div>
          <p style="font-size: 18px">
           {{review.content}}
          </p>
    </div>
</template>

<script>
export default {
  props: ["review"],
  name: "Review",
  data: function () {
    return {
    };
  },
  mounted() {
   
  },

};
</script>
<style scoped src="@/css/fishingAdventure.css"></style>