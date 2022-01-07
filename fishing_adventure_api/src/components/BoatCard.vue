<template>
  <div>
    <div class="card mb-3 bg-dark mt-3" style="width: 65%; margin: auto">
      <div class="row g-0" v-on:click="openBoat">
        <div class="col-md-4 shadow-none">
          <img
            style="width: 100%; height: 225px; object-fit: cover"
            :src="require('@/assets/' + boatEntity.imagePath)"
            class="img-fluid rounded-start shadow-none"
          />
        </div>

        <div class="col-md-8 shadow-none" name="main-col">
          <div class="card-body shadow-none">
            <div class="card-text shadow-none" style="display: flex">
              <h5 class="card-title shadow-none">{{ boatEntity.name }}</h5>
              <p class="advertiserTitle shadow-none">
                @{{ boatEntity.boatOwner.name
                }}{{ boatEntity.boatOwner.surname }}
              </p>
              <p
                v-if="path == 'myboats'"
                class="top-right-corner shadow-none"
                v-on:click="preventPropagation"
              >
                <i
                  class="fas fa-edit fa-lg shadow-none me-3"
                  style="color: #293c4e"
                  data-bs-toggle="modal"
                  :data-bs-target="'#boatEntity' + boatEntity.id"
                ></i>
                <i
                  class="fas fa-edit fa-lg shadow-none me-3"
                  style="color: #293c4e"
                  v-if="boatEntity.hasAppointments"
                  v-on:click="showAlert"
                ></i>
                <i
                  class="fas fa-minus-square fa-lg shadow-none"
                  v-on:click="deleteBoat"
                ></i>
              </p>
            </div>
            <div class="card-text shadow-none" style="display: flex">
              <div class="shadow-none">
                <p class="card-text text-left shadow-none mb-1">
                  {{ boatEntity.description }}
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
                <i class="fas fa-star shadow-none"> {{ boatEntity.rating }}</i>
              </p>
            </div>
            <div class="card-text fw-bold shadow-none" style="display: flex">
              <p class="shadow-none" style="margin: 0">
                {{ boatEntity.location.address.street }},
                {{ boatEntity.location.address.city }},
                {{ boatEntity.location.address.country }}
              </p>
              <p
                v-if="path != 'myboats'"
                class="shadow-none"
                style="
                  margin: 0;
                  text-align: right;
                  margin-left: auto;
                  font-size: x-large;
                "
              >
                ${{ boatEntity.pricePerDay }}/day
              </p>
              <p
                v-if="path == 'myboats'"
                class="shadow-none"
                style="
                  margin: 0;
                  text-align: right;
                  margin-left: auto;
                  font-size: x-large;
                "
              >
                ${{ boatEntity.pricePerDay }}/day
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <NewBoatModal
    v-if="path == 'myboats'"
    :boat="boatEntity"
    :id="'boatEntity' + boatEntity.id"
  ></NewBoatModal>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import NewBoatModal from "./NewBoatModal/NewBoatModal.vue";

export default {
  components: { NewBoatModal },
  props: ["boatEntity", "info"],
  emits: ["reservation"],
  setup(props) {
    const date = ref();
    onMounted(() => {
      const startDate = new Date(props.boatEntity.availabilityStart);
      const endDate = new Date(props.boatEntity.availabilityEnd);
      startDate.setHours(startDate.getHours() - 1);
      endDate.setHours(endDate.getHours() - 1);
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
    if (window.location.href.includes("/search/boats")) {
      this.path = "searchboats";
    } else if (window.location.href.includes("/boats")) {
      this.path = "myboats";
    }
  },
  methods: {
    showAlert: function () {
      if (this.entity.hasAppointments) {
        this.$toast.show(
          "Cottage can't be deleted because it has existing reservations."
        );
        return;
      }
    },
    openBoat: function () {
      if (this.path == "searchboats") {
        window.location.href =
          "/boat/?id=" +
          this.boatEntity.id +
          "&date=" +
          this.info.date +
          "&persons=" +
          this.info.persons;
      } else {
        window.location.href = "/boat/?id=" + this.boatEntity.id;
      }
    },
    preventPropagation: function (event) {
      event.preventDefault();
      event.stopPropagation();
    },
    deleteBoat: function () {
      if (this.boatEntity.hasAppointments) {
        this.$toast.show(
          "Cottage can't be edited because it has existing reservations."
        );
        return;
      }
      axios
        .get("http://localhost:8080/boat/deleteBoat/" + this.boatEntity.id, {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then(window.location.reload());
    },
    createReservation: function (event) {
      let object = {
        entity: this.entity,
        event: event,
      };
      this.$emit("reservation", object);
      this.preventPropagation(event);
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
