<template>
  <div>
    <div class="title">
      <h1 v-if="searching == '' || searching == 'cottages'">
        Find your perfect vacation
      </h1>
      <img
        v-if="searching == '' || searching == 'cottages'"
        style="height: 10rem"
        src="@/assets/house4.png"
      />
      <h1
        v-if="
          searching == '' || searching == 'boats' || searching == 'adventures'
        "
      >
        Find your perfect adventure
      </h1>
      <img
        v-if="searching == '' || searching == 'boats'"
        style="height: 10rem"
        src="@/assets/yatch.png"
      />
      <img
        v-if="searching == '' || searching == 'adventures'"
        style="height: 10rem"
        src="@/assets/fishing1.png"
      />
    </div>
    <div
      style="
        background-color: #212529;
        padding: 15px;
        text-align: -webkit-center;
      "
    >
      <div
        class="
          container
          w-100
          row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-5
        "
        style="justify-content: space-evenly; align-items: center"
      >
        <div class="col-md-4">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
          />
        </div>
        <div>
          <Datepicker
            style="
              width: 100%;
              margin-right: 10px;
              border: 1px solid white;
              border-radius: 5px;
            "
            dark
            id="picker"
            v-model="date"
            range
            :enableTimePicker="true"
          ></Datepicker>
        </div>
        <div class="col-md-5">
          <div class="input-group">
            <span class="input-group-text">Persons</span>
            <input
              type="number"
              min="1"
              max="15"
              v-model="numberOfPersons"
              class="form-control"
            />
          </div>
        </div>
        <div class="col-md-5">
          <div style="display: flex">
            <span class="input-group-text">Rating</span>
            <div
              class="rating-div form-control"
              style="min-width: 135px; padding: 0"
              v-on:click="searchCottage"
            >
              <div class="rating" style="height: 0">
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
        <button
          class="btn btn-primary shadow-none mb-2"
          style="background-color: rgb(0 51 51); border-color: rgb(0 51 51)"
          v-on:click="search"
        >
          Search
        </button>
      </div>
    </div>
    <div v-if="searching == 'cottages'" style="margin-top: 5%">
      <CottageCard
        v-for="homeEntity in homeEntities"
        :key="homeEntity.id"
        v-bind:entity="homeEntity"
        v-bind:info="reservationInfo"
      ></CottageCard>
    </div>
    <div v-if="searching == 'boats'" style="margin-top: 5%">
      <BoatCard
        v-for="boatEntity in boatEntities"
        :key="boatEntity.id"
        v-bind:boatEntity="boatEntity"
        v-bind:info="reservationInfo"
      ></BoatCard>
    </div>
    <div v-if="searching == 'adventures'" style="margin-top: 5%">
      <AdventureCard
        v-for="adventureEntity in adventureEntities"
        :key="adventureEntity.id"
        v-bind:adventureEntity="adventureEntity"
      ></AdventureCard>
    </div>
  </div>
</template>

