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
        v-for="entitie in searchResults"
        :key="entitie.id"
        v-bind:entitie="entitie"
      ></CottageCard>
    </div>
  </div>
  <NewCottageModal></NewCottageModal>
</template>

<script>
import CottageCard from "@/components/CottageCard.vue";
import NewCottageModal from "@/components/NewCottageModal.vue";
export default {
  components: { CottageCard, NewCottageModal },
  data: function () {
    return {
      numberOfPersons: "",
      searchText: "",
      searchResults: [
        {
          id: "1",
          name: "Villa Madam",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "4.85",
          location: "768 Ketch Harbour Lane New Port Richey, FL 34653",
          availabilityStart: "10 Dec 2021 14:00:00 GMT",
          availabilityEnd: "15 Dec 2021 11:00:00 GMT",
          image: "c1.jpg",
          vacationHomeOwner: "jamesDean",
          price: 50.0,
        },
        {
          id: "2",
          name: "Catherine's Vineyard",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "3.95",
          location: "849 Cedar Drive Niceville, FL 32578",
          availabilityStart: "8 Dec 2021 14:00:00 GMT",
          availabilityEnd: "12 Dec 2021 11:00:00 GMT",
          image: "c3.jpg",
          vacationHomeOwner: "jamesDean",
          price: 45.55,
        },
      ],
      entities: [
        {
          id: "1",
          name: "Villa Madam",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "4.85",
          location: "768 Ketch Harbour Lane New Port Richey, FL 34653",
          availabilityStart: "10 Dec 2021 14:00:00 GMT",
          availabilityEnd: "15 Dec 2021 11:00:00 GMT",
          image: "c1.jpg",
          vacationHomeOwner: "jamesDean",
          price: 30.0,
        },
        {
          id: "2",
          name: "Catherine's Vineyard",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "3.95",
          location: "849 Cedar Drive Niceville, FL 32578",
          availabilityStart: "8 Dec 2021 14:00:00 GMT",
          availabilityEnd: "12 Dec 2021 11:00:00 GMT",
          image: "c3.jpg",
          vacationHomeOwner: "jamesDean",
          price: 61.0,
        },
      ],
    };
  },
  methods: {
    searchCottage: function () {
      if (this.searchText != "" && this.searchText.trim().lenght != 0) {
        let searchParts = this.searchText.trim().split(" ");

        this.searchResults = [];
        for (let entitie of this.entities) {
          let matches = true;
          for (let i = 0; i < searchParts.length; i++) {
            if (
              !entitie.name
                .toLocaleLowerCase()
                .includes(searchParts[i].toLocaleLowerCase()) &&
              !entitie.location
                .toLocaleLowerCase()
                .includes(searchParts[i].toLocaleLowerCase()) &&
              !entitie.vacationHomeOwner
                .toLocaleLowerCase()
                .includes(searchParts[i].toLocaleLowerCase())
            ) {
              matches = false;
              break;
            }
          }
          if (matches) {
            this.searchResults.push(entitie);
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
        for (let entitie of this.searchResults) {
          if (parseFloat(entitie.rating) >= rating) {
            newResults.push(entitie);
          }
        }

        this.searchResults = newResults;
      }
    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
