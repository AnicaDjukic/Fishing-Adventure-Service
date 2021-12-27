<template>
  <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      style="text-shadow: 1px 1px 2px #333"
    >
      <b-carousel-slide v-for="item in items" :key="item.id">
        <img :src="require('@/assets/' + item.path)" />
      </b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
export default {
  props: ["items"],
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
};
</script>

<style scoped></style>
