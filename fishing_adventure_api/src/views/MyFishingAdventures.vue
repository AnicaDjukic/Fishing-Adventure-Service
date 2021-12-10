<template>
  <div>
    <div class="title">
      <h1>My adventures</h1>
      <i
        class="far fa-fish fa-3x"
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
            New adventure
          </button>
        </div>
        <div class="col-md-4">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
            v-model="searchText"
            v-on:keyup="searchAdventures"
            v-on:click="searchAdventures"
          />
        </div>
        <div class="col-md-4">
          <div style="display: flex">
            <span class="input-group-text">Rating</span>
            <div
              class="rating-div form-control"
              style="min-width: 135px"
              v-on:click="searchAdventures"
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
      <AdventureCard
        v-for="entitie in searchResults"
        :key="entitie.id"
        v-bind:adventureEntitie="entitie"
      ></AdventureCard>
    </div>
  </div>
</template>

<script>
import AdventureCard from "@/components/AdventureCard.vue";
import axios from "axios";
export default {
    components: { AdventureCard },
    data: function () {
        return {
        clickedAdventureCardForEdit: "",
        numberOfPersons: "",
        searchText: "",
         //searchResults: [],
        searchResults: [{
          id: "1",
          imagePath: "fa21.jpg",
          name: "Villa Madam",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "4.85",
            street: "Ketch Harbour Lane",
            city: "New Port Richey",
            country: "USA",
          availabilityStart: "10 Dec 2021 14:00:00 GMT",
          availabilityEnd: "15 Dec 2021 11:00:00 GMT",
          image: "c1.jpg",
          fishingInstructor:  {
            id: "1",
            name: "Sam",
            surname: "Lee",
            email: "sam_lee@gmail.com",
            userType: "FISHING_INSTRUCTOR",
            date: "03.12.2021.",
            telephone: "0634375921",
            country: "Serbia",
            city: "City",
            street: "Street 2",
            },
          price: 30.0,
        },
        {
          id: "2",
          imagePath: "fa21.jpg",
          name: "Catherine's Vineyard",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "3.95",
            street: "Ketch Harbour Lane",
            city: "New Port Richey",
            country: "USA",
          availabilityStart: "8 Dec 2021 14:00:00 GMT",
          availabilityEnd: "12 Dec 2021 11:00:00 GMT",
          image: "c3.jpg",
          fishingInstructor: {
            id: "2",
            name: "John",
            surname: "Larsson",
            email: "john_smith@gmail.com",
            userType: "FISHING_INSTRUCTOR",
            date: "01.12.2021.",
            telephone: "0632868924",
            country: "Germany",
            city: "City",
            street: "Street 7",
            },
          price: 61.0,
        },]
        
        };
    },
    mounted: function () {
        axios
        .get("http://localhost:8080/fishingAdventure/all/" + localStorage.email, {
            headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.jwt,
            },
        })
        .then(
            (res) => {
            console.log(res.data);
            this.searchResults = res.data;
            },
            (err) => {
            console.log(err);
            }
        );
    },
    methods: {
        searchAdventures: function () {
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
        editAdventure: function (event) {
        console.log(event);
        },
    },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
