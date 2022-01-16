<template>
  <div>
    <div class="card mb-3 bg-dark mt-3" style="width: 65%; margin: auto">
      <div class="row g-0" v-on:click="openCottage">
        <div class="col-md-4 shadow-none">
          <img
            style="width: 100%; height: 225px; object-fit: cover"
            :src="getImageUrl(entity.imagePath)"
            class="img-fluid rounded-start shadow-none"
          />
        </div>

        <div class="col-md-8 shadow-none" name="main-col">
          <div class="card-body shadow-none">
            <div class="card-text shadow-none" style="display: flex">
              <h5 class="card-title shadow-none">{{ entity.name }}</h5>
              <p class="advertiserTitle shadow-none">
                @{{ entity.vacationHomeOwner.name
                }}{{ entity.vacationHomeOwner.surname }}
              </p>
              <p
                v-if="path == 'mycottages'"
                class="top-right-corner shadow-none"
                v-on:click="preventPropagation"
              >
                <i
                  class="fas fa-edit fa-lg shadow-none me-3"
                  style="color: #293c4e"
                  data-bs-toggle="modal"
                  :data-bs-target="'#entity' + entity.id"
                  v-if="!entity.hasAppointments"
                ></i>
                <i
                  class="fas fa-edit fa-lg shadow-none me-3"
                  style="color: #293c4e"
                  v-if="entity.hasAppointments"
                  v-on:click="showAlert"
                ></i>
                <i
                  class="fas fa-minus-square fa-lg shadow-none"
                  v-on:click="deleteCottage"
                ></i>
              </p>
            </div>
            <div class="card-text shadow-none" style="display: flex">
              <div class="shadow-none">
                <p class="card-text text-left shadow-none mb-1">
                  {{ entity.description }}
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
                <i class="fas fa-star shadow-none"> {{ entity.rating }}</i>
              </p>
            </div>
            <div class="card-text fw-bold shadow-none" style="display: flex">
              <p class="shadow-none" style="margin: 0">
                {{ entity.location.address.street }},
                {{ entity.location.address.city }},
                {{ entity.location.address.country }}
              </p>
              <p
                v-if="path != 'mycottages'"
                class="shadow-none"
                style="
                  margin: 0;
                  text-align: right;
                  margin-left: auto;
                  font-size: x-large;
                "
              >
                ${{ entity.pricePerDay }}/day
              </p>
              <p
                v-if="path == 'mycottages'"
                class="shadow-none"
                style="
                  margin: 0;
                  text-align: right;
                  margin-left: auto;
                  font-size: x-large;
                "
              >
                ${{ entity.pricePerDay }}/day
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <CottageModal
    v-if="path == 'mycottages'"
    :cottage="entity"
    :id="'entity' + entity.id"
  ></CottageModal>
</template>

<script>
import { ref, onMounted } from "vue";
import CottageModal from "@/components/NewEntityModals/CottageModal.vue";
import axios from "axios";

export default {
  components: { CottageModal },
  props: ["entity", "info"],
  emits: ["reservation"],
  setup(props) {
    const date = ref();
    onMounted(() => {
      const startDate = new Date(props.entity.availabilityStart);
      const endDate = new Date(props.entity.availabilityEnd);
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
    if (window.location.href.includes("/search/cottages")) {
      this.path = "searchcottages";
    } else if (window.location.href.includes("/cottages")) {
      this.path = "mycottages";
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
    openCottage: function () {
      if (this.path == "searchcottages") {
        window.location.href =
          "/cottage/?id=" +
          this.entity.id +
          "&date=" +
          this.info.date +
          "&persons=" +
          this.info.persons;
      } else {
        window.location.href = "/cottage/?id=" + this.entity.id;
      }
    },
    preventPropagation: function (event) {
      event.preventDefault();
      event.stopPropagation();
    },
    deleteCottage: function () {
      if (this.entity.hasAppointments) {
        this.$toast.show(
          "Cottage can't be edited because it has existing reservations."
        );
        return;
      }

      axios
        .get(
          "http://localhost:8080/vacationHome/deleteHome/" + this.entity.id,
          {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          }
        )
        .then(window.location.reload());
    },
    getImageUrl: function (imagePath) {
      return require("@/assets/" + imagePath);
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
