<template>
  <div>
    <div class="card mb-3 bg-dark mt-3" style="width: 65%; margin: auto">
      <div class="row g-0" v-on:click="openAdventure">
        <div class="col-md-4 shadow-none">
          <img
            style="width: 100%; height: 225px; object-fit: cover"
            :src="require('@/assets/' + adventureEntity.imagePath)"
            class="img-fluid rounded-start shadow-none"
          />
        </div>

        <div class="col-md-8 shadow-none" name="main-col">
          <div class="card-body shadow-none">
            <div class="card-text shadow-none" style="display: flex">
              <h5 class="card-title shadow-none">{{ adventureEntity.name }}</h5>
              <p class="advertiserTitle shadow-none">
                @{{ adventureEntity.fishingInstructor.name
                }}{{ adventureEntity.fishingInstructor.surname }}
              </p>
              <p
                v-if="path == 'myadventures'"
                class="top-right-corner shadow-none"
              >
                <i
                  class="fas fa-edit fa-lg shadow-none me-3"
                  style="color: #293c4e"
                ></i>
                <i class="fas fa-minus-square fa-lg shadow-none"></i>
              </p>
            </div>
            <div class="card-text shadow-none" style="display: flex">
              <div class="shadow-none">
                <p class="card-text text-left shadow-none mb-1">
                  {{ adventureEntity.description }}
                </p>
                <p
                  class="card-text text-left shadow-none mb-3 flex-column d-flex flex-md-row"
                  style="align-items: center"
                >
                  Available:
                  <Datepicker
                    v-if="path == 'myadventures'"
                    style="
                      margin-left: 2%;
                      margin-top: 2%;
                      border: 1px solid white;
                      border-radius: 5px;
                      width: 100%;
                      box-shadow: none !important;
                    "
                    dark
                    id="picker"
                    v-model="date"
                    range
                    :partialRange="false"
                    placeholder="Select date"
                    :enableTimePicker="true"
                    minutesIncrement="15"
                    :minDate="new Date()"
                  ></Datepicker>
                  <Datepicker
                    v-if="path != 'myadventures'"
                    style="
                      margin-left: 2%;
                      margin-top: 2%;
                      border: 1px solid white;
                      border-radius: 5px;
                      width: 100%;
                      box-shadow: none !important;
                    "
                    dark
                    id="picker"
                    v-model="date"
                    range
                    :partialRange="false"
                    placeholder="Select date"
                    :enableTimePicker="true"
                    minutesIncrement="15"
                    :minDate="new Date()"
                    disabled
                  ></Datepicker>
                </p>
              </div>
              <p
                class="shadow-none"
                style="
                  margin: 0;
                  text-align: right;
                  margin-left: auto;
                  color: #c79e2c;
                  width: 30%;
                "
              >
                <i class="fas fa-star shadow-none">
                  {{ adventureEntity.rating }}</i
                >
              </p>
            </div>
            <div class="card-text fw-bold shadow-none" style="display: flex">
              <p class="shadow-none" style="margin: 0">
                {{ adventureEntity.location.address.street }} {{ adventureEntity.location.address.city }}
                {{ adventureEntity.location.address.country }}
              </p>
              <p
                class="shadow-none"
                style="
                  margin: 0;
                  text-align: right;
                  margin-left: auto;
                  font-size: x-large;
                "
              ></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";

export default {
  props: ["adventureEntity"],
  setup() {
    const date = ref();
    onMounted(() => {
      const startDate = new Date(2021, 12, 5, 14);
      const endDate = new Date(2021, 12, 15, 11);
      date.value = [startDate, endDate];
    });
    return {
      date,
    };
  },
  data: function () {
    return {
      path: "",
    };
  },
  mounted: function () {
    if (window.location.href.includes("/search/adventures")) {
      this.path = "searchadventures";
    } else if (window.location.href.includes("/fishingAdventures")) {
      this.path = "myadventures";
    }
  },
  methods: {
    openAdventure: function () {
      window.location.href = "/fishingAdventure";
    },
  },
};
</script>
<style scoped>
.img-fluid {
  height: 100%;
}
h5 {
  color: white;
}
p {
  color: gray;
}
@media (min-width: 768px) {
  .col-md-4 {
    flex: 0 0 auto;
    width: 25%;
  }
}
@media (min-width: 768px) {
  .col-md-8 {
    flex: 0 0 auto;
    width: 74.666667%;
  }
}
div {
  text-align: left;
}

.row {
  align-items: center;
}
.card :hover {
  cursor: pointer;
  box-shadow: 2px 2px 15px 2px rgba(187, 178, 178, 0.664);
  transition: 0.5s;
}

.advertiserTitle {
  background-color: rgba(128, 128, 128, 0.226);
  color: gray;
  padding: 2px;
  border-radius: 5px;
  margin-left: 3%;
}
.advertiserTitle:hover {
  box-shadow: 3px 5px 0px 0px rgb(22 20 20 / 66%) !important;
  transition: 0.5s;
}
.top-right-corner {
  margin: 0;
  text-align: right;
  margin-left: auto;
  color: #832626;
}
.top-right-corner i:hover {
  box-shadow: 3px 5px 0px 0px rgb(22 20 20 / 66%) !important;
  transition: 0.5s;
}
</style>
