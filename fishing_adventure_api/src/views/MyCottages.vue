<template>
  <div>
    <div class="title">
      <h1>My cottages</h1>
      <i
        class="far fa-home fa-3x"
        style="font-family: 'Font Awesome 5 Pro'"
      ></i>
    </div>
    <div
      style="
        background-color: #212529;
        padding: 15px;
        text-align: -webkit-center;
      "
    >
      <div
        class="container w-100 row row-cols-1 row-cols-sm-1 row-cols-md-4"
        style="justify-content: space-evenly; align-items: center"
      >
        <div class="col-md-2">
          <button
            type="button"
            class="btn btn-outline-primary text-nowrap me-2"
          >
            New cottage
          </button>
        </div>
        <div class="col-md-2">
          <button
            type="button"
            class="btn btn-outline-primary text-nowrap me-2"
            v-on:click="probajMetodu"
          >
            New cottage
          </button>
        </div>
        <div class="col-md-3">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
          />
        </div>
        <div class="col-md-4">
          <div style="display: flex">
            <span class="input-group-text">Price</span>
            <input
              class="form-control"
              type="search"
              placeholder="Enter price.."
              aria-label="Enter price.."
            />
          </div>
        </div>
        <div class="col-md-3">
          <div style="display: flex">
            <span class="input-group-text">Rating</span>
            <div class="rating-div form-control" style="min-width: 135px">
              <div class="rating">
                <input type="radio" name="star" id="star1" value="5" />
                <label for="star1"></label>
                <input type="radio" name="star" id="star2" value="4" />
                <label for="star2"></label>
                <input type="radio" name="star" id="star3" value="3" />
                <label for="star3"></label>
                <input type="radio" name="star" id="star4" value="2" />
                <label for="star4"></label>
                <input type="radio" name="star" id="star5" value="1" />
                <label for="star5"></label>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div style="margin-top: 5%">
      <CottageCard v-for="index in 10" :key="index"></CottageCard>
    </div>
  </div>
</template>

<script>
import CottageCard from "@/components/CottageCard.vue";
import axios from "axios";
export default {
  components: { CottageCard },
  data: function () {
    return {
      numberOfPersons: "",
    };
  },
  methods: {
    probajMetodu: function () {
      axios
        .get("http://localhost:8080/vacationHome/all", {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            "Authorization": "Bearer " + localStorage.jwt 
          },
        })
        .then((res) => {
          console.log(res);
          console.log(localStorage.jwt);
        },
        (err) => {console.log(err);
          console.log(localStorage.jwt);});
    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