<script>
import Datepicker from "vue3-date-time-picker";
import "vue3-date-time-picker/dist/main.css";
import { ref, onMounted } from "vue";
import CottageCard from "@/components/CottageCard.vue";
import BoatCard from "@/components/BoatCard.vue";
import AdventureCard from "@/components/AdventureCard.vue";
import axios from "axios";
import moment from "moment";
export default {
  components: { Datepicker, CottageCard, BoatCard, AdventureCard },
  setup() {
    const date = ref();
    // For demo purposes assign range from the current date
    onMounted(() => {
      const startDate = new Date();
      const endDate = new Date(new Date().setDate(startDate.getDate() + 7));
      date.value = [startDate, endDate];
    });

    return {
      date,
    };
  },
  data: function () {
    return {
      numberOfPersons: 0,
      searching: "",
      homeEntities: [],
      boatEntities: [],
      adventureEntities: [],
      review: false,
      loggedInRole: undefined,
      showModal: false,
      object: {},
      reservationInfo: {},
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/users/getRole", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.loggedInRole = res.data;
        if (this.loggedInRole == "ROLE_CLIENT") {
          this.review = true;
        }
      });
    if (window.location.href.includes("/search/cottages")) {
      this.searching = "cottages";
      axios
        .get("http://localhost:8080/vacationHome/all", {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
          },
        })
        .then((res) => {
          this.homeEntities = res.data;
          for (let e of this.homeEntities) {
            e.rating = Number(e.rating).toFixed(2);
          }
        });
    } else if (window.location.href.includes("/search/adventures")) {
      this.searching = "adventures";
      axios
        .get("http://localhost:8080/fishingAdventure/all", {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
          },
        })
        .then((res) => {
          this.adventureEntities = res.data;
          for (let adventure of this.adventureEntities) {
            adventure.rating = Number(adventure.rating).toFixed(2);
          }
        });
    } else if (window.location.href.includes("/search/boats")) {
      this.searching = "boats";
      axios
        .get("http://localhost:8080/boat/all", {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
          },
        })
        .then((res) => {
          this.boatEntities = res.data;
          for (let boat of this.boatEntities) {
            boat.rating = Number(boat.rating).toFixed(2);
          }
        });
    }
  },
  methods: {
    updateReservationInfo: function () {
      let info = {
        date: this.date,
        persons: this.numberOfPersons,
      };
      this.reservationInfo = info;
    },
    updateDatePicker(value) {
      console.log("updating datepicker value");
      this.date = value;
    },
    search: function () {
      let info = {
        date: this.date,
        persons: this.numberOfPersons,
      };
      this.reservationInfo = info;
      if (window.location.href.includes("/search/cottages")) {
        this.searching = "cottages";
        if (
          this.date[0] != undefined &&
          this.date[1] != undefined
        ) {
          this.searchCottagesByDateAndPersons();
        }
      } else if (window.location.href.includes("/search/boats")) {
        this.searching = "boats";
        if (
          this.date[0] != undefined &&
          this.date[1] != undefined
        ) {
          this.searchBoatsByDateAndPersons();
        }
      }
    },
    searchCottagesByDateAndPersons: function () {
      axios
        .get(
          "http://localhost:8080/vacationHome/search?start=" +
            moment(this.date[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&end=" +
            moment(this.date[1]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&persons=" +
            this.numberOfPersons,
          {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
            },
          }
        )
        .then((res) => {
          this.homeEntities = res.data;
          for (let e of this.homeEntities) {
            e.rating = Number(e.rating).toFixed(2);
          }
        });
    },
    searchBoatsByDateAndPersons: function () {
      axios
        .get(
          "http://localhost:8080/boat/search?start=" +
            moment(this.date[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&end=" +
            moment(this.date[1]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&persons=" +
            this.numberOfPersons,
          {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
            },
          }
        )
        .then((res) => {
          this.boatEntities = res.data;
          for (let e of this.boatEntities) {
            e.rating = Number(e.rating).toFixed(2);
          }
        });
    },
  },
};
</script>

<style scoped>
.carousel {
  position: relative;
  top: 80%;
  left: 50%;
  transform: translate(-50%, 0%);
  width: 100%;
}

.title {
  color: white;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  padding: 2% 0;
  background-image: linear-gradient(rgb(33 37 41), rgba(4, 9, 30, 0.527)),
    url(/img/lake1.08d1171f.jpg);
}

h1 {
  color: white !important;
  font-size: 4rem;
  font-style: italic;
  font-family: "Font Awesome 5 Brands";
  font-weight: bold;
}
.form-control {
  background-color: #393c3f73;
  color: white;
}

.form-control:focus {
  box-shadow: 0 0 0 0.2rem rgba(255, 255, 255, 0.432);
}

.container {
  display: flex;
}

.form-floating > label {
  color: gray;
}

.input-group-text {
  background-color: #393c3f73;
  color: gray;
}
.rating {
  display: inline-flex;
  justify-content: center;
  width: fit-content;
  transform: rotateY(180deg);
}

.rating label {
  display: block;
  cursor: pointer;
  background-color: transparent;
}

.rating label::before {
  font-family: "Font Awesome 5 Free";
  font-weight: 900;
  content: "\f005";
  position: relative;
  display: block;
  color: white;
  font-size: 3vh;
}

.rating label::after {
  font-family: "Font Awesome 5 Free";
  font-weight: 900;
  content: "\f005";
  position: absolute;
  display: block;
  color: rgb(255, 217, 0);
  opacity: 0;
  font-size: 3vh;
  top: 0vh;
  transition: 0.5s;
  text-shadow: 0 2px 5px rgba(0, 0, 0, 0.5);
}

.rating label:hover:after,
.rating label:hover ~ label:after,
.rating input:checked ~ label:after {
  opacity: 1;
}

.rating-div label {
  color: white;
  font-size: 18px;
  margin-bottom: 2%;
}

input[type="radio"] {
  display: none;
}
</style>
