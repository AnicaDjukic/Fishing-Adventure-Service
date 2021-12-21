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
            data-bs-toggle="modal"
            data-bs-target="#NewCottageModal"
          >
            New cottage
          </button>
        </div>
        <div class="col-md-4">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
            v-model="searchText"
            v-on:keyup="searchCottage"
            v-on:click="searchCottage"
          />
        </div>
        <div class="col-md-4">
          <div style="display: flex">
            <span class="input-group-text">Rating</span>
            <div
              class="rating-div form-control"
              style="min-width: 135px"
              v-on:click="searchCottage"
            >
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
      <CottageCard
        v-for="entity in searchResults"
        :key="entity.id"
        v-bind:entity="entity"
      ></CottageCard>
    </div>
  </div>
  <NewCottageModal></NewCottageModal>
</template>

<script>
import CottageCard from "@/components/CottageCard.vue";
import axios from "axios";
import NewCottageModal from "@/components/NewCottageModal.vue";
export default {
  components: { CottageCard, NewCottageModal },
  data: function () {
    return {
      clickedCottageForEdit: "",
      numberOfPersons: "",
      searchText: "",
      searchResults: [],
      entities: [],
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/vacationHome/allByUser", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.jwt,
        },
      })
      .then((res) => {
        this.searchResults = res.data;
        this.entities = res.data;
      });
  },
  methods: {
    searchCottage: function () {
      if (this.searchText != "" && this.searchText.trim().lenght != 0) {
        let searchParts = this.searchText.trim().split(" ");

        this.searchResults = [];
        for (let entity of this.entities) {
          let matches = true;
          for (let i = 0; i < searchParts.length; i++) {
            let address = entity.location.address;
            let owner = entity.vacationHomeOwner;
            let string =
              entity.name +
              address.streat +
              address.city +
              address.country +
              owner.name +
              owner.surname;
            if (
              !string
                .toLocaleLowerCase()
                .includes(searchParts[i].toLocaleLowerCase())
            ) {
              matches = false;
              break;
            }
          }
          if (matches) {
            this.searchResults.push(entity);
          }
        }
      } else {
        this.searchResults = this.entities;
      }

      let stars = document.querySelector(
        "input[type='radio'][name='star']:checked"
      );
      if (stars != null && this.searchResults != null) {
        let rating;
        if (document.getElementById("star1").checked) {
          rating = 5;
        } else if (document.getElementById("star2").checked) {
          rating = 4;
        } else if (document.getElementById("star3").checked) {
          rating = 3;
        } else if (document.getElementById("star4").checked) {
          rating = 2;
        } else {
          rating = 1;
        }

        let newResults = [];
        for (let entity of this.searchResults) {
          if (parseFloat(entity.rating) >= rating) {
            newResults.push(entity);
          }
        }

        this.searchResults = newResults;
      }
    },
    editCottage: function (event) {
      console.log(event);
      // this.$("#NewCottageModal").modal({ show: false });
      // this.$("#NewCottageModal").modal("show");
    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
