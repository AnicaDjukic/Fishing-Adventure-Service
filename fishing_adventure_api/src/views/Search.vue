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
    <div class="searchBar">
      <div
        class="
          container
          w-100
          row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-2
          fields
        "
      >
        <div class="col-md-4 col-lg-5 col-xl-3">
          <Datepicker
            class="datePricker"
            :format="format"
            dark
            id="picker"
            v-model="date"
            range
            :enableTimePicker="true"
          ></Datepicker>
        </div>
        <div class="col-md-5 col-lg-3 col-xl-2">
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
        <div class="col-md-5 col-lg-4 col-xl-3">
          <div style="display: flex">
            <span class="input-group-text">Rating</span>
            <div class="rating-div form-control" v-on:click="searchCottage">
              <div class="rating" style="height: 0">
                <input
                  type="radio"
                  v-on:click="rateService('star1')"
                  name="star"
                  id="star1"
                  value="5"
                />
                <label for="star1"></label>
                <input
                  type="radio"
                  v-on:click="rateService('star2')"
                  name="star"
                  id="star2"
                  value="4"
                />
                <label for="star2"></label>
                <input
                  type="radio"
                  v-on:click="rateService('star3')"
                  name="star"
                  id="star3"
                  value="3"
                />
                <label for="star3"></label>
                <input
                  type="radio"
                  v-on:click="rateService('star4')"
                  name="star"
                  id="star4"
                  value="2"
                />
                <label for="star4"></label>
                <input
                  type="radio"
                  v-on:click="rateService('star5')"
                  name="star"
                  id="star5"
                  value="1"
                />
                <label for="star5"></label>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-5 col-lg-4 col-xl-2 flex searchBtn-div">
          <button
            class="btn btn-primary advanceSearchBtn checkBtn"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#allAdvencedSearch"
            aria-expanded="false"
            aria-controls="advencedSearch filterAndSort"
            v-on:click="advencedSearch"
            id="advencedSearchBtn"
          >
            <i class="fas fa-search-plus" style="color: white"></i>
          </button>

          <button class="btn btn-primary searchBtn" v-on:click="search">
            Search
          </button>
        </div>
      </div>
      <div id="allAdvencedSearch" class="collapse multi-collapse">
        <div
          class="
            container
            w-100
            row
            row-cols-1
            row-cols-sm-1
            row-cols-md-2
            row-cols-lg-2
            row-cols-xl-6
            mt-2
          "
        >
          <div id="advencedSearch" style="width: 100%">
            <div style="display: flex; gap: 1rem">
              <div class="col" style="max-width: 100% !important">
                <input
                  class="form-control me-2"
                  type="search"
                  v-model="input"
                  placeholder="Search by name, address or advertiser..."
                  aria-label="Search"
                />
              </div>
              <div class="col">
                <button
                  class="
                    btn btn-primary
                    shadow-none
                    mb-2
                    col-md-5 col-xl-2
                    checkBtn
                  "
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#filterAndSort"
                  aria-expanded="false"
                  aria-controls="filterAndSort"
                  v-on:click="filtersCliced"
                  id="filterAndSortBtn"
                >
                  Sort
                  <i class="fas fa-caret-down"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
        <div
          class="
            container
            w-100
            row
            row-cols-1
            row-cols-sm-1
            row-cols-md-2
            row-cols-lg-2
            row-cols-xl-6
            mt-2
          "
        >
          <div class="collapse" id="filterAndSort">
            <div class="filterAndSort">
              <!--<div class="filters">
                <ul>
                  <li v-on:click="filterOption" id="filter1">Filter 1</li>
                  <li v-on:click="filterOption" id="filter2">Filter 2</li>
                  <li v-on:click="filterOption" id="filter3">Filter 3</li>
                </ul>
              </div>-->
              <div class="sorts">
                <ul>
                  <li v-on:click="sortOption" id="sort1">Sort by name</li>
                  <li v-on:click="sortOption" id="sort2">Sort by place</li>
                  <li v-on:click="sortOption" id="sort3">Sort by rate</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
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
import CottageCard from "@/components/EntityCards/CottageCard.vue";
import BoatCard from "@/components/EntityCards/BoatCard.vue";
import AdventureCard from "@/components/EntityCards/AdventureCard.vue";
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
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

    const format = (date) => {
      const day0 = date[0].getDate();
      const month0 = date[0].getMonth() + 1;
      const year0 = date[0].getFullYear() - 2000;
      const hour0 = date[0].getHours();
      const minutes0 = date[0].getMinutes();

      const day1 = date[1].getDate();
      const year1 = date[1].getFullYear() - 2000;
      const month1 = date[1].getMonth() + 1;
      const hour1 = date[1].getHours();
      const minutes1 = date[1].getMinutes();

      return `${month0}/${day0}/${year0} ${hour0}:${minutes0} - ${month1}/${day1}/${year1} ${hour1}:${minutes1}`;
    };

    return {
      date,
      format,
    };
  },
  data: function () {
    return {
      numberOfPersons: 0,
      rating: 0,
      searching: "",
      homeEntities: [],
      boatEntities: [],
      adventureEntities: [],
      review: false,
      loggedInRole: undefined,
      showModal: false,
      object: {},
      reservationInfo: {},
      chosenSort: "",
      chosenFilters: [],
      input:""
    };
  },
  mounted: function () {
    axios
      .get("/users/getRole", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
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
        .get("/vacationHome/all", {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
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
        .get("/fishingAdventure/all", {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.adventureEntities = res.data;
          for (let adventure of this.adventureEntities) {
            adventure.rating = Number(adventure.rating).toFixed(2);
          }
        });
    } else if (window.location.href.includes("/search/boats")) {
      this.searching = "boats";
      axios
        .get("/boat/all", {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
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
    /*filterOption: function (event) {
      let filterId = event.target.id;
      if (this.chosenFilters.includes(filterId)) {
        for (let i = 0; i < this.chosenFilters.length; i++) {
          if (this.chosenFilters[i] == filterId) {
            this.chosenFilters.splice(i, 1);
            document
              .getElementById(event.target.id)
              .classList.remove("activeOption");
            break;
          }
        }
      } else {
        this.chosenFilters.push(filterId);
        document.getElementById(event.target.id).classList.add("activeOption");
      }
      console.log(this.chosenFilters);
    },*/
    rateService: function (id) {
      var ele = document.getElementById(id);
      this.rating = ele.value;
    },
    sortOption: function (event) {
      let sortId = event.target.id;
      document.getElementById(sortId).classList.add("activeOption");
      if (this.chosenSort != "") {
        document
          .getElementById(this.chosenSort)
          .classList.remove("activeOption");
      }
      this.chosenSort = sortId;
      console.log(this.chosenSort);
    },
    filtersCliced: function () {
      let btn = document.getElementById("filterAndSortBtn");
      if (btn.classList.contains("active")) {
        btn.classList.remove("active");
      } else {
        btn.classList.add("active");
      }
    },
    advencedSearch: function () {
      let btn = document.getElementById("advencedSearchBtn");
      if (btn.classList.contains("active")) {
        btn.classList.remove("active");
      } else {
        btn.classList.add("active");
      }
    },
    updateReservationInfo: function () {
      let info = {
        date: this.date,
        persons: this.numberOfPersons,
      };
      this.reservationInfo = info;
    },
    updateDatePicker(value) {
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
        if (this.date[0] != undefined && this.date[1] != undefined) {
          this.searchCottages();
        }
      } else if (window.location.href.includes("/search/boats")) {
        this.searching = "boats";
        if (this.date[0] != undefined && this.date[1] != undefined) {
          this.searchBoats();
        }
      } else {
        this.searching = "adventures";
        if (this.date[0] != undefined && this.date[1] != undefined) {
          this.searchAdventures();
        }
      }
    },
    searchCottages: function () {
      axios
        .get(
          "/vacationHome/search?start=" +
            moment(this.date[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&end=" +
            moment(this.date[1]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&persons=" +
            this.numberOfPersons +
            "&rating=" +
            this.rating + "&input=" + this.input,
          {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
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
    searchBoats: function () {
      axios
        .get(
          "/boat/search?start=" +
            moment(this.date[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&end=" +
            moment(this.date[1]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&persons=" +
            this.numberOfPersons +
            "&rating=" +
            this.rating + "&input=" + this.input,
          {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
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
    searchAdventures : function() {
      axios
        .get(
          "/fishingAdventure/search?start=" +
            moment(this.date[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&end=" +
            moment(this.date[1]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&persons=" +
            this.numberOfPersons +
            "&rating=" +
            this.rating + "&input=" + this.input,
          {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            },
          }
        )
        .then((res) => {
          this.adventureEntities = res.data;
          for (let e of this.adventureEntities) {
            e.rating = Number(e.rating).toFixed(2);
          }
        });
    }
  },
};
</script>

<style scoped src="@/css/search.css"></style>
