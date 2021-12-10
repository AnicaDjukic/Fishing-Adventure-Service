<template>
  <section class="intro">
    <h1>Things we can do for you</h1>
    <p>
      Lorem Ipsum is simply dummy text of the printing and typesetting industry.
      Lorem Ipsum has been the industry's standard dummy text ever since the
      1500s, when an unknown printer took a galley of type and scrambled it to
      make a type specimen book. It has survived not only five centuries, but
      also the leap into electronic typesetting, remaining essentially
      unchanged. It was popularised in the 1960s with the release of Letraset
      sheets containing Lorem Ipsum passages, and more recently with desktop
      publishing software like Aldus PageMaker including versions of Lorem
      Ipsum.
    </p>
  </section>

  <section class="cards">
    <div class="row row-cols-1 row-cols-md-3 g-5">
      <div class="col" v-on:click="searchBoats">
        <div class="card h-100 bg-dark hover-zoom">
          <img
            src="@/assets/b2.jpg"
            class="card-img-top shadow-none"
            alt="..."
          />
          <div class="card-body shadow-none">
            <h5 class="card-title text-white shadow-none">Boats</h5>
            <p class="card-text shadow-none">
              This is a longer card with supporting text below as a natural
              lead-in to additional content. This content is a little bit
              longer.
            </p>
          </div>
        </div>
      </div>
      <div class="col" v-on:click="searchCottages">
        <div class="card h-100 bg-dark">
          <img
            src="@/assets/c2.jpg"
            class="card-img-top shadow-none"
            alt="..."
          />
          <div class="card-body shadow-none">
            <h5 class="card-title text-white shadow-none">Cottages</h5>
            <p class="card-text shadow-none">
              This is a longer card with supporting text below as a natural
              lead-in to additional content. This content is a little bit
              longer.
            </p>
          </div>
        </div>
      </div>
      <div class="col" v-on:click="searchAdventures">
        <div class="card h-100 bg-dark">
          <img
            src="@/assets/fa10.jpg"
            class="card-img-top shadow-none"
            alt="..."
          />
          <div class="card-body shadow-none">
            <h5 class="card-title text-white shadow-none">Fishing instructors</h5>
            <p class="card-text shadow-none">
              This is a longer card with supporting text below as a natural
              lead-in to additional content.
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "HomeComponent",
  methods: {
    searchBoats: function () {
      window.location.href = "/search/boats";
    },
    searchCottages: function () {
      window.location.href = "/search/cottages";
    },
    searchAdventures: function () {
      window.location.href = "/search/fishingInstructors";
    },
  },
};
</script>

<style scoped>
.intro {
  margin: 5%;
}

.cards {
  margin: 0 5% 10% 5%;
}
.col :hover {
  cursor: pointer;
  box-shadow: 2px 2px 15px 2px rgba(187, 178, 178, 0.664);
  transition: 0.5s;
}
</style>
