<template>
  <div>
    <div class="title">
      <h1>Find your perfect vacation</h1>
      <i v-if="searching == '' || searching == 'cottages'" class="far fa-home fa-3x" style="font-family: 'Font Awesome 5 Pro'"></i
      ><i
        v-if="searching == '' || searching == 'boats'"
        class="far fa-anchor fa-3x"
        style="font-family: 'Font Awesome 5 Pro'"
      ></i>
      <i
        v-if="searching == '' || searching == 'adventures'"
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
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
          />
        </div>
        <div class="col-md-5">
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
            :enableTimePicker="false"
          ></Datepicker>
        </div>
        <div class="col-md-5">
          <div class="input-group">
            <span class="input-group-text">Number of persons</span>
            <input
              type="number"
              min="1"
              max="15"
              v-model="numberOfPersons"
              class="form-control"
            />
          </div>
        </div>
      </div>
    </div>
    <div v-if="searching == 'cottages'" style="margin-top: 5%">
      <CottageCard
        v-for="entitie in entities"
        :key="entitie.id"
        v-bind:entitie="entitie"
      ></CottageCard>
    </div>
    <div v-if="searching == 'boats'" style="margin-top: 5%">
      <BoatCard 
        v-for="boatEntitie in boatEntities"
        :key="boatEntitie.id"
        v-bind:boatEntitie="boatEntitie"></BoatCard>
    </div>
    <div v-if="searching == 'fishingInstructors'" style="margin-top: 5%">
      <InstructorCard v-for="index in 10" :key="index"></InstructorCard>
    </div>
  </div>
</template>

<script>
import Datepicker from "vue3-date-time-picker";
import "vue3-date-time-picker/dist/main.css";
import { ref, onMounted } from "vue";
import CottageCard from "@/components/CottageCard.vue";
import BoatCard from "@/components/BoatCard.vue";
import InstructorCard from "@/components/InstructorCard.vue";
import axios from "axios"
export default {
  components: { Datepicker, CottageCard, BoatCard, InstructorCard },
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
      numberOfPersons: "",
      searching: "",
      entities: [
        {
          id: "1",
          name: "Villa Madam",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "4.85",
          location: "",
          availabilityStart: "10 Dec 2021 14:00:00 GMT",
          availabilityEnd: "15 Dec 2021 11:00:00 GMT",
          image: "c1.jpg",
          vacationHomeOwner: "jamesDean",
        },
        {
          id: "2",
          name: "Catherine's Vineyard",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "3.95",
          location: "",
          availabilityStart: "8 Dec 2021 14:00:00 GMT",
          availabilityEnd: "12 Dec 2021 11:00:00 GMT",
          image: "c3.jpg",
          vacationHomeOwner: "jamesDean",
        },
        {
          id: "3",
          name: "Hillside Cottage",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "4.44",
          location: "",
          availabilityStart: "6 Dec 2021 14:00:00 GMT",
          availabilityEnd: "9 Dec 2021 11:00:00 GMT",
          image: "c9.jpg",
          vacationHomeOwner: "zoeyClemons",
        },
        {
          id: "4",
          name: "Morris cottage",
          description:
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
          cancellationRule: "",
          rating: "4.65",
          location: "",
          availabilityStart: "23 Dec 2021 14:00:00 GMT",
          availabilityEnd: "25 Dec 2021 11:00:00 GMT",
          image: "c14.jpg",
          vacationHomeOwner: "nikkiMorrison",
        },
      ],
      boatEntities:[],
      fishingInstructor:[]
    };
  },
  mounted: function () {
    if (window.location.href.includes("/search/cottages")) {
      this.searching = "cottages";
    } else if (window.location.href.includes("/search/fishingInstructors")) {
      this.searching = "fishingInstructors";
    } else if (window.location.href.includes("/search/boats")) {
      this.searching = "boats";
    }
    axios
        .get("http://localhost:8080/boat/all", {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080"
          },
        })
        .then(
          (res) => {
            this.boatEntities = res.data
            for(let boat of this.boatEntities){
              boat.rating = (Number(boat.rating)).toFixed(2);
            }
          }
        );

    axios
        .get("http://localhost:8080/fishingInstructor/all", {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080"
          },
        })
        .then(
          (res) => {
            this.fishingInstructor = res.data
          }
        );
  },
  methods: {},
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
  margin: 5%;
}

h1 {
  color: white !important;
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
</style>
